import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolBacktracking {

    private List<Tarea> tareas;
    private List<Procesador> procesadores;

    private List<Procesador> mejorSolucion;
    private int mejorTiempoMaximo;
    private int estadosGenerados;

    public SolBacktracking(List<Tarea> tareas, List<Procesador> procesadores){
        this.tareas = new LinkedList<>(tareas);
        this.procesadores = new LinkedList<>(procesadores);
        this.mejorSolucion = new LinkedList<>();
        this.estadosGenerados = 0;
        this.mejorTiempoMaximo = 0;
    }

    public void backtracking(int tiempoX) {
        back(new ArrayList<>(procesadores), 0,0,tiempoX);
        System.out.println("Backtracking: ");
        if(mejorSolucion!=null && this.mejorTiempoMaximo!=0){
            mostrarSolucion(mejorSolucion);
        }
        else{
            System.out.println("No hay solucion posible");
        }
    }

    private void back(List<Procesador> solucion, int index,int tiempoMaximo,int tiempoX) {
        if (index == tareas.size()) {
            if (mejorSolucion.isEmpty() && mejorTiempoMaximo == 0 || tiempoMaximo < mejorTiempoMaximo) {
                mejorSolucion.clear();
                for (Procesador p : solucion) {
                    mejorSolucion.add(p.getCopy());
                }
                mejorTiempoMaximo = tiempoMaximo;
            }
        } else {
            this.estadosGenerados++;
            Tarea t = tareas.get(index);
            for (Procesador p : solucion) {
                if(esValido(p,t,tiempoX)){
                    p.addTarea(t);
                    int nuevoTiempoMaximo = tiempoMaximo(tiempoMaximo,p);
                    if(mejorTiempoMaximo==0 || nuevoTiempoMaximo < this.mejorTiempoMaximo){
                        back(solucion, index + 1,nuevoTiempoMaximo,tiempoX);
                    }
                    p.removeTarea(t);
                }
            }
        }
    }


    private boolean esValido(Procesador p,Tarea t,int tiempoX){
        int cont = 0;
        for(Tarea a: p.getTareas()){
            if(a.is_critica()){
                cont++;
            }
        }

        if(cont>2 && t.is_critica()){
            return false;
        }

        if (!p.isRefrigerado() && p.getTiempoEjecucionMaximo() + t.getTiempo_ejec() > tiempoX) {
            return false;
        }
        return true;
    }


    private int tiempoMaximo(int tiempoMaximo, Procesador p){
        int tiempoEjecucion = p.getTiempoEjecucionMaximo();
        if(tiempoEjecucion > tiempoMaximo){
            return tiempoEjecucion;
        }
        return tiempoMaximo;
    }


    private void mostrarSolucion(List<Procesador> lista){
        for(int i = 0; i<lista.size();i++){

            System.out.println(lista.get(i).getId()+"{"+lista.get(i).getTareas()+"}");
        }
        System.out.println("Tiempo Maximo de Ejecucion: "+this.mejorTiempoMaximo);
        System.out.println("Estados generados: "+this.estadosGenerados);
        System.out.println(" ");
    }



}
