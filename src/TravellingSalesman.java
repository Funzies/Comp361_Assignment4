import java.io.File;
import java.util.List;

public class TravellingSalesman {

    public static void main(String[] args){
        File berlin52 = new File(args[0]);
        File kroB150 = new File(args[1]);
        File a280 = new File(args[2]);
        File ali535 = new File(args[3]);

        Loader loader = new Loader();
        List<Node> berlin52Nodes = loader.loadData(berlin52);
        List<Node> kroB150Nodes = loader.loadData(kroB150);
        List<Node> a280Nodes = loader.loadData(a280);
        List<Node> ali535Nodes = loader.loadData(ali535);

        //finding solutions using MST Heuristic algorithm
        MSTHeuristic MST = new MSTHeuristic();
        MST.solveTSP(berlin52Nodes, "berlin52");
        MST.solveTSP(kroB150Nodes, "kroB150");
        MST.solveTSP(a280Nodes, "a280");
        MST.solveTSP(ali535Nodes, "ali535");

        //finding solution to a280 using Simulated Annealing algorithm
        //WARNING: this one may take a while.
        SimulatedAnnealing SA = new SimulatedAnnealing();
        SA.solveTSP(a280Nodes, "a280");
    }
}
