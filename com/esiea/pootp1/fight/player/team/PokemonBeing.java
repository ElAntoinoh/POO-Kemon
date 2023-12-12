package com.esiea.pootp1.fight.player.team;

import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.Pokemon;

public class PokemonBeing {
    private String name;

    private Type type;

    private int hp, attack, defense, speed;

    public PokemonBeing(Pokemon pokemon) {
        this.name = pokemon.getName();

        this.type = pokemon.getType();

        this.hp      = pokemon.getMinHP     () + (int) (Math.random() * (pokemon.getMaxHP     () - pokemon.getMinHP     ()));
        this.attack  = pokemon.getMinAttack () + (int) (Math.random() * (pokemon.getMaxAttack () - pokemon.getMinAttack ()));
        this.defense = pokemon.getMinDefense() + (int) (Math.random() * (pokemon.getMaxDefense() - pokemon.getMinDefense()));
        this.speed   = pokemon.getMinSpeed  () + (int) (Math.random() * (pokemon.getMaxSpeed  () - pokemon.getMinSpeed  ()));
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public int getHP() {
        return this.hp;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getSpeed() {
        return this.speed;
    }
}
