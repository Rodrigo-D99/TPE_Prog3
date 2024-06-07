import java.util.*;

public class SolGreedy {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private int tiempoMaximo;
    private int estadosGenerados;
    private int cantTareasAsignadas;
    private boolean existeSol;

    public SolGreedy(List<Procesador> procesadores, List<Tarea> tareas){
        this.tiempoMaximo = 0;
        this.cantTareasAsignadas = 0;
        this.estadosGenerados = 0;
        this.procesadores = new ArrayList<>(procesadores);
        this.tareas = new ArrayList<>(tareas);
        this.existeSol = false;
    }

    public void greedy(int tiempoX){
        List<Procesador> solucion;
        solucion = greedy(new LinkedList<>(procesadores),new LinkedList<>(tareas),tiempoX);
        System.out.println("\nGreedy:");
        if(this.tiempoMaximo != 0 && this.cantTareasAsignadas==tareas.size()){
            printSolucion(solucion);
            this.existeSol = true;
        }else{
            System.out.println("Estados Generados: " + estadosGenerados);
            System.out.println("Tareas asignadas: " + cantTareasAsignadas);
            System.out.println("No hay solucion posible\n");
        }
    }

    private List<Procesador> greedy(List<Procesador> procesadores, List<Tarea> tareas,int tiempoX){
        //ordenarTareas(tareas);//Ordenar tareas por tiempo de ejecucion
        Collections.sort(tareas);
        int tiempoMaximoGlobal = 0;
        int menorTiempoMaximoProcesador;
        while(!tareas.isEmpty()){ // recorrer hasta que no queden tareas por asignar
            Tarea t = tareas.remove(0); //Tomar primera tarea
            menorTiempoMaximoProcesador = Integer.MAX_VALUE;
            Procesador procesadorConMenosCarga = null;

            for(Procesador p:procesadores){
                if(isValido(p,t,tiempoX)){
                    int tiempoMaximo = p.getTiempoEjecucionMaximo();
                    if(tiempoMaximo < menorTiempoMaximoProcesador){
                        this.estadosGenerados++;
                        menorTiempoMaximoProcesador = tiempoMaximo;
                        procesadorConMenosCarga = p;
                        if (cantTareasAsignadas==0)//Solo sirve para la primer tarea
                            break; //de otra forma se iteran todos los procesadores para la primer tarea
                    }
                }
            }

            if (procesadorConMenosCarga != null) {
                procesadorConMenosCarga.addTarea(t);
                Collections.sort(procesadores);
                cantTareasAsignadas++;
                int tiempoEjecucionActual = procesadorConMenosCarga.getTiempoEjecucionMaximo();
                if (tiempoEjecucionActual > tiempoMaximoGlobal) {
                    tiempoMaximoGlobal = tiempoEjecucionActual;
                }
            }
        }
        this.tiempoMaximo = tiempoMaximoGlobal;
        return procesadores;
    }

    private <T extends Comparable<T>> void ordenarTareas(List<Tarea> list) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) { //Bubble sort mejorado
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) < 0) {
                    Tarea temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // no se hicieron cambios, la lista esta ordenada
            }
        }
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
        System.out.println("Estados Generados: " + estadosGenerados);
    }

    public boolean existeSol() {
        return existeSol;
    }
}
