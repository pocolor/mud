package me.pocolor.mud.core.game.pokemon.element;

public enum ElementEffectiveness {
    WEAK(1.6f),
    NONE(1.0f),
    RESISTANT(0.6f),
    IMMUNE(0.4f);

    private final float multiplier;

    ElementEffectiveness(float multiplier) {
        this.multiplier = multiplier;
    }

    public float getMultiplier() {
        return multiplier;
    }
}
