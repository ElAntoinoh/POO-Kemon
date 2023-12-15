package com.esiea.pootp1.fight.player;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.bag.Bag;
import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.Pokemon;

public class Player {
    private static int NB_PLAYERS = 1;

    private Controller controller;

    private int numPlayer;

    private String name;

    private Team team;

    private Bag bag;

    public Player(Controller controller, String name) {
        this.controller = controller;

        this.numPlayer = NB_PLAYERS++;

        this.name = name;

        this.team = new Team(this);

        this.bag = new Bag(this);
    }

    public boolean hasAlivePokemons() {
        for (Pokemon pokemon : this.team.getMembers()) {
            if (pokemon.isAlive()) {
                return true;
            }
        }

        return false;
    }

    public Controller getController() {
        return this.controller;
    }

    public int getNum() {
        return this.numPlayer;
    }

    public String getName() {
        return this.name;
    }

    public Team getTeam() {
        return this.team;
    }

    public Bag getBag() {
        return this.bag;
    }

    public String toString() {
        return this.name + " (" + this.numPlayer + ") :\n" + this.team.toString() + '\n' + this.bag.toString();
    }
}
