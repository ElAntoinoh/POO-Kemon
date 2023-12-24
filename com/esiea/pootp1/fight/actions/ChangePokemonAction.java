package com.esiea.pootp1.fight.actions;

import com.esiea.pootp1.fight.battlefield.Battlefield;
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
        String sRet = null;

        this.player.switchPokemons(this.firstPokemon, this.secondPokemon);

        Battlefield battlefield = this.player.getController().getFight().getBattlefield();

        if (battlefield.isFlooded() && battlefield.getStater().equals(this.firstPokemon)) {
            battlefield.dry();
            sRet = String.format("Le départ de %s permet au terrain de sécher", this.firstPokemon.getName());
        }

        return sRet;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "%s retire %s et envoie %s sur le terrain !";

        if (bonusInformation != null) format += "\n%s";

        System.out.format(
            format,
            this.player.getName(),
            this.firstPokemon.getName(),
            this.secondPokemon.getName(),
            bonusInformation
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
