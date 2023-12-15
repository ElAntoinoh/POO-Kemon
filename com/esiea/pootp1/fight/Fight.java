package com.esiea.pootp1.fight;

import java.util.ArrayList;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.members.Pokemon;

public class Fight {
    private Controller controller;

    private ArrayList<Player> players;

    public Fight(Controller controller, ArrayList<Player> players) {
        this.controller = controller;

        this.players = players;
    }

    public Controller getController() {
        return this.controller;
    }
}
