package com.esiea.pootp1.fight.player.bag;

import java.util.ArrayList;
import java.util.Random;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.models.consumables.Consumable;

public class Bag {
    public final static int MAXIMUM_NUMBER_OF_ITEMS = 5;

    private Player player;

    private ArrayList<Item> items = new ArrayList<>();

    public Bag(Player player) {
        this.player = player;

        fillVoidBag();

        setRandomItems();
    }

    private void fillVoidBag() {
        for (int i = 0; i < MAXIMUM_NUMBER_OF_ITEMS; i++) this.items.add(null);
    }

    public void setRandomItems() {
        ArrayList<Consumable> consumableList = this.player.getController().getGlobalBag().getConsumableList();

        Random random = new Random();
        
        for (int i = 0; i < MAXIMUM_NUMBER_OF_ITEMS; i++) {
            int index = random.nextInt(consumableList.size());

            items.set(i, new Item(consumableList.get(index)));
        }
    }

    public void setItems(ArrayList<Item> newItems) {
        assert newItems.size() == MAXIMUM_NUMBER_OF_ITEMS;

        for (int i = 0; i < MAXIMUM_NUMBER_OF_ITEMS; i++) {
            Item newItem = newItems.get(i);

            this.items.set(i, newItem);
        }
    }

    public int getLongestItemName() {
        return this.items.stream()
            .mapToInt(item -> item.getName().length())
            .max()
            .orElse(0);
    }

    public boolean contains(Item item) {
        return this.items.contains(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }
}