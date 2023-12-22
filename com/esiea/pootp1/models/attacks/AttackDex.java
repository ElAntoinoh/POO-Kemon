package com.esiea.pootp1.models.attacks;

import java.util.ArrayList;

import com.esiea.pootp1.models.Type;

public class AttackDex {
    private ArrayList<Attack> attacksList;

    public AttackDex() {
        this.attacksList = new ArrayList<>();
    }

    public void addAttack(Attack attack) {
        this.attacksList.add(attack);
    }

    public ArrayList<Attack> getAttacksFromType(Type searchedType) {
        return new ArrayList<>(
            this.attacksList.stream()
                .filter(attack -> attack.getType().equals(searchedType))
                .toList()
        );
    }
}