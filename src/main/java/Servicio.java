import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Servicio {
    private HashMap<String,LinkedList<Tarea>> tareaXID;
    private final int M=7;
    private LinkedList<Tarea> tareas;
    private LinkedList<Procesador>procesadors;
    private LinkedList<Tarea> critica, noCritica;
    private ArbolBinario arbolBinario;

    //Completar con las estructuras y métodos privados que se
   /* requieran.

     * Expresar la complejidad temporal del constructor.
     */
    public Servicio(String pathProcesadores, String pathTareas) {
        tareas=new LinkedList<>();
        procesadors=new LinkedList<>();
        tareaXID=new HashMap<>();
        arbolBinario=new ArbolBinario();
        critica=new LinkedList<>();
        noCritica=new LinkedList<>();
        leerProcesadores(pathProcesadores,this.procesadors);
        leerTareas(pathTareas);
    }
    /*
     * Expresar la complejidad temporal del servicio 1.
     * LOG(N)
     */
    public Tarea servicio1(String ID) {
        int i=Integer.parseInt(ID)%M;
        for (Tarea t:tareaXID.get(i)){
            if (String.valueOf(t.getId()).equals(ID))
                return t;
        }
        return null;
    }
    /*
     * Expresar la complejidad temporal del servicio 2.
     * O(1)
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
    private void leerTareas(String archivo) {
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
                this.addTarea(tarea);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTarea(Tarea t){
        LinkedList<Tarea>ta=new LinkedList<>();
        if (t!=null){
            ta.add(t);
            servicio2(t.is_critica());
            tareas.add(t);
            tareaXID.put(String.valueOf(t.getId()),ta);
            this.arbolBinario.addNodo(new Nodo(t));
    }}

}
