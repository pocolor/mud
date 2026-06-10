package me.pocolor.mud.server;

import me.pocolor.mud.core.event.EventBus;
import me.pocolor.mud.server.event.ClientReadEvent;

import me.pocolor.mud.server.exception.ClientNotConnectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncSocketClient implements Client {
    private static final Logger log = LoggerFactory.getLogger(AsyncSocketClient.class);

    private final UUID clientUUID;
    private boolean connected;

    private final AsyncSocketServer server;
    private final AsynchronousSocketChannel socketChannel;
    private final EventBus eventBus;

    private final Queue<String> writeQueue;
    private final AtomicBoolean writing;

    public AsyncSocketClient(AsyncSocketServer server, AsynchronousSocketChannel socketChannel, EventBus eventBus) {
        clientUUID = UUID.randomUUID();
        connected = false;

        this.server = server;
        this.socketChannel = socketChannel;
        this.eventBus = eventBus;

        writeQueue = new ConcurrentLinkedQueue<>();
        writing = new AtomicBoolean(false);
    }

    @Override
    public UUID getClientUUID() {
        return clientUUID;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public void disconnect() throws IOException {
        server.disconnectClient(clientUUID);
    }

    @Override
    public void sendMessage(UUID to, String message) throws IOException {
        server.clientSendMessage(clientUUID, to, message);
    }

    @Override
    public void broadcastMessage(String message) throws IOException {
        server.clientBroadcastMessage(clientUUID, message);
    }

    private void ensureConnected() {
        if (!connected) {
            log.error("Client not connected. {}", this);
            throw new ClientNotConnectedException();
        }
    }

    public void read() {
        ensureConnected();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ReadContext context = new ReadContext();
        context.buffer = buffer;
        socketChannel.read(buffer, context, new ReadHandler());
    }

    private static class ReadContext {
        ByteBuffer buffer;
    }

    private class ReadHandler implements CompletionHandler<Integer, ReadContext> {
        @Override
        public void completed(Integer bytesRead, ReadContext context) {
            if (bytesRead == -1) {
                return;
            }

            context.buffer.flip();
            String message = new String(context.buffer.array(), context.buffer.position(), bytesRead);
            context.buffer.clear();

            eventBus.publish(new ClientReadEvent(clientUUID, message));
            log.trace("Read completed. {}", AsyncSocketClient.this);

            read();
        }

        @Override
        public void failed(Throwable exc, ReadContext context) {
            log.trace("Read failed. {}", AsyncSocketClient.this, exc);

            read();
        }
    }

    public void write(String message) {
        ensureConnected();

        writeQueue.offer(message);
        writeIfHasMessages();
    }

    private void writeIfHasMessages() {
        ensureConnected();

        if (!writing.compareAndSet(false, true)) {
            return;
        }

        String message = writeQueue.poll();
        if (message == null) {
            writing.set(false);
            return;
        }

        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        WriteContext context = new WriteContext();
        context.buffer = buffer;
        socketChannel.write(buffer, context, new WriteHandler());
    }

    private static class WriteContext {
        ByteBuffer buffer;
    }

    private class WriteHandler implements CompletionHandler<Integer, WriteContext> {
        @Override
        public void completed(Integer bytesWritten, WriteContext context) {
            if (context.buffer.hasRemaining()) {
                socketChannel.write(context.buffer, context, this);
                return;
            }

            writing.set(false);
            log.trace("Write completed. {}", AsyncSocketClient.this);

            writeIfHasMessages();
        }

        @Override
        public void failed(Throwable exc, WriteContext context) {
            writing.set(false);
            log.trace("Write failed. {}", AsyncSocketClient.this, exc);

            writeIfHasMessages();
        }
    }
}
