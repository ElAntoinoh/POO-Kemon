package com.esiea.pootp1.models.consumables;

import java.util.ArrayList;

public class GlobalBag {
    private ArrayList<Consumable> consumableList = new ArrayList<>();

    public void addConsumable(Consumable consumable) {
        for (int i = 0; i < consumable.getQuantity(); i++) {
            this.consumableList.add(consumable);
        }
    }

    public ArrayList<Consumable> getConsumableList() {
        return this.consumableList;
    }
}