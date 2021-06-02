package com.polytech;

import com.polytech.Comparator.SolutionComparator;

import java.util.*;

public class Solution {

    private int nbBins;
    private ArrayList<Bin> listBin;

    private final int MAX_SEARCH_NEIGHBOUR_ITERATION = 10;

    public Solution() {
        listBin = new ArrayList<>();
    }

    public Solution(ArrayList<Bin> listBin) {
        this.listBin = listBin;
        this.nbBins = (listBin==null)? 0 : listBin.size();
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

    public Solution getNeighbour() {
        try {
            return switchTwoItems();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Solution switchTwoItems() {

        System.out.println("Bins avant le switch : ");
        System.out.println(fullSolution());

        if (nbBins > 1) {

            Random random = new Random();

            //Try to switch items between two bins MAX_SEARCH_NEIGHBOUR_ITERATION times
            //Otherwise, put the item of the first bin in a new one
            for (int i = 0; i < MAX_SEARCH_NEIGHBOUR_ITERATION; i++) {

                //Choose the first bin for the item switch
                int indexBin1 = random.nextInt(listBin.size());
                Bin bin1 = listBin.get(indexBin1);

                int indexBin2;

                //Choose a second bin
                do {
                    indexBin2 = random.nextInt(listBin.size());
                } while (indexBin2 == indexBin1);

                Bin bin2 = listBin.get(indexBin2);

                System.out.println("Index 1 : " + indexBin1);
                System.out.println("Index 2 : " + indexBin2);

                //Choose an item to switch in the first bin
                int indexItem1 = random.nextInt(listBin.get(indexBin1).getListItems().size());
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

                        System.out.println("Bins après le switch : ");
                        System.out.println(fullSolution());

                        return this;
                    }
//
                }

            }


//            LinkedList<Bin> shuffledBinList = (LinkedList<Bin>) getListBin().clone();
//            Collections.shuffle(shuffledBinList);
//
//            for (Bin bin1 : shuffledBinList) {
//                for (Bin bin2 : shuffledBinList) {
//                    if (!bin1.equals(bin2)) {
//
//                        LinkedList<Item> itemLinkedListBin1 = bin1.getListItems();
//                        Collections.shuffle(itemLinkedListBin1);
//
//                        for (Item item1 : itemLinkedListBin1) {
//                            for (Item item2 : bin2.getListItems()) {
//                                if (item1.getSize() >= item2.getSize() + bin2.getEmptySize()) {
//                                    System.out.println("contains " + bin1.getListItems().contains(item1));
//
//                                    bin1.removeItem(item1);
//                                    bin2.removeItem(item2);
//
//                                    bin1.addItem(item2);
//                                    bin2.addItem(item1);
//
//                                    return this;
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
        }

        return this;
    }


    public int getNbBins() {
        return nbBins;
    }

    public void setNbBins(int nbBins) {
        this.nbBins = nbBins;
    }

    public ArrayList<Bin> getListBin() {
        return listBin;
    }

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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
