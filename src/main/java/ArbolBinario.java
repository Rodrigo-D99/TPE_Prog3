import java.util.*;

class ArbolBinario {
    Nodo raiz;

    public void getNodos(Nodo raiz, List<Nodo> nodos) {
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
        List<Nodo> nodos = new ArrayList<>();
        getNodos(raiz, nodos);
        int n = nodos.size();
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
        if (raiz==null)
            this.raiz=n;
        else{
            this.raiz.addNodo(n);
        }
    }



//    public static void main(String[] args) {
//        ArbolBinario tree = new ArbolBinario();
//        tree.raiz = new Nodo(10);
//        tree.raiz.getDer() = new Nodo(30);
//        tree.raiz.getDer().getDer() = new Nodo(36);
//        tree.raiz.getDer().getIzq() = new Nodo(14);
//        tree.raiz.getIzq() = new Nodo(8);
//        tree.raiz.getIzq().getIzq() = new Nodo(7);
//        tree.raiz.getIzq().getIzq().getIzq() = new Nodo(6);
//        tree.raiz.getIzq().getIzq().getIzq().getIzq() = new Nodo(5);
//
//        tree.raiz = tree.balancearArbolDesgetder(tree.raiz);
//        System.out.println("PreorgetDer() traversal of balanced BST is :");
//        tree.preOrden(tree.raiz);
//    }
}
