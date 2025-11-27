public class AVL {

    // Atributos
    private NodoAVL raiz;

    // Constructor
    public AVL() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // Búsqueda
    public NodoAVL buscar(int llaveBuscar) {
        NodoAVL nodoTemp = raiz;
        while (nodoTemp != null && nodoTemp.getLlave() != llaveBuscar) {
            if (llaveBuscar < nodoTemp.getLlave()) {
                nodoTemp = nodoTemp.getHijoIzquierdo();
            } else {
                nodoTemp = nodoTemp.getHijoDerecho();
            }
        }
        if (nodoTemp == null) {
            System.out.println("El nodo buscado no está en el árbol.\n");
        }
        return nodoTemp;
    }
    // Método público para llamar desde Main
public void imprimir() {
    imprimir(raiz, "", true);
}

// Método privado recursivo
private void imprimir(NodoAVL nodo, String indent, boolean ultimo) {
    if (nodo != null) {
        System.out.print(indent);
        if (ultimo) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "| ";
        }
        System.out.println(nodo.getLlave());

        imprimir(nodo.getHijoIzquierdo(), indent, false);
        imprimir(nodo.getHijoDerecho(), indent, true);
    }
}

    // Inserción pública
    public void insertar(int llave) {
        raiz = insertar(raiz, llave);
    }

    // Inserción privada recursiva
    private NodoAVL insertar(NodoAVL nodo, int llave) {
        if (nodo == null) {
            return new NodoAVL(llave);
        }

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(insertar(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(insertar(nodo.getHijoDerecho(), llave));
        } else {
            return nodo; // No duplicados
        }

        nodo.actualizarAltura();
        int balance = nodo.balancear();

        // Casos de rotación
        if (balance > 1 && llave < nodo.getHijoIzquierdo().getLlave()) {
            return nodo.rotarIzquieda(nodo); // LL
        }
        if (balance < -1 && llave > nodo.getHijoDerecho().getLlave()) {
            return nodo.rotarDerecha(nodo); // RR
        }
        if (balance > 1 && llave > nodo.getHijoIzquierdo().getLlave()) {
            return nodo.rotarIzquierdaDerecha(nodo); // LR
        }
        if (balance < -1 && llave < nodo.getHijoDerecho().getLlave()) {
            return nodo.rotarDerechaIzquierda(nodo); // RL
        }

        return nodo;
    }

    // Eliminación pública
    public void eliminar(int llave) {
        raiz = eliminar(raiz, llave);
    }

    // Eliminación recursiva
    private NodoAVL eliminar(NodoAVL nodo, int llave) {
        if (nodo == null) return null;

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(eliminar(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(eliminar(nodo.getHijoDerecho(), llave));
        } else {
            // Nodo con un hijo o sin hijos
            if (nodo.getHijoIzquierdo() == null) return nodo.getHijoDerecho();
            if (nodo.getHijoDerecho() == null) return nodo.getHijoIzquierdo();

            // Nodo con dos hijos
            NodoAVL sucesor = obtenerMinimo(nodo.getHijoDerecho());
            nodo = new NodoAVL(sucesor.getLlave());
            nodo.setHijoIzquierdo(nodo.getHijoIzquierdo());
            nodo.setHijoDerecho(eliminar(nodo.getHijoDerecho(), sucesor.getLlave()));
        }

        nodo.actualizarAltura();
        int balance = nodo.balancear();

        // Casos de rotación después de eliminar
        if (balance > 1 && nodo.getHijoIzquierdo().balancear() >= 0)
            return nodo.rotarIzquieda(nodo); // LL
        if (balance > 1 && nodo.getHijoIzquierdo().balancear() < 0)
            return nodo.rotarIzquierdaDerecha(nodo); // LR
        if (balance < -1 && nodo.getHijoDerecho().balancear() <= 0)
            return nodo.rotarDerecha(nodo); // RR
        if (balance < -1 && nodo.getHijoDerecho().balancear() > 0)
            return nodo.rotarDerechaIzquierda(nodo); // RL

        return nodo;
    }

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }
        return actual;
    }

    // Recorridos públicos
    public void enOrden() {
        enOrden(raiz);
        System.out.println();
    }

    public void preOrden() {
        preOrden(raiz);
        System.out.println();
    }

    public void postOrden() {
        postOrden(raiz);
        System.out.println();
    }

    // Recorridos privados
    private void enOrden(NodoAVL nodo) {
        if (nodo != null) {
            enOrden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getLlave() + " ");
            enOrden(nodo.getHijoDerecho());
        }
    }

    private void preOrden(NodoAVL nodo) {
        if (nodo != null) {
            System.out.print(nodo.getLlave() + " ");
            preOrden(nodo.getHijoIzquierdo());
            preOrden(nodo.getHijoDerecho());
        }
    }

    private void postOrden(NodoAVL nodo) {
        if (nodo != null) {
            postOrden(nodo.getHijoIzquierdo());
            postOrden(nodo.getHijoDerecho());
            System.out.print(nodo.getLlave() + " ");
        }
    }
}
