package com.esiea.pootp1.vue;

import java.util.ArrayList;
import java.util.Scanner;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.Player;

public class ConsoleInterface {
    private Controller controller;
    
    private Scanner scanner;
    
    private WelcomeInterface        welcomeInterface;
    private DataCollectionInterface dataCollectionInterface;
    private TeamBuildingInterface   teamBuildingInterface;
    private BagFillingInterface     bagFillingInterface;
    private FightChoiceInterface    fightChoiceInterface;

    public ConsoleInterface(Controller controller) {
        this.controller = controller;

        this.scanner = new Scanner(System.in);

        this.welcomeInterface        = new WelcomeInterface       (this);
        this.dataCollectionInterface = new DataCollectionInterface(this);
        this.teamBuildingInterface   = new TeamBuildingInterface  (this);
        this.bagFillingInterface     = new BagFillingInterface    (this);
        this.fightChoiceInterface    = new FightChoiceInterface   (this);
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void waitForAction() {
        this.scanner.nextLine();
    }

    public void printWelcomeAnimation() {
        this.welcomeInterface.printWelcomeAnimation();
    }

    public void printWelcomeMessage() {
        this.welcomeInterface.printWelcomeMessage();
    }

    public ArrayList<String> askNames() {
        return this.dataCollectionInterface.askNames();
    }

    public void printWelcomePlayer(Player player) {
        System.out.format("Bienvenue, %s (%d) !\n", player.getName(), player.getNum());
    }
    
    public void askTeam(Player p) {
        this.teamBuildingInterface.askTeam(p);
    }

    public void askBag(Player p) {
        this.bagFillingInterface.askBag(p);
    }

    public void askChoice(Player p) {
        this.fightChoiceInterface.askChoice(p);
    }

    public Controller getController() {
        return this.controller;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}
