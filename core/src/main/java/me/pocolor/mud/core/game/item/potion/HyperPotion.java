package me.pocolor.mud.core.game.item.potion;

public class HyperPotion extends BasePotion {
    @Override
    public String getName() {
        return "Hyper Potion";
    }

    @Override
    public String getDescription() {
        return "A spray-type medicine for treating wounds. It restores the HP of one Pokémon by 200 points.";
    }

    @Override
    public int getHpRestore() {
        return 200;
    }
}
