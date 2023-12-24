package com.esiea.pootp1.fight.actions;

import java.util.Random;

import com.esiea.pootp1.fight.battlefield.State;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.fight.player.team.members.moves.Move;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.attributes.types.ElecAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.WaterAttributes;

public class AttackAction extends Action {
    private static double MIN_ATTACK_COEF = 0.85d;
    private static double MAX_ATTACK_COEF = 1d;

    private Pokemon attacker;
    private Player target;

    private Move move;

    private AttackAction(Pokemon attacker, Player target, Move move) {
        this.attacker = attacker;
        this.target = target;
        this.move = move;
    }

    public static AttackAction createAttackAction(Pokemon attacker, Player target, Move move) {
        assert attacker.getMoveSet().getMoves().contains(move) || move == null;

        return new AttackAction(attacker, target, move);
    }

    @Override
    public String activate() {
        String sRet = null;

        int damage;

        Pokemon defendingPokemon = this.target.getFightingPokemon();

        int attackerAttack = this.attacker.getAttack();
        int targetDefense  = defendingPokemon.getDefense();

        double coef = MIN_ATTACK_COEF + new Random().nextDouble() * (MAX_ATTACK_COEF - MIN_ATTACK_COEF);

        if (move == null) {
            damage = (int) (20 * attackerAttack * coef / targetDefense);
        } else {
            int movePower = this.move.getAttack().getPower();

            double avantage = 1.0;

            if (this.attacker.getType().getStrengths().contains(defendingPokemon.getType())) {
                avantage = 2.0;
                sRet = "C'est super efficace !";
            }

            if (this.attacker.getType().getWeaknesses().contains(defendingPokemon.getType())) {
                avantage = 0.5;
                sRet = "Ce n'est pas très efficace..";
            }

            damage = (int) (((11 * attackerAttack * movePower)/(25 * targetDefense) + 2) * avantage * coef);
        }

        if (this.attacker.getTeam().getPlayer().getController().getFight().getBattlefield().isFlooded() &&
            this.attacker.getType() != Type.WATER &&
            new Random().nextDouble() < ((WaterAttributes) this.attacker.getTeam().getPlayer().getController().getFight().getBattlefield().getStater().getTypeAttributes()).getFall()) {
        
            this.attacker.harm(damage / 4);
            
            sRet = "Mais il glisse sur le terrain inondé !";
        }

        else if (attacker.getStatus() == Status.PARALYZED && new Random().nextDouble() >= 0.25) {
            sRet = "Mais il ne peut pas bouger..";
        }

        else if (new Random().nextDouble() < this.move.getAttack().getFailureProbability()) {
            sRet = "Mais cela échoue..";
        }

        else {
            defendingPokemon.harm(damage);

            switch (this.attacker.getType()) {
                case DIRT -> {
                    
                }

                case ELEC -> {
                    if (new Random().nextDouble() < ((ElecAttributes) this.attacker.getTypeAttributes()).getParalysis()) {
                        defendingPokemon.setStatus(Status.PARALYZED);

                        String pokemonName = defendingPokemon.getName();
                        String playerName = defendingPokemon.getTeam().getPlayer().getName();
                        String status = Status.getStatsDisplayText().get(defendingPokemon.getStatus());

                        sRet += String.format("\nLe %s de %s a été %s !", pokemonName, playerName, status);
                    }
                }

                case FIRE -> {

                }

                case INSECT -> {

                }

                case NATURE -> {

                }

                case NORMAL -> {

                }

                case PLANT -> {

                }

                case WATER -> {
                    if (new Random().nextDouble() < ((WaterAttributes) this.attacker.getTypeAttributes()).getFlood()) {
                        this.attacker.getTeam().getPlayer().getController().getFight().getBattlefield().setState(State.FLOODED, this.attacker);

                        sRet += "\nLe terrain est tout inondé !";
                    }
                }
            }
        }

        this.move.use();

        return sRet;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "Le %s de %s utilise %s sur le %s de %s !";

        if (bonusInformation != null) format += "\n%s";

        System.out.format(
            format,
            this.attacker.getName(),
            this.attacker.getTeam().getPlayer().getName(),
            this.move.getName(),
            this.target.getFightingPokemon().getName(),
            this.target.getName(),
            bonusInformation
        );

        this.attacker.getTeam().getPlayer().getController().getConsoleInterface().waitForAction();
    }

    public Pokemon getAttacker() {
        return this.attacker;
    }

    public Player getTarget() {
        return this.target;
    }

    public Move getMove() {
        return this.move;
    }
}
