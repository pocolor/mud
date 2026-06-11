package me.pocolor.mud.core.game.item.potion;

import me.pocolor.mud.core.game.item.Item;

public abstract class BasePotion extends Item {
    public int getHpRestore() {
        return 0;
    }
    public boolean restoresMaxHp() {
        return false;
    }
}
