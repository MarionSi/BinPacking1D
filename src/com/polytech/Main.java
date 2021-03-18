package com.polytech;

import com.polytech.utils.Reader;

import java.io.File;
import java.util.ArrayList;

public class Main {

    private final static String fileName0 = "rsc/binpack1d_00.txt";
    private final static String ROOT_RSC = "rsc/";
//    private final static String ROOT_RSC = "";
    private static ArrayList<String> filesName;


    public static void main(String[] args) {
        fillFileList();

        for (String file : filesName) {
            optimisateur optimisateur = Reader.readFileAsOptimisateur(file);
            System.out.println(file);
            System.out.println("borneInf = " + optimisateur.getLowerBound());

            Solution solutionFirstFitDecreasing = optimisateur.generateSolutionFromFirstFitDecreasingAlgorithm();
            System.out.println(solutionFirstFitDecreasing.toString() + "\n");
        }

    }


    private static void fillFileList() {

        filesName = new ArrayList<>();

        File[] files = new File(ROOT_RSC).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filesName.add(ROOT_RSC+file.getName());
                }
            }
        }
        else
        {
            System.err.println("Ressources files not found");
        }
    }
}
