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
        ArrayList<Attack> attacks = new ArrayList<>();

        for (Attack a : this.attacksList) {
            if (a.getType().equals(searchedType)) {
                attacks.add(a);
            }
        }

        return attacks;
    }

    public int getLongestAttackName() {
        int maxLength = 0;

        for (Attack attack : this.attacksList) {
            int attackNameLength = attack.getName().length();

            if (attackNameLength > maxLength) {
                maxLength = attackNameLength;
            }
        }

        return maxLength;
    }
}