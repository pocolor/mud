package me.pocolor.mud.core.game.item;

import me.pocolor.mud.core.interfaces.Describable;
import me.pocolor.mud.core.interfaces.Nameable;

import java.io.Serializable;

public abstract class Item implements Serializable, Nameable, Describable {
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        return getName().equals(item.getName());
    }

    @Override
    public final int hashCode() {
        return getName().hashCode();
    }

    @Override
    public final String toString() {
        return getName();
    }
}
