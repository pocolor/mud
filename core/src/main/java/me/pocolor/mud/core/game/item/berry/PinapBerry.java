package me.pocolor.mud.core.game.item.berry;

public class PinapBerry extends BaseBerry {
    @Override
    public String getName() {
        return "Pinap Berry";
    }

    @Override
    public String getDescription() {
        return "Feed this to a Pokémon to make it drop more candy.";
    }

    @Override
    public float getCatchCandyMultiplier() {
        return 2.0f;
    }
}
