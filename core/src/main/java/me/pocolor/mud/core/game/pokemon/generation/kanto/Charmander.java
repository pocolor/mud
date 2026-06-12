package me.pocolor.mud.core.game.pokemon.generation.kanto;

import me.pocolor.mud.core.game.pokemon.Pokemon;
import me.pocolor.mud.core.game.pokemon.attack.fast.GenericAttack;
import me.pocolor.mud.core.game.pokemon.element.ElementType;

public class Charmander extends Pokemon {
    public Charmander() {
        super(new GenericAttack(ElementType.FIRE, 10));
    }

    @Override
    public ElementType getType() {
        return ElementType.FIRE;
    }

    @Override
    public int getMaxHp() {
        return 50;
    }

    @Override
    public String getName() {
        return "Charmander";
    }
}
