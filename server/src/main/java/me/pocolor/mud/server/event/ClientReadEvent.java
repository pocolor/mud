package me.pocolor.mud.server.event;

import java.util.UUID;

public class ClientReadEvent extends ClientEvent {
    private final String message;

    public ClientReadEvent(UUID clientUUID, String message) {
        super(clientUUID);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
