package com.polytech;

import com.polytech.algorithm.FirstFitDecreasing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public Solution generateSolutionFromFirstFitDecreasingAlgorithm() {
        FirstFitDecreasing firstFitDecreasingAlgorithm = new FirstFitDecreasing();
        return firstFitDecreasingAlgorithm.generateSolution(listItems, binSize);
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

    //region Getter & Setter
    public void addItem(Item item)
    {
        listItems.add(item);
    }

    public void setBinSize(int binSize) {
        this.binSize = binSize;
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
