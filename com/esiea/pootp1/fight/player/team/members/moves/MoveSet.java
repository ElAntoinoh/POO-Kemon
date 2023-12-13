package com.esiea.pootp1.fight.player.team.members.moves;

import java.util.ArrayList;
import java.util.Collections;

import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.models.attacks.Attack;

public class MoveSet {
    private final static int MAXIMUM_NUMBER_OF_MOVES = 4;

    private Pokemon pokemon;

    private ArrayList<Move> moves = new ArrayList<>();

    public MoveSet(Pokemon pokemon) {
        this.pokemon = pokemon;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MOVES; i++) this.moves.add(null);

        setRandomAttacks();
    }

    public void setRandomAttacks() {
        ArrayList<Attack> learnableAttacks = new ArrayList<>();

        learnableAttacks.addAll(this.pokemon.getTeam().getPlayer().getController().getAttackDex().getAttacksFromType(this.pokemon.getType()));
        learnableAttacks.addAll(this.pokemon.getTeam().getPlayer().getController().getAttackDex().getAttacksFromType(this.pokemon.getType()));

        Collections.shuffle(learnableAttacks);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MOVES; i++) {
            this.moves.set(i, new Move(learnableAttacks.get(i)));
        }
    }

    public ArrayList<Move> getMoves() {
        return this.moves;
    }

    public String toString() {
        String str = "";

        for (Move m : this.moves) str += m.toString() + '\n';

        return str;
    }
}
