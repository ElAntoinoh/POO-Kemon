package com.esiea.pootp1.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.esiea.pootp1.controller.Controller;

public class AttacksFileReader {
    Controller controller;
    File file;

    public AttacksFileReader(Controller controller, String lien) {
        this.controller = controller;
        this.file = new File(lien);
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;

            while ((line = br.readLine()) != null) {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
