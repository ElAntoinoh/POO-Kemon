package com.esiea.pootp1.interfaces;

import java.util.ArrayList;
import java.util.Scanner;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.interfaces.earlygame.BagFillingInterface;
import com.esiea.pootp1.interfaces.earlygame.DataCollectionInterface;
import com.esiea.pootp1.interfaces.earlygame.TeamBuildingInterface;
import com.esiea.pootp1.interfaces.earlygame.WelcomeInterface;
import com.esiea.pootp1.interfaces.endgame.FightResultInterface;
import com.esiea.pootp1.interfaces.midgame.FightChoiceInterface;

public class ConsoleInterface {
    private Controller controller;
    
    private Scanner scanner;
    
    private WelcomeInterface        welcomeInterface;
    private DataCollectionInterface dataCollectionInterface;
    private TeamBuildingInterface   teamBuildingInterface;
    private BagFillingInterface     bagFillingInterface;
    private FightChoiceInterface    fightChoiceInterface;
    private FightResultInterface    fightResultInterface;

    public ConsoleInterface(Controller controller) {
        this.controller = controller;

        this.scanner = new Scanner(System.in);

        this.welcomeInterface        = new WelcomeInterface       (this);
        this.dataCollectionInterface = new DataCollectionInterface(this);
        this.teamBuildingInterface   = new TeamBuildingInterface  (this);
        this.bagFillingInterface     = new BagFillingInterface    (this);
        this.fightChoiceInterface    = new FightChoiceInterface   (this);
        this.fightResultInterface    = new FightResultInterface   (this);
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

    public void printFightResult(Player player) {
        this.fightResultInterface.print(player);
    }
    
    public void askTeam(Player p) {
        this.teamBuildingInterface.askTeam(p);
    }

    public void askBag(Player p) {
        this.bagFillingInterface.askBag(p);
    }

    public Action askActionChoice(Player p) {
        return this.fightChoiceInterface.askGlobalChoice(p);
    }

    public Action askChangePokemonChoice(Player p) {
        return this.fightChoiceInterface.askChangePokemonChoice(p);
    }

    public Controller getController() {
        return this.controller;
    }

    public FightChoiceInterface getFightChoiceInterface() {
        return this.fightChoiceInterface;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}
