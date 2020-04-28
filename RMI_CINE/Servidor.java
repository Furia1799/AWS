
import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente

    public Servidor() {
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Scanner scanner = new Scanner(System.in);
        Pelicula pelicula = new Pelicula();
        //Servidor pelis

        Remote remote = UnicastRemoteObject.exportObject(new Interface() {

            /*
				Sobrescribir opcionalmente los métodos que escribimos en la interfaz
             */
            @Override
            public File comprar_boletos(int id, int cant) throws RemoteException {//atencion id
                File boleto=null;
                try {
                    boleto = pelicula.venta_boleto(id, cant);
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                return boleto;
            }

            @Override
            public String add_pelicula() throws RemoteException {
                String nombre, genero, duracion, clasificacion, horario;
                int asientos;
                double precio_boleto;
                
                System.out.println("** Agregar pelicula **");
                //System.out.println("Id para indentificar la pelicula :");
                //id = teclado.nextInt();
                System.out.println("Nombre de la pelicula :");
                nombre = scanner.nextLine();
                System.out.println("Genero de la pelicula :");
                genero = scanner.nextLine();
                System.out.println("Duracion de la pelicula :");
                duracion = scanner.nextLine();
                System.out.println("Clasificacion de la pelicula :");
                clasificacion = scanner.nextLine();
                System.out.println("Horario de la pelicula :");
                horario = scanner.nextLine();
                System.out.println("Capacidad de la pelicula :");
                asientos = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Precio por boleto :");
                precio_boleto = scanner.nextDouble();
                //peliculas.add(pelicula);
                scanner.nextLine();
                pelicula.crear_pelicula(nombre, genero, duracion, clasificacion, horario, asientos,precio_boleto);
                return "--- SE AGREGO UNA PELICULA CON EXITO ---\n";
            }

            @Override
            public String mostrar_peliculas() throws RemoteException {
               int cantidad =  pelicula.getLista_peliculas().size();
                
                if(cantidad > 0){
                    return pelicula.getLista_peliculas().toString();
                }else {
                    return "--- SIN PELICULAS --- \n";
                }   
            }

        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
        System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Ejemplo", remote); // Registrar calculadora
    }

}
