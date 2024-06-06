import java.util.*;

public class Backtracking {

    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private Map<Procesador, List<Tarea>> mejorAsignacion;
    private int mejorTiempoMaximo;
    private int cantidadEstadosGenerados;
    private int tiempoMaxNoRefrigerado;

    /**
     * Esta clase implementa una estrategia de backtracking para asignar tareas a procesadores,
     * minimizando el tiempo final de ejecución. La estrategia tiene en cuenta dos restricciones:
     * 1. Ningún procesador puede ejecutar más de 2 tareas críticas.
     * 2. Los procesadores no refrigerados no pueden exceder un tiempo máximo de ejecución especificado.
     */
    public Backtracking(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaxNoRefrigerado) {
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoMaxNoRefrigerado = tiempoMaxNoRefrigerado;
        this.mejorAsignacion = new HashMap<>();
        this.mejorTiempoMaximo = Integer.MAX_VALUE;
        this.cantidadEstadosGenerados = 0;


        for (Procesador procesador : procesadores) {
            mejorAsignacion.put(procesador, new ArrayList<>());
        }
    }


    /**
     * Inicia el proceso de asignación de tareas utilizando la técnica de backtracking.
     */
    public void asignarTareas() {
        backtrack(new HashMap<>(), 0);
    }

    /**
     * Realiza el proceso de backtracking para encontrar la mejor asignación de tareas a procesadores.
     * @param asignacionActual Mapa que representa la asignación actual de tareas a procesadores.
     * @param tareaIndex Índice de la tarea actual a asignar.
     */
    private void backtrack(Map<Procesador, List<Tarea>> asignacionActual, int tareaIndex) {
        if (tareaIndex == tareas.size()) {
            int tiempoMaximo = calcularTiempoMaximo(asignacionActual);
            if (tiempoMaximo < mejorTiempoMaximo) {
                mejorTiempoMaximo = tiempoMaximo;
                mejorAsignacion = new HashMap<>();
                for (Procesador p : asignacionActual.keySet()) {
                    mejorAsignacion.put(p, new ArrayList<>(asignacionActual.get(p)));
                }
            }
            cantidadEstadosGenerados++;
            return;
        }

        Tarea tarea = tareas.get(tareaIndex);
        for (Procesador procesador : procesadores) {
            if (puedeAsignar(procesador, tarea, asignacionActual)) {
                asignacionActual.putIfAbsent(procesador, new ArrayList<>());
                asignacionActual.get(procesador).add(tarea);
                backtrack(asignacionActual, tareaIndex + 1);
                asignacionActual.get(procesador).remove(tarea);
            }
        }
    }

    /**
     * Verifica si una tarea puede ser asignada a un procesador específico cumpliendo las restricciones.
     * @param procesador El procesador al que se intenta asignar la tarea.
     * @param tarea La tarea que se intenta asignar.
     * @param asignacionActual Mapa que representa la asignación actual de tareas a procesadores.
     * @return true si la tarea puede ser asignada al procesador, false en caso contrario.
     */
    private boolean puedeAsignar(Procesador procesador, Tarea tarea, Map<Procesador, List<Tarea>> asignacionActual) {
        List<Tarea> tareasAsignadas = asignacionActual.getOrDefault(procesador, new ArrayList<>());

        // Verificar el número de tareas críticas
        long tareasCriticas = tareasAsignadas.stream().filter(Tarea::is_critica).count();
        if (tarea.is_critica() && tareasCriticas >= 2) {
            return false;
        }

        // Verificar el tiempo máximo para procesadores no refrigerados
        if (!procesador.is_refrigerado()) {
            int tiempoTotal = tareasAsignadas.stream().mapToInt(Tarea::getTiempo_ejec).sum();
            if (tiempoTotal + tarea.getTiempo_ejec() > tiempoMaxNoRefrigerado) {
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
    private int calcularTiempoMaximo(Map<Procesador, List<Tarea>> asignacion) {
        int maximo = 0;
        for (List<Tarea> tareas : asignacion.values()) {
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
        System.out.println("Métrica para analizar el costo de la solución (cantidad de estados generados): " + cantidadEstadosGenerados);
    }

}
