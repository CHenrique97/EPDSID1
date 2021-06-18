import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

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
        for (int i=0;i<part.subPartList.size();i++) {
            System.out.println(part.subPartList.get(i)[0]);
            System.out.println(part.subPartList.get(i)[1]);
            System.out.println(part.subPartList.get(i)[2]);
        }
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
    public static void deletesubp(Hello stub, Part part) throws RemoteException, NotBoundException {

        for (int i=0;i<part.subPartList.size();i++){
            System.out.println("Apagando parte "+part.subPartList.get(i)[0]+" no server "+part.subPartList.get(i)[1]);
            System.out.println("Mudando de servidor");
            stub=bind(part.subPartList.get(i)[1]);
            System.out.println("apagando peca");
            stub.deletesubp(part.subPartList.get(i)[0]);
            System.out.println("removendo da lista");

        }
            part.subPartList=new ArrayList<>();
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
                        System.out.println("Digite o codigo da peça");
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
                       String repetitions =sc.next();
                       part.isPrimitive=false;
                       String[] data ={part.code,currentServer,repetitions};
                       subPartList.add(data);
                       System.out.println("Peças adicionadas");

                        break;
                    case "addp":
                        System.out.println("adicionado parte ...");
                        part.subPartList=subPartList;
                        addp(stub,part,currentServer);
                        System.out.println("parte adicionada");
                        break;
                    case "createp":
                        String partCode =  UUID.randomUUID().toString();
                        System.out.println("Digite o nome da peça");
                        String partName=sc.next();
                        System.out.println("Digite a descrição da peça");
                        String partDesc=sc.next();
                        part = new Part(partCode,partName,partDesc, currentServer,true);
                        System.out.println("Peça criada");
                        System.out.println("Codigo da peça:"+part.code);
                        System.out.println("Nome   da peça:"+part.name);
                        System.out.println("Descriçao da peça:"+part.description);
                        System.out.println("Repositorio da peça:"+part.repository);
                        System.out.println("Peça é primitiva:"+part.isPrimitive);
                        break;
                    case "changerepo":
                        System.out.println("Digite o novo repositorio");
                        cliCommand=sc.next();
                        currentServer=cliCommand;
                        stub = bind(cliCommand);
                        break;
                    case "deletesubp":

                        deletesubp(stub,part);

                        break;
                    default:
                        System.out.println("Comando não reconhecido");
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