import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Servicio {
    private ArrayList<Tarea> tareas;
    private ArrayList<Procesador>procesadors;


    //Completar con las estructuras y métodos privados que se
   /* requieran.

     * Expresar la complejidad temporal del constructor.
     */
    public Servicio(String pathProcesadores, String pathTareas) {
        tareas=new ArrayList<>();
        procesadors=new ArrayList<>();
        leerProcesadores(pathProcesadores,this.procesadors);
        leerTareas(pathTareas,tareas);
    }
    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    public Tarea servicio1(String ID) {
        return null;
    }
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
    public List<Tarea> servicio2(boolean esCritica) {
        return List.of();
    }
    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return List.of();
    }

    private static void leerProcesadores(String archivo, List<Procesador> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Leer y descartar la primera línea (encabezado)
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String codigo = datos[1];
                boolean refrigerado = Boolean.parseBoolean(datos[2]);
                int anio = Integer.parseInt(datos[3]);
                Procesador procesador = new Procesador(id,anio,refrigerado,codigo  );
                lista.add(procesador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void leerTareas(String archivo, List<Tarea> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Leer y descartar la primera línea (encabezado)
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                int tiempo = Integer.parseInt(datos[2]);
                boolean critica = Boolean.parseBoolean(datos[3]);
                int prioridad = Integer.parseInt(datos[4]);
                Tarea tarea = new Tarea(id, nombre, tiempo, critica, prioridad);
                lista.add(tarea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
