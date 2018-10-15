import java.io.File;
import java.util.List;

public class TravellingSalesman {

    public static void main(String[] args){
        File a280 = new File("data/a280.tsp");
        Loader loader = new Loader();
        List<Node> a280Nodes = loader.loadData(a280);
        MSTHeuristic MST = new MSTHeuristic();
        MST.solveTSP(a280Nodes);
    }
}
