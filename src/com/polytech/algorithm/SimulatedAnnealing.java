package com.polytech.algorithm;

import com.polytech.Item;
import com.polytech.Solution;

import java.util.LinkedList;
import java.util.Random;

public class SimulatedAnnealing implements Algorithm {

    private final int n1 = 100;
    private final int n2 = 1000;
    private final double mu = 0.8;

    private double t0;
    private Solution x0;

    public SimulatedAnnealing(double t0, Solution x0) {
        this.t0 = t0;
        this.x0 = x0;
    }

    @Override
    public Solution generateSolution(LinkedList<Item> listItems, int binSize) throws CloneNotSupportedException {

        Solution xmin = x0;
        Integer fmax = xmin.updateFitnessAndGet();
        Double t = t0;

        Solution xnplus1 = xmin.clone();

        for (int k = 0; k < n1; k++) {
            for (int l = 1; l < n2; l++) {
                Solution xn = xnplus1.clone();
                Solution y = xn.getNeighbour();
                int deltaF = fmax - y.updateFitnessAndGet();
                if (deltaF <= 0) {
                    xnplus1 = y;
                    if (xnplus1.getFitness() > fmax) {
                        xmin = xnplus1;
                        fmax = xnplus1.getFitness();
                    }
                }
                else
                {
                    Random random = new Random();
                    double p = random.nextDouble();

                    if (p <= Math.exp(-deltaF/t)) {
                        xnplus1 = y;
                    }
                    else {
                        xnplus1 = xn;
                    }
                }
            }
            t = mu*t;
        }
        return xmin;
    }

    private Solution generateSolutionInitiale(LinkedList<Item> listItems, int binSize) {
        OneBinPerItem firstFit = new OneBinPerItem();
        Solution solutionInitiale = firstFit.generateSolution(listItems, binSize);
        System.out.println("Solution initiale " + solutionInitiale.toString());
        return solutionInitiale;
    }
}
