package me.pocolor.mud.core.event;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class EventBus {
    private final ConcurrentMap<Class<? extends Event>, Set<EventListener>> listeners;

    public EventBus() {
        listeners = new ConcurrentHashMap<>();
    }

    public void publish(Event event) {
        for (EventListener listener : listeners.getOrDefault(event.getClass(), new CopyOnWriteArraySet<>())) {
            listener.onEvent(event);
        }
    }

    public void subscribe(Class<? extends Event> event, EventListener listener) {
        if (!listeners.containsKey(event)) {
            listeners.put(event, new CopyOnWriteArraySet<>());
        }

        listeners.get(event).add(listener);
    }
}
