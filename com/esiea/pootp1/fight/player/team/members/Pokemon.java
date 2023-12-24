package com.esiea.pootp1.fight.player.team.members;

import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.moves.MoveSet;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.GenericPokemon;
import com.esiea.pootp1.models.pokemons.attributes.Attributes;

public class Pokemon {
    private GenericPokemon family;

    private Team team;

    private int hp, maxHP, attack, defense, speed;

    private MoveSet moveSet;

    private Status status = Status.NORMAL;

    private int nbTurnsWithStatus = 0;

    public Pokemon(Team team, GenericPokemon family) {
        this.family = family;

        this.team = team;

        this.hp      = family.getMinHP     () + (int) (Math.random() * (family.getMaxHP     () - family.getMinHP     ()));
        this.attack  = family.getMinAttack () + (int) (Math.random() * (family.getMaxAttack () - family.getMinAttack ()));
        this.defense = family.getMinDefense() + (int) (Math.random() * (family.getMaxDefense() - family.getMinDefense()));
        this.speed   = family.getMinSpeed  () + (int) (Math.random() * (family.getMaxSpeed  () - family.getMinSpeed  ()));

        this.maxHP = this.hp;

        this.moveSet = new MoveSet(this);
    }

    public boolean harm(int amount) {
        this.hp -= amount;

        if (this.hp < 0) this.hp = 0;

        return this.hp != 0;
    }

    public void heal(int amount) {
        this.hp += amount;

        if (this.hp > this.maxHP) this.hp = this.maxHP;
    }

    public boolean isAlive() {
        return this.hp != 0;
    }

    public int getLongestMoveNameLength() {
        return this.moveSet.getLongestMoveNameLength();
    }

    public int getLongestMoveTypeLength() {
        return this.moveSet.getLongestMoveTypeLength();
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.nbTurnsWithStatus = 1;
    }

    public Team getTeam() {
        return this.team;
    }

    public String getName() {
        return this.family.getName();
    }

    public Type getType() {
        return this.family.getType();
    }

    public Attributes getTypeAttributes() {
        return this.family.getTypeAttributes();
    }

    public int getHP() {
        return this.hp;
    }
    
    public int getMaxHP() {
        return this.maxHP;
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

    public Status getStatus() {
        return this.status;
    }

    public int getNbTurnsWithStatus() {
        return this.nbTurnsWithStatus;
    }
}
