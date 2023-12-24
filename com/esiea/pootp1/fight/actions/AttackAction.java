package com.esiea.pootp1.fight.actions;

import java.util.Random;

import com.esiea.pootp1.fight.battlefield.State;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.fight.player.team.members.moves.Move;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.attributes.types.DirtAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.ElecAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.FireAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.InsectAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.PlantAttributes;
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
        String sRet = "";

        int damage;

        Pokemon defendingPokemon = this.target.getFightingPokemon();

        int attackerAttack = this.attacker.getAttack();
        int targetDefense  = defendingPokemon.getDefense();

        // Attack with hands
        if (move == null) {
            damage = calculateHandsAttackDamages(attackerAttack, targetDefense);
        }
        
        // Attack with a move
        else {
            double avantage = 1.0;

            // Effictive attack
            if (this.attacker.getType().getStrengths().contains(defendingPokemon.getType())) {
                avantage = 2.0;
                sRet = "C'est super efficace !";
            }

            // Ineffective attack
            else if (this.attacker.getType().getWeaknesses().contains(defendingPokemon.getType())) {
                avantage = 0.5;
                sRet = "Ce n'est pas très efficace..";
            }

            damage = calculateMoveAttackDamages(attackerAttack, this.move.getAttack().getPower(), targetDefense, avantage);
        }

        // Flooded terrain capacity
        if (this.attacker.getTeam().getPlayer().getController().getFight().getBattlefield().isFlooded() &&
            this.attacker.getType() != Type.WATER &&
            new Random().nextDouble() < ((WaterAttributes) this.attacker.getTeam().getPlayer().getController().getFight().getBattlefield().getStater().getTypeAttributes()).getFall()) {
        
            this.attacker.harm(damage / 4);

            sRet = "Mais il glisse sur le terrain inondé !";
        }

        // Paralyzation capacity
        else if (attacker.getStatus() == Status.PARALYZED && new Random().nextDouble() >= 0.25) {
            sRet = "Mais il ne peut pas bouger..";
        }

        // Probability to fail
        else if (new Random().nextDouble() < this.move.getAttack().getFailureProbability()) {
            sRet = "Mais cela échoue..";
        }

        // Attack is gonna be thrown
        else {
            // Harm the target
            defendingPokemon.harm(damage);

            // Check for attacker's type's special capacity
            switch (this.attacker.getType()) {
                case DIRT -> {
                    if (new Random().nextDouble() < ((DirtAttributes) this.attacker.getTypeAttributes()).getHide()) {
                        // TODO Dirt type special capacity
                    }
                }

                case ELEC -> {
                    if (new Random().nextDouble() < ((ElecAttributes) this.attacker.getTypeAttributes()).getParalysis()) {
                        defendingPokemon.setStatus(Status.PARALYZED);

                        String pokemonName = defendingPokemon.getName();
                        String playerName = defendingPokemon.getTeam().getPlayer().getName();
                        String status = Status.getStatusDisplayText().get(defendingPokemon.getStatus());

                        sRet += String.format("\nLe %s de %s a été %s !", pokemonName, playerName, status);
                    }
                }

                case FIRE -> {
                    if (new Random().nextDouble() < ((FireAttributes) this.attacker.getTypeAttributes()).getBurn()) {
                        defendingPokemon.setStatus(Status.BURNED);

                        String pokemonName = defendingPokemon.getName();
                        String playerName = defendingPokemon.getTeam().getPlayer().getName();
                        String status = Status.getStatusDisplayText().get(defendingPokemon.getStatus());

                        sRet += String.format("\nLe %s de %s a été %s !", pokemonName, playerName, status);
                    }
                }

                case INSECT -> {
                    if (new Random().nextDouble() < ((InsectAttributes) this.attacker.getTypeAttributes()).getPoison()) {
                        defendingPokemon.setStatus(Status.POISONED);

                        String pokemonName = defendingPokemon.getName();
                        String playerName = defendingPokemon.getTeam().getPlayer().getName();
                        String status = Status.getStatusDisplayText().get(defendingPokemon.getStatus());

                        sRet += String.format("\nLe %s de %s a été %s !", pokemonName, playerName, status);
                    }
                }

                case PLANT -> {
                    if (new Random().nextDouble() < ((PlantAttributes) this.attacker.getTypeAttributes()).getHeal()) {
                        this.attacker.setStatus(Status.NORMAL);

                        sRet += String.format("\nToutes les alterations d'état du %s de %s ont été soignées", this.attacker.getName(), this.attacker.getTeam().getPlayer().getName());
                    }
                }

                case WATER -> {
                    if (new Random().nextDouble() < ((WaterAttributes) this.attacker.getTypeAttributes()).getFlood()) {
                        this.attacker.getTeam().getPlayer().getController().getFight().getBattlefield().setState(State.FLOODED, this.attacker);

                        sRet += "\nLe terrain est tout inondé !";
                    }
                }
            }
        }

        // Taking into account the use of the attack
        this.move.use();

        return sRet;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "Le %s de %s utilise %s sur le %s de %s !";

        if (bonusInformation != "") format += (bonusInformation.startsWith("\n") ? "" : "\n") + "%s";

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

    public int calculateHandsAttackDamages(int attackerAttack, int targetDefense) {
        double coef = MIN_ATTACK_COEF + new Random().nextDouble() * (MAX_ATTACK_COEF - MIN_ATTACK_COEF);

        return (int) (20 * attackerAttack * coef / targetDefense);
    }

    public int calculateMoveAttackDamages(int attackerAttack, int movePower, int targetDefense, double avantage) {
        double coef = MIN_ATTACK_COEF + new Random().nextDouble() * (MAX_ATTACK_COEF - MIN_ATTACK_COEF);

        return (int) (((11 * attackerAttack * movePower)/(25 * targetDefense) + 2) * avantage * coef);
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
