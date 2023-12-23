package com.esiea.pootp1.fight.player.team.members.moves;

import java.util.ArrayList;
import java.util.Collections;

import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.attacks.Attack;

public class MoveSet {
    public final static int MAXIMUM_NUMBER_OF_MOVES = 4;

    private Pokemon pokemon;

    private ArrayList<Move> moves = new ArrayList<>();

    public MoveSet(Pokemon pokemon) {
        this.pokemon = pokemon;

        fillVoidMoveSet();

        setRandomAttacks();
    }

    private void fillVoidMoveSet() {
        for (int i = 0; i < MAXIMUM_NUMBER_OF_MOVES; i++) this.moves.add(null);
    }

    public void setRandomAttacks() {
        ArrayList<Attack> learnableAttacks = new ArrayList<>();

        learnableAttacks.addAll(this.pokemon.getTeam().getPlayer().getController().getAttackDex().getAttacksFromType(this.pokemon.getType()));
        learnableAttacks.addAll(this.pokemon.getTeam().getPlayer().getController().getAttackDex().getAttacksFromType(Type.NORMAL));

        Collections.shuffle(learnableAttacks);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MOVES; i++) {
            this.moves.set(i, new Move(learnableAttacks.get(i)));
        }
    }

    public int getLongestMoveNameLength() {
        return this.moves.stream()
            .mapToInt(move -> move.getName().length())
            .max()
            .orElse(0);
    }

    public int getLongestMoveTypeLength() {
        return this.moves.stream()
            .mapToInt(move -> Type.getTypeDisplayText().get(move.getType()).length())
            .max().orElse(0);
    }

    public boolean contains(Move move) {
        return this.moves.contains(move);
    }

    public ArrayList<Move> getMoves() {
        return this.moves;
    }
}
