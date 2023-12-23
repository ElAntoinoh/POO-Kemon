package com.esiea.pootp1.fight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.actions.AttackAction;
import com.esiea.pootp1.fight.actions.ChangePokemonAction;
import com.esiea.pootp1.fight.actions.UseObjectAction;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;

public class Fight {
    private static Comparator<Player> speedComparator = new Comparator<Player>() {
        public int compare(Player p1, Player p2) {
            return p2.getFightingPokemon().getSpeed() - p1.getFightingPokemon().getSpeed();
        }
    };

    private Controller controller;

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

            if (playerIndex >= this.livingPlayers.size()) {
                processTurn(turn);

                playerIndex = 0;
                turn = new Turn();
            }

            updateLivingPlayersList();
        }

        this.controller.getConsoleInterface().printFightResult(getWinner());
    }

    public void processTurn(Turn turn) {
        ArrayList<Player> speedSortedPlayers = this.livingPlayers;
        speedSortedPlayers.sort(Fight.speedComparator);

        processChangePokemonActions(turn, speedSortedPlayers);
        processUseObjectActions    (turn, speedSortedPlayers);
        processAttackActions       (turn, speedSortedPlayers);

        updateLivingPlayersList();
    }

    private void processChangePokemonActions(Turn turn, ArrayList<Player> speedSortedPlayers) {
        HashMap<Player, ChangePokemonAction> changePokemonActions = turn.getChangePokemonActions();

        for (Player player : speedSortedPlayers) {
            if (changePokemonActions.containsKey(player)) {
                processAction(changePokemonActions.get(player));
            }
        }
    }

    private void processUseObjectActions(Turn turn, ArrayList<Player> speedSortedPlayers) {
        HashMap<Player, UseObjectAction> useObjectActions = turn.getUseObjectActions();

        for (Player player : speedSortedPlayers) {
            if (useObjectActions.containsKey(player)) {
                processAction(useObjectActions.get(player));
            }
        }
    }

    private void processAttackActions(Turn turn, ArrayList<Player> speedSortedPlayers) {
        HashMap<Player, AttackAction> attackActions = turn.getAttackActions();

        for (Player player : speedSortedPlayers) {
            if (attackActions.containsKey(player)) {
                AttackAction action = attackActions.get(player);

                if (!action.getAttacker().getFightingPokemon().isAlive()) continue;

                processAction(attackActions.get(player));

                Player target = attackActions.get(player).getTarget();

                if (!target.getFightingPokemon().isAlive() && target.hasAlivePokemons()) {
                    if (target.getAlivePokemons().size() == 1) {
                        Pokemon firstPokemon  = target.getFightingPokemon();
                        Pokemon secondPokemon = target.getAlivePokemons().get(0);

                        this.controller.getConsoleInterface().getFightChoiceInterface().printMandatoryPokemon(firstPokemon, secondPokemon);

                        target.switchPokemons(firstPokemon, secondPokemon);
                    }

                    else {
                        this.controller.getConsoleInterface().getFightChoiceInterface().askChangePokemonChoice(target).activate();
                    }
                }
            }
        }
    }

    private void processAction(Action action) {
        this.controller.getConsoleInterface().clearConsole();

        action.print(action.activate());
    }

    private void updateLivingPlayersList() {
        this.livingPlayers = new ArrayList<>(
            this.players.stream()
                .filter(p -> p.hasAlivePokemons())
                .toList()
        );
    }

    public int getLongestPlayerName(Player excludedPlayer) {
        return this.livingPlayers.stream()
            .filter(player -> !player.equals(excludedPlayer))
            .mapToInt(player -> player.getName().length())
            .max()
            .orElse(0);
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
