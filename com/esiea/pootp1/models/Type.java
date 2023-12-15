package com.esiea.pootp1.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public enum Type {
    NORMAL,                          // Normal type
    DIRT, ELEC, WATER, FIRE, NATURE, // Basic types
    PLANT, INSECT;                   // Subtypes

    /* --------------------------------- */
    /* Attributes of the enumerated type */
    /* --------------------------------- */

    private static ArrayList<Type> usableTypes = new ArrayList<>();

    private static Map<String, Type> typeConfigText = Map.ofEntries(
        Map.entry("Normal"  , NORMAL),
        Map.entry("Dirt"    , DIRT  ),
        Map.entry("Electric", ELEC  ),
        Map.entry("Water"   , WATER ),
        Map.entry("Fire"    , FIRE  ),
        Map.entry("Nature"  , NATURE),
        Map.entry("Plant"   , PLANT ),
        Map.entry("Insect"  , INSECT)
    );

    private static Map<Type, String> typeDisplayText = Map.ofEntries(
        Map.entry(NORMAL, "Normal" ),
        Map.entry(DIRT  , "Terre"   ),
        Map.entry(ELEC  , "Foudre" ),
        Map.entry(WATER , "Eau"    ),
        Map.entry(FIRE  , "Feu"    ),
        Map.entry(NATURE, "Nature" ),
        Map.entry(PLANT , "Plante" ),
        Map.entry(INSECT, "Insecte")
    );

    /* ------------------------------------------- */
    /* Attributes of the enumerated type instances */
    /* ------------------------------------------- */

    private Type parent = null;

    private boolean isUsable = true;

    private ArrayList<Type> strengths;
    private ArrayList<Type> weaknesses;

    /* ------------------------------ */
    /* Methods of the enumerated type */
    /* ------------------------------ */

    static {
        setStrengths();

        setWeaknesses();

        setParentalities();

        setAttributesLegacy();

        setUnusableTypes();

        fillUsableTypesList();
    }

    private static void setStrengths() {
        NORMAL.strengths = new ArrayList<>(                                  );
        DIRT  .strengths = new ArrayList<>(Arrays.asList(new Type[] {ELEC  }));
        ELEC  .strengths = new ArrayList<>(Arrays.asList(new Type[] {WATER }));
        WATER .strengths = new ArrayList<>(Arrays.asList(new Type[] {FIRE  }));
        FIRE  .strengths = new ArrayList<>(Arrays.asList(new Type[] {NATURE}));
        NATURE.strengths = new ArrayList<>(Arrays.asList(new Type[] {DIRT  }));
    }

    private static void setWeaknesses() {
        NORMAL.weaknesses = new ArrayList<>(                                  );
        DIRT  .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {NATURE}));
        ELEC  .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {DIRT  }));
        WATER .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {ELEC  }));
        FIRE  .weaknesses = new ArrayList<>(Arrays.asList(new Type[] {WATER }));
        NATURE.weaknesses = new ArrayList<>(Arrays.asList(new Type[] {FIRE  }));
    }

    private static void setParentalities() {
        PLANT.parent = NATURE;
        INSECT.parent = NATURE;
    }

    private static void setAttributesLegacy() {
        for (Type type : Type.values()) {
            if (type.parent != null) {
                // Set current type attributes
                type.strengths  = type.parent.strengths;
                type.weaknesses = type.parent.weaknesses;

                // Add type to the weaknesses of types that the type's parent has advantage against
                for (Type strength : type.parent.strengths) strength.weaknesses.add(type);

                // Add type to the strengths of types against which the type's parent has disadvantage
                for (Type weakness : type.parent.weaknesses) weakness.strengths.add(type);
            }
        }
    }

    private static void setUnusableTypes() {
        NATURE.isUsable = false;
    }

    private static void fillUsableTypesList() {
        for (Type t : Type.values()) {
            if (t.isUsable) {
                Type.usableTypes.add(t);
            }
        }
    }

    public static int getMaxNameLength() {
        int maxLength = 0;

        for (String displayNames : Type.typeDisplayText.values()) {
            int typeNameLength = displayNames.length();

            if (typeNameLength > maxLength) {
                maxLength = typeNameLength;
            }
        }

        return maxLength;
    }

    /* ---------------------------------------- */
    /* Methods of the enumerated type instances */
    /* ---------------------------------------- */

    public static ArrayList<Type> getUsableTypes() {
        return Type.usableTypes;
    }

    public static Map<String, Type> getTypeConfigText() {
        return Type.typeConfigText;
    }

    public static Map<Type, String> getTypeDisplayText() {
        return Type.typeDisplayText;
    }

    public ArrayList<Type> getStrengths() {
        return this.strengths;
    }

    public ArrayList<Type> getWeaknesses() {
        return this.weaknesses;
    }
}