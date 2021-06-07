package com.polytech;

import com.polytech.Comparator.ItemComparator;
import com.polytech.algorithm.FirstFitDecreasing;
import com.polytech.algorithm.ProgrammationLineaire;
import com.polytech.algorithm.Question4.FirstFit;
import com.polytech.algorithm.Question4.OneBinPerItem;

import com.polytech.algorithm.RecuitSimule;

import com.polytech.algorithm.Question7.TabuSearch;


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class optimisateur {

    private int binSize = 150;

    private LinkedList<Item> listItems;

    public optimisateur() {
        listItems = new LinkedList<>();
    }

    public optimisateur(int binSize) {
        listItems = new LinkedList<>();
        this.binSize = binSize;
    }

    /**
     * Method used to get the lower bound  (?)
     * @return
     */
    public int getLowerBound() {

        int sommeObjets = 0;

        for (Item item : listItems) {
            sommeObjets += item.getSize();
        }

        return (int) Math.ceil((float) sommeObjets / (float) binSize);
    }

    public int getBinSize() {
        return binSize;
    }

    public Solution generateSolutionFromFirstFitDecreasingAlgorithm() {
        FirstFitDecreasing firstFitDecreasingAlgorithm = new FirstFitDecreasing();
        return firstFitDecreasingAlgorithm.generateSolution(getClonedListOfItems(), binSize);
    }

    public Solution generateSolutionFromOneBinPerItem() {
        OneBinPerItem oneBinPerItemAlgorithm = new OneBinPerItem();
        return oneBinPerItemAlgorithm.generateSolution(getClonedListOfItems(), binSize);
    }

    public Solution generateSolutionFromFirstFitAlgorithm() {
        FirstFit firstFitAlgorithm = new FirstFit();
        return firstFitAlgorithm.generateSolution(getClonedListOfItems(), binSize);
    }

    public Solution generateSolutionFromProrammationLineaire() throws CloneNotSupportedException {
        ProgrammationLineaire programmationLineaire = new ProgrammationLineaire();
        long startTime = System.currentTimeMillis();
        Solution solution = programmationLineaire.generateSolution(getClonedListOfItems(), binSize);
        long endTime = System.currentTimeMillis();
        System.out.println("Temps d'execution " + Math.abs(endTime - startTime));
        return solution;
    }

    public void getSolutionFromPLUntilSolutionIsFound() throws CloneNotSupportedException {

        ProgrammationLineaire programmationLineaire = new ProgrammationLineaire();
        LinkedList<Item> listcloned = getClonedListOfItems();
        long time;

        do {
            long startTime = System.currentTimeMillis();
            Solution solution = programmationLineaire.generateSolution(listcloned, binSize);
            long endTime = System.currentTimeMillis();
            time = endTime - startTime;

            if (time < 600000) {
                System.out.println("Pour " + listcloned.size() + " items, il aura fallut " + time + "ms pour trouver une solution optimale");
                listcloned.addAll(getClonedListOfItems(listcloned));
            } else {
                System.out.println("Pour " + listcloned.size() + " items, il faut plus de 10min pour trouver une solution optimale");
            }

        } while (time < 600000);

    }

    public Solution generateSolutionFromTabuSearchAlgorithm() throws CloneNotSupportedException {
        TabuSearch tabuSearchAlgorithm = new TabuSearch();
        return tabuSearchAlgorithm.generateSolution(listItems, binSize);
    }

    public Solution generateSolutionFromRecuitSimule(double t0) throws CloneNotSupportedException {
        RecuitSimule recuitSimule = new RecuitSimule(t0);
        return recuitSimule.generateSolution(getClonedListOfItems(), binSize);
    }

    public void printDetails() {
        System.out.println("Taille d'une bin : " + binSize);
        System.out.println("Quantité d'items : " + listItems.size());

    }

    public void saveItemsDataInExcelFile(String name) {
        try {

            FileWriter myWriter = new FileWriter(name + "_details.txt");

            Map<Integer, List<Item>> map = listItems.stream().collect(groupingBy(Item::getSize));
            List<Integer> keySorted = map.keySet().stream().sorted().collect(Collectors.toList());
            for (Integer i : keySorted) {
                myWriter.write(i + "\t" + map.get(i).size() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printItemsDetails() {
        System.out.println("Taille bin " + getBinSize());
        System.out.println("Nb bin " + listItems.size());
        double moyenne = listItems.stream().mapToInt(Item::getSize).average().getAsDouble();
        System.out.println("Moyenne taille item " + moyenne);
        LinkedList<Item> listItemsSorted = getClonedListOfItems();
        Collections.sort(listItemsSorted, new ItemComparator());
        System.out.println("Médiane taille item " + listItemsSorted.get(Math.round(listItemsSorted.size() / 2)).getSize());
        double variance = 0;
        for (Item item : listItems) {
            variance += Math.pow(item.getSize() - moyenne, 2);
        }
        variance = (double) variance / (listItems.size());
        System.out.println("Variance taille items " + variance);
        System.out.println("Ecart-type :" + Math.sqrt(variance));
    }

    //region Getter & Setter
    public void addItem(Item item)
    {
        listItems.add(item);
    }

    public void setBinSize(int binSize) {
        this.binSize = binSize;
    }

    public LinkedList<Item> getClonedListOfItems() {
        LinkedList<Item> toReturn = new LinkedList<>();
        try {
            for (Item item : listItems) toReturn.add(item.clone());
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return toReturn;
    }

    public LinkedList<Item> getClonedListOfItems(LinkedList<Item> listToClone) {
        LinkedList<Item> toReturn = new LinkedList<>();
        try {
            for (Item item : listToClone) toReturn.add(item.clone());
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return toReturn;
    }
    //endregion

    //region Override Methods
    @Override
    public String toString() {
        return "optimisateur{" +
                "binSize=" + binSize +
                ", listItems=" + listItems +
                '}';
    }
    //endregion
}
