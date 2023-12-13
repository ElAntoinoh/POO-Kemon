package com.esiea.pootp1.fight.player;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.bag.Bag;
import com.esiea.pootp1.fight.player.team.Team;

public class Player {
    private static int NB_PLAYERS = 1;

    private Controller controller;

    private int numJoueur;

    private String name;

    private Team team;

    private Bag bag;

    public Player(Controller controller, String name) {
        this.controller = controller;

        this.numJoueur = NB_PLAYERS++;

        this.name = name;

        this.team = new Team(this);

        this.bag = new Bag(this);
    }

    public Controller getController() {
        return this.controller;
    }

    public String getName() {
        return this.name;
    }

    public Team getTeam() {
        return this.team;
    }

    public String toString() {
        return this.name + " (" + this.numJoueur + ") :\n" + this.team.toString() + '\n' + this.bag.toString();
    }
}
