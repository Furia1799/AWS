

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class Cliente {

    public static void main(String[] args) {
        String ip = "34.229.250.35";
        int puerto = 6666;

        Socket cliente;
        try {
            cliente = new Socket(ip, puerto);
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            do {
                System.out.println("Introdusca un nombre :");
                String enviar = scanner.nextLine();
                out.writeUTF(enviar);
                
                if(enviar.equals("#")){
                    break;
                }
                out.flush();//limpiar bufer
                
                System.out.println("El total de la suma de su nombre en ascii es : ");
                String resivir = in.readUTF();
                System.out.println(resivir);
                System.out.println("--------------------------------------------");
            } while (true);
            
            in.close();
            out.close();
            cliente.close();

        } catch (IOException ex) {
            System.out.println("ERROR EN EL CLIENTE "+ex);
        }
    }
}
