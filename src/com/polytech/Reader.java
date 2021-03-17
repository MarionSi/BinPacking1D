package com.polytech;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static optimisateur readFileAsOptimisateur(String filename) {
        optimisateur optimisateur = new optimisateur();

        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            line = br.readLine();
            optimisateur.setBinSize(Integer.parseInt(line.split(" ")[0]));

            while ((line = br.readLine()) != null)
            {
                optimisateur.addItem(new Item(Integer.parseInt(line)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optimisateur;
    }
}
