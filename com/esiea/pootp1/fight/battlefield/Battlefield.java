package com.esiea.pootp1.fight.battlefield;

import java.util.Random;

import com.esiea.pootp1.fight.Fight;
import com.esiea.pootp1.fight.player.team.members.Pokemon;

public class Battlefield {
    private Fight fight;
    
    private State state = State.NORMAL;

    private Pokemon stater = null;

    private int nbTurnsWithStatus = 0;

    public Battlefield(Fight fight) {
        this.fight = fight;
    }

    public void setState(State state, Pokemon pokemon) {
        this.state = state;

        this.stater = pokemon;

        this.nbTurnsWithStatus = new Random().nextInt(3);
    }

    public void tryToDry() {
        if (this.state != State.NORMAL && this.nbTurnsWithStatus-- == 0) {
            dry();

            this.fight.getController().getConsoleInterface().getFightChoiceInterface().printNormalBattlefieldState();
        }
    }

    public void dry() {
        this.state = State.NORMAL;
        
        this.stater = null;
    }

    public boolean isFlooded() {
        return this.state == State.FLOODED;
    }

    public State getState() {
        return this.state;
    }

    public Pokemon getStater() {
        return this.stater;
    }
}
