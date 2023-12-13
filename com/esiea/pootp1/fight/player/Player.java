package com.esiea.pootp1.fight.player;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.models.consumables.Consumable;

public class Player {
    private final static int MAX_BAG_CAPACITY = 5;

    private Controller controller;

    private Team team;

    private Consumable  [] bag;

    public Player(Controller controller) {
        this.controller = controller;

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
