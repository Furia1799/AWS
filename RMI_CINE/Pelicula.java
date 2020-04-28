
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Pelicula {

    //atributos
    private int id;
    private String nombre;
    private String genero;
    private String duracion;
    private String clasificacion;
    private String horario;
    private int asientos;
    private double precio_boleto;
    private int cont;

    ArrayList<Pelicula> lista_peliculas = new ArrayList();
    Pelicula pelicula;
    int asientos_disponibles;
    Date date = new Date();
    DateFormat hour = new SimpleDateFormat("HH:mm:ss");
    
    DateFormat fecha = new SimpleDateFormat("dd/MM/yy");

    public Pelicula() {

    }

    public Pelicula(int id, String nombre, String genero, String duracion, String clasificacion, String horario, int asientos, double precio_boleto) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.horario = horario;
        this.asientos = asientos;
        this.asientos_disponibles = asientos;
        this.precio_boleto = precio_boleto;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Pelicula> getLista_peliculas() {
        return lista_peliculas;
    }

    public void setLista_peliculas(ArrayList<Pelicula> lista_peliculas) {
        this.lista_peliculas = lista_peliculas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public int getAsientos_disponibles() {
        return asientos_disponibles;
    }

    public void setAsientos_disponibles(int asientos_disponibles) {
        this.asientos_disponibles = asientos_disponibles;
    }

    public double getPrecio_boleto() {
        return precio_boleto;
    }

    public void setPrecio_boleto(double precio_boleto) {
        this.precio_boleto = precio_boleto;
    }

    // metodos pelicula
    public void crear_pelicula(String nombre, String genero,
            String duracion, String clasificacion, String horario, int asientos, double precio_boleto) {//ser

        Pelicula pelicula = new Pelicula(id, nombre, genero, duracion,
                clasificacion, horario, asientos, precio_boleto);
        lista_peliculas.add(pelicula);
        id++;
        System.out.println("----Se agrego una pelicula correctamente---- \n");
    }

    public void mostrar_lista_peliculas() {
        int num = 1;
        for (Object obj : lista_peliculas) {
            System.out.println("Pelicula " + num);
            pelicula = (Pelicula) obj;
            System.out.println(pelicula.toString());
            num++;
            System.out.println("");
        }
        System.out.println("Se mostro la lista de peliculas \n");
    }

    public File venta_boleto(int id_pelicula, int boletos) throws IOException {
        cont++;
        File file = new File("/home/ubuntu/GIT/AWS/RMI_CINE/"+"Ticket" + cont + ".txt");
        FileWriter fr = null;
        fr = new FileWriter(file);
        boolean encontrar = false;

        for (Object obj : lista_peliculas) {
            pelicula = (Pelicula) obj;
            if (pelicula.getId() == id_pelicula) { //verificar si existe la pelicula
                //System.out.println("**PELICULA ENCONTRADA");
                //System.out.println(pelicula.toString());
                //System.out.println("ASIENTOS DISPONIBLE : " + pelicula.getAsientos_disponibles());
                if (pelicula.getAsientos_disponibles() - boletos > 0) { //verificar si podemos comprar los boletos
                    int descuento = pelicula.getAsientos_disponibles() - boletos;
                    pelicula.setAsientos_disponibles(descuento);
                    //System.out.println("Asientos comprados correctamente");
                    //System.out.println(" total de ASIENTOS DISPONIBLE : " + pelicula.getAsientos_disponibles());
                    encontrar = true;
                    try {
                        fr.write("Hora: " + hour.format(date));
                        fr.write(" Fecha: " + fecha.format(date) + "\n");
                        fr.write("Pelicula: " + lista_peliculas.get(id_pelicula).getNombre() + "\n");
                        fr.write("Id pelicula: " + id_pelicula + "\n");
                        fr.write("Horario: " + lista_peliculas.get(id_pelicula).getHorario() + "\n");
                        fr.write("Precio por boleto: " + lista_peliculas.get(id_pelicula).getPrecio_boleto()+ "\n");
                        fr.write("Asientos Reservados: " + boletos + "\n");
                        fr.write("Total : " + (lista_peliculas.get(id_pelicula).getPrecio_boleto() * boletos));
                        fr.write("\r\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        //close resources
                        try {
                            fr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                } else {
                    System.out.println("boletos no disponibles");
                }

            }

        }

        if (encontrar == false) {
            System.out.println("--Pelicula no encontrada--");
            System.out.println("intente de nuevo \n");
        }
        System.out.println("\n");
        return file;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", nombre=" + nombre + ", genero="
                + genero + ", duracion=" + duracion + ", clasificacion=" + clasificacion
                + ", horario=" + horario + ", asientos totales=" + asientos
                + ", precio boleto =" + precio_boleto
                + ", asientos DISPONIBLES=" + asientos_disponibles + "}\n";
    }

}
