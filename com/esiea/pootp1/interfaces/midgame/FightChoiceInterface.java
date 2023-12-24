package com.esiea.pootp1.interfaces.midgame;

import java.util.ArrayList;
import java.util.Scanner;

import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.interfaces.ConsoleInterface;
import com.esiea.pootp1.interfaces.midgame.choices.AttackChoiceInterface;
import com.esiea.pootp1.interfaces.midgame.choices.ObjectChoiceInterface;
import com.esiea.pootp1.interfaces.midgame.choices.PokemonChoiceInterface;
import com.esiea.pootp1.models.Type;

public class FightChoiceInterface {
    private ConsoleInterface consoleInterface;

    private AttackChoiceInterface  attackChoiceInterface;
    private ObjectChoiceInterface  objectChoiceInterface;
    private PokemonChoiceInterface pokemonChoiceInterface;

    public FightChoiceInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;

        this.attackChoiceInterface  = new AttackChoiceInterface (this);
        this.objectChoiceInterface  = new ObjectChoiceInterface (this);
        this.pokemonChoiceInterface = new PokemonChoiceInterface(this);
    }

    public Action askGlobalChoice(Player player) {
        printChoice(player);

        Scanner scanner = this.consoleInterface.getScanner();

        int choice;

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 3) break;
            }

            scanner.nextLine();

            printChoice(player);
        }

        Action action = null;

        switch (choice) {
            case 1 -> action = this.attackChoiceInterface .askAttackChoice (player);
            case 2 -> action = this.objectChoiceInterface .askObjectChoice (player);
            case 3 -> action = this.pokemonChoiceInterface.askPokemonChoice(player, true);
        }

        if (action == null) askGlobalChoice(player);

        return action;
    }

    public Action askChangePokemonChoice(Player p) {
        return this.pokemonChoiceInterface.askPokemonChoice(p, false);
    }

    private void printChoice(Player player) {
        this.consoleInterface.clearConsole();

        printTurn(player);
        printPokemonStats(player);

        String title = String.format("Que doit faire %s ?", player.getFightingPokemon().getName());

        String[] options = new String[] {
            "Attaquer",
            "Utiliser un objet",
            "Changer de pokémon"
        };

        String[] formatedOptions = new String[options.length];

        int indexLength = Integer.toString(options.length).length();

        for (int i = 0; i < options.length; i++) {
            formatedOptions[i] = String.format("[%" + indexLength + "d] %s", i + 1, options[i]);
        }

        int longestLineLength = title.length();

        for (String formatedOption : formatedOptions) {
            if (formatedOption.length() > longestLineLength) {
                longestLineLength = formatedOption.length();
            }
        }

        String str = "┌" + "─".repeat(longestLineLength + 2) + "┐\n";

        str += String.format("│ %-" + longestLineLength + "s │\n%s\n", title, "│" + " ".repeat(longestLineLength + 2) + "│");

        for (int i = 0; i < options.length; i++) {
            str += String.format("│ %-" + longestLineLength + "s │\n", formatedOptions[i]);
        }

        str += "└" + "─".repeat(longestLineLength + 2) + "┘\n";

        str += "\nChoix : ";
        
        System.out.print(str);
    }

    private void printTurn(Player player) {
        String line2 = String.format("│ Tour de %s (%d) ! │\n", player.getName(), player.getNum());

        String line1 = "┌" + "─".repeat(line2.length() - 3) + "┐\n";
        String line3 = "└" + "─".repeat(line2.length() - 3) + "┘\n";

        System.out.print(line1 + line2 + line3);
    }

    private void printPokemonStats(Player player) {
        Pokemon pokemon = player.getFightingPokemon();

        String name = pokemon.getName();
    
        String hp    = Integer.toString(pokemon.getHP());
        String maxHP = Integer.toString(pokemon.getMaxHP());

        String type = Type.getTypeDisplayText().get(pokemon.getType());

        String status = Status.getStatsDisplayText().get(pokemon.getStatus());

        String line2 = String.format("│ %" + name.length() + "s │ %s/%s │ %s | %s |\n", name, hp, maxHP, type, status);

        String line1 = "┌" + "─".repeat(name.length() + 2) + "┬" + "─".repeat(hp.length() + maxHP.length() + 1 + 2) + "┬" + "─".repeat(type.length() + 2) + "┬" + "─".repeat(status.length() + 2) + "┐\n";
        String line3 = "└" + "─".repeat(name.length() + 2) + "┴" + "─".repeat(hp.length() + maxHP.length() + 1 + 2) + "┴" + "─".repeat(type.length() + 2) + "┴" + "─".repeat(status.length() + 2) + "┘\n";

        System.out.print(line1 + line2 + line3);
    }

    public void printFightChoice(String title, String indication, ArrayList<String> options) {
        int longestLineLength = title.length();

        if (indication != null && indication.length() > longestLineLength) longestLineLength = indication.length();

        for (String option : options) {
            if (option.length() > longestLineLength) {
                longestLineLength = option.length();
            }
        }

        String str = "┌" + "─".repeat(longestLineLength + 2) + "┐\n";

        str += String.format("│ %-" + longestLineLength + "s │\n%s\n", title, "│" + " ".repeat(longestLineLength + 2) + "│");

        if (indication != null) {
            str += String.format("│ %-" + longestLineLength + "s │\n%s\n", indication, "│" + " ".repeat(longestLineLength + 2) + "│");
        }

        for (String option : options) {
            str += String.format("│ %-" + longestLineLength + "s │\n", option);
        }

        str += "└" + "─".repeat(longestLineLength + 2) + "┘\n";

        str += "\nChoix : ";

        System.out.print(str);
    }

    public void printMandatoryPokemon(Pokemon firstPokemon, Pokemon secondPokemon) {
        this.pokemonChoiceInterface.printMandatoryPokemon(firstPokemon, secondPokemon);
    }

    public void printNewStatus(Pokemon pokemon, Status status) {
        this.consoleInterface.clearConsole();

        System.out.format("Le %s de %s a été %s !", pokemon.getName(), pokemon.getTeam().getPlayer().getName(), Status.getStatsDisplayText().get(status));
    
        this.consoleInterface.waitForAction();
    }

    public void printStatusCuration(Pokemon pokemon, Status status) {
        this.consoleInterface.clearConsole();

        System.out.format("Le %s de %s n'est plus %s !", pokemon.getName(), pokemon.getTeam().getPlayer().getName(), Status.getStatsDisplayText().get(status));
    
        this.consoleInterface.waitForAction();
    }

    public void printNormalBattlefieldState() {
        this.consoleInterface.clearConsole();

        System.out.print("Le terrain est de retour à la normale");
    
        this.consoleInterface.waitForAction();
    }

    public ConsoleInterface getConsoleInterface() {
        return this.consoleInterface;
    }
}
