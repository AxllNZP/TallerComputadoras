package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Repuesto;

class NodoRepuesto {

    private final Repuesto repuesto;
    private NodoRepuesto izquierda;
    private NodoRepuesto derecha;

    NodoRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    Repuesto getRepuesto() {
        return repuesto;
    }

    NodoRepuesto getIzquierda() {
        return izquierda;
    }

    NodoRepuesto getDerecha() {
        return derecha;
    }

    void setIzquierda(NodoRepuesto izquierda) {
        this.izquierda = izquierda;
    }

    void setDerecha(NodoRepuesto derecha) {
        this.derecha = derecha;
    }
}