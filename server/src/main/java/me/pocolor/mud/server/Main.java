package me.pocolor.mud.server;

import me.pocolor.mud.core.event.EventBus;
import me.pocolor.mud.server.event.ClientConnectedEvent;
import me.pocolor.mud.server.event.ClientDisconnectedEvent;
import me.pocolor.mud.server.event.ClientReadEvent;

public class Main {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        eventBus.subscribe(ClientConnectedEvent.class, System.out::println);
        eventBus.subscribe(ClientDisconnectedEvent.class, System.out::println);
        eventBus.subscribe(ClientReadEvent.class, System.out::println);

        try (Server server = new AsyncSocketServer("127.0.0.1", 1234, eventBus)) {
            server.open();

            Thread.sleep(10 * 1000);

            server.serverBroadcastMessage("Hello World!");

            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
