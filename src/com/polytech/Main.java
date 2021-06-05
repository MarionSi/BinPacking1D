package com.polytech;

import com.polytech.algorithm.ProgrammationLineaire;
import com.polytech.algorithm.RecuitSimule;
import com.polytech.utils.Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private final static String fileName0 = "rsc/binpack1d_00.txt";
    private final static String ROOT_RSC = "rsc/";
    private static ArrayList<String> filesName;

    static {
        System.loadLibrary("jniortools");
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        fillFileList();
        ProgrammationLineaire programmationLineaire = new ProgrammationLineaire();
        Solution solution = programmationLineaire.generateSolution(null, -1);

        for (String file : filesName) {
            optimisateur optimisateur = Reader.readFileAsOptimisateur(file);
            System.out.println(file);
            System.out.println("borneInf = " + optimisateur.getLowerBound());

            Solution meilleureSolution = optimisateur.generateSolutionFromRecuitSimule(0.6);

            System.out.println("La meilleure solution c'est " + meilleureSolution.toString() +"\n");
//            Solution solutionFirstFitDecreasing = optimisateur.generateSolutionFromFirstFitDecreasingAlgorithm();
//            System.out.println(solutionFirstFitDecreasing.toString() + "\n");
//
//            Solution solutionFromOneBinPerItem = optimisateur.generateSolutionFromOneBinPerItem();
//            System.out.println(solutionFromOneBinPerItem.toString() + "\n");
//
//            Solution solutionFirstFit = optimisateur.generateSolutionFromFirstFitAlgorithm();
//            System.out.println(solutionFirstFit.toString() + "\n");
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
