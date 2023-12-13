package com.esiea.pootp1.fight.player.team.members.moves;

import com.esiea.pootp1.models.attacks.Attack;

public class Move {
    private Attack attack;

    private int nbUseLeft;

    public Move(Attack attack) {
        this.attack = attack;

        this.nbUseLeft = this.attack.getNbUtilisations();
    }

    public boolean canBeUsed() {
        return this.nbUseLeft != 0;
    }

    public void use() {
        this.nbUseLeft--;
    }

    public String toString() {
        return this.attack.getName() + " (" + this.nbUseLeft + "/" + this.attack.getNbUtilisations() + ")";
    }
}
