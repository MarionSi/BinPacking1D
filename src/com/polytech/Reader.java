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
//            line = br.readLine();
            line = br.readLine();
            optimisateur.setBinSize(Integer.parseInt(line.split(" ")[1]));

            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
                optimisateur.addItem(new Item(Integer.parseInt(line)));
            }

            System.out.println(optimisateur.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optimisateur;
    }
}
