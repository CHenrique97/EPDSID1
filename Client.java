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

        Registry registry = LocateRegistry.getRegistry(null);
        return (Hello) registry.lookup(repo);
    };
    public static Part createp (String currentServer){
        Scanner sc=new Scanner(System.in);
        String partCode =  UUID.randomUUID().toString();
        System.out.println("Digite o nome da peca");
        String partName=sc.nextLine();
        System.out.println("Digite a descrição da peca");
        String partDesc=sc.nextLine();
        Part part = new Part(partCode,partName,partDesc, currentServer,true);
        part.subPartList = new ArrayList<>();
        System.out.println("Peca criada");
        System.out.println("Codigo da peca:"+part.code);
        System.out.println("Nome   da peca:"+part.name);
        System.out.println("Descriçao da peca:"+part.description);
        System.out.println("Repositorio da peca:"+part.repository);
        System.out.println("Peca e primitiva:"+part.isPrimitive);

        return part;
    }

    public static void listp(Hello stub) throws RemoteException {
        int length = stub.listGet().size();
        System.out.println(length);

        for (int i=0 ; i<length; i++){
            System.out.print(i+" : ");
            System.out.println(stub.listGet().get(i).code);
        }


    };
    public static Part getp(Hello stub, String code, ArrayList<Part> partList) throws RemoteException {
        System.out.println("/////////////// Peça selecionada ///////////////");
        return stub.getp(code,partList);
    };

    public static void showp(Part part) throws RemoteException {
        System.out.println("/////////////// Detalhes da peca ///////////////");

        System.out.println("Codigo: " + part.code);
        System.out.println("Nome: " + part.name);
        System.out.println("Descricao: " + part.description);
        System.out.println("E primitivo : " + part.isPrimitive);
        if(part.subPartList.size() > 0) {
            System.out.println("Esta peca contem subpartes:");
        }
        else {
            System.out.println("Esta peca nao contem subpartes.");
        }
        for (int i=0;i<part.subPartList.size();i++) {
            System.out.println("//////////// Detalhes da subparte " + i + " ////////////");
            System.out.println("Nome: " + part.subPartList.get(i)[0]);
            System.out.println("Repositorio: " + part.subPartList.get(i)[1]);
            System.out.println("Quantidade: " + part.subPartList.get(i)[2]);
        }
        System.out.println("///////////////////////////////////////////////");
    };
    public static void clearlist(Hello stub, int x) throws RemoteException {
        stub.clearlist(x);
    };
    public static Part addsubpart(String currentServer ,Part part,ArrayList <String[]> subPartList) throws RemoteException  {
        Scanner sc=new Scanner(System.in);
        System.out.println("Digite o numero de sub partes");
        String repetitions =sc.next();
        part.isPrimitive=false;
        String[] data ={part.code,currentServer,repetitions};
        subPartList.add(data);
        System.out.println("Pecas adicionadas");

        return part;
    };
    public static Part addforeignsubpart(Part part,ArrayList <String[]> subPartList) throws RemoteException  {
        Scanner sc=new Scanner(System.in);
        System.out.println("Digite o codigo da peca");
        String partCode =sc.next();
        System.out.println("Digite o repositorio");
        String foreignServer =sc.next();
        System.out.println("Digite o numero de sub partes");
        String repetitions =sc.next();
        part.isPrimitive=false;
        String [] data = new String[]{partCode, foreignServer, repetitions};
        subPartList.add(data);

        System.out.println("Pecas adicionadas");
        return part;
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
            Part subpart = new Part("placeholder","placeholder","mais placeholder",null,true);
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
                        part = addsubpart(currentServer,part,subPartList);
                       break;
                    case "addforeignsubpart":

                        part = addforeignsubpart(part,subPartList);
                        break;
                    case "addp":
                        System.out.println("adicionado parte ...");
                        part.subPartList=subPartList;
                        addp(stub,part,currentServer);
                        System.out.println("parte adicionada");
                        break;
                    case "createp":
                        part=createp(currentServer);
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