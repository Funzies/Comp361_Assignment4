import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Comp361 Assignment 4
 * Simulated Annealing algorithm to solve the travelling salesman problem
 * Jack Huang
 * 300343247
 */


public class SimulatedAnnealing {

    final int MAXITERATIONS = 1000;
    final double INITIALTEMP = 5000;
    final double COOLINGCONSTANT = 0.98;

    double[] convergenceWeights = new double[MAXITERATIONS];


    public void solveTSP(List<Node> nodes, String name){
        List<Node> randomisedNodes = new ArrayList<>(nodes);
        Collections.shuffle(randomisedNodes);
        List<Node> solution = findSolution(randomisedNodes, MAXITERATIONS);
        outputSolution(solution, name);
        //outputConvergence(convergenceWeights);
    }

    public List<Node> findSolution(List<Node> nodes, int maxIterations){
        double temperature = INITIALTEMP;
        List<Node> best = nodes;
        double bestWeight = Double.MAX_VALUE; //never accept the random, initial solution
        for (int i=0; i<maxIterations; i++){
            List<List<Node>> neighbors = findAllNeighbors(best);
            for (List<Node> candidate : neighbors){
                double candidateWeight = calculateWeight(candidate);
                if (acceptanceCheck(candidateWeight, bestWeight, temperature)){
                    best = candidate;
                    bestWeight = candidateWeight;
                }
            }
            //record weight progression
            convergenceWeights[i] = bestWeight;
            //cool temperature
            temperature *= COOLINGCONSTANT;
        }
        return best;
    }
    /**
     * Returns true if the algorithm accepts the solution
     */
    public boolean acceptanceCheck(double candidateWeight, double bestWeight, double temperature){
        if (candidateWeight < bestWeight){
            return true;
        }
        if (Math.exp(-1*(candidateWeight-bestWeight)/temperature) > Math.random()){
            return true;
        }
        return false;
    }

    public double calculateWeight(List<Node> nodes){
        double totalWeight = 0;
        for (int i=0; i<nodes.size()-1; i++) {
            totalWeight += Math.sqrt(Math.pow(nodes.get(i).x - nodes.get(i + 1).x, 2) + Math.pow(nodes.get(i).y - nodes.get(i + 1).y, 2));
        }
        return totalWeight;
    }

    /**
     * Outputs the weights in a csv format
     */
    public void outputConvergence(double[] weights){
        for (int i=0; i<weights.length; i++) {
            System.out.println(i+","+weights[i]);
        }
    }

    /***
     * Finds possible permutations of swapping nodes from the first half of the list to the 2nd half.
     */
    public List<List<Node>> findAllNeighbors(List<Node> nodes){
        List<List<Node>> neighbors = new ArrayList<>();
        for (int i=0; i<nodes.size()/2; i++){
            for (int j=nodes.size()/2; j<nodes.size(); j++){
                if (i != j){
                    List<Node> dummy = new ArrayList<>(nodes);
                    Collections.swap(dummy, i, j);
                    neighbors.add(dummy);
                }
            }
        }
        return neighbors;
    }


    public void outputSolution(List<Node> solution, String name){
        StringBuilder sb = new StringBuilder("\n"+name+" + Simulated Annealing\nPath is: ");
        for (int i=0; i<solution.size()-1; i++){
            sb.append(solution.get(i).id +" -> ");
        }
        sb.append(solution.get(solution.size()-1).id);
        sb.append("\nTotal Weight = "+calculateWeight(solution));
        System.out.println(sb.toString());
    }
}