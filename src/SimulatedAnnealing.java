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


    public void solveTSP(List<Node> nodes, String name){
        List<Node> randomisedNodes = new ArrayList<>(nodes);
        Collections.shuffle(randomisedNodes);
        findSolution(randomisedNodes, MAXITERATIONS);
        outputSolution(name);
    }

    public void findSolution(List<Node> nodes, int maxIterations){

    }



    public void outputSolution(String name){

    }
}
