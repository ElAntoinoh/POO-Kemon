package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.GenericPokemon;

public class PokemonsFileReader {
    // Configuration file's key words
    private final static String START_MONSTER = "Monster";
    private final static String END_MONSTER   = "EndMonster";
    private final static String NAME          = "Name";
    private final static String TYPE          = "Type";
    private final static String HP            = "HP";
    private final static String ATTACK        = "Attack";
    private final static String DEFENSE       = "Defense";
    private final static String SPEED         = "Speed";

    Controller controller;
    File file;

    public PokemonsFileReader(Controller controller, String link) {
        this.controller = controller;
        this.file = new File(link);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file, StandardCharsets.UTF_8))) {
            String line;
            String[] words;

            GenericPokemon currentPokemon = null;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                
                line = line.trim();

                words = line.split("\t");

                switch (words[0]) {
                    case START_MONSTER -> {
                        currentPokemon = new GenericPokemon();
                    }

                    case END_MONSTER -> {
                        addPokemon(currentPokemon);
                    }

                    case NAME -> {
                        currentPokemon.setName(words[1]);
                    }

                    case TYPE -> {
                        Type type = Type.getTypeConfigText().get(words[1]);

                        currentPokemon.setType(Type.getUsableTypes().contains(type) ? type : null);
                    }

                    case HP -> {
                        int minHP = Integer.parseInt(words[1]);

                        int maxHP = words.length > 2 ? Integer.parseInt(words[2]) : minHP;

                        currentPokemon.setHP(minHP, maxHP);
                    }

                    case ATTACK -> {
                        int minAttack = Integer.parseInt(words[1]);

                        int maxAttack = words.length > 2 ? Integer.parseInt(words[2]) : minAttack;

                        currentPokemon.setAttack(minAttack, maxAttack);
                    }

                    case DEFENSE -> {
                        int minDefense = Integer.parseInt(words[1]);

                        int maxDefense = words.length > 2 ? Integer.parseInt(words[2]) : minDefense;

                        currentPokemon.setDefense(minDefense, maxDefense);
                    }

                    case SPEED -> {
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

    private void addPokemon(GenericPokemon pokemon) {
        this.controller.addPokemon(pokemon);
    }
}
