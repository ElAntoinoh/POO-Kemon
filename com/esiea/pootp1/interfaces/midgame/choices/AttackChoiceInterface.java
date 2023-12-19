package com.esiea.pootp1.interfaces.midgame.choices;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.esiea.pootp1.fight.Fight;
import com.esiea.pootp1.fight.actions.AttackAction;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.moves.Move;
import com.esiea.pootp1.fight.player.team.members.moves.MoveSet;
import com.esiea.pootp1.interfaces.midgame.FightChoiceInterface;
import com.esiea.pootp1.models.Type;

public class AttackChoiceInterface {
    private final static String REGEX_IS_NUMERIC = "^\\d+$";

    private final static Pattern PATTERN_IS_NUMERIC = Pattern.compile(REGEX_IS_NUMERIC);

    private FightChoiceInterface fightChoiceInterface;

    public AttackChoiceInterface(FightChoiceInterface fightChoiceInterface) {
        this.fightChoiceInterface = fightChoiceInterface;
    }

    public AttackAction askAttackChoice(Player player) {
        printAttackChoice(player);

        Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

        String input;

        while (true) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();

                if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                    int intInput = Integer.parseInt(input);

                    if (intInput >= 1 && intInput <= MoveSet.MAXIMUM_NUMBER_OF_MOVES) break;
                }

                else if (input.equals("r")) break;
            }

            printAttackChoice(player);
        }

        AttackAction action = null;

        switch (input) {
            case "r" -> action = (AttackAction) this.fightChoiceInterface.askGlobalChoice(player);

            default -> {
                Player target = askTargetChoice(player);

                if (target == null) {
                    action = askAttackChoice(player);
                } else {
                    Move move = player.getFightingPokemon().getMoveSet().getMoves().get(Integer.parseInt(input) - 1);

                    action = AttackAction.createAttackAction(player, target, move); 
                }
            }
        }

        return action;
    }

    private void printAttackChoice(Player player) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String str = String.format("Quelle attaque doit lancer %s ?\n\n", player.getFightingPokemon().getName());

        str += "Retour : r\n\n";

        int nameLength = this.fightChoiceInterface.getConsoleInterface().getController().getAttackDex().getLongestAttackName();
        int typeLength = Type.getMaxNameLength();

        int i = 1;

        for (Move move : player.getFightingPokemon().getMoveSet().getMoves()) {
            String name = move.getName();

            String type = Type.getTypeDisplayText().get(move.getType());

            int nbUsesLeft = move.getNbUsesLeft();

            int nbUsesMax = move.getMaxNbUses();

            str += String.format("[%d] %-" + nameLength + "s | %-" + typeLength + "s | %d/%d\n", i++, name, type, nbUsesLeft, nbUsesMax);
        }

        System.out.format("%s\nChoix : ", str);
    }

    private Player askTargetChoice(Player player) {
        printTargetChoice(player);

        Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

        String input;

        while (true) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();

                if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                    int intInput = Integer.parseInt(input);

                    if (intInput >= 1 && intInput <= this.fightChoiceInterface.getConsoleInterface().getController().getFight().getLivingPlayersList().size()) break;
                }

                else if (input.equals("r")) break;
            }

            printTargetChoice(player);
        }

        Player target = null;

        switch (input) {
            case "r" -> target = null;

            default -> target = this.fightChoiceInterface.getConsoleInterface().getController().getFight().getLivingPlayersList().get(Integer.parseInt(input) - 1);
        }

        return target;
    }

    private void printTargetChoice(Player player) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String str = String.format("Qui doit attaquer %s ?\n\n", player.getFightingPokemon().getName());

        str += "Retour : r\n\n";

        Fight fight = this.fightChoiceInterface.getConsoleInterface().getController().getFight();

        int idLength = Integer.toString(fight.getLivingPlayersList().size()).length();
        int nameLength = fight.getLongestPlayerName();

        for (Player p : fight.getLivingPlayersList()) {
            if (!player.equals(p)) {
                String pokemonName = p.getFightingPokemon().getName();
                String pokemonType = Type.getTypeDisplayText().get(p.getFightingPokemon().getType());

                str += String.format("[%" + idLength + "d] %" + nameLength + "s | %s (%s)\n", p.getNum(), p.getName(), pokemonName, pokemonType);
            }
        }

        System.out.format("%s\nChoix : ", str);
    }
}
