import java.rmi.Remote;
import java.rmi.RemoteException;

/*
	Declarar firma de métodos que serán sobrescritos
*/
public interface Interfaz extends Remote {
    String hola(String nombre) throws RemoteException;
    
}