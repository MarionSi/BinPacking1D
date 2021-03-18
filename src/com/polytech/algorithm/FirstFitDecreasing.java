package com.polytech.algorithm;

import com.polytech.Bin;
import com.polytech.Item;
import com.polytech.ItemComparator;
import com.polytech.Solution;

import java.util.Collections;
import java.util.LinkedList;

public class FirstFitDecreasing implements Algorithm {

    @Override
    public Solution generateSolution(LinkedList<Item> listItems, int binSize) {

        //Tri des items
        LinkedList<Item> listItemsSorted = (LinkedList<Item>) listItems.clone();
        Collections.sort(listItemsSorted, new ItemComparator());

        LinkedList<Bin> listBins = new LinkedList<>();


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

        return new Solution(listBins);
    }
}
