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

        if (item.getSize() <= emptySize) {
            listItems.add(item);
            item.placedInBin(this);
            emptySize = emptySize - item.getSize();
            return true;
        } else {
            return false;
        }

    }

    public LinkedList<Item> getListItems() {
        return listItems;
    }

    public int getEmptySize() {
        return emptySize;
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
