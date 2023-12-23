package com.esiea.pootp1.interfaces.endgame;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.interfaces.ConsoleInterface;

public class FightResultInterface {
    private ConsoleInterface consoleInterface;

    public FightResultInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void print(Player player) {
        this.consoleInterface.clearConsole();

        System.out.format("Félicitation %s (%d) !!!\n", player.getName(), player.getNum());

        this.consoleInterface.waitForAction();
    }
}
