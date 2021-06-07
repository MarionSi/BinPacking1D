package com.polytech;

import com.polytech.utils.Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final static String ROOT_RSC = "rsc/";
    private static ArrayList<String> filesName;

    public static void main(String[] args) {
        int choice;
        boolean stop = false;
        while(!stop) {
            System.out.println("================================================");
            System.out.println("|           Projet de bin packing 1D           |");
            System.out.println("================================================\n");
            System.out.println("-----> Quel algorithme voulez-vous exécuter ?");
            System.out.println("1° - First Fit Decreasing ");
            System.out.println("2° - First Fit");
            System.out.println("3° - Programmation linéaire");
            System.out.println("4° - Un item par bin");
            System.out.println("5° - Recuit simulé");
            System.out.println("6° - Recherche Tabou");
            System.out.println("0° - Quitter");

            Scanner keyboard = new Scanner(System.in);
            try {
                choice = keyboard.nextInt();

                switch(choice) {
                    case 0: stop = true;
                    break;

                    case 1:
                        fileChoiceMenu();
                        int menuChoice_1 = keyboard.nextInt();
                        if(menuChoice_1 == 0) {
                            stop = true;
                        }
                        else {
                            printMenuFirstFitDecreasing(menuChoice_1, keyboard);
                        }
                        break;

                    case 2:
                        fileChoiceMenu();
                        int menuChoice_2 = keyboard.nextInt();
                        if(menuChoice_2 == 0) {
                            stop = true;
                        }
                        else {
                            printMenuFirstFit(menuChoice_2, keyboard);
                        }
                        break;

                    case 3:
                        fileChoiceMenu();
                        int menuChoice_3 = keyboard.nextInt();
                        if(menuChoice_3 == 0) {
                            stop = true;
                        }
                        else {
                            printMenuLinearProgramming(menuChoice_3, keyboard);
                        }
                        break;

                    case 4:
                        fileChoiceMenu();
                        int menuChoice_4 = keyboard.nextInt();
                        if(menuChoice_4 == 0) {
                            stop = true;
                        }
                        else {
                            printMenuOneBinPerItem(menuChoice_4, keyboard);
                        }
                        break;

                    case 5:
                        fileChoiceMenu();
                        int menuChoice_5 = keyboard.nextInt();
                        if(menuChoice_5 == 0) {
                            stop = true;
                        }
                        else {
                            printMenuSimulatedAnnealing(menuChoice_5, keyboard);
                        }
                    break;
                    
                    case 6:
                        fileChoiceMenu();
                        int menuChoice_6 = keyboard.nextInt();
                        if(menuChoice_6 == 0) {
                            stop = true;
                        }
                        else {
                            printMenuTabuSearch(menuChoice_6, keyboard);
                        }
                    break;

                    default: continue;
                }

            } catch (Exception e) {
                continue;
            }
        }
    }



    public  static void printMenuFirstFitDecreasing(int choice, Scanner keyboard) {
        if(choice == 1) {
            fillFileList();
            for (String filename : filesName
            ) {
                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- FIRST FIT DECREASING -----------------------\n");

                Solution solutionFirstFitDecreasing = optimisator.generateSolutionFromFirstFitDecreasingAlgorithm();
                System.out.println("Fitness : " + solutionFirstFitDecreasing.getFitness());
                System.out.println(solutionFirstFitDecreasing.toString() + "\n");
            }
        } else {
            if(choice == 2) {
                String filename;
                fileMenu();
                int fileNumber = keyboard.nextInt();
                filename = getFilename(fileNumber);

                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- FIRST FIT DECREASING -----------------------\n");

                Solution solutionFirstFitDecreasing = optimisator.generateSolutionFromFirstFitDecreasingAlgorithm();
                System.out.println("Fitness : " + solutionFirstFitDecreasing.getFitness());
                System.out.println(solutionFirstFitDecreasing.toString() + "\n");

            } else {
                System.out.println("Veuillez saisir un choix valide");
            }
        }

    }

    public  static void printMenuFirstFit(int choice, Scanner keyboard) {
        if(choice == 1) {
            fillFileList();
            for (String filename : filesName
            ) {
                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- FIRST FIT -----------------------\n");

                Solution solutionFirstFit = optimisator.generateSolutionFromFirstFitAlgorithm();
                System.out.println("Fitness : " + solutionFirstFit.getFitness());
                System.out.println(solutionFirstFit.toString() + "\n");
            }
        } else {
            if(choice == 2) {
                String filename;
                fileMenu();
                int fileNumber = keyboard.nextInt();
                filename = getFilename(fileNumber);

                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- FIRST FIT -----------------------\n");

                Solution solutionFirstFit = optimisator.generateSolutionFromFirstFitAlgorithm();
                System.out.println("Fitness : " + solutionFirstFit.getFitness());
                System.out.println(solutionFirstFit.toString() + "\n");

            } else {
                System.out.println("Veuillez saisir un choix valide");
            }
        }
    }
    
    public static void printMenuLinearProgramming(int choice, Scanner keyboard) throws CloneNotSupportedException {
        if(choice == 1) {
            fillFileList();
            for (String filename : filesName
            ) {
                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- PROGRAMMATION LINEAIRE -----------------------\n");

                Solution solutionLP = optimisator.generateSolutionFromLinearProgramming();
                System.out.println("Fitness : " + solutionLP.getFitness());
                System.out.println(solutionLP.toString() + "\n");
            }
        } else {
            if(choice == 2) {
                String filename;
                fileMenu();
                int fileNumber = keyboard.nextInt();
                filename = getFilename(fileNumber);

                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- PROGRAMMATION LINEAIRE -----------------------\n");

                Solution solutionLP = optimisator.generateSolutionFromLinearProgramming();
                System.out.println("Fitness : " + solutionLP.getFitness());
                System.out.println(solutionLP.toString() + "\n");

            } else {
                System.out.println("Veuillez saisir un choix valide");
            }
        }
    }

    public  static void printMenuOneBinPerItem(int choice, Scanner keyboard) {
        if(choice == 1) {
            fillFileList();
            for (String filename : filesName
            ) {
                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- UN ITEM PAR BIN -----------------------\n");

                Solution solutionOBPI = optimisator.generateSolutionFromOneBinPerItem();
                System.out.println("Fitness : " + solutionOBPI.getFitness());
                System.out.println(solutionOBPI.toString() + "\n");
            }
        } else {
            if(choice == 2) {
                String filename;
                fileMenu();
                int fileNumber = keyboard.nextInt();
                filename = getFilename(fileNumber);

                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- UN ITEM PAR BIN -----------------------\n");

                Solution solutionOBPI = optimisator.generateSolutionFromOneBinPerItem();
                System.out.println("Fitness : " + solutionOBPI.getFitness());
                System.out.println(solutionOBPI.toString() + "\n");

            } else {
                System.out.println("Veuillez saisir un choix valide");
            }
        }
    }

    public  static void printMenuSimulatedAnnealing(int choice, Scanner keyboard) throws CloneNotSupportedException {
        if(choice == 1) {
            fillFileList();
            for (String filename : filesName
            ) {
                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- RECUIT SIMULE -----------------------\n");

                Solution solutionSimulatedAnnealing = optimisator.generateSolutionFromSimulatedAnnealing(0.4, optimisator.generateSolutionFromOneBinPerItem());
                System.out.println("Fitness : " + solutionSimulatedAnnealing.getFitness());
                System.out.println(solutionSimulatedAnnealing.toString() + "\n");
            }
        } else {
            if(choice == 2) {
                String filename;
                fileMenu();
                int fileNumber = keyboard.nextInt();
                filename = getFilename(fileNumber);

                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- RECUIT SIMULE -----------------------\n");

                Solution solutionSimulatedAnnealing = optimisator.generateSolutionFromSimulatedAnnealing(0.4, optimisator.generateSolutionFromOneBinPerItem());
                System.out.println("Fitness : " + solutionSimulatedAnnealing.getFitness());
                System.out.println(solutionSimulatedAnnealing.toString() + "\n");

            } else {
                System.out.println("Veuillez saisir un choix valide");
            }
        }
    }

    public  static void printMenuTabuSearch(int choice, Scanner keyboard) throws CloneNotSupportedException {
        if(choice == 1) {
            fillFileList();
            for (String filename : filesName
            ) {
                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- METHODE TABOU -----------------------\n");

                Solution solutionTabuSearch = optimisator.generateSolutionFromTabuSearchAlgorithm(5, 1000, 50);
                System.out.println("Fitness : " + solutionTabuSearch.getFitness());
                System.out.println(solutionTabuSearch.toString() + "\n");
            }
        } else {
            if(choice == 2) {
                String filename;
                fileMenu();
                int fileNumber = keyboard.nextInt();
                filename = getFilename(fileNumber);

                Optimisator optimisator = Reader.readFileAsOptimisateur(filename);
                System.out.println("Fichier " + filename);
                System.out.println("----------------------- METHODE TABOU -----------------------\n");

                Solution solutionTabuSearch = optimisator.generateSolutionFromTabuSearchAlgorithm(5, 1000, 50);
                System.out.println("Fitness : " + solutionTabuSearch.getFitness());
                System.out.println(solutionTabuSearch.toString() + "\n");

            } else {
                System.out.println("Veuillez saisir un choix valide");
            }
        }
    }



    public static void fillFileList() {

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

    public static void fileChoiceMenu() {
        System.out.println("\nVoulez-vous générer des solutions pour tous les fichiers ou pour un seul ?");
        System.out.println("1° - Tous");
        System.out.println("2° - Un seul");
        System.out.println("0° - Quitter");
    }

    public static void fileMenu() {
        fillFileList();
        for(int j = 0; j < filesName.size(); j++) {
            System.out.println(j+1 +"°- Fichier " + filesName.get(j));
        }
    }

    public static String getFilename(Integer choice) {
        String filename = "rsc/binpack1d_00.txt";
        try {
            switch (choice) {
                case 1: filename = "rsc/binpack1d_00.txt";
                    break;

                case 2: filename = "rsc/binpack1d_01.txt";
                    break;

                case 3: filename = "rsc/binpack1d_02.txt";
                    break;

                case 4: filename = "rsc/binpack1d_03.txt";
                    break;

                case 5: filename = "rsc/binpack1d_04.txt";
                    break;

                case 6: filename = "rsc/binpack1d_05.txt";
                    break;

                case 7: filename = "rsc/binpack1d_06.txt";
                    break;

                case 8: filename = "rsc/binpack1d_11.txt";
                    break;

                case 9: filename = "rsc/binpack1d_12.txt";
                    break;

                case 10: filename = "rsc/binpack1d_13.txt";
                    break;

                case 11: filename = "rsc/binpack1d_14.txt";
                    break;

                case 12: filename = "rsc/binpack1d_21.txt";
                    break;

                case 13: filename = "rsc/binpack1d_31.txt";
                    break;

            }
        } catch(Exception e) {
            e.getMessage();
        }
        return filename;
    }

}
