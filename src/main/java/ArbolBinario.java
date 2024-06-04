import java.util.*;

class ArbolBinario {
    Nodo raiz;

    /* This function traverses the skewed binary tree and
       stores its nodos pointers in ArrayList nodos[] */
    void getNodos(Nodo raiz, List<Nodo> nodos) {
        // Base case
        if (raiz == null)
            return;

        // Store nodos in Inorder (which is sorted
        // order for BST)
        getNodos(raiz.izq, nodos);
        nodos.add(raiz);
        getNodos(raiz.der, nodos);
    }

    /* Recursive function to construct binary tree */
    Nodo crearArbol(List<Nodo> nodos, int inicio, int fin) {
        // base case
        if (inicio > fin)
            return null;

        /* Get the middle element and make it raiz */
        int mitad = (inicio + fin) / 2;
        Nodo nodo = nodos.get(mitad);

        /* Using index in Inorder traversal, construct
           izq and der subtrees */
        nodo.izq = crearArbol(nodos, inicio, mitad - 1);
        nodo.der = crearArbol(nodos, mitad + 1, fin);

        return nodo;
    }

    // This function converts an unbalanced BST to
    // a balanced BST
    Nodo balancearArbolDesdeRaiz(Nodo raiz) {
        // Store nodos of given BST in sorted order
        List<Nodo> nodos = new ArrayList<>();
        getNodos(raiz, nodos);

        // Constructs BST from nodos[]
        int n = nodos.size();
        return crearArbol(nodos, 0, n - 1);
    }

    void preOrden(Nodo nodo) {
        if (nodo == null)
            return;
        System.out.print(nodo.info + " ");
        preOrden(nodo.izq);
        preOrden(nodo.der);
    }

    public void addNodo(Nodo n){
        int compare = this.compareTo(n);
        if (compare != 0){
            if (compare > 0){
                if (this.raiz.der == null)
                    this.raiz.der = n;
                else
                    this.raiz.der.addNodo(n);
            }else {
                if (this.raiz.izq == null)
                    this.raiz.izq = n;
                else
                    this.raiz.izq.addNodo(n);
            }
        }
    }



    public static void main(String[] args) {
        ArbolBinario tree = new ArbolBinario();
        tree.raiz = new Nodo(10);
        tree.raiz.der = new Nodo(30);
        tree.raiz.der.der = new Nodo(36);
        tree.raiz.der.izq = new Nodo(14);
        tree.raiz.izq = new Nodo(8);
        tree.raiz.izq.izq = new Nodo(7);
        tree.raiz.izq.izq.izq = new Nodo(6);
        tree.raiz.izq.izq.izq.izq = new Nodo(5);

        tree.raiz = tree.balancearArbolDesdeRaiz(tree.raiz);
        System.out.println("Preorder traversal of balanced BST is :");
        tree.preOrden(tree.raiz);
    }
}
