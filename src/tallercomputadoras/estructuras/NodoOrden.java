package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.OrdenReparacion;

class NodoOrden {

    private final OrdenReparacion orden;
    private NodoOrden siguiente;

    NodoOrden(OrdenReparacion orden) {
        this.orden = orden;
        this.siguiente = null;
    }

    OrdenReparacion getOrden() {
        return orden;
    }

    NodoOrden getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoOrden siguiente) {
        this.siguiente = siguiente;
    }
}