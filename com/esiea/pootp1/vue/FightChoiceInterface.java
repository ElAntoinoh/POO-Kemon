package com.esiea.pootp1.vue;

import java.util.Scanner;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.models.Type;

public class FightChoiceInterface {
    private ConsoleInterface consoleInterface;

    public FightChoiceInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void askChoice(Player player) {
        printChoice(player);

        Scanner scanner = this.consoleInterface.getScanner();

        int choice;

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2 || choice == 3) break;
            }

            scanner.nextLine();

            printChoice(player);
        }

        switch (choice) {
            case 1 -> askAttack       (player);
            case 2 -> askObject       (player);
            case 3 -> askPokemonChange(player);
        }
    }

    private void printChoice(Player player) {
        this.consoleInterface.clearConsole();

        printTurn(player);

        String str = String.format("Que doit faire %s ?\n\n", player.getFightingPokemon().getName());

        str += "[1] Attaquer\n";
        str += "[2] Utiliser un objet\n";
        str += "[3] Échanger sa place\n";

        str += "\nChoix : ";
        
        System.out.print(str);
    }

    private void printTurn(Player player) {
        System.out.format("Tour de %s (%d) !\n", player.getName(), player.getNum());
    }

    private void askAttack(Player player) {

    }

    private void askObject(Player player) {

    }

    private void askPokemonChange(Player player) {
        printPokemonChange(player);

        Scanner scanner = this.consoleInterface.getScanner();

        String input;

        while (true) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();

                if (input.equals("r") || input.equals("1") || input.equals("2")) break;
            }

            printPokemonChange(player);
        }

        switch (input) {
            case "r" -> askChoice(player);
            case "1" -> player.changeFightingPokemon(1);
            case "2" -> player.changeFightingPokemon(2);
        }
    }

    private void printPokemonChange(Player player) {
        this.consoleInterface.clearConsole();

        String str = String.format("Qui doit remplacer %s au combat ?\n\n", player.getFightingPokemon().getName());

        str += "Retour : r\n\n";

        int i = 1;
        
        for (Pokemon pokemon : player.getAlivePokemons()) {
            if (!pokemon.equals(player.getFightingPokemon())) {
                str += String.format("[%d] %s (%s)\n", i++, pokemon.getName(), Type.getTypeDisplayText().get(pokemon.getType()));
            }
        }

        System.out.format("%s\nChoix : ", str);
    }
}
