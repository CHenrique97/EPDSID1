import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// Creating Remote interface for our application 
public interface Hello extends Remote {
    void printMsg() throws RemoteException;
    Part Data(int x) throws RemoteException;
    ArrayList<Part> listGet() throws RemoteException;
    void clearlist(int x) throws RemoteException;
    void addsubpart(int x,Part part) throws RemoteException;
    void addp(Part part) throws RemoteException;
    Part getp(String code,ArrayList<Part> partsList)throws RemoteException;
} 