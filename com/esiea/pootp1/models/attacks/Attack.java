package com.esiea.pootp1.models.attacks;

import com.esiea.pootp1.models.Type;

public class Attack {
    private String name;

    private Type type;

    private int nbUtilisations;
    private int power;
    private double failureProbability;

    public Attack() {};
}