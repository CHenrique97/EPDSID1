import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server extends PartRepo {
    public Server() {}
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        try {
            // Instantiating the implementation class
            PartRepo obj = new PartRepo();

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry();
            System.err.println("Binding String");
            String bindingString=sc.next();
            registry.bind(bindingString, stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}