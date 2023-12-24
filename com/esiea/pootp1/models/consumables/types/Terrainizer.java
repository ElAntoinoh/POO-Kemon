package com.esiea.pootp1.models.consumables.types;

import com.esiea.pootp1.fight.battlefield.State;
import com.esiea.pootp1.models.consumables.Consumable;

public class Terrainizer extends Consumable {
    private State state;

    public Terrainizer(Consumable consumable) {
        this.name = consumable.getName();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }
}
