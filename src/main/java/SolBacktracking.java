import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolBacktracking {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private List<Procesador> mejorSolucion;
    private int mejorTiempoMaximo;
    private int asignacionesRealizadas;
    private int solucionesEvaluadas;

    public SolBacktracking(List<Procesador> procesadores, List<Tarea> tareas){
        this.asignacionesRealizadas = 0;
        this.mejorTiempoMaximo = 0;
        this.procesadores = new ArrayList<>();
        this.tareas = new ArrayList<>();
        procesadores.forEach(p -> this.procesadores.add(p.getCopy()));
        tareas.forEach(t -> this.tareas.add(t.getCopy()));
        this.mejorSolucion = new ArrayList<>();
    }

    public void backtracking(int tiempoX) {
        System.out.println("\nBacktracking:");
        this.solucionesEvaluadas = 0;
        Collections.sort(tareas);//esto junto con el metodo tieneTareasPeroOtrosNo, ayuda a lograr una distribucion
        //de carga mas pareja para las primeras tareas
        back(new ArrayList<>(procesadores), 0, 0, tiempoX);
        if (!mejorSolucion.isEmpty() && this.mejorTiempoMaximo != 0) {
            mostrarSolucion(mejorSolucion);
        } else {
            System.out.println("No hay solucion posible");
        }
    }

    private void back(List<Procesador> solucion, int index, int tiempoMaximo, int tiempoX) {
        if (index == tareas.size()) {
            actualizarMejorSolucion(solucion, tiempoMaximo);
        } else {
            Tarea t = tareas.get(index);
            for (Procesador p : solucion) {
                if (isValido(p, t, tiempoX) && !tieneTareasPeroOtrosNo(solucion, p)) {
                    this.asignacionesRealizadas++;
                    p.addTarea(t);
                    int nuevoTiempoMaximo = Math.max(p.getTiempoEjecucionMaximo(), tiempoMaximo);
                    if (mejorTiempoMaximo == 0 || nuevoTiempoMaximo < this.mejorTiempoMaximo) {
                        back(solucion, index + 1, nuevoTiempoMaximo, tiempoX);
                    }
                    p.removeTarea(t);
                }
            }
        }
    }

    private void actualizarMejorSolucion(List<Procesador> solucion, int tiempoMaximo) {
        if (mejorSolucion.isEmpty() && mejorTiempoMaximo == 0 || tiempoMaximo < mejorTiempoMaximo) {
            mejorSolucion.clear();
            for (Procesador p : solucion) {
                mejorSolucion.add(p.getCopy());
            }
            mejorTiempoMaximo = tiempoMaximo;
        }
        this.solucionesEvaluadas++;
    }


    private boolean isValido(Procesador p, Tarea t, int tiempoX){
        return p.cantTareasCriticas() < 2 || !t.is_critica() && (p.isRefrigerado() || p.getTiempoEjecucionMaximo() + t.getTiempo_ejec() <= tiempoX);
    }

    /*
        La idea de este metodo es garantizar que todas las soluciones contemplen que
        todos los procesadores tengan por lo menos 1 tarea,
        esto hace que las asignaciones de tareas bajen de +6M a +2k
        y las soluciones completas bajan de 56 a 40.
    */
    private boolean tieneTareasPeroOtrosNo(List<Procesador> procesadores, Procesador p) {
        if (!p.getTareas().isEmpty()) {
            for (Procesador otroProcesador : procesadores) {
                if (otroProcesador != p && otroProcesador.getTareas().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void mostrarSolucion(List<Procesador> lista){
        System.out.println("Solucion obtenida: ");
        lista.forEach(p -> {
            System.out.println("Procesador " + p.getId() + " Tiempo=" + p.getTiempoEjecucionMaximo() + " - Tareas: Cantidad=" + p.getTareas().size() + " Detalle=" + p.getTareas());
        });
        System.out.println("Tiempo maximo de Ejecucion: " + mejorTiempoMaximo);
        System.out.println("Asignaciones Realizadas: " + asignacionesRealizadas);
        System.out.println("Soluciones completas evaluadas: " + solucionesEvaluadas);
    }
}
