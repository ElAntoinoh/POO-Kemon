package com.esiea.pootp1.fight.player.team.members;

import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.moves.MoveSet;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.GenericPokemon;

public class Pokemon {
    private Team team;

    private String name;

    private Type type;

    private int hp, attack, defense, speed;

    private MoveSet moveSet;

    public Pokemon(Team team, GenericPokemon family) {
        this.team = team;

        this.name = family.getName();

        this.type = family.getType();

        this.hp      = family.getMinHP     () + (int) (Math.random() * (family.getMaxHP     () - family.getMinHP     ()));
        this.attack  = family.getMinAttack () + (int) (Math.random() * (family.getMaxAttack () - family.getMinAttack ()));
        this.defense = family.getMinDefense() + (int) (Math.random() * (family.getMaxDefense() - family.getMinDefense()));
        this.speed   = family.getMinSpeed  () + (int) (Math.random() * (family.getMaxSpeed  () - family.getMinSpeed  ()));

        this.moveSet = new MoveSet(this);
    }

    public Team getTeam() {
        return this.team;
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

    public MoveSet getMoveSet() {
        return this.moveSet;
    }

    public String toString() {
        String str = this.name + '\n';

        str += this.moveSet.toString();

        return str;
    }
}
