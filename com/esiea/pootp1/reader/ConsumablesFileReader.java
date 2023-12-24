package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.fight.battlefield.State;
import com.esiea.pootp1.fight.player.team.members.Status;
import com.esiea.pootp1.models.consumables.Consumable;
import com.esiea.pootp1.models.consumables.types.Medecine;
import com.esiea.pootp1.models.consumables.types.Potion;
import com.esiea.pootp1.models.consumables.types.Terrainizer;

public class ConsumablesFileReader {
    // Configuration file's key words
    private final static String START_CONSUMABLE = "Consumable";
    private final static String END_CONSUMABLE   = "EndConsumable";
    private final static String NAME             = "Name";
    private final static String TYPE             = "Type";
    private final static String QUANTITY         = "Quantity";

    private final static String POWER = "Power";

    private final static String STATUS = "Status";

    private final static String STATE = "State";

    private Controller controller;
    private File file;

    public ConsumablesFileReader(Controller controller, String link) {
        this.controller = controller;
        this.file = new File(link);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file, StandardCharsets.UTF_8))) {
            String line;
            String[] words;

            Consumable consumable = null;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                
                line = line.trim();

                words = line.split("\t");

                switch (words[0]) {
                    case START_CONSUMABLE -> {
                        consumable = new Consumable();
                    }

                    case END_CONSUMABLE -> {
                        addConsumable(consumable);
                    }

                    case NAME -> {
                        consumable.setName(words[1]);
                    }

                    case TYPE -> {
                        switch (words[1]) {
                            case "Potion"      -> consumable = new Potion     (consumable);
                            case "Medecine"    -> consumable = new Medecine   (consumable);
                            case "Terrainizer" -> consumable = new Terrainizer(consumable);
                        }
                    }

                    case QUANTITY -> {
                        consumable.setQuantity(Integer.parseInt(words[1]));
                    }

                    case POWER -> {
                        ((Potion) consumable).setPower(Integer.parseInt(words[1]));
                    }

                    case STATUS -> {
                        ((Medecine) consumable).setStatus(Status.getStatusConfigText().get(words[1]));
                    }

                    case STATE -> {
                        ((Terrainizer) consumable).setState(State.getStateConfigText().get(words[1]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addConsumable(Consumable consumable) {
        this.controller.addConsumable(consumable);
    }
}