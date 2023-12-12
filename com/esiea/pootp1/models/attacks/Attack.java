package com.esiea.pootp1.models.attacks;

import com.esiea.pootp1.models.Type;

public class Attack {
    private String name;

    private Type type;

    private int nbUtilisations;
    private int power;

    private double failureProbability;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setNbUtilisations(int nbUtilisations) {
        this.nbUtilisations = nbUtilisations;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public int getNbUtilisations() {
        return this.nbUtilisations;
    }

    public int getPower() {
        return this.power;
    }

    public double getFailureProbability() {
        return this.failureProbability;
    }
}