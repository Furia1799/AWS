
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {

    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
            /*
				Sobrescribir opcionalmente los métodos que escribimos en la interfaz
             */
            @Override
            public String hola(String nombre) throws RemoteException {
                return "Hola " + nombre + " Te Saludo Desde el Servidor!";
            }
         ;

            
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
        System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Ejemplo", remote); // Registrar calculadora
    }
}
