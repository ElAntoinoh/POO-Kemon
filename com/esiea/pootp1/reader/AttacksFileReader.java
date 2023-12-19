package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.attacks.Attack;

public class AttacksFileReader {
    // Configuration file's key words
    private final static String START_ATTACK     = "Attack";
    private final static String END_ATTACK       = "EndAttack";
    private final static String NAME             = "Name";
    private final static String TYPE             = "Type";
    private final static String POWER            = "Power";
    private final static String NB_USE           = "NbUse";
    private final static String FAIL_PROBABILITY = "Fail";

    Controller controller;
    File file;

    public AttacksFileReader(Controller controller, String lien) {
        this.controller = controller;
        this.file = new File(lien);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file, StandardCharsets.UTF_8))) {
            String line;
            String[] words;

            Attack currentAttack = null;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                
                line = line.trim();

                words = line.split("\t");

                switch (words[0]) {
                    case START_ATTACK -> {
                        currentAttack = new Attack();
                    }

                    case END_ATTACK -> {
                        addAttack(currentAttack);
                    }

                    case NAME -> {
                        currentAttack.setName(words[1]);
                    }

                    case TYPE -> {
                        currentAttack.setType(Type.getTypeConfigText().get(words[1]));
                    }

                    case POWER -> {
                        currentAttack.setPower(Integer.parseInt(words[1]));
                    }

                    case NB_USE -> {
                        currentAttack.setMaxNbUses(Integer.parseInt(words[1]));
                    }

                    case FAIL_PROBABILITY -> {
                        currentAttack.setFailureProbability(Double.parseDouble(words[1]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAttack(Attack attack) {
        this.controller.addAttack(attack);
    }
}
