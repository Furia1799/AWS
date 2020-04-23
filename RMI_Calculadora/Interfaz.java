import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interfaz extends Remote {
    
    double suma(double number_one, double number_two) throws RemoteException;
    double resta(double number_one, double number_two) throws RemoteException;
    double multiplicacion(double number_one, double number_two) throws RemoteException;
    double divicion(double number_one, double number_two) throws RemoteException;
}
