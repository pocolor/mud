package me.pocolor.mud.server.event;

import java.util.UUID;

public class ClientConnectedEvent extends ClientEvent {
    public ClientConnectedEvent(UUID clientUUID) {
        super(clientUUID);
    }
}
