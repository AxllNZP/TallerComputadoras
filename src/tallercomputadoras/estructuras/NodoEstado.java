package tallercomputadoras.estructuras;

class NodoEstado {

    private final String estado;
    private NodoEstado siguiente;

    NodoEstado(String estado) {
        this.estado = estado;
        siguiente = null;
    }

    String getEstado() {
        return estado;
    }

    NodoEstado getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoEstado siguiente) {
        this.siguiente = siguiente;
    }
}