package com.esiea.pootp1.fight.player.team;

import java.util.ArrayList;
import java.util.Random;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.models.pokemons.GenericPokemon;

public class Team {
    public final static int MAXIMUM_NUMBER_OF_MEMBERS = 3;

    private Player player;

    private ArrayList<Pokemon> members = new ArrayList<>();

    public Team(Player player) {
        this.player = player;

        fillVoidTeam();

        setRandomMembers();
    }

    private void fillVoidTeam() {
        for (int i = 0; i < MAXIMUM_NUMBER_OF_MEMBERS; i++) this.members.add(null);
    }

    public void setRandomMembers() {
        ArrayList<GenericPokemon> pokemonList = this.player.getController().getPokeDex().getPokemonList();

        Random random = new Random();

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MEMBERS; i++) {
            int index = random.nextInt(pokemonList.size());

            this.members.set(i, new Pokemon(this, pokemonList.get(index)));
        }
    }

    public void setMembers(ArrayList<Pokemon> newMembers) {
        assert newMembers.size() == MAXIMUM_NUMBER_OF_MEMBERS;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MEMBERS; i++) {
            Pokemon newMember = newMembers.get(i);
            
            newMember.setTeam(this);
            this.members.set(i, newMember);
        }
    }

    public void switchPokemons(Pokemon firstPokemon, Pokemon secondPokemon) {
        int firstPokemonIndex  = this.members.indexOf(firstPokemon );
        int secondPokemonIndex = this.members.indexOf(secondPokemon);

        this.members.set(firstPokemonIndex, secondPokemon);
        this.members.set(secondPokemonIndex, firstPokemon);
    }

    public ArrayList<Pokemon> getAlivePokemons() {
        return new ArrayList<>(
            this.members.stream()
            .filter(Pokemon::isAlive)
            .toList()
        );
    }

    public int getLongestMemberNameLength(Pokemon excludedPokemon) {
        return this.members.stream()
            .filter(pokemon -> !pokemon.equals(excludedPokemon))
            .mapToInt(pokemon -> pokemon.getName().length())
            .max()
            .orElse(0);
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Pokemon> getMembers() {
        return this.members;
    }
}
