package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Repuesto;
import java.util.function.Consumer;
public class AVLRepuestos {

    private NodoRepuesto raiz;
    private int operacionesBuscadas;
    private final java.util.List<String> historialBalanceo; // NUEVO: Registro de operaciones

    public AVLRepuestos() {
        raiz = null;
        historialBalanceo = new java.util.ArrayList<>();
        precargar();
    }

    public java.util.List<String> getHistorialBalanceo() {
        return historialBalanceo;
    }

    public int getOperacionesBuscadas() {
        return operacionesBuscadas;
    }

    private int obtenerAltura(NodoRepuesto nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private int obtenerBalance(NodoRepuesto nodo) {
        return nodo == null ? 0 : obtenerAltura(nodo.getIzquierda()) - obtenerAltura(nodo.getDerecha());
    }

    // --- ROTACIONES ---

    private NodoRepuesto rotacionDerecha(NodoRepuesto y) {
        historialBalanceo.add(" -> Ejecutando Rotación Simple Derecha en nodo: " + y.getRepuesto().getCodigo());
        NodoRepuesto x = y.getIzquierda();
        NodoRepuesto T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        y.setAltura(Math.max(obtenerAltura(y.getIzquierda()), obtenerAltura(y.getDerecha())) + 1);
        x.setAltura(Math.max(obtenerAltura(x.getIzquierda()), obtenerAltura(x.getDerecha())) + 1);

        return x;
    }

    private NodoRepuesto rotacionIzquierda(NodoRepuesto x) {
        historialBalanceo.add(" -> Ejecutando Rotación Simple Izquierda en nodo: " + x.getRepuesto().getCodigo());
        NodoRepuesto y = x.getDerecha();
        NodoRepuesto T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        x.setAltura(Math.max(obtenerAltura(x.getIzquierda()), obtenerAltura(x.getDerecha())) + 1);
        y.setAltura(Math.max(obtenerAltura(y.getIzquierda()), obtenerAltura(y.getDerecha())) + 1);

        return y;
    }

    // --- INSERCIÓN AVL ---

    public void insertar(Repuesto repuesto) {
        if (buscarIndexada(repuesto.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe un repuesto con ese código.");
        }
        historialBalanceo.clear(); // NUEVO: Limpia el historial antes de cada inserción
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
        } else {
            return nodo;
        }

        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzquierda()), obtenerAltura(nodo.getDerecha())));

        int balance = obtenerBalance(nodo);

        // Registro de factor de equilibrio crítico
        if (balance > 1 || balance < -1) {
            historialBalanceo.add("Desbalance detectado en nodo: " + nodo.getRepuesto().getCodigo() + " (Factor de equilibrio: " + balance + ")");
        }

        // Caso Izquierda Izquierda
        if (balance > 1 && repuesto.getCodigo().compareTo(nodo.getIzquierda().getRepuesto().getCodigo()) < 0) {
            historialBalanceo.add("Caso identificado: Izquierda-Izquierda (LL)");
            return rotacionDerecha(nodo);
        }
        // Caso Derecha Derecha
        if (balance < -1 && repuesto.getCodigo().compareTo(nodo.getDerecha().getRepuesto().getCodigo()) > 0) {
            historialBalanceo.add("Caso identificado: Derecha-Derecha (RR)");
            return rotacionIzquierda(nodo);
        }
        // Caso Izquierda Derecha
        if (balance > 1 && repuesto.getCodigo().compareTo(nodo.getIzquierda().getRepuesto().getCodigo()) > 0) {
            historialBalanceo.add("Caso identificado: Izquierda-Derecha (LR) -> Requiere rotación doble");
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }
        // Caso Derecha Izquierda
        if (balance < -1 && repuesto.getCodigo().compareTo(nodo.getDerecha().getRepuesto().getCodigo()) < 0) {
            historialBalanceo.add("Caso identificado: Derecha-Izquierda (RL) -> Requiere rotación doble");
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
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
        // Precarga ordenada para forzar el desbalanceo en un ABB normal y demostrar la eficacia del AVL
        insertar(new Repuesto("REP-010", "Teclado USB", 8));
        insertar(new Repuesto("REP-020", "Ventilador", 5));
        insertar(new Repuesto("REP-030", "Memoria RAM 8 GB", 6));
        insertar(new Repuesto("REP-040", "Fuente de poder", 3));
        insertar(new Repuesto("REP-050", "Disco SSD 500 GB", 4));
    }
}