package com.polytech.algorithm;

import com.polytech.Bin;
import com.polytech.Comparator.ItemComparator;
import com.polytech.Item;
import com.polytech.Solution;
import com.polytech.algorithm.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class FirstFit implements Algorithm {
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

        //Randomize items in the list
        LinkedList<Item> listItemsSorted = (LinkedList<Item>) listItems.clone();
        Collections.shuffle(listItemsSorted);

        ArrayList<Bin> listBins = new ArrayList<>();


        for (Item item : listItemsSorted)
        {
            //Test if the item can be placed in an existing bins
            for (Bin bin : listBins)
            {
                if (bin.addItem(item)) break;
            }

            //Otherwise, create one and put the item in it
            if (!item.isPlaced()){
                Bin newBin = new Bin(binSize);
                listBins.add(newBin);
                newBin.addItem(item);
            }
        }

        return new Solution(listBins);
    }
}
