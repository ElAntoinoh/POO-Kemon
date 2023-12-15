package com.esiea.pootp1.vue;

import java.util.ArrayList;
import java.util.Scanner;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.Player;

public class ConsoleInterface {
    private Controller controller;
    
    private Scanner scanner;
    
    private DataCollectionInterface dataCollectionInterface;
    private TeamBuildingInterface   teamBuildingInterface;
    private BagFillingInterface     bagFillingInterface;

    public ConsoleInterface(Controller controller) {
        this.controller = controller;

        this.scanner = new Scanner(System.in);

        this.dataCollectionInterface = new DataCollectionInterface(this);
        this.teamBuildingInterface   = new TeamBuildingInterface  (this);
        this.bagFillingInterface     = new BagFillingInterface    (this);
    }

    public ArrayList<String> askNames() {
        return this.dataCollectionInterface.askNames();
    }
    
    public void askTeam(Player p) {
        this.teamBuildingInterface.askTeam(p);
    }

    public void askBag(Player p) {
        this.bagFillingInterface.askBag(p);
    }

    public Controller getController() {
        return this.controller;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}
