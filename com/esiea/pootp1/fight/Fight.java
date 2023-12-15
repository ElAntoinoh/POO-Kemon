package com.esiea.pootp1.fight;

import java.util.ArrayList;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.Player;

public class Fight {
    private Controller controller;

    private ArrayList<Player> players;

    private ArrayList<Player> livingPlayers;

    public Fight(Controller controller, ArrayList<Player> players) {
        this.controller = controller;

        this.players = this.livingPlayers = players;

        startFight();
    }

    private void startFight() {
        while (!isOver()) {
            

            updateLivingPlayersList();
        }

        System.out.println("Gagnant : " + this.getWinner().getName() + " (" + this.getWinner().getNum() + ")");
    }

    private void updateLivingPlayersList() {
        ArrayList<Player> newLivingPlayers = new ArrayList<>();

        for (Player player : this.players) {
            if (player.hasAlivePokemons()) {
                newLivingPlayers.add(player);
            }
        }

        this.livingPlayers = newLivingPlayers;
    }

    private boolean isOver() {
        return this.livingPlayers.size() == 1;
    }

    private Player getWinner() {
        return this.livingPlayers.get(0);
    }

    public Controller getController() {
        return this.controller;
    }
}
