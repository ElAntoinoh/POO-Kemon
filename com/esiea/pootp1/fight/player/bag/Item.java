package com.esiea.pootp1.fight.player.bag;

import com.esiea.pootp1.models.consumables.Consumable;

public class Item {
    private Consumable consumable;

    private Bag bag;

    public Item(Bag bag, Consumable consumable) {
        this.bag = bag;
        this.consumable = consumable;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public String toString() {
        return this.consumable.getName();
    }
}
