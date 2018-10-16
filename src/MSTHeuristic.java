/**
 * Comp361 Assignment 4
 * MST algorithm to solve the travelling salesman problem
 * Jack Huang
 * 300343247
 *
 */

import java.util.*;

/***
 * This implementation uses Prim's algorithm to calculate the MST
 * as well as Euclidean distance to calculate weight/cost
 */
public class MSTHeuristic {
    List<Node> nodes;
    Stack<Node> s = new Stack<>();

    public void solveTSP(List<Node> nodes, String name){
        List<Edge> edges = createMST(nodes);
        List<Node> circuit = findTour(nodes.get(0));
        outputSolution(circuit, name);
    }


    /**
     * Creates a MST using Prim's MST algorithm
     * @param nodes list of points to travel to
     */
    public List<Edge> createMST(List<Node> nodes){
        List<Node> unvisited = new ArrayList<>(nodes);
        List<Edge> edges = new ArrayList<>();
        Node from = nodes.get(0); //first node is simply chosen to be the first in the tree
        unvisited.remove(from);
        while (!unvisited.isEmpty()){
            Node bestNeighbor = null;
            double leastCost = Double.MAX_VALUE;
            for (Node n : unvisited){
                double cost = Math.sqrt(Math.pow(n.x - from.x, 2)+Math.pow(n.y - from.y, 2));
                if (cost < leastCost){
                    bestNeighbor  = n;
                    leastCost = cost;
                }
            }
            //add two edges to create a eulerian graph
            for(int i=0; i<2;i++) {
                Edge e = new Edge(from, bestNeighbor, leastCost);
                edges.add(e);
                from.addEdge(e);
                bestNeighbor.addEdge(e);
            }

            from = bestNeighbor;
            unvisited.remove(from);
        }
        return edges;
    }

    /**
     * Algorithm used to find the Euler tour was from this website:
     * http://www.graph-magics.com/articles/euler.php
     */
    public List<Node> findTour(Node n) {
        Stack<Node> s = new Stack<>();
        List<Node> circuit = new ArrayList<>();
        Node currentNode = n;
        s.push(currentNode);
        while (!s.isEmpty()) {
            if (currentNode.edges.isEmpty()){
                circuit.add(currentNode);
                currentNode = s.pop();
            }
            else {
                Edge e = currentNode.edges.get(0);
                Node neighbour = e.getNeighbour(currentNode);
                currentNode.removeEdge(e);
                neighbour.removeEdge(e);
                s.push(currentNode);
                currentNode = neighbour;
            }
        }
        //getting rid of duplicates
        List<Node> finalCircuit = new ArrayList<>();
        for (Node node : circuit){
            if (!finalCircuit.contains(node)){
                finalCircuit.add(node);
            }
        }
        //add back in the starting node
        finalCircuit.add(0,n);
        Collections.reverse(finalCircuit);
        return finalCircuit;
    }

    public void outputSolution(List<Node> circuit, String name){
        StringBuilder sb = new StringBuilder("\n"+name+" + MST heuristic\nPath is: ");
        double totalWeight = 0;
        for (int i=0; i<circuit.size()-1; i++){
            sb.append(circuit.get(i).id +" -> ");
            totalWeight +=Math.sqrt(Math.pow(circuit.get(i).x - circuit.get(i+1).x, 2)+Math.pow(circuit.get(i).y - circuit.get(i+1).y, 2));
        }
        sb.append(circuit.get(circuit.size()-1).id);
        sb.append("\nTotal Weight = "+totalWeight);
        System.out.println(sb.toString());
    }
}
