package com.esiea.pootp1.interfaces.midgame.choices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        AttackAction action = null;

        if (player.getFightingPokemon().getMoveSet().getMoves().stream().mapToInt(Move::getNbUsesLeft).sum() != 0) {
            printAttackChoice(player);

            Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

            String input;

            while (true) {
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();

                    if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                        int intInput = Integer.parseInt(input);

                        if (intInput >= 1 && intInput <= MoveSet.MAXIMUM_NUMBER_OF_MOVES) {
                            Move move = player.getFightingPokemon().getMoveSet().getMoves().get(intInput - 1);

                            if (move.canBeUsed()) break;
                        }
                    }

                    else if (input.equals("r")) break;
                }

                printAttackChoice(player);
            }

            switch (input) {
                case "r" -> action = null;

                default -> {
                    Player target = askTargetChoice(player);

                    if (target == null) {
                        action = askAttackChoice(player);
                    } else {
                        Move move = player.getFightingPokemon().getMoveSet().getMoves().get(Integer.parseInt(input) - 1);

                        action = AttackAction.createAttackAction(player.getFightingPokemon(), target, move); 
                    }
                }
            }
        }

        return action;
    }

    private void printAttackChoice(Player player) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String title = String.format("Quelle attaque doit lancer %s ?", player.getFightingPokemon().getName());

        String indication = "Retour : r";

        int nameLength = player.getFightingPokemon().getLongestMoveNameLength();
        int typeLength = player.getFightingPokemon().getLongestMoveTypeLength();

        ArrayList<String> options = new ArrayList<>();

        for (Move move : player.getFightingPokemon().getMoveSet().getMoves()) {
            String type = Type.getTypeDisplayText().get(move.getType());

            int index = player.getFightingPokemon().getMoveSet().getMoves().indexOf(move) + 1;

            options.add(String.format("[%d] %-" + nameLength + "s | %-" + typeLength + "s | %d/%d", index, move.getName(), type, move.getNbUsesLeft(), move.getMaxNbUses()));
        }

        this.fightChoiceInterface.printFightChoice(title, indication, options);
    }

    private Player askTargetChoice(Player player) {
        Player target = null;

        ArrayList<Player> livingPlayers = this.fightChoiceInterface.getConsoleInterface().getController().getFight().getLivingPlayersList();

        if (player.getController().getFight().getLivingPlayersList().size() == 2) {
            target = livingPlayers.get(Math.abs(1 - livingPlayers.indexOf(player)));
        }

        else {
            printTargetChoice(player);

            Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

            List<Player> players = livingPlayers;

            List<Integer> playersNumbers = players.stream()
                .filter(p -> p.getNum() != player.getNum())
                .map(Player::getNum)
                .collect(Collectors.toList());

            String input;

            while (true) {
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();

                    if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                        int intInput = Integer.parseInt(input);

                        if (playersNumbers.contains(intInput)) break;
                    }

                    else if (input.equals("r")) break;
                }

                printTargetChoice(player);
            }

            switch (input) {
                case "r" -> target = null;

                default -> target = livingPlayers.get(Integer.parseInt(input) - 1);
            }
        }
        
        return target;   
    }

    private void printTargetChoice(Player player) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String title = String.format("Qui doit attaquer %s ?", player.getFightingPokemon().getName());

        String indication = "Retour : r";
        
        Fight fight = this.fightChoiceInterface.getConsoleInterface().getController().getFight();

        int idLength = Integer.toString(fight.getLivingPlayersList().size()).length();
        int nameLength = fight.getLongestPlayerName(player);

        ArrayList<String> options = new ArrayList<>();

        for (Player p : fight.getLivingPlayersList()) {
            if (!player.equals(p)) {
                String pokemonName = p.getFightingPokemon().getName();
                String pokemonType = Type.getTypeDisplayText().get(p.getFightingPokemon().getType());

                options.add(String.format("[%" + idLength + "d] %-" + nameLength + "s | %s (%s)", p.getNum(), p.getName(), pokemonName, pokemonType));
            }
        }

        this.fightChoiceInterface.printFightChoice(title, indication, options);
    }
}
