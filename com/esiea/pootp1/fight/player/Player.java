package com.esiea.pootp1.fight.player;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.team.PokemonBeing;
import com.esiea.pootp1.models.consumables.Consumable;

public class Player {
    private final static int MAX_TEAM_MEMBERS = 3;
    private final static int MAX_BAG_CAPACITY = 5;

    private Controller controller;

    private PokemonBeing[] team;
    private Consumable  [] bag;

    public Player(Controller controller) {
        this.controller = controller;

        this.team = new PokemonBeing[MAX_TEAM_MEMBERS];
        this.bag  = new Consumable  [MAX_BAG_CAPACITY];
    }

    public Controller geController() {
        return this.controller;
    }
}
