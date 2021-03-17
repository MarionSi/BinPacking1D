package com.polytech;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getSize() > o2.getSize()) return -1;
        else return 1;
    }

}
