public class Node {
    int id;
    double x;
    double y;

    public Node(int id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        String s = "Id: "+id+", x="+x+", y="+y;
        return s;
    }
}
