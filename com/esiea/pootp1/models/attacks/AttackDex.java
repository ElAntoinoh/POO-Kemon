package com.esiea.pootp1.models.attacks;

import java.util.ArrayList;

public class AttackDex {
    private ArrayList<Attack> attacksList;

    public AttackDex() {
        this.attacksList = new ArrayList<>();
    }

    public void addAttack(Attack attack) {
        this.attacksList.add(attack);
    }
}