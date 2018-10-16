/**
 * represents an edge between two nodes
 */

public class Edge {

    Node node1;
    Node node2;
    double cost;

    public Edge(Node node1, Node node2, double cost){
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }
    public Edge(Node node1, Node node2){
        this.node1 = node1;
        this.node2 = node2;
        this.cost = Math.sqrt(Math.pow(node1.x - node2.x, 2)+Math.pow(node1.y - node2.y, 2));
    }

    public Node getNeighbour(Node n){
        if (n.equals(node1)){
            return node2;
        }
        else if (n.equals(node2)) {
            return node2;
        }
        else {
            return null;
        }
    }

    public String toString(){
        String s = "From: "+node1.id+" To: "+node2.id+", Cost = "+cost;
        return s;
    }
}
