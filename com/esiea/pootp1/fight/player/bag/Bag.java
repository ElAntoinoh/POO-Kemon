package com.esiea.pootp1.fight.player.bag;

import java.util.ArrayList;
import java.util.Collections;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.models.consumables.Consumable;

public class Bag {
    private final static int MAXIMUM_NUMBER_OF_ELEMENTS = 5;

    private Player player;

    private ArrayList<Item> elements = new ArrayList<>();

    public Bag(Player player) {
        this.player = player;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS; i++) this.elements.add(null);

        setRandomElements();
    }

    public void setRandomElements() {
        ArrayList<Consumable> consumableList = this.player.getController().getGlobalBag().getConsumableList();

        Collections.shuffle(consumableList);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_ELEMENTS; i++) {
            elements.set(i, new Item(consumableList.get(i)));
        }
    }

    public String toString() {
        String str = "Sac :\n";

        for (Item i : this.elements) str += i.toString() + '\n';

        return str;
    }
}