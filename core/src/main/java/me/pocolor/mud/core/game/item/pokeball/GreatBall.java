package me.pocolor.mud.core.game.item.pokeball;

public class GreatBall extends BasePokeBall {
    @Override
    public String getName() {
        return "Great Ball";
    }

    @Override
    public String getDescription() {
        return "A good, high-performance Poké Ball that provides a higher catch rate than a standard Poké Ball.";
    }

    @Override
    public float getCatchChanceMultiplier() {
        return 1.5f;
    }
}
