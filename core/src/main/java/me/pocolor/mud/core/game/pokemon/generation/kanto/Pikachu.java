package me.pocolor.mud.core.game.pokemon.generation.kanto;

import me.pocolor.mud.core.game.pokemon.Pokemon;
import me.pocolor.mud.core.game.pokemon.attack.fast.GenericAttack;
import me.pocolor.mud.core.game.pokemon.element.ElementType;

public class Pikachu extends Pokemon {
    public Pikachu() {
        super(new GenericAttack(ElementType.ELECTRIC, 4));
    }

    @Override
    public ElementType getType() {
        return ElementType.ELECTRIC;
    }

    @Override
    public int getMaxHp() {
        return 80;
    }

    @Override
    public String getName() {
        return "Pikachu";
    }
}
