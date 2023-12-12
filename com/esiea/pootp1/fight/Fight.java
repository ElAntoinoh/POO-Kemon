package com.esiea.pootp1.fight;

import com.esiea.pootp1.controller.Controller;

public class Fight {
    private Controller controller;

    public Fight(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return this.controller;
    }
}
