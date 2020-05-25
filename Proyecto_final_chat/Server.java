
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bryan
 */
public class Server {

    public static void main(String[] args) {
        final int PUERTO = 6666;
        ServerSocket socket_server = null;
        Socket client_socket = null;
        int con = 0;
        
        //Usuarios lista_usuarios = new Usuarios();

        try {
            socket_server = new ServerSocket(PUERTO);

            System.out.println("Server Connected");
            while (true) {
                client_socket = socket_server.accept();
                //System.out.println("Client : " + client_socket.getInetAddress().getHostName());
                String usuario_ip = (String) client_socket.getInetAddress().getHostName();
                System.out.println("nombre : " + usuario_ip);
               // Usuarios usuario = new Usuarios(usuario_ip);
               // lista_usuarios.agregar(con, usuario);
                System.out.println("usuario agregado a la lista");
                //System.out.println("lista : "+Arrays.toString(lista_usuarios.getLista_usuarios()));
                

                Hilo_Server hilo = new Hilo_Server(client_socket,con); //,lista_usuarios
                hilo.start();
                con++;
            }

        } catch (Exception e) {
            System.out.println("Erro de server " + e);
        }

    }

}
