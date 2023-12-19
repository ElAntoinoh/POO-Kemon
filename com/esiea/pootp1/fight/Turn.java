package com.esiea.pootp1.fight;

import java.util.HashMap;
import java.util.Map;

import com.esiea.pootp1.fight.actions.Action;
import com.esiea.pootp1.fight.actions.AttackAction;
import com.esiea.pootp1.fight.actions.ChangePokemonAction;
import com.esiea.pootp1.fight.actions.UseObjectAction;
import com.esiea.pootp1.fight.player.Player;

public class Turn {
    private HashMap<Player, Action> actions = new HashMap<>();

    public void addAction(Player player, Action action) {
        this.actions.put(player, action);
    }

    public HashMap<Player, Action> getActions() {
        return this.actions;
    }

    public HashMap<Player, AttackAction> getAttackActions() {
        HashMap<Player, AttackAction> hashMap = new HashMap<>();

        for (Map.Entry<Player, Action> entry : this.actions.entrySet()) {
            if (entry.getValue() instanceof AttackAction) {
                hashMap.put(entry.getKey(), (AttackAction) entry.getValue());
            }
        }

        return hashMap;
    }

    public HashMap<Player, ChangePokemonAction> getChangePokemonActions() {
        HashMap<Player, ChangePokemonAction> hashMap = new HashMap<>();

        for (Map.Entry<Player, Action> entry : this.actions.entrySet()) {
            if (entry.getValue() instanceof ChangePokemonAction) {
                hashMap.put(entry.getKey(), (ChangePokemonAction) entry.getValue());
            }
        }

        return hashMap;
    }

    public HashMap<Player, UseObjectAction> getUseObjectActions() {
        HashMap<Player, UseObjectAction> hashMap = new HashMap<>();

        for (Map.Entry<Player, Action> entry : this.actions.entrySet()) {
            if (entry.getValue() instanceof UseObjectAction) {
                hashMap.put(entry.getKey(), (UseObjectAction) entry.getValue());
            }
        }

        return hashMap;
    }
}
