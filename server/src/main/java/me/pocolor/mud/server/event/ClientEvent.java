package me.pocolor.mud.server.event;

import me.pocolor.mud.core.event.Event;

import java.util.UUID;

public abstract class ClientEvent extends Event {
    private final UUID clientUUID;

    public ClientEvent(UUID clientUUID) {
        this.clientUUID = clientUUID;
    }

    public UUID getClientUUID() {
        return this.clientUUID;
    }
}
