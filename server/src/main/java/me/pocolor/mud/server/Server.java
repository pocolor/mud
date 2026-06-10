package me.pocolor.mud.server;

import java.io.IOException;
import java.util.UUID;

public interface Server extends AutoCloseable {
    UUID getServerUUID();
    boolean isOpened();

    void open() throws IOException;
    @Override
    void close() throws IOException;

    void disconnectClient(UUID clientID) throws IOException;
    void clientSendMessage(UUID from, UUID to, String message) throws IOException;
    void clientBroadcastMessage(UUID from, String message) throws IOException;
    void serverSendMessage(UUID to, String message) throws IOException;
    void serverBroadcastMessage(String message) throws IOException;
}
