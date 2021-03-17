package com.polytech;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private final static String fileName0 = "rsc/binpack1d_00.txt";
    private static ArrayList<String> filesName = new ArrayList<>();


    public static void main(String[] args) {
        fillFileList();

        for (String file : filesName) {
            optimisateur optimisateur = Reader.readFileAsOptimisateur(file);
            System.out.println(file + " -> borneInf = " + optimisateur.getBorneInf());
        }



    }


    private static void fillFileList() {

        File[] files = new File("rsc").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                filesName.add("rsc/"+file.getName());
            }
        }
    }
}
