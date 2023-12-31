package com.esiea.pootp1.models.pokemons;

import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.attributes.Attributes;

public class GenericPokemon {
    private String name;

    private Type type;

    private Attributes typeAttributes;

    private int minHP,      maxHP;
    private int minAttack,  maxAttack;
    private int minDefense, maxDefense;
    private int minSpeed,   maxSpeed;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTypeAttributes(Attributes typeAttributes) {
        this.typeAttributes = typeAttributes;
    }
    
    public void setHP(int minHP, int maxHP) {
        this.minHP = minHP;
        this.maxHP = maxHP;
    }

    public void setAttack(int minAttack, int maxAttack) {
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
    }

    public void setDefense(int minDefense, int maxDefense) {
        this.minDefense = minDefense;
        this.maxDefense = maxDefense;
    }

    public void setSpeed(int minSpeed, int maxSpeed) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public Attributes getTypeAttributes() {
        return this.typeAttributes;
    }

    public int getMinHP() {
        return this.minHP;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public int getMinAttack() {
        return this.minAttack;
    }

    public int getMaxAttack() {
        return this.maxAttack;
    }

    public int getMinDefense() {
        return this.minDefense;
    }

    public int getMaxDefense() {
        return this.maxDefense;
    }

    public int getMinSpeed() {
        return this.minSpeed;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public String toString() {
        return this.name;
    }
}
