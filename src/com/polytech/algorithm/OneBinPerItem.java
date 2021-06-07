package com.polytech.algorithm;

import com.polytech.Bin;
import com.polytech.Item;
import com.polytech.Solution;
import com.polytech.algorithm.Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

public class OneBinPerItem implements Algorithm {
    @Override
    public Solution generateSolution(LinkedList<Item> listItems, int binSize) {

        if (listItems == null) {
            System.err.println("listItems is null, impossible to handle it");
            return null;
        }

        if (binSize <= 0) {
            System.err.println("binSize must be greater than 0");
            return null;
        }

        LinkedList<Item> listItemsSorted = (LinkedList<Item>) listItems.clone();
        ArrayList<Bin> listBins = new ArrayList<>();

        // Create a bin per item and put the item in it
        for (Item item : listItemsSorted
             ) {
            Bin newBin = new Bin(binSize);
            listBins.add(newBin);
            newBin.addItem(item);
        }

        return new Solution(listBins);
    }
}
