
public class PartRepo implements Hello {
    Part newPart = new Part("Amogus");
    Part smallerPart = new Part("VerySus");
    // Implementing the interface method
    public void printMsg() {

        newPart.partsList.add(smallerPart);
        System.out.println(newPart.code);
        System.out.println(newPart.partsList.size());
    }
    public String Code() {
        return smallerPart.code;
    }

}