package com.esiea.pootp1.models.pokemons;

import java.util.ArrayList;

public class PokeDex {
    private ArrayList<GenericPokemon> pokemonsList;

    public PokeDex() {
        this.pokemonsList = new ArrayList<>();
    }

    public void addPokemon(GenericPokemon pokemon) {
        this.pokemonsList.add(pokemon);
    }

    public int getMaxNameLength() {
        return this.pokemonsList.stream()
            .mapToInt(pokemon -> pokemon.getName().length())
            .max()
            .orElse(0);
    }

    public ArrayList<GenericPokemon> getPokemonList() {
        return this.pokemonsList;
    }

    public String toString() {
        String str = "";

        for (GenericPokemon p : this.pokemonsList) {
            str += p.toString() + '\n';
        }

        return str;
    }
}
