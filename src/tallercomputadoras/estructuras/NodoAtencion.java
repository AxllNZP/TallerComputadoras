package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.AtencionDiagnostico;

class NodoAtencion {

    private final AtencionDiagnostico atencion;
    private NodoAtencion siguiente;

    NodoAtencion(AtencionDiagnostico atencion) {
        this.atencion = atencion;
        siguiente = null;
    }

    AtencionDiagnostico getAtencion() {
        return atencion;
    }

    NodoAtencion getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoAtencion siguiente) {
        this.siguiente = siguiente;
    }
}