package tallercomputadoras.estructuras;

public class NodoEstado {

    private final String estado;
    private NodoEstado siguiente;

    public NodoEstado(String estado) {
        this.estado = estado;
        siguiente = null;
    }

    public String getEstado() {
        return estado;
    }

    public NodoEstado getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoEstado siguiente) {
        this.siguiente = siguiente;
    }
}