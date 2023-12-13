package com.esiea.pootp1.models.pokemons;

import java.util.ArrayList;

public class PokeDex {
    private ArrayList<Pokemon> pokemonsList;

    public PokeDex() {
        this.pokemonsList = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemonsList.add(pokemon);
    }

    public ArrayList<Pokemon> getPokemonList() {
        return this.pokemonsList;
    }

    public String toString() {
        String str = "";

        for (Pokemon p : this.pokemonsList) {
            str += p.toString() + '\n';
        }

        return str;
    }
}
