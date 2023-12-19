package com.esiea.pootp1.fight.player.team.members;

import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.moves.Move;
import com.esiea.pootp1.fight.player.team.members.moves.MoveSet;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.GenericPokemon;

public class Pokemon {
    private Team team;

    private String name;

    private Type type;

    private int hp, maxHP, attack, defense, speed;

    private MoveSet moveSet;

    public Pokemon(Team team, GenericPokemon family) {
        this.team = team;

        this.name = family.getName();

        this.type = family.getType();

        this.hp      = family.getMinHP     () + (int) (Math.random() * (family.getMaxHP     () - family.getMinHP     ()));
        this.attack  = family.getMinAttack () + (int) (Math.random() * (family.getMaxAttack () - family.getMinAttack ()));
        this.defense = family.getMinDefense() + (int) (Math.random() * (family.getMaxDefense() - family.getMinDefense()));
        this.speed   = family.getMinSpeed  () + (int) (Math.random() * (family.getMaxSpeed  () - family.getMinSpeed  ()));

        this.maxHP = this.hp;

        this.moveSet = new MoveSet(this);
    }

    public boolean isAlive() {
        return this.hp != 0;
    }

    public int getLongestMoveNameLength() {
        int maxLength = 0;

        for (Move move : this.moveSet.getMoves()) {
            int moveNameLength = move.getName().length();

            if (moveNameLength > maxLength) {
                maxLength = moveNameLength;
            }
        }

        return maxLength;
    }

    public int getLongestMoveTypeLength() {
        int maxLength = 0;

        for (Move move : this.moveSet.getMoves()) {
            int moveTypeLength = Type.getTypeDisplayText().get(move.getType()).length();

            if (moveTypeLength > maxLength) {
                maxLength = moveTypeLength;
            }
        }

        return maxLength;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public String toString() {
        String str = this.name + '\n';

        str += this.moveSet.toString();

        return str;
    }
}
