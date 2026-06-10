package me.pocolor.mud.server;

import java.io.IOException;
import java.util.UUID;

public interface Client {
    UUID getClientUUID();
    boolean isConnected();

    void disconnect() throws IOException;
    void sendMessage(UUID to, String message) throws IOException;
    void broadcastMessage(String message) throws IOException;
}
