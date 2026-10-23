package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Repuesto;

public class NodoRepuesto {

    private final Repuesto repuesto;
    private NodoRepuesto izquierda;
    private NodoRepuesto derecha;

    public NodoRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public NodoRepuesto getIzquierda() {
        return izquierda;
    }

    public NodoRepuesto getDerecha() {
        return derecha;
    }

    void setIzquierda(NodoRepuesto izquierda) {
        this.izquierda = izquierda;
    }

    void setDerecha(NodoRepuesto derecha) {
        this.derecha = derecha;
    }
}