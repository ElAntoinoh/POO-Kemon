package com.esiea.pootp1.fight;

import java.util.ArrayList;
import java.util.Map;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.player.Player;

public class Fight {
    private Controller controller;

    private ArrayList<Turn> turns = new ArrayList<>();

    private ArrayList<Player> players, livingPlayers;

    public Fight(Controller controller, ArrayList<Player> players) {
        this.controller = controller;

        this.players = this.livingPlayers = players;
    }

    public void start() {
        int playerIndex = 0;

        Turn turn = new Turn();

        while (!isOver()) {
            Player player = this.livingPlayers.get(playerIndex++);

            turn.addAction(player, this.controller.getConsoleInterface().askActionChoice(player));

            for (Map.Entry<Player, Action> entry : turn.getActions().entrySet()) {
                System.out.println(entry.getValue());
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (playerIndex >= this.livingPlayers.size()) {
                playerIndex = 0;
                turn = new Turn();
            }

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

    public int getLongestPlayerName() {
        int maxLength = 0;

        for (Player player : this.livingPlayers) {
            int playerNameLength = player.getName().length();

            if (playerNameLength > maxLength) {
                maxLength = playerNameLength;
            }
        }

        return maxLength;
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

    public ArrayList<Player> getLivingPlayersList() {
        return this.livingPlayers;
    }
}
