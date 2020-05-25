
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bryan
 */
public class Hilo_Server extends Thread {

    private Socket socket_cliente = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private String nombre = "";
    //private Usuarios usuario = null;
    private final int con;
    private final int PUERTO = 6666;

    public Hilo_Server(Socket cliente , int con) { //Usuarios usuario
        this.socket_cliente = cliente;
        this.con = con;
       // this.usuario = usuario;

    }

    @Override
    public void run() {
        System.out.println("Hilo comenzo servidor");
        //Usuarios usuario = new Usuarios(nombre, cliente);

        try {
            do {

                in = new DataInputStream(socket_cliente.getInputStream());

                String nombre_recibido = in.readUTF();
                String mensaje = in.readUTF();
                System.out.println("mensaje recibido del hilo servidor");
                System.out.println(nombre_recibido + " >>> " + mensaje);

                if (mensaje.equals("#")) {
                    break;
                }
                /*
                for (int i = 0; i < usuario.getLista_usuarios().length; i++) {
                    Socket enviar_destino = new Socket(usuario.obtener_usuario(i).getIp(), PUERTO);
                    out = new DataOutputStream(enviar_destino.getOutputStream());
                    out.writeUTF(nombre_recibido);
                    out.writeUTF(mensaje);
                    System.out.println("menajes enviados a los usuarios");
                    enviar_destino.close();

                }*/
                
                 Socket enviar_destino = new Socket("192.168.56.1", PUERTO);
                    out = new DataOutputStream(enviar_destino.getOutputStream());
                    out.writeUTF(nombre_recibido);
                    out.writeUTF(mensaje);
                    System.out.println("menajes enviados a los usuarios");
                    enviar_destino.close();

                //out.writeUTF(nombre_recibido);
                //out.writeUTF(mensaje);
                // enviar_mensaje(nombre_recibido, mensaje);
                //out.flush();
                System.out.println("------------------------------------");
            } while (true);

            in.close();
            out.close();
            socket_cliente.close();

        } catch (Exception e) {
            System.out.println("perdio conexion con cliente");
        }
        System.out.println("Se a desconectado el cliente");

    }

}
