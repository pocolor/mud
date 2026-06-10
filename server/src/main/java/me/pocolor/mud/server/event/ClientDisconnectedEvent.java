package me.pocolor.mud.server.event;

import java.util.UUID;

public class ClientDisconnectedEvent extends ClientEvent {
    public ClientDisconnectedEvent(UUID clientUUID) {
        super(clientUUID);
    }
}
