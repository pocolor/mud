package me.pocolor.mud.core.game.pokemon;

import me.pocolor.mud.core.game.pokemon.generation.kanto.Bulbasaur;
import me.pocolor.mud.core.game.pokemon.generation.kanto.Charmander;
import me.pocolor.mud.core.game.pokemon.generation.kanto.Pikachu;

public final class PokemonRegistry {
    private PokemonRegistry() {}

    // kanto
    public static Bulbasaur bulbasaur() { return new Bulbasaur(); }
    public static Charmander charmander() { return new Charmander(); }
    public static Pikachu pikachu() { return new Pikachu(); }
}
