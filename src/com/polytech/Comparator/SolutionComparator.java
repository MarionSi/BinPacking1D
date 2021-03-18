package com.polytech.Comparator;

import com.polytech.Solution;

import java.util.Comparator;

public class SolutionComparator implements Comparator<Solution> {

    @Override
    public int compare(Solution o1, Solution o2) {
        if (o1.getNbBins() > o2.getNbBins()) return 1;
        else return -1;
    }

}
