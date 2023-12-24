package com.esiea.pootp1.fight.actions;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.bag.Item;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.models.consumables.healings.Medecine;
import com.esiea.pootp1.models.consumables.healings.Potion;

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
        String sRet = null;

        this.player.getBag().getItems().remove(item);

        if (this.item.getConsumable() instanceof Potion) {
            this.target.heal(((Potion) this.item.getConsumable()).getPower());

            sRet = String.format("Les points de vie de %s ont augmentés", this.target.getName());
        }

        else {
            Status curedStatus = ((Medecine) this.item.getConsumable()).getStatus();

            if (this.target.getStatus() == curedStatus) {
                this.target.setStatus(Status.NORMAL);
                
                sRet = String.format("%s n'est plus %s", this.target.getName(), Status.getStatsDisplayText().get(curedStatus));
            } else {
                sRet = "Ce n'était pas très utile...";
            }
        }

        return sRet;
    }

    @Override
    public void print(String bonusInformation) {
        String format = "%s utilise %s sur %s !";

        if (bonusInformation != null) format += "\n%s";

        System.out.format(
            format,
            this.player.getName(),
            this.item.getName(),
            this.target.getName(),
            bonusInformation
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
