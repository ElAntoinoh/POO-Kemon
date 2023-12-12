package com.esiea.pootp1.fight.player.team;

import java.util.ArrayList;
import java.util.Collections;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.attacks.Attack;
import com.esiea.pootp1.models.pokemons.Pokemon;

public class PokemonBeing {
    private final static int MAX_NB_ATTACKS = 4;

    private Player player;

    private String name;

    private Type type;

    private int hp, attack, defense, speed;

    private Attack[] attacks;

    public PokemonBeing(Player player, Pokemon pokemon) {
        this.player = player;

        this.name = pokemon.getName();

        this.type = pokemon.getType();

        this.hp      = pokemon.getMinHP     () + (int) (Math.random() * (pokemon.getMaxHP     () - pokemon.getMinHP     ()));
        this.attack  = pokemon.getMinAttack () + (int) (Math.random() * (pokemon.getMaxAttack () - pokemon.getMinAttack ()));
        this.defense = pokemon.getMinDefense() + (int) (Math.random() * (pokemon.getMaxDefense() - pokemon.getMinDefense()));
        this.speed   = pokemon.getMinSpeed  () + (int) (Math.random() * (pokemon.getMaxSpeed  () - pokemon.getMinSpeed  ()));

        this.attacks = new Attack[MAX_NB_ATTACKS];

        chooseAttacks();
    }

    private void chooseAttacks() {
        ArrayList<Attack> learnableAttacks = new ArrayList<>();

        learnableAttacks.addAll(this.player.geController().getAttackDex().getAttacksFromType(this.type));
        learnableAttacks.addAll(this.player.geController().getAttackDex().getAttacksFromType(Type.NORMAL));

        Collections.shuffle(learnableAttacks);

        for (int i = 0; i < MAX_NB_ATTACKS; i++) {
            this.attacks[i] = learnableAttacks.get(i);
        }
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

    public Attack[] getAttacks() {
        return this.attacks;
    }
}
