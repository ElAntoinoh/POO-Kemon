package com.esiea.pootp1.models.pokemons.attributes.types;

import com.esiea.pootp1.models.pokemons.attributes.Attributes;

public class FireAttributes extends Attributes {
    private double burn;

    public void setBurn(double burn) {
        this.burn = burn;
    }

    public double getBurn() {
        return this.burn;
    }
}
