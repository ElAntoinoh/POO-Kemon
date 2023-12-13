package com.esiea.pootp1.fight.player.bag;

import com.esiea.pootp1.models.consumables.Consumable;

public class Item {
    private Consumable consumable;

    public Item(Consumable consumable) {
        this.consumable = consumable;
    }

    public String toString() {
        return this.consumable.getName();
    }
}
