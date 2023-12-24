package com.esiea.pootp1.fight.battlefield;

public class Battlefield {
    private State state = State.NORMAL;

    public void setState(State state) {
        this.state = state;
    }

    public boolean isFlooded() {
        return this.state == State.FLOODED;
    }
}
