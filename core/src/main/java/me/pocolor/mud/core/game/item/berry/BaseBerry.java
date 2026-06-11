package me.pocolor.mud.core.game.item.berry;

import me.pocolor.mud.core.game.item.Item;

public abstract class BaseBerry extends Item {
    public float getCatchChanceMultiplier() {
        return 1.0f;
    }

    public float getCatchCandyMultiplier() {
        return 1.0f;
    }
}
