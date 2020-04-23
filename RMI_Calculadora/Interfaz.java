import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interfaz extends Remote {
    
    double suma(double number_one, double number_two) throws RemoteException;
}
