package me.pocolor.mud.core.game.item;

import me.pocolor.mud.core.game.item.berry.PinapBerry;
import me.pocolor.mud.core.game.item.berry.RazzBerry;
import me.pocolor.mud.core.game.item.pokeball.GreatBall;
import me.pocolor.mud.core.game.item.pokeball.PokeBall;
import me.pocolor.mud.core.game.item.pokeball.UltraBall;
import me.pocolor.mud.core.game.item.potion.HyperPotion;
import me.pocolor.mud.core.game.item.potion.MaxPotion;
import me.pocolor.mud.core.game.item.potion.Potion;
import me.pocolor.mud.core.game.item.potion.SuperPotion;
import me.pocolor.mud.core.game.item.revive.MaxRevive;
import me.pocolor.mud.core.game.item.revive.Revive;

public final class ItemRegistry {
    private ItemRegistry() {}

    // berries
    public static final RazzBerry razzBerry = new RazzBerry();
    public static final PinapBerry pinapBerry = new PinapBerry();

    // pokeballs
    public static final PokeBall pokeBall = new PokeBall();
    public static final GreatBall greatBall = new GreatBall();
    public static final UltraBall ultraBall = new UltraBall();

    // potions
    public static final Potion potion = new Potion();
    public static final SuperPotion superPotion = new SuperPotion();
    public static final HyperPotion hyperPotion = new HyperPotion();
    public static final MaxPotion maxPotion = new MaxPotion();

    // revives
    public static final Revive revive = new Revive();
    public static final MaxRevive maxRevive = new MaxRevive();
}
