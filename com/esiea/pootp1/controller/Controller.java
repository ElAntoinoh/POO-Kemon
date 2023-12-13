package com.esiea.pootp1.controller;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.models.attacks.Attack;
import com.esiea.pootp1.models.attacks.AttackDex;
import com.esiea.pootp1.models.pokemons.PokeDex;
import com.esiea.pootp1.models.pokemons.Pokemon;
import com.esiea.pootp1.reader.AttacksFileReader;
import com.esiea.pootp1.reader.PokemonsFileReader;

public class Controller {
    private final static String POKEMONS_FILE_PATH = "res/pokemons.txt";
    private final static String ATTACKS_FILE_PATH  = "res/attacks.txt";

    private PokeDex pokeDex;
    private AttackDex attackDex;

    public Controller() {
        this.pokeDex   = new PokeDex();
        this.attackDex = new AttackDex();

        new PokemonsFileReader(this, POKEMONS_FILE_PATH).readFile();
        new AttacksFileReader (this, ATTACKS_FILE_PATH ).readFile();

        Player player1 = new Player(this, "Antoine");
        Player player2 = new Player(this, "Jules");

        System.out.println(player1);
        System.out.println(player2);
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokeDex.addPokemon(pokemon);
    }

    public void addAttack(Attack attack) {
        this.attackDex.addAttack(attack);
    }

    public PokeDex getPokeDex() {
        return this.pokeDex;
    }

    public AttackDex getAttackDex() {
        return this.attackDex;
    }
}
