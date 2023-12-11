package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.Pokemon;

public class PokemonsFileReader {
    private final static String START_MONSTER_KEY_WORD = "Monster";
    private final static String END_MONSTER_KEY_WORD   = "EndMonster";

    private final static String NAME_KEY_WORD    = "Name";
    private final static String TYPE_KEY_WORD    = "Type";
    private final static String HP_KEY_WORD      = "HP";
    private final static String ATTACK_KEY_WORD  = "Attack";
    private final static String DEFENSE_KEY_WORD = "Defense";
    private final static String SPEED_KEY_WORD   = "Speed";

    Controller controller;
    File file;

    public PokemonsFileReader(Controller controller, String lien) {
        this.controller = controller;
        this.file = new File(lien);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            String[] words;

            Pokemon currentPokemon = null;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                
                if (line.charAt(0) == '\t') line = line.substring(1);

                words = line.split("\t");

                switch (words[0]) {
                    case START_MONSTER_KEY_WORD -> {
                        currentPokemon = new Pokemon();
                    }

                    case END_MONSTER_KEY_WORD -> {
                        addPokemon(currentPokemon);
                    }

                    case NAME_KEY_WORD -> {
                        currentPokemon.setName(words[1]);
                    }

                    case TYPE_KEY_WORD -> {
                        Type type = null;

                        switch (words[1]) {
                            case "Earth"    -> type = Type.TERRE;
                            case "Electric" -> type = Type.FOUDRE;
                            case "Water"    -> type = Type.EAU;
                            case "Grass"    -> type = Type.HERBE;
                        }

                        currentPokemon.setType(type);
                    }

                    case HP_KEY_WORD -> {
                        int minHP = Integer.parseInt(words[1]);

                        int maxHP = words.length > 2 ? Integer.parseInt(words[2]) : minHP;

                        currentPokemon.setHP(minHP, maxHP);
                    }

                    case ATTACK_KEY_WORD -> {
                        int minAttack = Integer.parseInt(words[1]);

                        int maxAttack = words.length > 2 ? Integer.parseInt(words[2]) : minAttack;

                        currentPokemon.setAttack(minAttack, maxAttack);
                    }

                    case DEFENSE_KEY_WORD -> {
                        int minDefense = Integer.parseInt(words[1]);

                        int maxDefense = words.length > 2 ? Integer.parseInt(words[2]) : minDefense;

                        currentPokemon.setDefense(minDefense, maxDefense);
                    }

                    case SPEED_KEY_WORD -> {
                        int minSpeed = Integer.parseInt(words[1]);

                        int maxSpeed = words.length > 2 ? Integer.parseInt(words[2]) : minSpeed;

                        currentPokemon.setSpeed(minSpeed, maxSpeed);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPokemon(Pokemon pokemon) {
        this.controller.addPokemon(pokemon);
    }
}
