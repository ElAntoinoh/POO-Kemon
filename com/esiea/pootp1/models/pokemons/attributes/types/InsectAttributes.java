package com.esiea.pootp1.models.pokemons.attributes.types;

import com.esiea.pootp1.models.pokemons.attributes.Attributes;

public class InsectAttributes extends Attributes {
    private double poison;

    public void setPoison(double poison) {
        this.poison = poison;
    }

    public double getPoison() {
        return this.poison;
    }
}
