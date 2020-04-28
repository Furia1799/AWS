import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {

    String add_pelicula() throws RemoteException;

    String mostrar_peliculas() throws RemoteException;

    File comprar_boletos(int id, int cant) throws RemoteException;

}