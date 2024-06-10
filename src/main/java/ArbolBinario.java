import java.util.*;

class ArbolBinario {
    private Nodo raiz;

    private void getNodos(Nodo raiz, List<Nodo> nodos) {
        if (raiz == null)
            return;
        getNodos(raiz.getIzq(), nodos);
        nodos.add(raiz);
        getNodos(raiz.getDer(), nodos);
    }

    private Nodo crearArbol(List<Nodo> nodos, int inicio, int fin) {
        if (inicio > fin)
            return null;
        int mitad = (inicio + fin) / 2;
        Nodo nodo = nodos.get(mitad);
        nodo.addNodo(crearArbol(nodos, inicio, mitad - 1));
        nodo.addNodo(crearArbol(nodos, mitad + 1, fin));
        return nodo;
    }


    private Nodo balancearArbol(Nodo raiz) {
        List<Nodo> nodos = new ArrayList<>(); //Todos los nodos en una lista
                                                //todavia se apuntan entre si
        getNodos(raiz, nodos);
        int n = nodos.size();
        nodos.forEach(Nodo::resetNodos);
        return crearArbol(nodos, 0, n - 1);
    }

    private void preOrden(Nodo nodo) {
        if (nodo == null)
            return;
        System.out.print(nodo.getPrioridad() + " ");
        preOrden(nodo.getIzq());
        preOrden(nodo.getDer());
    }

    public void addNodo(Nodo n){
        if (n != null){
            if (raiz==null)
                this.raiz=n;
            else{
                this.raiz.addNodo(n);
            }
            verificarYBalancear();
        }
    }

    public List<Tarea> getNodosPrioridad(int prioridadInferior, int prioridadSuperior) {
        LinkedList<Tarea> result = new LinkedList<>();
        getNodosPrioridad(this.raiz, prioridadInferior, prioridadSuperior, result);
        return result;
    }

    private void getNodosPrioridad(Nodo nodo, int prioridadInferior, int prioridadSuperior, List<Tarea> result) {
        if (nodo == null)
            return;
        int prioridad = nodo.getPrioridad();
        if (prioridad >= prioridadInferior && prioridad <= prioridadSuperior) {
            result.addAll(nodo.getInfo());
        }
        getNodosPrioridad(nodo.getIzq(), prioridadInferior, prioridadSuperior, result);
        getNodosPrioridad(nodo.getDer(), prioridadInferior, prioridadSuperior, result);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.getIzq()), altura(nodo.getDer()));
    }

    // Método para verificar si el árbol está balanceado
    private boolean estaBalanceado(Nodo nodo) {
        if (nodo == null) {
            return true;
        }
        int alturaIzq = altura(nodo.getIzq());
        int alturaDer = altura(nodo.getDer());
        if (Math.abs(alturaIzq - alturaDer) > 1) {
            return false;
        }
        return estaBalanceado(nodo.getIzq()) && estaBalanceado(nodo.getDer());
    }

    private void verificarYBalancear() {
        if (!estaBalanceado(this.raiz)) {
            this.raiz = balancearArbol(this.raiz);
        }
    }

}