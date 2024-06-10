import java.util.*;

public class Backtracking {

    private LinkedList<Procesador> procesadores;
    private LinkedList<Tarea> tareas;
    private HashMap<Procesador, LinkedList<Tarea>> mejorAsignacion;
    private int mejorTiempoMaximo;
    private int cantidadEstadosGenerados;


    /**
     * Esta clase implementa una estrategia de backtracking para asignar tareas a procesadores,
     * minimizando el tiempo final de ejecución. La estrategia tiene en cuenta dos restricciones:
     * 1. Ningún procesador puede ejecutar más de 2 tareas críticas.
     * 2. Los procesadores no refrigerados no pueden exceder un tiempo máximo de ejecución especificado.
     */
    public Backtracking(LinkedList<Procesador> procesadores, LinkedList<Tarea> tareas) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.mejorAsignacion = new HashMap<>();
        this.mejorTiempoMaximo = Integer.MAX_VALUE;
        this.cantidadEstadosGenerados = 0;
        setMejorAsignacion();


    }
    private void setMejorAsignacion(){
        for (Procesador procesador : procesadores) {
            mejorAsignacion.put(procesador, new LinkedList<>());
        }
    }

    /**
     * Inicia el proceso de asignación de tareas utilizando la técnica de backtracking.
     */
    public void backtrack(int tiempoX) {
        if (!this.tareas.isEmpty() && !this.procesadores.isEmpty() && tiempoX>0)
            backtrack(new HashMap<>(),0,tiempoX);
    }

    /**
     * Realiza el proceso de backtracking para encontrar la mejor asignación de tareas a procesadores.
     * @param asignacionActual Mapa que representa la asignación actual de tareas a procesadores.
     * param tareaIndex Índice de la tarea actual a asignar.
     */
    private void backtrack(HashMap<Procesador, LinkedList<Tarea>> asignacionActual,int tareaIndex,int tiempoX) {
        if (tareaIndex == tareas.size()) {
            int tiempoMaximo = calcularTiempoMaximo(asignacionActual);
            if (tiempoMaximo < mejorTiempoMaximo) {
                mejorTiempoMaximo = tiempoMaximo;
                mejorAsignacion = new HashMap<>();
                for (Procesador p : asignacionActual.keySet()) {
                    mejorAsignacion.put(p, new LinkedList<>(asignacionActual.get(p)));
                }
                mostrarResultado();
            }
            cantidadEstadosGenerados++;
        }
        else {
            Tarea tarea = tareas.get(tareaIndex);
            for (Procesador procesador : procesadores) {
                if (puedeAsignar(procesador, tarea, asignacionActual,tiempoX)) {
                    asignacionActual.putIfAbsent(procesador, new LinkedList<>());
                    agregarTareaAProc(procesador,tarea,asignacionActual);
                    backtrack(asignacionActual, tareaIndex + 1,tiempoX);
                    sacarTareaAProc(procesador,tarea,asignacionActual);
                }
            }
        }
    }


    private void agregarTareaAProc(Procesador procesador, Tarea tarea, HashMap<Procesador, LinkedList<Tarea>> solucionParcial) {
        solucionParcial.get(procesador).add(tarea);
    }
    private void sacarTareaAProc(Procesador procesador, Tarea tarea, HashMap<Procesador, LinkedList<Tarea>> solucionParcial) {
        solucionParcial.get(procesador).remove(tarea);
    }
    /**
     * Verifica si una tarea puede ser asignada a un procesador específico cumpliendo las restricciones.
     * @param procesador El procesador al que se intenta asignar la tarea.
     * @param tarea La tarea que se intenta asignar.
     * @param asignacionActual Mapa que representa la asignación actual de tareas a procesadores.
     * @return true si la tarea puede ser asignada al procesador, false en caso contrario.
     */
    private boolean puedeAsignar(Procesador procesador, Tarea tarea, HashMap<Procesador, LinkedList<Tarea>> asignacionActual, int tiempoMaxNoRefrigerado) {
        LinkedList<Tarea> tareasAsignadas = asignacionActual.getOrDefault(procesador, new LinkedList<>());

        // Verificar el número de tareas críticas
        long tareasCriticas = tareasAsignadas.stream().filter(Tarea::is_critica).count();
        if (tarea.is_critica() && tareasCriticas >= 2) {
            return false;
        }
        // Verificar el tiempo máximo para procesadores no refrigerados
        else if ((!procesador.is_refrigerado() || procesador.getTiempoEjecucionMaximo() + tarea.getTiempo_ejec() <= tiempoMaxNoRefrigerado||solucionVacia())) {
            int tiempoTotal = tareasAsignadas.stream().mapToInt(Tarea::getTiempo_ejec).sum();
            return tiempoTotal + tarea.getTiempo_ejec() <= tiempoMaxNoRefrigerado;
        }

        return true;
    }
    private boolean solucionVacia() {
        for (Procesador nextProcesador : mejorAsignacion.keySet()) {
            if (!mejorAsignacion.get(nextProcesador).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula el tiempo máximo de ejecución de una asignación de tareas a procesadores.
     * @param asignacion Mapa que representa la asignación de tareas a procesadores.
     * retorna El tiempo máximo de ejecución.
     */
    private int calcularTiempoMaximo(HashMap<Procesador, LinkedList<Tarea>> asignacion) {
        int maximo = 0;
        for (LinkedList<Tarea> tareas : asignacion.values()) {
            int tiempoTotal = tareas.stream().mapToInt(Tarea::getTiempo_ejec).sum();
            if (tiempoTotal > maximo) {
                maximo = tiempoTotal;
            }
        }
        return maximo;
    }

    /**
     * Muestra el resultado de la mejor asignación encontrada.
     */
    public void mostrarResultado() {
        System.out.println("Backtracking");
        System.out.println("Solución obtenida: ");
        for (Procesador procesador : mejorAsignacion.keySet()) {
            System.out.println(procesador);
            for (Tarea tarea : mejorAsignacion.get(procesador)) {
                System.out.println("  " + tarea);
            }
        }
        System.out.println("Solución obtenida: tiempo máximo de ejecución: " + mejorTiempoMaximo);
        System.out.println("Métrica para analizar el costo de la solución (cantidad de estados generados): " + cantidadEstadosGenerados+"\n");
    }

}
