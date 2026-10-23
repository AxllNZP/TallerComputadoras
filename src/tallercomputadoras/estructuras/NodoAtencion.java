package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.AtencionDiagnostico;

public class NodoAtencion {

    private final AtencionDiagnostico atencion;
    private NodoAtencion siguiente;

    public NodoAtencion(AtencionDiagnostico atencion) {
        this.atencion = atencion;
        siguiente = null;
    }

    public AtencionDiagnostico getAtencion() {
        return atencion;
    }

    public NodoAtencion getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoAtencion siguiente) {
        this.siguiente = siguiente;
    }
}