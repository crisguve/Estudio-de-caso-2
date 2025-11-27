public class NodoAVL {

    //Atributos.
    private int llave;
    private NodoAVL hijoIzquierdo;
    private NodoAVL hijoDerecho;
    private int altura;

    //Métodos.
    //Constructor.
    
    public NodoAVL(int llave) {
        this.llave = llave;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        altura = 1; //Cualquier nodo es una hoja al momento de su inserción.
                    // La altura de toda hoja es por defecto 1.
    }

    //Getters.
    public int getLlave() {
        return llave;
    }

    public NodoAVL getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoAVL getHijoDerecho() {
        return hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    //Setters.
    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int evaluarAltura(NodoAVL nodoEvaluar) {
        if (nodoEvaluar == null) {
            return 0;
        }
        return nodoEvaluar.getAltura();
    }

    public int evaluarBalance(NodoAVL nodoEvaluar) {
        if (nodoEvaluar == null) {
            return 0;
        }
        return evaluarAltura(nodoEvaluar.getHijoIzquierdo()) -
                evaluarAltura(nodoEvaluar.getHijoDerecho());
    }

    public int balancear() {
        return evaluarBalance(this);
    }

    //public NodoAVL rotarIzquierda(NodoAVL nodoRotar) { ... }


    public NodoAVL rotarIzquieda(NodoAVL nodoRotar){

        NodoAVL aux2= nodoRotar.hijoIzquierdo;
        nodoRotar.hijoIzquierdo=aux2.hijoDerecho;
        aux2.hijoDerecho=nodoRotar;

        nodoRotar.actualizarAltura();
        aux2.actualizarAltura();

        return aux2;

    }

    //public NodoAVL rotarDerecha(NodoAVL nodoRotar) { ... }

    public NodoAVL rotarDerecha(NodoAVL nodoRotar){
        NodoAVL aux2=nodoRotar.hijoDerecho;
        nodoRotar.hijoDerecho=aux2.hijoIzquierdo;
        aux2.hijoIzquierdo=nodoRotar;

        nodoRotar.actualizarAltura();
        aux2.actualizarAltura();

        return aux2;
    }

    //public NodoAVL rotarIzquierdaDerecha(NodoAVL nodoRotar) { ... }

    public NodoAVL rotarIzquierdaDerecha(NodoAVL aux){
        aux.hijoIzquierdo=rotarDerecha(aux.hijoIzquierdo);
        return rotarIzquieda(aux);
    }



    //public NodoAVL rotarDerechaIzquierda(NodoAVL nodoRotar) { ... }

    public NodoAVL rotarDerechaIzquierda(NodoAVL aux){
        aux.hijoDerecho=rotarIzquieda(aux.hijoDerecho);
        return rotarDerecha(aux);
    }

    public void actualizarAltura() {
        this.altura = Math.max(evaluarAltura(hijoIzquierdo), evaluarAltura(hijoDerecho)) + 1;
    }
}