import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    private Client() {}

    public static Hello bind(String repo) throws RemoteException, NotBoundException {
        // Getting the registry
        Registry registry = LocateRegistry.getRegistry(null);
        return (Hello) registry.lookup(repo);
    };
    public static void listp(Hello stub) throws RemoteException {
        int length = stub.listGet().size();
        System.out.println(length);

        for (int i=0 ; i<length; i++){
            System.out.print(i+" : ");
            System.out.println(stub.listGet().get(i).code);
        }


    };
    public  void getp() {
        //todo
    };
    public static void showp(Part part, int x) throws RemoteException {
        System.out.println(part.code);
        System.out.println(part.name);
        System.out.println(part.description);
        System.out.println(part.partsList.size() +" subpeças");

    };
    public void clearlist() {

    };
    public void addsubpart() {
        //todo
    };
    public  void addp() {
        //todo
    };
    public static void main(String[] args) {
        try {
            Part part = new Part("placeholder","placeholder","mais placeholder");
            Scanner sc=new Scanner(System.in);
            String cliCommand="";
            cliCommand=sc.next();
            while (!cliCommand.equals("quit")){
                cliCommand=sc.next();

                Hello stub = bind("Hello");
                listp(stub);
                //showp(part,0);

            }


            // Looking up the registry for the remote object


            // Calling the remote method using the obtained object


            // System.out.println("Remote method invoked");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}