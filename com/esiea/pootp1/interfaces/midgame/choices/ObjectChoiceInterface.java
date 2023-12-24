package com.esiea.pootp1.interfaces.midgame.choices;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.esiea.pootp1.fight.actions.UseObjectAction;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.bag.Bag;
import com.esiea.pootp1.fight.player.bag.Item;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.interfaces.midgame.FightChoiceInterface;

public class ObjectChoiceInterface {
    private final static String REGEX_IS_NUMERIC = "^\\d+$";

    private final static Pattern PATTERN_IS_NUMERIC = Pattern.compile(REGEX_IS_NUMERIC);

    private FightChoiceInterface fightChoiceInterface;

    public ObjectChoiceInterface(FightChoiceInterface fightChoiceInterface) {
        this.fightChoiceInterface = fightChoiceInterface;
    }

    public UseObjectAction askObjectChoice(Player player) {
        printObjectChoice(player);

        Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

        String input;

        while (true) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();

                if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                    int intInput = Integer.parseInt(input);

                    if (intInput >= 1 && intInput <= Bag.MAXIMUM_NUMBER_OF_ITEMS) break;
                }

                else if (input.equals("r")) break;
            }

            printObjectChoice(player);
        }

        UseObjectAction action = null;

        switch (input) {
            case "r" -> action = null;

            default -> {
                Pokemon target = askTargetChoice(player);

                if (target == null) {
                    action = askObjectChoice(player);
                } else {
                    Item item = player.getBag().getItems().get(Integer.parseInt(input) - 1);

                    action = UseObjectAction.createUseObjectAction(player, target, item); 
                }
            }
        }

        return action;
    }

    private void printObjectChoice(Player player) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String title = "Que voulez-vous utiliser ?";
        
        String indication = "Retour : r";

        int nameLength = player.getBag().getLongestItemName();

        ArrayList<String> options = new ArrayList<>();

        int i = 1;

        for (Item item : player.getBag().getItems()) {
            options.add(String.format("[%d] %-" + nameLength + "s", i++, item.getName()));
        }

        this.fightChoiceInterface.printFightChoice(title, indication, options);
    }

    private Pokemon askTargetChoice(Player player) {
        printTargetChoice(player);

        Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

        String input;

        while (true) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();

                if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                    int intInput = Integer.parseInt(input);

                    if (intInput >= 1 && intInput <= player.getAlivePokemons().size()) break;
                }

                else if (input.equals("r")) break;
            }

            printTargetChoice(player);
        }

        Pokemon target = null;

        switch (input) {
            case "r" -> target = null;

            default -> target = player.getAlivePokemons().get(Integer.parseInt(input) - 1);
        }

        return target;
    }

    private void printTargetChoice(Player player) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String title = "Qui doit recevoir l'objet ?";
        
        String indication = "Retour : r";

        ArrayList<String> options = new ArrayList<>();

        int i = 1;

        for (Pokemon pokemon : player.getAlivePokemons()) {
            options.add(String.format("[%d] %s | %s", i++, pokemon.getName(), Status.getStatsDisplayText().get(pokemon.getStatus())));
        }

        this.fightChoiceInterface.printFightChoice(title, indication, options);
    }
}
