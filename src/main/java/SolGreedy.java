import java.util.*;

public class SolGreedy {
    private List<Tarea> tareasCritica, tareasNoCriticas;
    private List<Procesador> procesadores;
    private int tiempoMaximo;
    private int candidatosConsiderados;
    private int cantTareasAsignadas;

    public SolGreedy(List<Procesador> procesadores, List<Tarea> tareasCritica, LinkedList<Tarea> tareasNoCriticas){
        this.tiempoMaximo = 0;
        this.cantTareasAsignadas = 0;
        this.candidatosConsiderados = 0;
        this.procesadores =new ArrayList<>();
        this.tareasCritica = new ArrayList<>();
        this.tareasNoCriticas = new ArrayList<>();
        procesadores.forEach(p -> this.procesadores.add(p.getCopy()));
        tareasCritica.forEach(t -> this.tareasCritica.add(t.getCopy()));
        tareasNoCriticas.forEach(t -> this.tareasNoCriticas.add(t.getCopy()));
    }


    /*
    * La idea es ordenar las tareas por tiempo de ejecucion descendente
    * y mantener ordenados los procesadores por tiempo de ejecucion ascendente.
    * Tomando y quitando de la lista a la primer tarea, se busca al procesador valido menos cargado
    * para asignarle la tarea.
    * Se asignan primero las tareas criticas, se busca dispersarlas para evitar la restriccion
    * de max tareas criticas por procesador
    * */
    public void greedy(int tiempoX){
        List<Procesador> solucion;
        solucion = greedy(new LinkedList<>(procesadores), new LinkedList<>(tareasCritica), tiempoX);
        greedy(solucion, new LinkedList<>(tareasNoCriticas), tiempoX);
        System.out.println("\nGreedy:");
        if(this.tiempoMaximo != 0 && this.cantTareasAsignadas==(tareasCritica.size()+tareasNoCriticas.size())){
            printSolucion(solucion);
        }else{
            System.out.println("Estados Generados: " + candidatosConsiderados);
            System.out.println("Tareas asignadas: " + cantTareasAsignadas);
            System.out.println("No hay solucion posible\n");
        }
    }

    private List<Procesador> greedy(List<Procesador> procesadores, List<Tarea> tareas, int tiempoX){
        Collections.sort(tareas);//Ordenar tareas por tiempo de ejecucion
        while(!tareas.isEmpty()){ // recorrer hasta que no queden tareas por asignar
            Tarea t = tareas.remove(0); //Tomar primera tarea
            Procesador procesadorConMenosCarga = null;

            for(Procesador p:procesadores){
                candidatosConsiderados++;
                if(isValido(p, t, tiempoX)){
                    procesadorConMenosCarga = p;
                    break;
                }
            }

            if (procesadorConMenosCarga != null) {
                procesadorConMenosCarga.addTarea(t);
                Collections.sort(procesadores);//ordenar procesadores por tiempo max de ejecucion
                cantTareasAsignadas++;
            }else
                break;//si no se selecciono un procesador para la tarea, significa que esta no sera asignada
                    // por ende no hay una solucion valida
        }
        this.tiempoMaximo = procesadores.get(procesadores.size()-1).getTiempoEjecucionMaximo();
        return procesadores;
    }

    private boolean isValido(Procesador p, Tarea t, int tiempoX){
        return !(p.cantTareasCriticas() == 2 && t.is_critica()) && (p.isRefrigerado() || p.getTiempoEjecucionMaximo() + t.getTiempo_ejec() <= tiempoX);
    }

    private void printSolucion(List<Procesador> lista){
        System.out.println("Solucion obtenida: ");
        lista.forEach(p -> {
            System.out.println("Procesador " + p.getId() + " Tiempo="+ p.getTiempoEjecucionMaximo() +" - Tareas: Cantidad="+ p.getTareas().size() +" Detalle=" + p.getTareas());
        });
        System.out.println("Tiempo maximo de Ejecucion: "+this.tiempoMaximo);
        System.out.println("Candidatos Considerados (Procesadores): " + candidatosConsiderados);
    }
}
