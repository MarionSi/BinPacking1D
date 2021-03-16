package com.polytech;

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
}
