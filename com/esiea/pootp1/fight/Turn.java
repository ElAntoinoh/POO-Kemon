package com.esiea.pootp1.fight;

import java.util.HashMap;

import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.player.Player;

public class Turn {
    private HashMap<Player, Action> actions = new HashMap<>();

    public void addAction(Player player, Action action) {
        this.actions.put(player, action);
    }

    public HashMap<Player, Action> getActions() {
        return this.actions;
    }
}
