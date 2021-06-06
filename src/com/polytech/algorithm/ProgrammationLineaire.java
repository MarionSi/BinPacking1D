package com.polytech.algorithm;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.polytech.Item;
import com.polytech.Solution;

import java.util.LinkedList;


public class ProgrammationLineaire implements Algorithm{

    @Override
    public Solution generateSolution(LinkedList<Item> listItems, int binSize) throws CloneNotSupportedException {
        Loader.loadNativeLibraries();

        // Create the linear solver with the SCIP backend.
        MPSolver solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            System.out.println("Could not create solver SCIP");
            return null;
        }

        MPVariable[][] x = new MPVariable[listItems.size()][listItems.size()];
        for (int i = 0; i < listItems.size(); ++i) {
            for (int j = 0; j < listItems.size(); ++j) {
                x[i][j] = solver.makeIntVar(0, 1, "");
            }
        }

        MPVariable[] y = new MPVariable[listItems.size()];
        for (int j = 0; j < listItems.size(); ++j) {
            y[j] = solver.makeIntVar(0, 1, "");
        }

        double infinity = java.lang.Double.POSITIVE_INFINITY;
        for (int i = 0; i < listItems.size(); ++i) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int j = 0; j < listItems.size(); ++j) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }

        for (int j = 0; j < listItems.size(); ++j) {
            MPConstraint constraint = solver.makeConstraint(0, infinity, "");
            constraint.setCoefficient(y[j], binSize);
            for (int i = 0; i < listItems.size(); ++i) {
                constraint.setCoefficient(x[i][j], -listItems.get(i).getSize());
            }
        }

        MPObjective objective = solver.objective();
        for (int j = 0; j < listItems.size(); ++j) {
            objective.setCoefficient(y[j], 1);
        }
        objective.setMinimization();

        final MPSolver.ResultStatus resultStatus = solver.solve();

        // Check that the problem has an optimal solution.
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("Number of bins used: " + objective.value());
            double totalWeight = 0;
            for (int j = 0; j < listItems.size(); ++j) {
                if (y[j].solutionValue() == 1) {
                    System.out.println("\nBin " + j + "\n");
                    double binWeight = 0;
                    for (int i = 0; i < listItems.size(); ++i) {
                        if (x[i][j].solutionValue() == 1) {
                            System.out.println("Item " + i + " - weight: " + listItems.get(i).getSize());
                            binWeight += listItems.get(i).getSize();
                        }
                    }
                    System.out.println("Packed bin weight: " + binWeight);
                    totalWeight += binWeight;
                }
            }
            System.out.println("\nTotal packed weight: " + totalWeight);
        } else {
            System.err.println("The problem does not have an optimal solution.");
        }

        return null;

    }

//    static class DataModel {
//        public final double[] weights = {48, 30, 19, 36, 36, 27, 42, 42, 36, 24, 30};
//        public final int numItems = weights.length;
//        public final int numBins = weights.length;
//        public final int binCapacity = 100;
//    }

    public static void main(String[] args) throws Exception {









    }
    public ProgrammationLineaire() {}
}