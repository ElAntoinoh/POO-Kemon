package com.esiea.pootp1.fight.actions;

import java.util.Random;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.fight.player.team.members.moves.Move;

public class AttackAction extends Action {
    private static double MIN_ATTACK_COEF = 0.85;
    private static double MAX_ATTACK_COEF = 1.;

    private Player attacker, target;

    private Move move;

    private AttackAction(Player attacker, Player target, Move move) {
        this.attacker = attacker;
        this.target = target;
        this.move = move;
    }

    public static AttackAction createAttackAction(Player attacker, Player target, Move move) {
        assert attacker.getFightingPokemon().getMoveSet().getMoves().contains(move) || move == null;

        return new AttackAction(attacker, target, move);
    }

    @Override
    public String activate() {
        String sRet = null;

        int damage;

        Pokemon attackingPokemon = this.attacker.getFightingPokemon();
        Pokemon defendingPokemon = this.target.getFightingPokemon();

        int attackerAttack = attackingPokemon.getAttack();
        int targetDefense  = defendingPokemon.getDefense();

        double coef = MIN_ATTACK_COEF + new Random().nextDouble() * (MAX_ATTACK_COEF - MIN_ATTACK_COEF);

        if (move == null) {
            damage = (int) (20 * attackerAttack * coef / targetDefense);
        } else {
            int movePower = this.move.getAttack().getPower();

            double avantage = 1.0;

            if (attackingPokemon.getType().getStrengths ().contains(defendingPokemon.getType())) {
                avantage = 2.0;
                sRet = "C'est super efficace !";
            }

            if (attackingPokemon.getType().getWeaknesses().contains(defendingPokemon.getType())) {
                avantage = 0.5;
                sRet = "Ce n'est pas très efficace..";
            }

            damage = (int) (((11 * attackerAttack * movePower)/(25 * targetDefense) + 2) * avantage * coef);
        }

        if (new Random().nextDouble() >= this.move.getAttack().getFailureProbability()) {
            defendingPokemon.harm(damage);

            // TODO Activer capacité spéciale de type
        } else {
            sRet = "Mais cela échoue..";
        }

        this.move.use();

        return sRet;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "Le %s de %s utilise %s sur le %s de %s !";

        if (bonusInformation != null) format += " %s";

        System.out.format(
            format,
            this.attacker.getFightingPokemon().getName(),
            this.attacker.getName(),
            this.move.getName(),
            this.target.getFightingPokemon().getName(),
            this.target.getName(),
            bonusInformation
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
