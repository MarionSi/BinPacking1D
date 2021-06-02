package com.polytech;

import java.util.ArrayList;
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

    public void removeItem(Item item) {
        listItems.remove(item);
        emptySize += item.getSize();
    }

    public boolean isEmpty(){
        return fullSize == emptySize;
    }

    public int getFullSize() {
        return fullSize;
    }

    public void setListItems(LinkedList<Item> listItems) {
        this.listItems = listItems;
    }

    public void setFullSize(int fullSize) {
        this.fullSize = fullSize;
    }

    public void setEmptySize(int emptySize) {
        this.emptySize = emptySize;
    }


    @Override
    protected Bin clone() {
        Bin bin = new Bin(fullSize);
        bin.setEmptySize(getEmptySize());
        bin.setFullSize(getFullSize());
        bin.setListItems((LinkedList<Item>) getListItems().clone());

        return bin;
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
