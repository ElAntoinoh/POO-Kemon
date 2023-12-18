package com.esiea.pootp1.fight.player;

import java.util.ArrayList;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.bag.Bag;
import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.models.Type;

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

    public ArrayList<Pokemon> getAlivePokemons() {
        return this.team.getAlivePokemons();
    }

    public boolean hasAlivePokemons() {
        return this.team.getAlivePokemons().size() != 0;
    }

    public Pokemon getFightingPokemon() {
        return this.team.getMembers().get(0);
    }

    public void setFightingPokemon(int newIndex) {
        this.team.switchPokemons(0, newIndex);
    }

    public void changeFightingPokemon(int indice) {
        int nbNotFightingPokemons = 0;

        ArrayList<Pokemon> alivePokemons = getAlivePokemons();

        for (int i = 0; i < alivePokemons.size(); i++) {
            if (!alivePokemons.get(i).equals(this.getFightingPokemon())) nbNotFightingPokemons++;

            if (nbNotFightingPokemons == indice) this.setFightingPokemon(i);
        }
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
