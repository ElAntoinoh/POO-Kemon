package com.esiea.pootp1.vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.bag.Bag;
import com.esiea.pootp1.fight.player.bag.Item;
import com.esiea.pootp1.models.consumables.Consumable;

public class BagFillingInterface {
    private final static int NUMBER_OF_ITEMS_PER_PAGE = 5;

    private final static String REGEX_IS_NUMERIC = "^\\d+$";
    private final static String REGEX_GO_TO_PAGE = "^P\\d+$";

    private final static Pattern PATTERN_IS_NUMERIC = Pattern.compile(REGEX_IS_NUMERIC);
    private final static Pattern PATTERN_GO_TO_PAGE = Pattern.compile(REGEX_GO_TO_PAGE); 

    private ConsoleInterface consoleInterface;

    public BagFillingInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void askBag(Player player) {
        printTypeOfBagChoice(player);

        Scanner scanner = this.consoleInterface.getScanner();

        int choice;

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2) break;
            }

            scanner.nextLine();

            printTypeOfBagChoice(player);
        }

        switch (choice) {
            case 1 -> player.getBag().setRandomItems();
            case 2 -> player.getBag().setItems(askItems(player.getBag()));
        }
    }

    private void printTypeOfBagChoice(Player player) {
        this.consoleInterface.clearConsole();
        this.consoleInterface.printWelcomePlayer(player);

        String str = "\nRemplis ton sac !\n\n";

        str += "[1] Sac aléatoire\n";
        str += "[2] Sac personalisé\n";

        str += "\nChoix : ";

        System.out.print(str);
    }

    private ArrayList<Item> askItems(Bag bag) {
        ArrayList<Consumable> consumablesList = this.consoleInterface.getController().getGlobalBag().getConsumableList();

        Collections.sort(consumablesList, (consumable1, consumable2) -> consumable1.getName().compareTo(consumable2.getName()));

        ArrayList<Item> members = new ArrayList<>();

        for (int i = 0; i < Bag.MAXIMUM_NUMBER_OF_ITEMS; i++) {
            members.add(askItem(bag, consumablesList));
        }

        return members;
    }

    private Item askItem(Bag bag, ArrayList<Consumable> consumablesList) {
        Scanner scanner = this.consoleInterface.getScanner();

        int numPage = 0;
        int nbPages =(consumablesList.size() - 1) / NUMBER_OF_ITEMS_PER_PAGE + 1;

        String input = null;

        while (scanner.hasNextLine()) {
            printBagChoiceHelp();
            printItemListPage(consumablesList, numPage, nbPages);

            input = scanner.next();

            if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                int intInput = Integer.parseInt(input);

                if (intInput >= 0 && intInput < consumablesList.size() + 1) {
                    if (intInput == 0) {
                        intInput = (int) (Math.random() * (consumablesList.size() - 1)) + 1;
                        input = Integer.toString(intInput);
                    }

                    break;
                }
            }
            
            else if (PATTERN_GO_TO_PAGE.matcher(input).matches()) {
                int indicePage = Integer.parseInt(input.substring(1));

                if (indicePage > 0 && indicePage - 1 < nbPages) numPage = indicePage - 1;
            }
            
            else if (input.equals("p")) numPage -= numPage == 0           ? 0 : 1;
            else if (input.equals("s")) numPage += numPage == nbPages - 1 ? 0 : 1;
        }

        Item item = new Item(bag, consumablesList.get(Integer.parseInt(input) - 1));

        consumablesList.remove(Integer.parseInt(input) - 1);

        return item;
    }

    private void printBagChoiceHelp() {
        this.consoleInterface.clearConsole();

        String str = "Choisissez un objet en entrant son numéro\n\n";

        str += String.format("%-24s : %s\n", "Afficher page n"         , "Pn");
        str += String.format("%-24s : %s\n", "Afficher page précédente", "p" );
        str += String.format("%-24s : %s\n", "Afficher page suivante"  , "s" );
        str += String.format("%-24s : %s\n", "Objet aléatoire"       , "0" );

        System.out.println(str);
    }

    private void printItemListPage(ArrayList<Consumable> consumablesList, int numPage, int nbPages) {
        for (int i = numPage * NUMBER_OF_ITEMS_PER_PAGE; i < (numPage + 1) * NUMBER_OF_ITEMS_PER_PAGE && i < consumablesList.size(); i++) {
            System.out.format("[%" + Integer.toString(consumablesList.size()).length() + "d] %s\n", i + 1, consumablesList.get(i).getName());
        }

        System.out.format("\nPage (%d/%d)\n\n", numPage + 1, nbPages);
    }
}