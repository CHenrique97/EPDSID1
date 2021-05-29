import java.util.ArrayList;
import java.util.Arrays;

public class PartRepo implements Hello {
    Part newPart = new Part("Amogus","Columbus","Very sus Indeed");

    Part smallerPart = new Part("VerySus","Augustus","Very sus Indeed");
    ArrayList<Part> partsList = new ArrayList<>(Arrays. asList(newPart,smallerPart));

    // Implementing the interface method
    public void printMsg() {
        String[] components= {"mini peça","43"};
        newPart.partsList.add(components);
        System.out.println(newPart.code);
        System.out.println(newPart.partsList.size());
    }
    public Part Data(int x ) {
        return partsList.get(x);
    }
    public void bind() {//todo
    };
    public ArrayList<Part>  listGet() {
        return partsList;
    };
    public  void getp() {
        //todo
    };
    public void showp() {
        //todo
    };
    public void clearlist() {
        //todo
    };
    public void addsubpart() {
        //todo
    };
    public  void addp() {
        //todo
    };
}