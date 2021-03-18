package com.polytech;

import java.util.LinkedList;

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

    public int getNbBins() {
        return nbBins;
    }

    public LinkedList<Bin> getListBin() {
        return listBin;
    }

    @Override
    public String toString() {
        return "Nb bins = " + nbBins;
    }
}
