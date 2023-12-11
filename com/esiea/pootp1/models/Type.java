package com.esiea.pootp1.models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Type {
    // Normal type
    NORMAL,
    
    // Basic types
    TERRE, FOUDRE, EAU, FEU, NATURE,
    
    // Subtypes
    PLANTE, INSECT;

    public static ArrayList<Type> usableTypes = new ArrayList<>();

    private String printedString;

    private Type parent = null;
    private boolean isUsable = true;

    private ArrayList<Type> strengths;
    private ArrayList<Type> weaknesses;

    static {
        // Types printed Strings
        NORMAL.printedString = "Normal";
        TERRE .printedString = "Earth";
        FOUDRE.printedString = "Electric";
        EAU   .printedString = "Water";
        FEU   .printedString = "Fire";
        NATURE.printedString = "Nature";
        PLANTE.printedString = "Plant";
        INSECT.printedString = "Insect";

        // Normal type attributes
        NORMAL.strengths  = new ArrayList<>();
        NORMAL.weaknesses = new ArrayList<>();

        // Basic types strengths
        TERRE .strengths = new ArrayList<>(Arrays.asList(new Type[] {FOUDRE}));
        FOUDRE.strengths = new ArrayList<>(Arrays.asList(new Type[] {EAU   }));
        EAU   .strengths = new ArrayList<>(Arrays.asList(new Type[] {FEU   }));
        FEU   .strengths = new ArrayList<>(Arrays.asList(new Type[] {NATURE}));
        NATURE.strengths = new ArrayList<>(Arrays.asList(new Type[] {TERRE }));

        // Basic types weaknesses
        TERRE .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {NATURE}));
        FOUDRE.weaknesses = new ArrayList<>(Arrays.asList(new Type[] {TERRE }));
        EAU   .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {FOUDRE}));
        FEU   .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {EAU   }));
        NATURE.weaknesses = new ArrayList<>(Arrays.asList(new Type[] {FEU   }));

        // Subtypes parenting
        PLANTE.parent = NATURE;
        INSECT.parent = NATURE;

        // Subtypes attributes
        for (Type t : Type.values()) {
            if (t.parent != null) {
                // Subtypes strengths
                t.strengths  = t.parent.strengths;

                // Subtypes weaknesses
                t.weaknesses = t.parent.weaknesses;
            }
        }

        // Non usable types
        NATURE.isUsable = false;

        // Usable types list filling
        for (Type t : Type.values()) {
            if (t.isUsable) {
                usableTypes.add(t);
            }
        }
    }

    public ArrayList<Type> getStrengths() {
        return this.strengths;
    }

    public ArrayList<Type> getWeaknesses() {
        return this.weaknesses;
    }

    public String toString() {
        return this.printedString;
    }
}