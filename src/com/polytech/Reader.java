package com.polytech;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static optimisateur readFileAsOptimisateur() {
        optimisateur optimisateur = new optimisateur();

        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("rsc/binpack1d_001.txt"));
            line = br.readLine();
            System.out.println(line);

            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optimisateur;
    }
}
