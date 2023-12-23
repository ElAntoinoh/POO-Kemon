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

    @Override
    public String activate() {
        this.player.switchPokemons(this.firstPokemon, this.secondPokemon);

        return null;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "%s retire %s et envoie %s sur le terrain !";

        System.out.format(
            format,
            this.player.getName(),
            this.firstPokemon.getName(),
            this.secondPokemon.getName()
        );

        this.player.getController().getConsoleInterface().waitForAction();
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
}
