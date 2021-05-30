import java.util.ArrayList;
import java.io.Serializable;

public class Part implements Serializable {
    String code;
    String name;
    String description;
    ArrayList<Part> partsList = new ArrayList<>();
    public Part(String code, String name,String description){
        this.code=code;
        this.name=name;
        this.description=description;


    }

}



