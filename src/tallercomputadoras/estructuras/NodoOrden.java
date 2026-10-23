package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.OrdenReparacion;

public class NodoOrden {

    private final OrdenReparacion orden;
    private NodoOrden siguiente;

    public NodoOrden(OrdenReparacion orden) {
        this.orden = orden;
        this.siguiente = null;
    }

    public OrdenReparacion getOrden() {
        return orden;
    }

    public NodoOrden getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoOrden siguiente) {
        this.siguiente = siguiente;
    }
}