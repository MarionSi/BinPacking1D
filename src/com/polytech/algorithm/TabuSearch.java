package com.polytech.algorithm;

import com.polytech.Item;
import com.polytech.Solution;
import com.polytech.algorithm.Algorithm;
import com.polytech.algorithm.OneBinPerItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class TabuSearch implements Algorithm {

    private ArrayList<Solution> tabuList;
    private ArrayList<Solution> neighbourList;
    private Solution initialSolution;
    private int tabuListSize = 5;
    private int maxIter = 1000;
    private int maxNeighbour = 50;

    public TabuSearch(int tabuListSize_, int iterationNumber, int iterationNeighbour) {
        this.tabuListSize = tabuListSize_;
        this.maxIter = iterationNumber;
        this.maxNeighbour = iterationNeighbour;
    }
    public void init(LinkedList<Item> listItems, int binSize) {
        initialSolution = new OneBinPerItem().generateSolution(listItems, binSize);
        tabuList = new ArrayList<>();
        neighbourList = new ArrayList<>();
    }

    @Override
    public Solution generateSolution(LinkedList<Item> listItems, int binSize) throws CloneNotSupportedException {
        init(listItems, binSize);
        Solution solutionInit = this.initialSolution;
        Solution solutionMin = solutionInit.clone();
        int fMin = solutionMin.updateFitnessAndGet();

        Solution soutMin = solutionMin;
        int soutFMin = fMin;

        Solution solution;
        int fSolution;
        int delta;

        for(int i = 0; i < maxIter; i++) {
            try {
                Solution solution_x = solutionMin.clone();
                generateNeighbours(solution_x);
                solution = getBestNeighbour();

                solution.updateFitnessAndGet();

                while(isSolutionInTabulist(solution)) {
                    solution = getBestNeighbour();
                    solution.updateFitnessAndGet();
                }

                fSolution = solution.updateFitnessAndGet();
                delta = fSolution - fMin;

                if (delta <= 0) {
                    if (tabuList.size() == tabuListSize) {
                        tabuList.remove(0);
                    }
                    tabuList.add(solution);
                } else {
                    solutionMin = solution;
                    solutionMin.setFitness(fSolution);
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }

        }
        System.out.println("Fitness solution initiale : " + soutFMin);
        System.out.println("Nb bins solution initiale : " + soutMin.getNbBins());

        return solutionMin;
    }


    public boolean isSolutionInTabulist(Solution solution) {
        boolean toReturn = false;
        for (Solution s: tabuList
             ) {
            toReturn = s.equals(solution);
        }
        return toReturn;
    }

    public Solution getBestNeighbour() {
        int fMinNeighbour = 0;
        Solution solutionMinNeighbour = new Solution();
        for (Solution s: neighbourList
             ) {
            if(s.updateFitnessAndGet() > fMinNeighbour) {
                solutionMinNeighbour = s;
                fMinNeighbour = s.getFitness();
            }
        }
        neighbourList.remove(solutionMinNeighbour);
        return solutionMinNeighbour;
    }

    public void generateNeighbours(Solution solution) {
        for (int j = 0; j < maxNeighbour; j++) {
            neighbourList.add(solution.getNeighbour());
        }
    }

}
