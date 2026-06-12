package me.pocolor.mud.core.game.pokemon;

import me.pocolor.mud.core.game.pokemon.attack.fast.FastAttack;
//import me.pocolor.mud.core.game.pokemon.attack.charged.ChargedAttack;
import me.pocolor.mud.core.game.pokemon.element.ElementType;

import me.pocolor.mud.core.interfaces.Nameable;

import java.io.Serializable;

public abstract class Pokemon implements Serializable, Nameable {
    protected String customName;
    protected int hp;

    protected final FastAttack fastAttack;
//    protected ChargedAttack chargedAttack;

    public Pokemon(FastAttack fastAttack) {
        customName = getName();
        hp = getMaxHp();

        this.fastAttack = fastAttack;
    }

    public abstract ElementType getType();
    public abstract int getMaxHp();

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.clamp(hp, 0, getMaxHp());
    }

    public FastAttack getFastAttack() {
        return this.fastAttack;
    }

//    public void setFastAttack(FastAttack fastAttack) {
//        this.fastAttack = fastAttack;
//    }

//    public ChargedAttack getChargedAttack() {
//        return this.chargedAttack;
//    }
//
//    public void setChargedAttack(ChargedAttack chargedAttack) {
//        this.chargedAttack = chargedAttack;
//    }
}
