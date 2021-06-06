package com.polytech;

import com.polytech.algorithm.ProgrammationLineaire;
import com.polytech.algorithm.RecuitSimule;
import com.polytech.utils.Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

public class Main {

    private final static String fileName0 = "rsc/binpack1d_01.txt";
    private final static String ROOT_RSC = "rsc/";
    private static ArrayList<String> filesName;
/*
    static {
        System.loadLibrary("jniortools");
    }
*/

    public static void main(String[] args) throws CloneNotSupportedException {
        fillFileList();
//        ProgrammationLineaire programmationLineaire = new ProgrammationLineaire();
//        Solution solution = programmationLineaire.generateSolution(null, -1);

        for (String file : filesName) {
            optimisateur optimisateur = Reader.readFileAsOptimisateur(file);
            System.out.println(file);
//            optimisateur.printItemsDetails();
            optimisateur.generateSolutionFromProrammationLineaire();
            System.out.println("\n");

//            System.out.println("BorneInf = " + optimisateur.getLowerBound());


//            System.out.println("----------------------- RECUIT SIMULE DECREASING -----------------------\n");
//            Solution meilleureSolution = optimisateur.generateSolutionFromRecuitSimule(0.2);
//
//            System.out.println("La meilleure solution c'est " + meilleureSolution.toString() + "\n");


//            long startTime = System.currentTimeMillis();
//            Solution solutionFirstFitDecreasing = optimisateur.generateSolutionFromFirstFitDecreasingAlgorithm();
//            long endTime = System.currentTimeMillis();
//            System.out.println(solutionFirstFitDecreasing.toString() + "\n");
//            System.out.println("Solution générée en " + Math.abs(startTime - endTime) + " ms!");

//
//            Solution solutionFromOneBinPerItem = optimisateur.generateSolutionFromOneBinPerItem();
//            System.out.println(solutionFromOneBinPerItem.toString() + "\n");
//
//            Solution solutionFirstFit = optimisateur.generateSolutionFromFirstFitAlgorithm();
//            System.out.println(solutionFirstFit.toString() + "\n");


            //System.out.println("borneInf = " + optimisateur.getLowerBound() + "\n");

            /*

            System.out.println("----------------------- FIRST FIT DECREASING -----------------------\n");

            Solution solutionFirstFitDecreasing = optimisateur.generateSolutionFromFirstFitDecreasingAlgorithm();
            System.out.println("Fitness : " + solutionFirstFitDecreasing.getFitness());
            System.out.println(solutionFirstFitDecreasing.toString() + "\n");

            System.out.println("----------------------- ONE ITEM PER BIN DECREASING -----------------------\n");

            Solution solutionFromOneBinPerItem = optimisateur.generateSolutionFromOneBinPerItem();
            System.out.println("Fitness : " + solutionFromOneBinPerItem.getFitness());
            System.out.println(solutionFromOneBinPerItem.toString() + "\n");

            System.out.println("----------------------- FIRST FIT -----------------------\n");

            Solution solutionFirstFit = optimisateur.generateSolutionFromFirstFitAlgorithm();
            System.out.println("Fitness : " + solutionFirstFit.getFitness());
            System.out.println(solutionFirstFit.toString() + "\n");

             */


//            System.out.println("----------------------- TABU SEARCH -----------------------\n");
//
//            Solution solutionTabu = optimisateur.generateSolutionFromTabuSearchAlgorithm();
//            System.out.println("Fitness : " + solutionTabu.getFitness());
//            System.out.println(solutionTabu.toString() + "\n");
            //System.out.println(solutionTabu.fullSolution() + "\n");
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
