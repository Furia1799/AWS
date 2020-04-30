

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bryan
 */
public class Servidor {

    public int suma_nombre(String cadena) {
        int suma = 0;

        for (int i = 0; i < cadena.length(); i++) {
            if (!((int) cadena.charAt(i) == 32)) {
                suma += (int) cadena.charAt(i);
                System.out.println("letra : " + cadena.charAt(i) + " Valor ascii : " + (int) cadena.charAt(i));
            }

        }
        return suma;
    }

    public static void main(String[] args) {
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            Servidor clase = new Servidor();
            ServerSocket server_socket = new ServerSocket(6666);
            System.out.println("SERVER CONECTADO ");

            Socket cliente_socket = new Socket();
            cliente_socket = server_socket.accept();

            in = new DataInputStream(cliente_socket.getInputStream());
            out = new DataOutputStream(cliente_socket.getOutputStream());
            do {

                String cadena = in.readUTF();
                
                if (cadena.equals("#")) {
                    break;
                }
                
                System.out.println("respuesta es " + cadena);
                String suma = String.valueOf(clase.suma_nombre(cadena));
                
                out.writeUTF(suma);
                out.flush();
                System.out.println("---------------------------------------");

            } while (true);

            in.close();
            out.close();
            server_socket.close();
        } catch (IOException ex) {
            System.out.println("ERROR EN SERVER " + ex);
        }
    }
}
