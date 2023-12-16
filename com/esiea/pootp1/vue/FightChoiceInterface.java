package com.esiea.pootp1.vue;

import java.util.Scanner;

import com.esiea.pootp1.fight.player.Player;

public class FightChoiceInterface {
    private ConsoleInterface consoleInterface;

    public FightChoiceInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void askChoice(Player player) {
        Scanner scanner = this.consoleInterface.getScanner();

        printChoice();

        while (!scanner.hasNextInt()) {
            System.out.println("Cette valeur doit être un entier.");
            printChoice();
            scanner.next();
        }
    }

    private void printChoice() {
        String str = "";

        str += "[1] Attaquer\n";
        str += "[2] Sac\n";
        str += "[3] Pokémon\n";
        
        System.out.println(str);
    }

    // Dans Attaque

}
