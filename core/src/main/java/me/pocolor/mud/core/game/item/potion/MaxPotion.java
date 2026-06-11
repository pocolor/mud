package me.pocolor.mud.core.game.item.potion;

public class MaxPotion extends BasePotion {
    @Override
    public String getName() {
        return "Max Potion";
    }

    @Override
    public String getDescription() {
        return "A spray-type medicine for treating wounds. It will completely restore the max HP of a single Pokémon.";
    }

    @Override
    public boolean restoresMaxHp() {
        return true;
    }
}
