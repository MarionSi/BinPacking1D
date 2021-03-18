package com.polytech.utils;

import com.polytech.Item;
import com.polytech.optimisateur;

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

            while ((line = br.readLine()) != null)
            {
                optimisateur.addItem(new Item(Integer.parseInt(line)));
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optimisateur;
    }
}
