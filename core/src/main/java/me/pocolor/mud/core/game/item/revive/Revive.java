package me.pocolor.mud.core.game.item.revive;

public class Revive extends BaseRevive {
    @Override
    public String getName() {
        return "Revive";
    }

    @Override
    public String getDescription() {
        return "A medicine that can revive fainted Pokémon. It also fully restores half of a fainted Pokémon's maximum HP.";
    }

    @Override
    public float getHpRestoreMultiplier() {
        return 0.5f;
    }
}
