package me.pocolor.mud.core.game.item.pokeball;

import me.pocolor.mud.core.game.item.Item;

public abstract class BasePokeBall extends Item {
    public float getCatchChanceMultiplier() {
        return 1.0f;
    }
}
