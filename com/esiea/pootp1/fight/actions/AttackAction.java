package com.esiea.pootp1.fight.actions;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.moves.Move;

public class AttackAction extends Action {
    private Player attacker, target;

    private Move move;

    private AttackAction(Player attacker, Player target, Move move) {
        this.attacker = attacker;
        this.target = target;
        this.move = move;
    }

    public static AttackAction createAttackAction(Player attacker, Player target, Move move) {
        assert attacker.getFightingPokemon().getMoveSet().getMoves().contains(move);

        return new AttackAction(attacker, target, move);
    }

    @Override
    public void activate() {
        
    }

    @Override
    public void print() {
        String format = "Le %s de %s utilise %s sur le %s de %s !";

        System.out.format(
            format,
            this.attacker.getFightingPokemon().getName(),
            this.attacker.getName(),
            this.move.getName(),
            this.target.getFightingPokemon().getName(),
            this.target.getName()
        );

        this.attacker.getController().getConsoleInterface().getScanner().nextLine();
    }

    public Player getAttacker() {
        return this.attacker;
    }

    public Player getTarget() {
        return this.target;
    }

    public Move getMove() {
        return this.move;
    }

    public String toString() {
        return this.attacker.getName() + " -> " + this.target.getName() + " (" + this.move.getName() + ")";
    }
}
