package me.pocolor.mud.server;

import me.pocolor.mud.core.event.EventBus;
import me.pocolor.mud.server.event.ClientConnectedEvent;
import me.pocolor.mud.server.event.ClientDisconnectedEvent;
import me.pocolor.mud.server.exception.ServerNotOpenedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AsyncSocketServer implements Server {
    private static final Logger log = LoggerFactory.getLogger(AsyncSocketServer.class);

    private final UUID serverUUID;
    private boolean opened;
    private final EventBus eventBus;

    private AsynchronousServerSocketChannel serverSocketChannel;
    private final InetSocketAddress address;
    private final ConcurrentMap<UUID, AsyncSocketClient> clients;

    public AsyncSocketServer(String host, int port, EventBus eventBus) throws IOException {
        serverUUID = UUID.randomUUID();
        opened = false;

        this.eventBus = eventBus;

        address = new InetSocketAddress(host, port);
        clients = new ConcurrentHashMap<>();
    }

    @Override
    public UUID getServerUUID() {
        return serverUUID;
    }

    @Override
    public boolean isOpened() {
        return opened;
    }

    @Override
    public void open() throws IOException {
        if (opened) {
            log.error("Server already opened. {}", this);
            throw new IllegalStateException("Server already opened.");
        }

        serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(address);
        opened = true;

        acceptClient();

        log.info("Server opened and accepting connections. {}", this);
    }

    @Override
    public void close() throws IOException {
        ensureOpened();

        for (AsyncSocketClient c : clients.values()) {
            disconnectClient(c.getClientUUID());
        }

        serverSocketChannel.close();
        opened = false;

        log.info("Server closed. {}", this);
    }

    @Override
    public void disconnectClient(UUID clientID) throws IOException {
        onClientDisconnected(clientID);
    }

    @Override
    public void clientSendMessage(UUID from, UUID to, String message) throws IOException {
        clientWrite(from, to, message);
    }

    @Override
    public void clientBroadcastMessage(UUID from, String message) throws IOException {
        for (UUID clientUUID : clients.keySet()) {
            clientWrite(from, clientUUID, message);
        }
    }

    @Override
    public void serverSendMessage(UUID to, String message) throws IOException {
        clientWrite(serverUUID, to, message);
    }

    @Override
    public void serverBroadcastMessage(String message) throws IOException {
        for (UUID clientUUID : clients.keySet()) {
            clientWrite(serverUUID, clientUUID, message);
        }
    }

    private void ensureOpened() {
        if (!opened) {
            log.error("Server not opened. {}", this);
            throw new ServerNotOpenedException();
        }
    }

    private void clientWrite(UUID from, UUID to, String message) {
        ensureOpened();

        if (!from.equals(serverUUID) && !clients.containsKey(from)) {
            log.error("Unknown sender UUID: {}", from);
            return;
        }

        if (!clients.containsKey(to)) {
            log.error("Unknown receiver UUID: {}", to);
            return;
        }

        clients.get(to).write(from + message + "\r\n");
    }

    private void acceptClient() {
        ensureOpened();

        serverSocketChannel.accept(null, new AcceptHandler());
    }

    private void onClientConnected(AsynchronousSocketChannel socketChannel) {
        ensureOpened();

        AsyncSocketClient client = new AsyncSocketClient(this, socketChannel, eventBus);
        clients.put(client.getClientUUID(), client);
        client.setConnected(true);
        client.read();

        eventBus.publish(new ClientConnectedEvent(client.getClientUUID()));
        log.info("Client connected. {}", client);
    }

    private void onClientDisconnected(UUID clientUUID) {
        ensureOpened();

        AsyncSocketClient client = clients.remove(clientUUID);
        client.setConnected(false);

        eventBus.publish(new ClientDisconnectedEvent(client.getClientUUID()));
        log.info("Client disconnected. {}", client);
    }

    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {
        @Override
        public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
            log.debug("Accept completed. {}", AsyncSocketServer.this);
            onClientConnected(socketChannel);
            acceptClient();
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            log.debug("Accept failed. {}", AsyncSocketServer.this, exc);
            acceptClient();
        }
    }
}
