package com.esiea.pootp1.models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Type {
    NORMAL, TERRE, FOUDRE, EAU, FEU, NATURE, PLANTE, INSECT;

    private Type parent;

    private ArrayList<Type> strengths;
    private ArrayList<Type> weaknesses;

    static {
        PLANTE.parent = NATURE;
        INSECT.parent = NATURE;

        NORMAL.strengths = new ArrayList<>();
        TERRE .strengths = new ArrayList<>(Arrays.asList(new Type[] {FOUDRE}));
        FOUDRE.strengths = new ArrayList<>(Arrays.asList(new Type[] {EAU   }));
        EAU   .strengths = new ArrayList<>(Arrays.asList(new Type[] {FEU   }));
        FEU   .strengths = new ArrayList<>(Arrays.asList(new Type[] {NATURE}));
        NATURE.strengths = new ArrayList<>(Arrays.asList(new Type[] {TERRE }));

        NORMAL.weaknesses = new ArrayList<>();
        TERRE .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {NATURE}));
        FOUDRE.weaknesses = new ArrayList<>(Arrays.asList(new Type[] {TERRE }));
        EAU   .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {FOUDRE}));
        FEU   .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {EAU   }));
        NATURE.weaknesses = new ArrayList<>(Arrays.asList(new Type[] {FEU   }));

        for (Type t : Type.values()) {
            if (t.parent != null) {
                t.strengths  = t.parent.strengths;
                t.weaknesses = t.parent.weaknesses;
            }
        }
    }

    public ArrayList<Type> getStrengths() {
        return this.strengths;
    }

    public ArrayList<Type> getWeaknesses() {
        return this.weaknesses;
    }
}