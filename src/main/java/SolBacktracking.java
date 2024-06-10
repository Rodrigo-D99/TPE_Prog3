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
        this.procesadores =new ArrayList<>();
        this.tareas = new ArrayList<>();
        procesadores.forEach(p -> this.procesadores.add(p.getCopy()));
        tareas.forEach(t -> this.tareas.add(t.getCopy()));
        this.mejorSolucion = new ArrayList<>();
    }

    public void backtracking(int tiempoX) {
        System.out.println("\nBacktracking:");
        this.solucionesEvaluadas = 0;
        back(new ArrayList<>(procesadores), 0, 0, tiempoX);
        if(!mejorSolucion.isEmpty() && this.mejorTiempoMaximo!=0){
            mostrarSolucion(mejorSolucion);
        }
        else{
            System.out.println("No hay solucion posible");
        }
    }

    private void back(List<Procesador> solucion, int index, int tiempoMaximo, int tiempoX) {
        if (index == tareas.size()) {
            if (mejorSolucion.isEmpty() && mejorTiempoMaximo == 0 || tiempoMaximo < mejorTiempoMaximo) {
                mejorSolucion.clear();
                for (Procesador p : solucion) {
                    System.out.println("Procesador " + p.getId() + " Tiempo="+ p.getTiempoEjecucionMaximo() +" - Tareas: Cantidad="+ p.getTareas().size() +" Detalle=" + p.getTareas());
                    mejorSolucion.add(p.getCopy());
                }
                mejorTiempoMaximo = tiempoMaximo;
            }
            this.solucionesEvaluadas++;
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

    private boolean isValido(Procesador p, Tarea t, int tiempoX){
        return p.cantTareasCriticas() < 2 || !t.is_critica() && (p.isRefrigerado() || p.getTiempoEjecucionMaximo() + t.getTiempo_ejec() <= tiempoX);
    }

    private void mostrarSolucion(List<Procesador> lista){
        System.out.println("Solucion obtenida: ");
        lista.forEach(p -> {
            System.out.println("Procesador " + p.getId() + " Tiempo="+ p.getTiempoEjecucionMaximo() +" - Tareas: Cantidad="+ p.getTareas().size() +" Detalle=" + p.getTareas());
        });
        System.out.println("Tiempo maximo de Ejecucion: "+ mejorTiempoMaximo);
        System.out.println("Asignaciones Realizadas: " + asignacionesRealizadas);
        System.out.println("Soluciones completas evaluadas: " + solucionesEvaluadas);
    }
}