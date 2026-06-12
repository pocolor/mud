package me.pocolor.mud.core.game.pokemon.generation.kanto;

import me.pocolor.mud.core.game.pokemon.Pokemon;
import me.pocolor.mud.core.game.pokemon.attack.fast.GenericAttack;
import me.pocolor.mud.core.game.pokemon.element.ElementType;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super(new GenericAttack(ElementType.WATER, 7));
    }

    @Override
    public ElementType getType() {
        return ElementType.WATER;
    }

    @Override
    public int getMaxHp() {
        return 60;
    }

    @Override
    public String getName() {
        return "Squirtle";
    }
}
