package com.esiea.pootp1.fight.player.team.members.moves;

import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.attacks.Attack;

public class Move {
    private Attack attack;

    private int nbUseLeft;

    public Move(Attack attack) {
        this.attack = attack;

        this.nbUseLeft = this.attack.getMaxNbUses();
    }

    public boolean canBeUsed() {
        return this.nbUseLeft != 0;
    }

    public void use() {
        this.nbUseLeft--;
    }

    public int getNbUsesLeft() {
        return this.nbUseLeft;
    }

    public int getMaxNbUses() {
        return this.attack.getMaxNbUses();
    }

    public String getName() {
        return this.attack.getName();
    }

    public Type getType() {
        return this.attack.getType();
    }
}
