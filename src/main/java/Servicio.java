import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Servicio {
    private final int MAX_TIEMPO = 100;
    private HashTableManager tareaXID; //indice para servicio 1
    private LinkedList<Tarea> tareas;
    private LinkedList<Procesador>procesadores;
    private LinkedList<Tarea> critica, noCritica; //indice para servicio 2
    private ArbolBinario arbolBinario; //indice para servicio 3
    private Backtracking solBacktracking;

    private SolGreedy solGreedy;

    //Completar con las estructuras y métodos privados que se
   /* requieran.

     * Expresar la complejidad temporal del constructor.
     */
    public Servicio(String path, String pathTareas) {
        tareas=new LinkedList<>();
        procesadores=new LinkedList<>();
        tareaXID=new HashTableManager();
        arbolBinario=new ArbolBinario();
        critica=new LinkedList<>();
        noCritica=new LinkedList<>();
        leer(path,this.procesadores);
        leerTareas(pathTareas);
        solBacktracking = new Backtracking(procesadores,tareas);
        this.solGreedy = new SolGreedy(procesadores, tareas);
    }
    /*
     * Expresar la complejidad temporal del servicio 1.
     * LOG(N)
     */
    public Tarea servicio1(String ID) {
        return this.tareaXID.getTarea(ID);
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
        prioridadInferior = Math.max(prioridadInferior, 0);
        prioridadSuperior = Math.min(prioridadSuperior, 100);
        if (prioridadSuperior>=prioridadInferior)
            return this.arbolBinario.getNodosPrioridad(prioridadInferior, prioridadSuperior);
        return null;
    }

    public void asignarTareas(int tiempoDEMax){
        tiempoDEMax = Math.abs(tiempoDEMax);
        System.out.println("\n\nServicio: llamar Greedy\n");
        solGreedy.greedy(tiempoDEMax);
        solBacktracking.backtracking(tiempoDEMax);
//        if (solGreedy.existeSol()){
//            System.out.println("\n\nServicio: existe una solucion, llamar Backtracking=TRUE\n");
//            solBacktracking.backtracking(tiempoDEMax);
//        }
//        else {
//            System.out.println("Servicio:\nNo existe una solucion\nLlamar Backtracking: FALSE");
//        }
    }

    private static void leer(String archivo, List<Procesador> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Leer y descartar la primera línea (encabezado)
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String codigo = datos[1];
                boolean refrigerado = Boolean.parseBoolean(datos[2]);
                int anio = Integer.parseInt(datos[3]);
                Procesador procesador = new Procesador(id,anio,refrigerado,codigo);
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

    private void addTarea(Tarea t){
        if (t!=null){
            tareas.add(t);
            this.arbolBinario.addNodo(new Nodo(t));
            this.tareaXID.addTarea(t);
            if (t.is_critica())
                this.critica.add(t);
            else
                this.noCritica.add(t);
    }}
}
