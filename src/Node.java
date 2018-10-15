import java.util.ArrayList;
import java.util.List;

public class Node {
    int id;
    double x;
    double y;
    List<Edge> edges;

    public Node(int id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
        edges = new ArrayList<>();
    }

    public String toString(){
        String s = "Id: "+id+", x="+x+", y="+y;
        return s;
    }
    public void addEdge(Edge e){
        edges.add(e);
    }
    public void removeEdge(Edge e){
        edges.remove(e);
    }
}
