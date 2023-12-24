package com.esiea.pootp1.models.consumables.types;

import com.esiea.pootp1.models.consumables.Consumable;

public class Potion extends Consumable {
    private int power;

    public Potion(Consumable consumable) {
        this.name = consumable.getName();
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }
}
