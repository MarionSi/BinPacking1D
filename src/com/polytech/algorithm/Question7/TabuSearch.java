package com.polytech.algorithm.Question7;

import com.polytech.Bin;
import com.polytech.Item;
import com.polytech.Solution;
import com.polytech.algorithm.Algorithm;
import com.polytech.algorithm.FirstFitDecreasing;
import com.polytech.algorithm.Question4.FirstFit;
import com.polytech.algorithm.Question4.OneBinPerItem;
import com.polytech.optimisateur;
import javafx.util.Pair;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.LinkedList;

public class TabuSearch implements Algorithm {

    private ArrayList<Solution> tabuList;
    private ArrayList<Solution> neighbourList;
    private Solution initialSolution;
    private int tabuListSize = 5;
    private int maxIter = 1500;
    private int maxVoisins = 100;

    public void init(LinkedList<Item> listItems, int binSize) {
        // init first fit decreasing
        initialSolution = new OneBinPerItem().generateSolution(listItems, binSize);
        tabuList = new ArrayList<>();
        neighbourList = new ArrayList<>();
    }

    @Override
    public Solution generateSolution(LinkedList<Item> listItems, int binSize) throws CloneNotSupportedException {
        System.out.println("-------START---------");

        init(listItems, binSize);
        Solution solutionInit = this.initialSolution;
        Solution solutionMin = solutionInit.clone();
        int fMin = solutionMin.updateFitnessAndGet();

        System.out.println("INITIAL FITNESS : " + fMin);
        System.out.println("INITIAL BINS : " + solutionMin.getNbBins());

        Solution solution;
        int fSolution;
        int delta;

        for(int i = 0; i < maxIter; i++) {
            //System.out.println("Itération : " + i);
            try {
                Solution solution_x = solutionMin.clone();
                generateNeighbours(solution_x);
                solution = getBestNeighbour();

                //solution = solution_x.getNeighbour();
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

        System.out.println("-------END---------" );
        return solutionMin;
    }

    public void setTabuListSize(int tabuListSize) {
        this.tabuListSize = tabuListSize;
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
        for (int j = 0; j < maxVoisins; j++) {
            neighbourList.add(solution.getNeighbour());
        }
    }

}
