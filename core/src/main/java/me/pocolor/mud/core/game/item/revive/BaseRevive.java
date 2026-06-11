package me.pocolor.mud.core.game.item.revive;

import me.pocolor.mud.core.game.item.Item;

public abstract class BaseRevive extends Item {
    public float getHpRestoreMultiplier() {
        return 0.0f;
    }
}
