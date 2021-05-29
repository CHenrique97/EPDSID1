import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    private Client() {}
    Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(null);

            // Looking up the registry for the remote object
            Hello stub = (Hello) registry.lookup("Hello");
            Hello stub2 =(Hello) registry.lookup("GoodBye");
            // Calling the remote method using the obtained object
            stub.printMsg();
            System.out.println(stub2.Code());
            // System.out.println("Remote method invoked");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}