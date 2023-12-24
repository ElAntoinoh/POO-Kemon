package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import com.esiea.pootp1.controller.Controller;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.GenericPokemon;
import com.esiea.pootp1.models.pokemons.attributes.Attributes;
import com.esiea.pootp1.models.pokemons.attributes.types.DirtAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.ElecAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.FireAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.InsectAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.PlantAttributes;
import com.esiea.pootp1.models.pokemons.attributes.types.WaterAttributes;

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

    private final static String HIDE      = "Hide";
    private final static String PARALYSIS = "Paralysis";
    private final static String BURN      = "Burn";
    private final static String POISON    = "Poison";
    private final static String HEAL      = "Heal";
    private final static String FLOOD     = "Flood";
    private final static String FALL      = "Fall";

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

            Attributes attributes = null;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) continue;
                
                line = line.trim();

                words = line.split("\t");

                switch (words[0]) {
                    case START_MONSTER -> {
                        currentPokemon = new GenericPokemon();
                    }

                    case END_MONSTER -> {
                        currentPokemon.setTypeAttributes(attributes);
                        addPokemon(currentPokemon);
                    }

                    case NAME -> {
                        currentPokemon.setName(words[1]);
                    }

                    case TYPE -> {
                        Type type = Type.getTypeConfigText().get(words[1]);

                        switch (type) {
                            case DIRT   -> attributes = new DirtAttributes  ();
                            case ELEC   -> attributes = new ElecAttributes  ();
                            case FIRE   -> attributes = new FireAttributes  ();
                            case INSECT -> attributes = new InsectAttributes();
                            case PLANT  -> attributes = new PlantAttributes ();
                            case WATER  -> attributes = new WaterAttributes ();
                        }

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

                    case HIDE -> {
                        ((DirtAttributes) attributes).setHide(Double.parseDouble(words[1]));
                    }

                    case PARALYSIS -> {
                        ((ElecAttributes) attributes).setParalysis(Double.parseDouble(words[1]));
                    }

                    case BURN -> {
                        ((FireAttributes) attributes).setBurn(Double.parseDouble(words[1]));
                    }

                    case POISON -> {
                        ((InsectAttributes) attributes).setPoison(Double.parseDouble(words[1]));
                    }

                    case HEAL -> {
                        ((PlantAttributes) attributes).setHeal(Double.parseDouble(words[1]));
                    }

                    case FLOOD -> {
                        ((WaterAttributes) attributes).setFlood(Double.parseDouble(words[1]));
                    }

                    case FALL -> {
                        ((WaterAttributes) attributes).setFall(Double.parseDouble(words[1]));
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
