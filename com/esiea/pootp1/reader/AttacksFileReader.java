package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.attacks.Attack;

public class AttacksFileReader {
    private final static String START_ATTACK_KEY_WORD = "Attack";
    private final static String END_ATTACK_KEY_WORD   = "EndAttack";

    private final static String NAME_KEY_WORD               = "Name";
    private final static String TYPE_KEY_WORD               = "Type";
    private final static String POWER_KEY_WORD              = "Power";
    private final static String NB_USE_KEY_WORD             = "NbUse";
    private final static String FAIL_PROBABILITY_KEY_WORD   = "Fail";

    Controller controller;
    File file;

    public AttacksFileReader(Controller controller, String lien) {
        this.controller = controller;
        this.file = new File(lien);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            String[] words;

            Attack currentAttack = null;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                
                if (line.charAt(0) == '\t') line = line.substring(1);

                words = line.split("\t");

                switch (words[0]) {
                    case START_ATTACK_KEY_WORD -> {
                        currentAttack = new Attack();
                    }

                    case END_ATTACK_KEY_WORD -> {
                        addAttack(currentAttack);
                    }

                    case NAME_KEY_WORD -> {
                        currentAttack.setName(words[1]);
                    }

                    case TYPE_KEY_WORD -> {
                        Type type = null;

                        switch (words[1]) {
                            case "Earth"    -> type = Type.TERRE;
                            case "Electric" -> type = Type.FOUDRE;
                            case "Water"    -> type = Type.EAU;
                            case "Grass"    -> type = Type.HERBE;
                            case "Normal"   -> type = Type.NORMAL;
                        }

                        currentAttack.setType(type);
                    }

                    case POWER_KEY_WORD -> {
                        currentAttack.setPower(Integer.parseInt(words[1]));
                    }

                    case NB_USE_KEY_WORD -> {
                        currentAttack.setNbUtilisations(Integer.parseInt(words[1]));
                    }

                    case FAIL_PROBABILITY_KEY_WORD -> {
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
