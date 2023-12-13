package com.esiea.pootp1.fight.player;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.models.consumables.Consumable;

public class Player {
    private final static int MAX_BAG_CAPACITY = 5;

    private static int NB_PLAYERS = 0;

    private Controller controller;

    private int numJoueur;

    private String name;

    private Team team;

    private Consumable  [] bag;

    public Player(Controller controller, String name) {
        this.controller = controller;

        this.numJoueur = NB_PLAYERS++;

        this.name = name;

        this.team = new Team(this);

        this.bag  = new Consumable  [MAX_BAG_CAPACITY];
    }

    public Controller getController() {
        return this.controller;
    }

    public String toString() {
        return "Player :\n" + this.team.toString();
    }
}
