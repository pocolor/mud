package me.pocolor.mud.core.game.item.potion;

public class SuperPotion extends BasePotion {
    @Override
    public String getName() {
        return "Super Potion";
    }

    @Override
    public String getDescription() {
        return "A spray-type medicine for treating wounds. It restores the HP of one Pokémon by 50 points.";
    }

    @Override
    public int getHpRestore() {
        return 50;
    }
}
