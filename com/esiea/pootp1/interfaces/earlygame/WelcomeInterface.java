package com.esiea.pootp1.interfaces.earlygame;

import java.util.ArrayList;
import java.util.Arrays;

import com.esiea.pootp1.interfaces.ConsoleInterface;

public class WelcomeInterface {
    private final static String LOGO = """
     _____                                                           _____ 
    ( ___ )---------------------------------------------------------( ___ )
     |   |                                                           |   | 
     |   |  ____   ___   ___         _     __                        |   | 
     |   | |  _ \\ / _ \\ / _ \\       | | __/_/ _ __ ___   ___  _ __   |   | 
     |   | | |_) | | | | | | |  __  | |/ / _ \\ '_ ` _ \\ / _ \\| '_ \\  |   | 
     |   | |  __/| |_| | |_| | |__| |   <  __/ | | | | | (_) | | | | |   | 
     |   | |_|    \\___/ \\___/       |_|\\_\\___|_| |_| |_|\\___/|_| |_| |   | 
     |___|                                                           |___| 
    (_____)---------------------------------------------------------(_____)""";

    private final static String WELCOME_MESSAGE = "\nBONJOUR ET BIENVENUE DANS POO-KEMON !\nAppuyez sur \"entrée\" pour commencer à jouer ";

    private final static int LOGOS_COLUMNS_WIDTH = 7;

    private ConsoleInterface consoleInterface;

    public WelcomeInterface(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public void printWelcomeAnimation() {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(WelcomeInterface.LOGO.split("\n")));

        int longestLine = getLongestLine(lines);

        int printedStringLength = 0;

        while (printedStringLength + 2 * LOGOS_COLUMNS_WIDTH <= longestLine) {
            this.consoleInterface.clearConsole();

            String frame = "";

            for (String line : lines) {
                frame += line.substring(0, LOGOS_COLUMNS_WIDTH + printedStringLength) + line.substring(longestLine - LOGOS_COLUMNS_WIDTH) + '\n';
            }

            System.out.print(frame);

            try { Thread.sleep(25); } catch (InterruptedException e) { e.printStackTrace(); }

            printedStringLength++;
        }
    }

    private int getLongestLine(ArrayList<String> lines) {
        int nbCharMax = 0;

        for (String s : lines) {
            int length = s.length();

            if (length > nbCharMax) {
                nbCharMax = length;
            }
        }

        return nbCharMax;
    }

    public void printWelcomeMessage() {
        System.out.print(WELCOME_MESSAGE);
    }
}
