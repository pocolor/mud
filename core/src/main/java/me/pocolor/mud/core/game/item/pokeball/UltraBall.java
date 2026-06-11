package me.pocolor.mud.core.game.item.pokeball;

public class UltraBall extends BasePokeBall {
    @Override
    public String getName() {
        return "Ultra Ball";
    }

    @Override
    public String getDescription() {
        return "An ultra-high performance Poké Ball that provides a higher Pokémon catch rate than a Great Ball.";
    }

    @Override
    public float getCatchChanceMultiplier() {
        return 2.0f;
    }
}
