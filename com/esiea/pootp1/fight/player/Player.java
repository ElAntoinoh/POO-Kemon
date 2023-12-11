package com.esiea.pootp1.fight.player;

import com.esiea.pootp1.fight.player.team.PokemonBeing;
import com.esiea.pootp1.models.consumables.Consumable;

public class Player {
    private final static int MAX_TEAM_MEMBERS = 3;
    private final static int MAX_BAG_CAPACITY = 5;

    private PokemonBeing[] team = new PokemonBeing[MAX_TEAM_MEMBERS];
    private Consumable  [] bag  = new Consumable  [MAX_BAG_CAPACITY];
}
