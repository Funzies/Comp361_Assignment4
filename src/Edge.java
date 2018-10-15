/**
 * represents an edge between two nodes
 */

public class Edge {

    Node from;
    Node to;
    double cost;

    public Edge(Node from, Node to, double cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public String toString(){
        String s = "From: "+from.id+" To: "+to.id+", Cost = "+cost;
        return s;
    }
}
