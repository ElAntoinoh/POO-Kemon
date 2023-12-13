package com.esiea.pootp1.controller;

import java.util.ArrayList;

import com.esiea.pootp1.fight.Fight;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.models.attacks.Attack;
import com.esiea.pootp1.models.attacks.AttackDex;
import com.esiea.pootp1.models.consumables.Consumable;
import com.esiea.pootp1.models.consumables.GlobalBag;
import com.esiea.pootp1.models.pokemons.PokeDex;
import com.esiea.pootp1.models.pokemons.GenericPokemon;
import com.esiea.pootp1.reader.AttacksFileReader;
import com.esiea.pootp1.reader.ConsumablesFileReader;
import com.esiea.pootp1.reader.PokemonsFileReader;
import com.esiea.pootp1.vue.ConsoleInterface;
import com.esiea.pootp1.vue.DataCollectionInterface;

public class Controller {
    private final static String POKEMONS_FILE_PATH    = "res/pokemons.txt";
    private final static String ATTACKS_FILE_PATH     = "res/attacks.txt";
    private final static String CONSUMABLES_FILE_PATH = "res/consumables.txt";

    private PokeDex pokeDex;
    private AttackDex attackDex;
    private GlobalBag globalBag;

    private ConsoleInterface consoleInterface;

    public Controller() {
        init();

        ArrayList<Player> players = new ArrayList<>();

        for (String string : this.consoleInterface.askNames()) {
            players.add(new Player(this, string));
        }

        for (Player p : players) {
            this.consoleInterface.askTeam(p);
        }

        Fight fight = new Fight(this, players);

        for (Player p : players) {
            System.out.println(p);
        }
    }

    private void init() {
        this.pokeDex       = new PokeDex();
        this.attackDex     = new AttackDex();
        this.globalBag = new GlobalBag();

        new PokemonsFileReader   (this, POKEMONS_FILE_PATH   ).readFile();
        new AttacksFileReader    (this, ATTACKS_FILE_PATH    ).readFile();
        new ConsumablesFileReader(this, CONSUMABLES_FILE_PATH).readFile();

        this.consoleInterface = new ConsoleInterface(this);
    }

    public void addPokemon(GenericPokemon pokemon) {
        this.pokeDex.addPokemon(pokemon);
    }

    public void addAttack(Attack attack) {
        this.attackDex.addAttack(attack);
    }

    public void addConsumable(Consumable consumable) {
        this.globalBag.addConsumable(consumable);
    }

    public PokeDex getPokeDex() {
        return this.pokeDex;
    }

    public AttackDex getAttackDex() {
        return this.attackDex;
    }

    public GlobalBag getGlobalBag() {
        return this.globalBag;
    }
}
