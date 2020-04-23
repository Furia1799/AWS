
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {

    private static final String IP = "172.31.47.38"; // Puedes cambiar a localhost
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interfaz interfaz = (Interfaz) registry.lookup("Ejemplo"); //Buscar en el registro...

        Scanner scanner = new Scanner(System.in);

        double number_one = 0, number_two = 0, result = 0;
        int opccion = 0;

        do {

            System.out.println("*******MENU*******\n");
            System.out.println("Suma........-> 1 ");
            System.out.println("Resta.......-> 2 ");
            System.out.println("Multiplicar.-> 3 ");
            System.out.println("Divir.......-> 4 ");
            System.out.println("");
            System.out.println("----SALIR----> 5 \n");
            System.out.println("++ Ingrese una opccion : ->");
            opccion = scanner.nextInt();

            if (opccion <= 4 && opccion > 0) {
                System.out.println("Ingrese el Valor del Numero (1) :");
                number_one = scanner.nextDouble();
                System.out.println("Ingrese el Valor del Numero (2) :");
                number_two = scanner.nextDouble();
            }

            switch (opccion) {
                case 1:
                    System.out.println("Opccion elejida --Suma--");
                    result = interfaz.suma(number_one, number_two);
                    break;
                case 2:
                    System.out.println("Opccion elejida --Resta--");
                    result = interfaz.resta(number_one, number_two);
                    break;
                case 3:
                    System.out.println("Opccion elejida --Multiplicacion--");
                    result = interfaz.multiplicacion(number_one, number_two);
                    break;
                case 4:
                    System.out.println("Opccion elejida --Divicion--");
                    result = interfaz.divicion(number_one, number_two);
                    break;
                case 5:
                    System.out.println("-- GRACIAS POR SU ATENCION , ADIOS --");
                    break;
                default:
                    System.out.println("OPCCION INCORRECTA :(");
                    break;

            }

            if (opccion <= 4 && opccion > 0) {
                System.out.println("Resultado is : " + result);
            }

            scanner.nextLine();
            System.out.println("------------------------------------------- \n");


            /* respuesta = interfaz.resta(number_one, number_two);

            System.out.println("Respuesta => " + respuesta);
            System.out.println("Presiona ENTER para continuar");
            scanner.nextLine();
             */
        } while (opccion != 5);
    }
}
