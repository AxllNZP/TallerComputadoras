package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Repuesto;
import java.util.function.Consumer;

public class ABBRepuestos {

    private NodoRepuesto raiz;
    private int operacionesBuscadas; // Contador para comparar rendimiento con AVL

    public ABBRepuestos() {
        raiz = null;
        precargar();
    }

    public int getOperacionesBuscadas() {
        return operacionesBuscadas;
    }

    public void insertar(Repuesto repuesto) {
        if (buscarIndexada(repuesto.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe un repuesto con ese código.");
        }
        raiz = insertarNodo(raiz, repuesto);
    }

    private NodoRepuesto insertarNodo(NodoRepuesto nodo, Repuesto repuesto) {
        if (nodo == null) {
            return new NodoRepuesto(repuesto);
        }

        int comparacion = repuesto.getCodigo().compareTo(nodo.getRepuesto().getCodigo());

        if (comparacion < 0) {
            nodo.setIzquierda(insertarNodo(nodo.getIzquierda(), repuesto));
        } else if (comparacion > 0) {
            nodo.setDerecha(insertarNodo(nodo.getDerecha(), repuesto));
        }

        return nodo;
    }

    public Repuesto buscarIndexada(String codigo) {
        String clave = codigo.trim().toUpperCase(java.util.Locale.ROOT);
        NodoRepuesto actual = raiz;
        operacionesBuscadas = 0;

        while (actual != null) {
            operacionesBuscadas++;
            int comparacion = clave.compareTo(actual.getRepuesto().getCodigo());

            if (comparacion == 0) {
                return actual.getRepuesto();
            } else if (comparacion < 0) {
                actual = actual.getIzquierda();
            } else {
                actual = actual.getDerecha();
            }
        }
        return null;
    }

    // --- RECORRIDOS ---

    public void recorrerInorden(Consumer<Repuesto> accion) { recorrerInorden(raiz, accion); }
    private void recorrerInorden(NodoRepuesto nodo, Consumer<Repuesto> accion) {
        if (nodo != null) {
            recorrerInorden(nodo.getIzquierda(), accion);
            accion.accept(nodo.getRepuesto());
            recorrerInorden(nodo.getDerecha(), accion);
        }
    }

    public void recorrerPreorden(Consumer<Repuesto> accion) { recorrerPreorden(raiz, accion); }
    private void recorrerPreorden(NodoRepuesto nodo, Consumer<Repuesto> accion) {
        if (nodo != null) {
            accion.accept(nodo.getRepuesto());
            recorrerPreorden(nodo.getIzquierda(), accion);
            recorrerPreorden(nodo.getDerecha(), accion);
        }
    }

    public void recorrerPostorden(Consumer<Repuesto> accion) { recorrerPostorden(raiz, accion); }
    private void recorrerPostorden(NodoRepuesto nodo, Consumer<Repuesto> accion) {
        if (nodo != null) {
            recorrerPostorden(nodo.getIzquierda(), accion);
            recorrerPostorden(nodo.getDerecha(), accion);
            accion.accept(nodo.getRepuesto());
        }
    }

    private void precargar() {
        insertar(new Repuesto("REP-030", "Memoria RAM 8 GB", 6));
        insertar(new Repuesto("REP-010", "Teclado USB", 8));
        insertar(new Repuesto("REP-050", "Disco SSD 500 GB", 4));
        insertar(new Repuesto("REP-020", "Ventilador", 5));
        insertar(new Repuesto("REP-040", "Fuente de poder", 3));
    }
}