package com.polytech.utils;

import com.polytech.Item;
import com.polytech.Optimisator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static Optimisator readFileAsOptimisateur(String filename) {
        Optimisator optimisator = new Optimisator();

        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            line = br.readLine();
            optimisator.setBinSize(Integer.parseInt(line.split(" ")[0]));

            while ((line = br.readLine()) != null)
            {
                optimisator.addItem(new Item(Integer.parseInt(line)));
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optimisator;
    }
}
