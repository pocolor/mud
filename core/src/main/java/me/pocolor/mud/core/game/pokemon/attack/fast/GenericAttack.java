package me.pocolor.mud.core.game.pokemon.attack.fast;

import me.pocolor.mud.core.game.pokemon.element.ElementType;

public class GenericAttack extends FastAttack {
    private final ElementType type;
    private final int dmg;

    public GenericAttack(ElementType type, int dmg) {
        this.type = type;
        this.dmg = dmg;
    }

    @Override
    public ElementType getType() {
        return type;
    }

    @Override
    public int getDmg() {
        return dmg;
    }
}
