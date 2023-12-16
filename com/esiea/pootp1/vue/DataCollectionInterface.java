package com.esiea.pootp1.vue;

import java.util.ArrayList;
import java.util.Scanner;

public class DataCollectionInterface {
    private ConsoleInterface consoleInterface;

    public DataCollectionInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public ArrayList<String> askNames() {
        System.out.print("Combien de personnes souhaitent jouer ? ");
        
        Scanner scanner = this.consoleInterface.getScanner();

        int nbPlayers;

        while (true) {
            if (scanner.hasNextInt()) {
                nbPlayers = scanner.nextInt();

                if (nbPlayers > 1) break;

                System.out.print("Combien de personnes souhaitent jouer ? (Valeur entière supérieure à 1 attendue) ");
            } else {
                System.out.print("Combien de personnes souhaitent jouer ? (Valeur entière attendue) ");
                scanner.next();
            }
        }

        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < nbPlayers; i++) {
            this.consoleInterface.clearConsole();

            System.out.print("Nom du joueur n°" + (i + 1) + " : ");

            names.add(scanner.next());
        }

        return names;
    }
}
