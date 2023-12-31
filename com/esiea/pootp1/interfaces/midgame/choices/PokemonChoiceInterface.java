package com.esiea.pootp1.interfaces.midgame.choices;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.esiea.pootp1.fight.actions.ChangePokemonAction;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.interfaces.midgame.FightChoiceInterface;
import com.esiea.pootp1.models.Type;

public class PokemonChoiceInterface {
    private final static String REGEX_IS_NUMERIC = "^\\d+$";

    private final static Pattern PATTERN_IS_NUMERIC = Pattern.compile(REGEX_IS_NUMERIC);

    private FightChoiceInterface fightChoiceInterface;

    public PokemonChoiceInterface(FightChoiceInterface fightChoiceInterface) {
        this.fightChoiceInterface = fightChoiceInterface;
    }

    public ChangePokemonAction askPokemonChoice(Player player, boolean isChoiceOptional) {
        printPokemonChoice(player, isChoiceOptional);

        Scanner scanner = this.fightChoiceInterface.getConsoleInterface().getScanner();

        String input;

        while (true) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();

                if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                    int intInput = Integer.parseInt(input);

                    if (intInput >= 1 && intInput <= Team.MAXIMUM_NUMBER_OF_MEMBERS - 1) break;
                }

                else if (isChoiceOptional && input.equals("r")) break;
            }

            printPokemonChoice(player, isChoiceOptional);
        }

        ChangePokemonAction action = null;

        switch (input) {
            case "r" -> action = null;

            default -> {
                Pokemon firstPokemon = player.getFightingPokemon();
                Pokemon secondPokemon = null;

                int nbNotFightingPokemons = 0;

                for (Pokemon pokemon : player.getAlivePokemons()) {
                    if (!pokemon.equals(firstPokemon)) nbNotFightingPokemons++;

                    if (nbNotFightingPokemons == Integer.parseInt(input)) secondPokemon = pokemon;
                }
                
                action = ChangePokemonAction.createChangePokemonAction(player, firstPokemon, secondPokemon);
            }
        }

        return action;
    }

    private void printPokemonChoice(Player player, boolean isChoiceOptional) {
        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        String title = isChoiceOptional ?
            String.format("Avez qui %s doit échanger sa place ?", player.getFightingPokemon().getName()) :
            String.format("Le %s de %s est KO, qui doit prendre sa place ?", player.getFightingPokemon().getName(), player.getName());
        
        String indication = isChoiceOptional ? "Retour : r" : null;

        int nameLength = player.getTeam().getLongestMemberNameLength(player.getFightingPokemon());

        ArrayList<String> options = new ArrayList<>();

        int i = 1;

        for (Pokemon pokemon : player.getTeam().getAlivePokemons()) {
            if (!pokemon.equals(player.getFightingPokemon())) {
                String name = pokemon.getName();
                String type = Type.getTypeDisplayText().get(pokemon.getType());

                options.add(String.format("[%d] %-" + nameLength + "s | %s", i++, name, type));
            }
        }

        this.fightChoiceInterface.printFightChoice(title, indication, options);
    }

    public void printMandatoryPokemon(Pokemon firstPokemon, Pokemon secondPokemon) {
        String line1 = String.format("Le %s de %s est K.O. !", firstPokemon.getName(), firstPokemon.getTeam().getPlayer().getName());
        String line2 = String.format("%s est envoyé au combat pour le remplacer !", secondPokemon.getName());

        int longestLineLength = line1.length() > line2.length() ? line1.length() : line2.length();

        String str = "┌" + "─".repeat(longestLineLength + 2) + "┐\n";

        str += String.format("│ %-" + longestLineLength + "s │\n", line1);
        str += String.format("│ %-" + longestLineLength + "s │\n", line2);

        str += "└" + "─".repeat(longestLineLength + 2) + "┘\n";

        this.fightChoiceInterface.getConsoleInterface().clearConsole();

        System.out.print(str);

        this.fightChoiceInterface.getConsoleInterface().waitForAction();
    }
}