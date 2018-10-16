import java.io.File;
import java.util.List;

public class TravellingSalesman {

    public static void main(String[] args){
        File berlin52 = new File("data/berlin52.tsp");
        File kroB150 = new File("data/kroB150.tsp");
        File a280 = new File("data/a280.tsp");
        File ali535 = new File("data/ali535.tsp");
        Loader loader = new Loader();
        List<Node> berlin52Nodes = loader.loadData(berlin52);
        List<Node> kroB150Nodes = loader.loadData(kroB150);
        List<Node> a280Nodes = loader.loadData(a280);
        List<Node> ali535Nodes = loader.loadData(ali535);
        MSTHeuristic MST = new MSTHeuristic();
        MST.solveTSP(berlin52Nodes, "berlin52");
        MST.solveTSP(kroB150Nodes, "kroB150");
        MST.solveTSP(a280Nodes, "a280");
        MST.solveTSP(ali535Nodes, "ali535");
    }
}
