package com.polytech;

import java.util.LinkedList;

public class Bin {

    private LinkedList<Item> listItems;

    private int fullSize;
    private int emptySize;

    public Bin(int fullSize) {
        listItems = new LinkedList<>();
        this.fullSize = fullSize;
        emptySize = fullSize;
    }

    public boolean addItem(Item item) {
//
//        System.out.println("Tentative de placer un item de taille " + item.getSize() + " dans une bin avec comme" +
//                "place restante " + emptySize);

        if (item.getSize() <= emptySize) {
            listItems.add(item);
            item.placedInBin(this);
            emptySize = emptySize - item.getSize();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "Bin{" +
                "quantité items =" + listItems.size() +
                "liste des items = " + listItems +
                ", fullSize=" + fullSize +
                ", emptySize=" + emptySize +
                '}';
    }
}
