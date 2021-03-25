package com.polytech;

import com.polytech.Comparator.SolutionComparator;

import java.util.*;

public class Solution {

    private int nbBins;
    
    private LinkedList<Bin> listBin;

    public Solution() {
        listBin = new LinkedList();
    }

    public Solution(LinkedList<Bin> listBin) {
        this.listBin = listBin;
        this.nbBins = (listBin==null)? 0 : listBin.size();
    }

    public static Solution getBestSolutionFromList(ArrayList<Solution> listSolutions) {

        if (listSolutions == null || listSolutions.isEmpty())
        {
            System.err.println("Impossible to get the best solution because listSolutions is null or empty");
            return null;
        }

        Collections.sort(listSolutions, new SolutionComparator());

        return listSolutions.get(0);
    }

    public int getNbBins() {
        return nbBins;
    }

    public void setNbBins(int nbBins) {
        this.nbBins = nbBins;
    }

    public LinkedList<Bin> getListBin() {
        return listBin;
    }

    @Override
    public String toString() {
        return "Nb bins = " + nbBins;
    }
}
