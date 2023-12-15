package com.esiea.pootp1.fight.player.bag;

import java.util.ArrayList;
import java.util.Collections;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.models.consumables.Consumable;

public class Bag {
    public final static int MAXIMUM_NUMBER_OF_ITEMS = 5;

    private Player player;

    private ArrayList<Item> items = new ArrayList<>();

    public Bag(Player player) {
        this.player = player;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_ITEMS; i++) this.items.add(null);

        setRandomItems();
    }

    public void setRandomItems() {
        ArrayList<Consumable> consumableList = this.player.getController().getGlobalBag().getConsumableList();

        Collections.shuffle(consumableList);

        for (int i = 0; i < MAXIMUM_NUMBER_OF_ITEMS; i++) {
            items.set(i, new Item(this, consumableList.get(i)));
        }
    }

    public void setItems(ArrayList<Item> newItems) {
        assert newItems.size() == MAXIMUM_NUMBER_OF_ITEMS;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_ITEMS; i++) {
            Item newItem = newItems.get(i);

            newItem.setBag(this);
            this.items.set(i, newItem);
        }
    }

    public String toString() {
        String str = "Sac :\n";

        for (Item i : this.items) str += i.toString() + '\n';

        return str;
    }
}