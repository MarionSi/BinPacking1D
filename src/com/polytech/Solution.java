package com.polytech;

import com.polytech.Comparator.SolutionComparator;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private int nbBins;
    private int fitness;
    private ArrayList<Bin> listBin;

    private final int MAX_SEARCH_NEIGHBOUR_ITERATION = 10;

    public Solution() {
        listBin = new ArrayList<>();
    }

    public Solution(ArrayList<Bin> listBin) {
        this.listBin = listBin;
        this.nbBins = (listBin==null)? 0 : listBin.size();
        calculateFitness();
    }

    public static Solution getBestSolutionFromList(ArrayList<Solution> listSolutions) {

        if (listSolutions == null || listSolutions.isEmpty())
        {
            System.err.println("Impossible to get the best solution because listSolutions is null or empty");
            return null;
        }

        Collections.sort(listSolutions, new SolutionComparator());

        return listSolutions.get(0);
    }

    public void addBin(Bin bin) {
        nbBins ++;
        listBin.add(bin);
    }

    public Solution getNeighbour() {
        try {
            Random random = new Random();
            Solution solution;
            if (random.nextBoolean()) {
                solution = putItemInAnotherBin();
            }
            else
            {
                solution = switchTwoItems();
            }

            solution.checkEmptyBin();
            return solution;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Solution putItemInAnotherBin()throws CloneNotSupportedException {

        Solution toReturn = this.clone();

        if (nbBins > 1) {

            Random random = new Random();

            //Try to switch items between two bins MAX_SEARCH_NEIGHBOUR_ITERATION times
            //Otherwise, put the item of the first bin in a new one
            for (int i = 0; i < MAX_SEARCH_NEIGHBOUR_ITERATION; i++) {

                //Choose the first bin for the item switch
                int indexBin1 = random.nextInt(toReturn.getListBin().size());
                Bin bin1 = toReturn.getListBin().get(indexBin1);

                int indexBin2;

                //Choose a second bin
                do {
                    indexBin2 = random.nextInt(toReturn.getListBin().size());
                } while (indexBin2 == indexBin1);

                Bin bin2 = toReturn.getListBin().get(indexBin2);

                //Choose an item to switch in the first bin
                int indexItem1 = random.nextInt(toReturn.getListBin().get(indexBin1).getListItems().size());
                Item item1 = bin1.getListItems().get(indexItem1);

                //Check if it's possible to put the item from the bin 1 in the bin 2
                if (item1.getSize() <= bin2.getEmptySize()) {
                    bin1.removeItem(item1);
                    bin2.addItem(item1);

                    return toReturn;
                }

            }

            return putItemInNewBin();
        }

        return toReturn;
    }

    public Solution switchTwoItems() throws CloneNotSupportedException {

        Solution toReturn = this.clone();

        if (nbBins > 1) {

            Random random = new Random();

            //Try to switch items between two bins MAX_SEARCH_NEIGHBOUR_ITERATION times
            //Otherwise, put the item of the first bin in a new one
            for (int i = 0; i < MAX_SEARCH_NEIGHBOUR_ITERATION; i++) {

                //Choose the first bin for the item switch
                int indexBin1 = random.nextInt(toReturn.getListBin().size());
                Bin bin1 = toReturn.getListBin().get(indexBin1);

                int indexBin2;

                //Choose a second bin
                do {
                    indexBin2 = random.nextInt(toReturn.getListBin().size());
                } while (indexBin2 == indexBin1);

                Bin bin2 = toReturn.getListBin().get(indexBin2);

                //Choose an item to switch in the first bin
                int indexItem1 = random.nextInt(toReturn.getListBin().get(indexBin1).getListItems().size());
                Item item1 = bin1.getListItems().get(indexItem1);

                //Try to put it in the second bin
                for (Item item2 : bin2.getListItems()) {

                    //Check if it's possible to switch the two items
                    int sizeBin1WithoutItem1 = bin1.getEmptySize() + item1.getSize();
                    int sizeBin2WithoutItem2 = bin2.getEmptySize() + item2.getSize();

                    if (sizeBin1WithoutItem1 >= item2.getSize() && sizeBin2WithoutItem2 >= item1.getSize()) {
                        bin1.removeItem(item1);
                        bin2.removeItem(item2);

                        bin1.addItem(item2);
                        bin2.addItem(item1);

                        return toReturn;
                    }

                }

            }

            return putItemInNewBin();
        }

        return toReturn;
    }

    private Solution putItemInNewBin() throws CloneNotSupportedException {
        Random random = new Random();
        Solution toReturn = this.clone();

        //If no switch is possible, put a random item in a new bin
        int indexBin = random.nextInt(toReturn.getListBin().size());
        Bin bin = toReturn.getListBin().get(indexBin);
        int indexItem = random.nextInt(bin.getListItems().size());
        Item itemToMove = bin.getListItems().get(indexItem);

        bin.removeItem(itemToMove);

        Bin newBin = new Bin(bin.getFullSize());
        newBin.addItem(itemToMove);

        toReturn.addBin(newBin);

        return toReturn;
    }

    private void checkEmptyBin() {
//        for (Bin bin : listBin){
//            if (bin.isEmpty()) {
//                removeBin(bin);
//            }
//        }
        List<Bin> emptyBin = listBin.stream().filter(bin -> bin.isEmpty()).collect(Collectors.toList());
        listBin.removeAll(emptyBin);
    }

    private void removeBin(Bin bin) {
        listBin.remove(bin);
        nbBins --;
    }

    public int getNbBins() {
        return listBin.size();
    }

    public void setNbBins(int nbBins) {
        this.nbBins = nbBins;
    }

    public ArrayList<Bin> getListBin() {
        return listBin;
    }

    public int getFitness() { return fitness; }

    public int updateFitnessAndGet() {
        calculateFitness();
        return getFitness();
    }


    public void setFitness(int fitness) { this.fitness = fitness; }

    @Override
    public String toString() {
        return "Nb bins = " + nbBins;
    }

    public String fullSolution() {
        String toReturn = "Solution";
        toReturn += "\n";
        for (Bin bin : listBin) {
            toReturn += bin.toString() + "\n";
        }

        return toReturn;
    }

    private void setListBin(ArrayList<Bin> listBin) {
        this.listBin = listBin;
    }

    @Override
    public Solution clone() throws CloneNotSupportedException {

        Solution solution = new Solution();

        for (Bin bin : getListBin()) {
            solution.addBin(bin.clone());
        }

        return solution;
    }

    public void calculateFitness() {
        int result = 0;
        int binAddition = 0;
        for (Bin bin: listBin
        ) {
            for (Item item: bin.getListItems()
            ) {
                binAddition = binAddition + item.getSize();
            }
            result = result + (binAddition * binAddition);
            binAddition = 0;
        }

        this.fitness = result;
    }




}
