package com.esiea.pootp1.interfaces.earlygame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.esiea.pootp1.fight.player.Player;
import com.esiea.pootp1.fight.player.team.Team;
import com.esiea.pootp1.fight.player.team.members.Pokemon;
import com.esiea.pootp1.interfaces.ConsoleInterface;
import com.esiea.pootp1.models.Type;
import com.esiea.pootp1.models.pokemons.GenericPokemon;

public class TeamBuildingInterface {
    private final static int NUMBER_OF_POKEMONS_PER_PAGE = 10;

    private final static String REGEX_IS_NUMERIC = "^\\d+$";
    private final static String REGEX_GO_TO_PAGE = "^P\\d+$";

    private final static Pattern PATTERN_IS_NUMERIC = Pattern.compile(REGEX_IS_NUMERIC);
    private final static Pattern PATTERN_GO_TO_PAGE = Pattern.compile(REGEX_GO_TO_PAGE);

    private ConsoleInterface consoleInterface;

    public TeamBuildingInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void askTeam(Player player) {
        printTypeOfTeamChoice(player);

        Scanner scanner = this.consoleInterface.getScanner();

        int choice;

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2) break;
            }

            scanner.nextLine();

            printTypeOfTeamChoice(player);
        }

        switch (choice) {
            case 1 -> player.getTeam().setRandomMembers();
            case 2 -> player.getTeam().setMembers(askMembers(player.getTeam()));
        }
    }

    private void printTypeOfTeamChoice(Player player) {
        this.consoleInterface.clearConsole();
        this.consoleInterface.printWelcomePlayer(player);

        String str = "\nChoisis ton équipe de combat !\n\n";
        
        str += "[1] Équipe aléatoire\n";
        str += "[2] Équipe personalisée\n";

        str += "\nChoix : ";

        System.out.print(str);
    }

    private ArrayList<Pokemon> askMembers(Team team) {
        ArrayList<Pokemon> members = new ArrayList<>();

        for (int i = 0; i < Team.MAXIMUM_NUMBER_OF_MEMBERS; i++) {
            members.add(askMember(team));
        }

        return members;
    }

    private Pokemon askMember(Team team) {
        Scanner scanner = this.consoleInterface.getScanner();

        ArrayList<GenericPokemon> pokemonsList = this.consoleInterface.getController().getPokeDex().getPokemonList();

        int numPage = 0;
        int nbPages = pokemonsList.size() / NUMBER_OF_POKEMONS_PER_PAGE + 1;

        String input = null;

        while (scanner.hasNextLine()) {
            this.consoleInterface.clearConsole();

            printMemberChoiceHelp();
            printPokemonListPage(pokemonsList, numPage, nbPages);

            input = scanner.next();

            if (PATTERN_IS_NUMERIC.matcher(input).matches()) {
                int intInput = Integer.parseInt(input);

                if (intInput >= 0 && intInput < pokemonsList.size() + 1) {
                    if (intInput == 0) {
                        intInput = (int) (Math.random() * (pokemonsList.size() - 1)) + 1;
                        input = Integer.toString(intInput);
                    }

                    break;
                }
            }
            
            else if (PATTERN_GO_TO_PAGE.matcher(input).matches()) {
                int indicePage = Integer.parseInt(input.substring(1));

                if (indicePage > 0 && indicePage - 1 < nbPages) numPage = indicePage - 1;
            }
            
            else if (input.equals("p")) numPage -= numPage == 0           ? 0 : 1;
            else if (input.equals("s")) numPage += numPage == nbPages - 1 ? 0 : 1;
        }

        return new Pokemon(team, pokemonsList.get(Integer.parseInt(input) - 1));
    }

    private void printMemberChoiceHelp() {
        String str = "Choisissez un pokémon en entrant son numéro\n\n";

        str += String.format("%-24s : %s\n", "Afficher page n"         , "Pn");
        str += String.format("%-24s : %s\n", "Afficher page précédente", "p" );
        str += String.format("%-24s : %s\n", "Afficher page suivante"  , "s" );
        str += String.format("%-24s : %s\n", "Pokémon aléatoire"       , "0" );

        System.out.println(str);
    }

    private void printPokemonListPage(ArrayList<GenericPokemon> pokemonsList, int numPage, int nbPages) {
        for (int i = numPage * NUMBER_OF_POKEMONS_PER_PAGE; i < (numPage + 1) * NUMBER_OF_POKEMONS_PER_PAGE && i < pokemonsList.size(); i++) {
            GenericPokemon pokemon = pokemonsList.get(i);

            int nbCharNum = Integer.toString(pokemonsList.size()).length();
            int nbCharName = this.consoleInterface.getController().getPokeDex().getMaxNameLength();

            int pokemonNum = i + 1;
            String pokemonName = pokemon.getName();
            String pokemonType = Type.getTypeDisplayText().get(pokemon.getType());

            System.out.format("[%" + nbCharNum + "d] %-" + nbCharName + "s | %s\n", pokemonNum, pokemonName, pokemonType);
        }

        System.out.format("\nPage (%d/%d)\n\n", numPage + 1, nbPages);
    }
}
