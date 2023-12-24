package com.esiea.pootp1.models.pokemons.attributes.types;

import com.esiea.pootp1.models.pokemons.attributes.Attributes;

public class WaterAttributes extends Attributes {
    private double flood, fall;

    public void setFlood(double flood) {
        this.flood = flood;
    }

    public void setFall(double fall) {
        this.fall = fall;
    }

    public double getFlood() {
        return this.flood;
    }

    public double getFall() {
        return this.fall;
    }
}
