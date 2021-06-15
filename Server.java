import java.io.InputStream;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Properties;

public class Server extends PartRepo {
    public Server() {}
    public static Part partBuilder (String partName,Properties prop){
        String[] partComponents=prop.getProperty(partName).split(",");


        Part newPart = new Part(partComponents[0], partComponents[1], partComponents[2], partComponents[3], Boolean.parseBoolean(partComponents[4]));
        if (!partComponents[7].equals("none")) {

            String[] smallerParts = partComponents[7].split(";");
            for (int i = 0; i < smallerParts.length; i++) {
                System.out.println(' '+smallerParts[i]);
                newPart.partsList.add(partBuilder(smallerParts[i], prop));
            }
        }
        return newPart;
    }


    public static Part[] repoBuilder(String serverName,Hello stub,Properties prop) throws RemoteException {
        String[] partList=prop.getProperty(serverName).split(";");
        for (int i =0; i< partList.length; i++ ){

            System.out.println(partList[i]);
            stub.addp(partBuilder(partList[i], prop),serverName);

        }
        return null;
    }
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        try {
            try (InputStream input = Server.class.getClassLoader().getResourceAsStream("server.properties")) {

                Properties prop = new Properties();

                if (input == null) {
                    System.out.println("Sorry, unable to find config.properties");
                    return;
                }

                prop.load(input);
                String[] serverList= prop.getProperty("serverList").split(";");
                Hello[] stub =new Hello [serverList.length];
                PartRepo[] obj = new PartRepo[serverList.length];
                for (int i=0; i < serverList.length; i++){
                    obj[i]= new PartRepo();
                    stub[i] = (Hello) UnicastRemoteObject.exportObject(obj[i], i);
                    Registry registry = LocateRegistry.getRegistry();
                    System.err.println("Binding String");
                    System.err.println(serverList[i]);
                    registry.bind(serverList[i], stub[i]);
                    repoBuilder(serverList[i], stub[i],prop);
                    System.err.println("Server ready");


                }
            }
            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}