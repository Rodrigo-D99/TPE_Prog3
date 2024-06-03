import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Servicio {
    private LinkedList<Tarea> tareas;
    private LinkedList<Procesador>procesadors;

    private LinkedList<Tarea> critica, noCritica;


    //Completar con las estructuras y métodos privados que se
   /* requieran.

     * Expresar la complejidad temporal del constructor.
     */
    public Servicio(String pathProcesadores, String pathTareas) {
        tareas=new LinkedList<>();
        procesadors=new LinkedList<>();
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
        if (esCritica)
            return critica;
        return noCritica;
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

    public void addTarea(Tarea t){
        if (t!=null)
            this.tareas.add(t);
    }

}
