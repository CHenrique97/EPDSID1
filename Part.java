import java.util.ArrayList;
import java.io.Serializable;

public class Part implements Serializable {
    String code;
    String name;
    String description;
    String repository;
    boolean isPrimitive;
    int numDirectSubparts;
    int  numPrimitiveSubparts;
    ArrayList<Part> partsList = new ArrayList<>();
    public Part(String code, String name,String description,String repository, Boolean isPrimitive, int numDirectSubparts, int numPrimitiveSubparts){
        this.code=code;
        this.name=name;
        this.repository=repository;
        this.isPrimitive=isPrimitive;
        this.description=description;
        this.numDirectSubparts=numDirectSubparts;
        this.numPrimitiveSubparts=numPrimitiveSubparts;

    }

}



