package com.polytech.algorithm;

import com.polytech.Item;
import com.polytech.Solution;

import java.util.LinkedList;

public interface Algorithm {

    public Solution generateSolution(LinkedList<Item> listItems, int binSize);
}
