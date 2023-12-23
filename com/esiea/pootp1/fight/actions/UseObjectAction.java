package com.esiea.pootp1.fight.actions;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.bag.Item;
import com.esiea.pootp1.fight.player.team.members.Pokemon;

public class UseObjectAction extends Action {
    private Player player;

    private Pokemon target;

    private Item item;

    private UseObjectAction(Player player, Pokemon target, Item item) {
        this.player = player;
        this.target = target;
        this.item = item;
    }

    public static UseObjectAction createUseObjectAction(Player player, Pokemon target, Item item) {
        assert player.getAlivePokemons().contains(target);
        assert player.getBag().contains(item);

        return new UseObjectAction(player, target, item);
    }

    @Override
    public String activate() {
        return null;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "%s utilise %s sur %s !";

        System.out.format(
            format,
            this.player.getName(),
            item.getName(),
            target.getName()
        );

        this.player.getController().getConsoleInterface().waitForAction();
    }

    public Player getPlayer() {
        return this.player;
    }

    public Pokemon getTarget() {
        return this.target;
    }

    public Item getItem() {
        return this.item;
    }
}
