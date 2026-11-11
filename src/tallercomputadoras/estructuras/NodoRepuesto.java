package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Repuesto;

class NodoRepuesto {

    private final Repuesto repuesto;
    private NodoRepuesto izquierda;
    private NodoRepuesto derecha;
    private int altura; // Necesario para el factor de balanceo AVL

    NodoRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
        this.altura = 1; // Un nodo nuevo siempre nace como hoja con altura 1
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

    // NUEVO: Getters y Setters para altura
    int getAltura() {
        return altura;
    }

    void setAltura(int altura) {
        this.altura = altura;
    }
}