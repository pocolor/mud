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
    public static RazzBerry razzBerry() { return new RazzBerry(); }
    public static PinapBerry pinapBerry() { return new PinapBerry(); }

    // pokeballs
    public static PokeBall pokeBall() { return new PokeBall(); }
    public static GreatBall greatBall() { return new GreatBall(); }
    public static UltraBall ultraBall() { return new UltraBall(); }

    // potions
    public static Potion potion() { return new Potion(); }
    public static SuperPotion superPotion() { return new SuperPotion(); }
    public static HyperPotion hyperPotion() { return new HyperPotion(); }
    public static MaxPotion maxPotion() { return new MaxPotion(); }

    // revives
    public static Revive revive() { return new Revive(); }
    public static MaxRevive maxRevive() { return new MaxRevive(); }
}
