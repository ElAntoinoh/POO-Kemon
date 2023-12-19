package com.esiea.pootp1.interfaces.midgame;

import java.util.Scanner;

import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.interfaces.ConsoleInterface;
import com.esiea.pootp1.interfaces.midgame.choices.AttackChoiceInterface;
import com.esiea.pootp1.interfaces.midgame.choices.ObjectChoiceInterface;
import com.esiea.pootp1.interfaces.midgame.choices.PokemonChoiceInterface;

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
            case 3 -> action = this.pokemonChoiceInterface.askPokemonChoice(player);
        }

        return action;
    }

    private void printTurn(Player player) {
        System.out.format("Tour de %s (%d) !\n", player.getName(), player.getNum());
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

    public ConsoleInterface getConsoleInterface() {
        return this.consoleInterface;
    }
}
