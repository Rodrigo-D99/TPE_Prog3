public class Nodo {
    Tarea info;
    Nodo izq, der;

    public Nodo(int info) {
        this.info = info;
        izq = der = null;
    }

    public void addNodo(Nodo n){

    }

    @Override
    public int compareTo(Nodo n) {
        return n.getInfo().compareTo(this.info.getNivel_prioridad());
    }
}
