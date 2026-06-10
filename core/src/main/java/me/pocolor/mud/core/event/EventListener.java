package me.pocolor.mud.core.event;

@FunctionalInterface
public interface EventListener {
    void onEvent(Event event);
}
