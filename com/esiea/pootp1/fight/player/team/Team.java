package com.esiea.pootp1.fight.player.team;

import java.util.ArrayList;
import java.util.Collections;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.models.pokemons.GenericPokemon;

public class Team {
    public final static int MAXIMUM_NUMBER_OF_MEMBERS = 3;

    private Player player;

    private ArrayList<Pokemon> members = new ArrayList<>();

    public Team(Player player) {
        this.player = player;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MEMBERS; i++) this.members.add(null);

        setRandomMembers();
    }

    public void setRandomMembers() {
        ArrayList<GenericPokemon> pokemonList = this.player.getController().getPokeDex().getPokemonList();

        Collections.shuffle(pokemonList);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_MEMBERS; i++) {
            this.members.set(i, new Pokemon(this, pokemonList.get(i)));
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
        ArrayList<Pokemon> alivePokemons = new ArrayList<>();

        for (Pokemon pokemon : this.members) {
            if (pokemon.isAlive()) {
                alivePokemons.add(pokemon);
            }
        }

        return alivePokemons;
    }

    public int getLongestMemberNameLength(boolean withFightingPokemon) {
        int maxLength = 0;

        for (Pokemon pokemon : this.members) {
            if (!withFightingPokemon && pokemon.equals(this.player.getFightingPokemon())) continue;
            
            int pokemonNameLength = pokemon.getName().length();

            if (pokemonNameLength > maxLength) {
                maxLength = pokemonNameLength;
            }
        }

        return maxLength;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Pokemon> getMembers() {
        return this.members;
    }

    public String toString() {
        String str = "Team :\n";

        for (Pokemon p : this.members) str += p.toString() + '\n';

        return str;
    }
}
