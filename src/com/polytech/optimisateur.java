package com.polytech;

import com.polytech.algorithm.FirstFitDecreasing;
import java.util.LinkedList;

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
