import java.util.ArrayList;
import java.util.Arrays;

public class PartRepo implements Hello {
    Part newPart = new Part("Amogus","Columbus","Very sus Indeed");

    Part smallerPart = new Part("VerySus","Augustus","Very sus Indeed");
    ArrayList<Part> partsList = new ArrayList<>(Arrays. asList(newPart,smallerPart));

    // Implementing the interface method
    public void printMsg() {

        newPart.partsList.add(smallerPart);
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
    public  Part getp(String code,ArrayList<Part> partsList) {
        for (int k = 0; k<partsList.size();k++){
            if (partsList.get(k).code.equals(code)){
                return partsList.get(k);
            }


        }
        return null;
    }
    public void clearlist(int x) {
        partsList.get(x).partsList= new ArrayList<>();
    };
    public void addsubpart(int x,Part part) {
        partsList.get(x).partsList.add(part);
    };
    public  void addp(Part part) {
        partsList.add(part);
    };
}