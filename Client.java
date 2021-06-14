import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
    public static Part getp(Hello stub, String code, ArrayList<Part> partList) throws RemoteException {
        return stub.getp(code,partList);
    };
    public static void showp(Part part) throws RemoteException {
        System.out.println(part.code);
        System.out.println(part.name);
        System.out.println(part.description);
        System.out.println(part.partsList.size() +" subpeças");

    };
    public static void clearlist(Hello stub, int x) throws RemoteException {
        stub.clearlist(x);
    };
    public void addsubpart(Hello stub, int x, Part part) throws RemoteException  {
        stub.addsubpart(x,part);
    };
    public static void addp(Hello stub, Part part) throws RemoteException {
        stub.addp(part);
    };
    public static void main(String[] args) {
        try {
            Part part = new Part("placeholder","placeholder","mais placeholder",null,true,0,0);

            Scanner sc=new Scanner(System.in);

            String cliCommand="";
            System.out.println("Digite o repositorio que voce deseja acessar");
            cliCommand=sc.next();
            Hello stub = bind(cliCommand);
            while (!cliCommand.equals("quit")){
                cliCommand=sc.next();
                switch (cliCommand) {
                    case "listp":
                        listp(stub);
                        break;
                    case "getp":
                        String code = sc.next();
                        part=getp(stub,code,stub.listGet());
                        break;
                    case "showp":
                        showp(part);
                        break;
                    case "clearList":
                        ArrayList tempList=stub.listGet();
                        int position=tempList.indexOf(part);
                        clearlist(stub,position);
                        break;
                   /* case "addsubpart":
                        ArrayList tempList2=stub.listGet();
                        int position2=tempList2.indexOf(part);
                        clearlist(stub,position2);
                        break;*/
                    case "addp":
                        addp(stub,part);
                        break;
                    case "changerepo":
                        System.out.println("Digite o novo repositorio");
                        cliCommand=sc.next();
                        stub = bind(cliCommand);
                        break;
                }

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