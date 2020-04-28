
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {

    private static final String IP = "172.31.47.38"; // Puedes cambiar a localhost172.31.80.107
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interface interfaz = (Interface) registry.lookup("Ejemplo"); //Buscar en el registro...

        String respuesta;
        int opccion = 0;
        File ticket;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("*** CINE UNITONAL ***");
            System.out.println("1.- Mostrar peliculas ");//regresara un arreglo
            System.out.println("2.- Comprar boletos ");//regresara un file
            System.out.println("3.- Agregar Pelicula ");//se ejecutan desde server
            System.out.println("4.- SALIR");
            System.out.println("Ingrese la opcion : ");
            opccion = scanner.nextInt();

            switch (opccion) {
                case 1:
                    System.out.println("***MOSTRAR PELICULAS***");
                    respuesta = interfaz.mostrar_peliculas();
                    System.out.println(respuesta);
                    break;
                case 2:
                    System.out.println("***COMPRAR BOLETOS***");
                    System.out.println("Ingrese id de la pelicula: ");
                    int id = scanner.nextInt();
                    System.out.println("Ingrese cantidad de boletos: ");
                    int cantidad = scanner.nextInt();
                    
                    
                    ticket = interfaz.comprar_boletos(id, cantidad);
                    System.out.println("-----COMPRA CONFIRMADA-----");
                    System.out.println("Se ha descargado el archivo!");
                    System.out.println("ruta de descarga: " + ticket.getAbsolutePath());
                    break;
                case 3:
                    System.out.println("Eres Administrador ?  si = 1 , no = 2");
                    int comprobar = scanner.nextInt();

                    if (comprobar == 1) {
                        System.out.println("Para agregar una pelicula valla al servidor");
                        respuesta = interfaz.add_pelicula();
                        System.out.println(respuesta);
                    } else {
                        System.out.println("Usted nada mas puede mostrar peliculas y comprar boletos \n");
                    }
                    break;
                case 4:
                    System.out.println("Gracias por su Compra.... ");
                    break;
                default:
                    System.out.println("Opccion incorrecta , ingrese la opccion de nuevo ");
                    break;
            }
        } while (opccion != 4);
    }

}
