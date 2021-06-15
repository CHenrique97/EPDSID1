import java.util.ArrayList;
import java.io.Serializable;

public class Part implements Serializable {
    String code;
    String name;
    String description;
    String repository;
    boolean isPrimitive;

    ArrayList<Part> partsList = new ArrayList<>();
    ArrayList <String[]> subPartList = new ArrayList<>();
    public Part(String code, String name,String description,String repository, Boolean isPrimitive){
        this.code=code;
        this.name=name;
        this.repository=repository;
        this.isPrimitive=isPrimitive;
        this.description=description;

    }

}



