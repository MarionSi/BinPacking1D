package com.polytech;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class optimisateur {

    private int binSize = 150;

    private LinkedList<Item> listItems;
    private LinkedList<Bin> listBins;

    public optimisateur() {
        listItems = new LinkedList<>();
        listBins = new LinkedList<>();
    }

    public void addItem(Item item)
    {
        listItems.add(item);
    }

    public void addBin(Bin bin)
    {
        listBins.add(bin);
    }

    public int getBinSize() {
        return binSize;
    }

    public void setBinSize(int binSize) {
        this.binSize = binSize;
    }

    public int getBorneInf() {

        int sommeObjets = 0;

        for (Item item : listItems) {
            sommeObjets += item.getSize();
        }

        return (int) Math.ceil((float) sommeObjets / (float) binSize);
    }

    public void FirstFitDecreasing() {

        //Tri des items
        LinkedList<Item> listItemsSorted = (LinkedList<Item>) listItems.clone();
        Collections.sort(listItemsSorted, new ItemComparator());


        for (Item item : listItemsSorted)
        {
            //Test bin
            boolean placed = false;
            for (Bin bin : listBins)
            {
                if (bin.addItem(item)) {
                    placed = true;
                    break;
                }
            }

            if (!placed){
                Bin newBin = new Bin(binSize);
                listBins.add(newBin);
                newBin.addItem(item);
            }

        }

        System.out.println("NbBins -> " + listBins.size());
//        for (Bin bin: listBins) {
//            System.out.println(bin.toString());
//        }


    }

    @Override
    public String toString() {
        return "optimisateur{" +
                "binSize=" + binSize +
                ", listItems=" + listItems +
                ", listBins=" + listBins +
                '}';
    }
}
