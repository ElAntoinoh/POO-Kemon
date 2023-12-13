package com.esiea.pootp1.vue;

import java.util.ArrayList;
import java.util.Scanner;

public class DataCollectionInterface {
    private ConsoleInterface consoleInterface;

    public DataCollectionInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public ArrayList<String> askNames() {
        Scanner scanner = this.consoleInterface.getScanner();

        System.out.print("Nombre de joueurs : ");

        while (!scanner.hasNextInt()) {
            System.out.println("Cette valeur doit être un entier.");
            System.out.print("Nombre de joueurs : ");
            scanner.next();
        }

        int nbPlayers = scanner.nextInt();

        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < nbPlayers; i++) {
            System.out.print("Nom du joueur n°" + (i + 1) + " : ");
            names.add(scanner.next());
        }

        return names;
    }
}
