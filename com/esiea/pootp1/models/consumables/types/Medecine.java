package com.esiea.pootp1.models.consumables.types;

import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.models.consumables.Consumable;

public class Medecine extends Consumable {
    private Status status;

    public Medecine(Consumable consumable) {
        this.name = consumable.getName();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}
