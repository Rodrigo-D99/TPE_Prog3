import com.sun.jdi.connect.spi.TransportService;

import java.util.LinkedList;

public class Nodo {
    private int prioridad;
    private LinkedList<Tarea> info;
    private Nodo izq, der;

    public Nodo(Tarea info) {
        this.info=new LinkedList<>();
        this.prioridad=info.getNivel_prioridad();
        izq = der = null;
        this.info.add(info);
    }

    public void addNodo(Nodo n){
        if (prioridad==n.prioridad)
            addAllTareas(n.info);
        else if (prioridad>n.prioridad)
            if (izq==null)
                izq=n;
            else
                izq.addNodo(n);
        else
            if (der==null)
                der=n;
            else
                der.addNodo(n);
    }
//    public void addTarea(Tarea t){
//        if (t!=null && t.getNivel_prioridad()==prioridad)
//            info.add(t);
//    }
    public void addAllTareas(LinkedList<Tarea> ts){
        this.info.addAll(ts);
    }
    public int getPrioridad() {
        return prioridad;
    }

    public LinkedList<Tarea> getInfo() {
        return new LinkedList<>(info);
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }


    public int compareTo(Nodo n) {
        return Integer.compare(n.getPrioridad(),this.prioridad);
    }
}
