package com.esiea.pootp1.fight.actions;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;

public class ChangePokemonAction extends Action {
    private Player player;

    private Pokemon firstPokemon, secondPokemon;

    private ChangePokemonAction(Player player, Pokemon firstPokemon, Pokemon secondPokemon) {
        this.player = player;
        this.firstPokemon = firstPokemon;
        this.secondPokemon = secondPokemon;
    }

    public static ChangePokemonAction createChangePokemonAction(Player player, Pokemon firstPokemon, Pokemon secondPokemon) {
        assert player.getAlivePokemons().contains(firstPokemon);
        assert player.getAlivePokemons().contains(secondPokemon);

        return new ChangePokemonAction(player, firstPokemon, secondPokemon);
    }

    public void print() {
        System.out.format("%s retire %s et envoie %s sur le terrain !", this.player.getName(), this.firstPokemon.getName(), this.secondPokemon.getName());

        player.getController().getConsoleInterface().getScanner().nextLine();
    }

    public Player getPlayer() {
        return this.player;
    }

    public Pokemon getFirstPokemon() {
        return this.firstPokemon;
    }

    public Pokemon getSecondPokemon() {
        return this.secondPokemon;
    }

    public String toString() {
        return this.player.getName() + " : " + this.firstPokemon.getName() + " -> " + this.secondPokemon.getName();
    }
}
