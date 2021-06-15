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
    public static void addp(Hello stub, Part part,String currentServer) throws RemoteException {
        stub.addp(part,currentServer);
    };
    public static void main(String[] args) {
        try {
            Part part = new Part("placeholder","placeholder","mais placeholder",null,true);
            ArrayList <String[]> subPartList = new ArrayList<>();
            Scanner sc=new Scanner(System.in);

            String cliCommand="";
            System.out.println("Digite o repositorio que voce deseja acessar");
            cliCommand=sc.next();
            String currentServer=cliCommand;
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
                   case "addsubpart":
                       System.out.println("Digite o numero de sub partes");
                       int repetitions =sc.nextInt();
                       String[] data ={part.code,currentServer};
                       for (int i =0;i <repetitions;i++ ){
                            subPartList.add(data);
                       }
                        break;
                    case "addp":
                        part.subPartList=subPartList;
                        addp(stub,part,currentServer);
                        break;
                    case "changerepo":
                        System.out.println("Digite o novo repositorio");
                        cliCommand=sc.next();
                        currentServer=cliCommand;
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