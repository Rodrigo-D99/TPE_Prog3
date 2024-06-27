import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Servicio {
    private HashMap<String, Tarea> tareaXID; //indice para servicio 1
    private LinkedList<Tarea> tareas;
    private LinkedList<Procesador>procesadores;
    private LinkedList<Tarea> critica, noCritica; //indice para servicio 2
    private ArbolBinario arbolBinario; //indice para servicio 3
    private SolBacktracking solBacktracking;

    private SolGreedy solGreedy;

    //Completar con las estructuras y métodos privados que se
   /* requieran.

     * Expresar la complejidad temporal del constructor.
     */
    public Servicio(String path, String pathTareas) {
        tareas=new LinkedList<>();
        procesadores=new LinkedList<>();
        tareaXID=new HashMap<>();
        arbolBinario=new ArbolBinario();
        critica=new LinkedList<>();
        noCritica=new LinkedList<>();
        Lector.leerProcesadores(path,this.procesadores);
        Lector.leerTareas(pathTareas, this);
        /*
            Cada clase tiene una lista de procesadores y tareas independiente.
            En tiempo de ejecucion las clases pueden usar el metodo getCopy de Procesador
             para mantener sincronizadas las asignaciones que va realizando
        * */
        solBacktracking = new SolBacktracking(getProcesadores(),getTareas());
        solGreedy = new SolGreedy(getProcesadores(), getTareas());
    }
    /*
     * Expresar la complejidad temporal del servicio 1.
     * O(n)
     */
    public Tarea servicio1(String ID) {
        return this.tareaXID.get(ID);
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
     * O(n)
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
    }

    public void addTarea(Tarea t){
        if (t!=null){
            tareas.add(t);
            this.arbolBinario.addNodo(new Nodo(t));
            this.tareaXID.put(t.getId(), t);
            if (t.is_critica())
                this.critica.add(t);
            else
                this.noCritica.add(t);
    }}

    public LinkedList<Tarea> getTareas() {
        LinkedList<Tarea> temp = new LinkedList<>();
        this.tareas.forEach(t -> temp.add(t.getCopy()));
        return temp;
    }

    public LinkedList<Procesador> getProcesadores() {
        LinkedList<Procesador> temp = new LinkedList<>();
        this.procesadores.forEach(p -> temp.add(p.getCopy()));
        return temp;
    }
}
