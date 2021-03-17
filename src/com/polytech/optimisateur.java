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

    public int getBorneInf() {

        int sommeObjets = 0;

        for (Item item : listItems) {
            sommeObjets += item.getSize();
        }

        return (int) Math.ceil((float) sommeObjets / (float) binSize);
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
