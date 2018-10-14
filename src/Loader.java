import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads the travelling salesman data
 */
public class Loader {

    public List<Node> loadData(File file){
        List<Node> nodes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while(br.ready()){
                String line = br.readLine();
                String[] tokens = line.split("\\s++");
                if (tokens[0].equals("")){
                    tokens = Arrays.copyOfRange(tokens, 1, 4);
                }
                if (isNumeric(tokens[0])){
                    double id = Double.parseDouble(tokens[0]);
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    nodes.add(new Node((int) id, x, y));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error in reading file!");
        }
        return nodes;
    }

    public boolean isNumeric(String s){
        try{
            double d = Double.parseDouble(s);

        } catch (NumberFormatException | NullPointerException npe){
            return false;
        }
        return true;
    }

}
