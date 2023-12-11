package com.esiea.pootp1.models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Type {
    NORMAL, TERRE, FOUDRE, EAU, FEU, HERBE;

    private ArrayList<Type> strengths;
    private ArrayList<Type> weaknesses;

    static {
        NORMAL.setStrengths(new ArrayList<>());
        TERRE .setStrengths(new ArrayList<>(Arrays.asList(new Type[] {FOUDRE})));
        FOUDRE.setStrengths(new ArrayList<>(Arrays.asList(new Type[] {EAU   })));
        EAU   .setStrengths(new ArrayList<>(Arrays.asList(new Type[] {FEU   })));
        FEU   .setStrengths(new ArrayList<>(Arrays.asList(new Type[] {HERBE })));
        HERBE .setStrengths(new ArrayList<>(Arrays.asList(new Type[] {TERRE })));

        NORMAL.setWeaknesses(new ArrayList<>());
        TERRE .setWeaknesses(new ArrayList<>(Arrays.asList(new Type[] {HERBE })));
        FOUDRE.setWeaknesses(new ArrayList<>(Arrays.asList(new Type[] {TERRE })));
        EAU   .setWeaknesses(new ArrayList<>(Arrays.asList(new Type[] {FOUDRE})));
        FEU   .setWeaknesses(new ArrayList<>(Arrays.asList(new Type[] {EAU   })));
        HERBE .setWeaknesses(new ArrayList<>(Arrays.asList(new Type[] {FEU   })));
    }

    private void setStrengths(ArrayList<Type> strengths) {
        this.strengths = strengths;
    }

    private void setWeaknesses(ArrayList<Type> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public ArrayList<Type> getStrengths() {
        return this.strengths;
    }

    public ArrayList<Type> getWeaknesses() {
        return this.weaknesses;
    }
}