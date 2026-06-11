package me.pocolor.mud.core.game.item.potion;

public class Potion extends BasePotion {
    @Override
    public String getName() {
        return "Potion";
    }

    @Override
    public String getDescription() {
        return "A spray-type medicine for treating wounds. It restores the HP of one Pokémon by 20 points.";
    }

    @Override
    public int getHpRestore() {
        return 20;
    }
}
