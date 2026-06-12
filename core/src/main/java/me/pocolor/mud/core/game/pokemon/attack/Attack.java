package me.pocolor.mud.core.game.pokemon.attack;

import me.pocolor.mud.core.game.pokemon.element.ElementType;
import me.pocolor.mud.core.interfaces.Nameable;

import java.io.Serializable;

public abstract class Attack implements Serializable, Nameable {
    public abstract ElementType getType();
    public abstract int getDmg();
}
