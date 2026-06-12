package me.pocolor.mud.core.game.pokemon.generation.kanto;

import me.pocolor.mud.core.game.pokemon.Pokemon;
import me.pocolor.mud.core.game.pokemon.attack.fast.GenericAttack;
import me.pocolor.mud.core.game.pokemon.element.ElementType;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super(new GenericAttack(ElementType.GRASS, 6));
    }

    @Override
    public ElementType getType() {
        return ElementType.GRASS;
    }

    @Override
    public int getMaxHp() {
        return 100;
    }

    @Override
    public String getName() {
        return "Bulbasaur";
    }
}
