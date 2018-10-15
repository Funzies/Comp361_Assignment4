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

    public void solveTSP(List<Node> nodes){
        List<Edge> edges = createMST(nodes);
        findTour(nodes.get(0));
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
            Edge e = new Edge(from, bestNeighbor, leastCost);
            edges.add(e);
            from.addEdge(e);
            e = new Edge(bestNeighbor, from, leastCost);
            edges.add(e);
            bestNeighbor.addEdge(e);


            from = bestNeighbor;
            unvisited.remove(from);
        }
        return edges;
    }

    /**
     * http://www.graph-magics.com/articles/euler.php
     */
    public void findTour(Node n) {
        Stack<Node> s = new Stack<>();
        List<Node> circuit = new ArrayList<>();
        Node currentNode = n;
        while (!s.isEmpty() && currentNode.edges.isEmpty()) {
            if (currentNode.edges.isEmpty()){
                circuit.add(currentNode);
                currentNode = s.pop();
            }
            else {
                Edge e = currentNode.edges.get(0);
                currentNode.removeEdge(e);
                s.push(currentNode);
                currentNode = e.to;
            }
        }
        for (Node node : circuit){
            System.out.println(node.toString());
        }
    }
}
