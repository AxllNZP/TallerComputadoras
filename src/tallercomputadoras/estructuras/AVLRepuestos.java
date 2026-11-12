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
        return nodo == null ? 0 : obtenerAltura(nodo.getDerecha()) - obtenerAltura(nodo.getIzquierda());
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

        // Registro de factor de equilibrio crítico (Derecha - Izquierda)
        if (balance > 1 || balance < -1) {
            historialBalanceo.add("Desbalance detectado en nodo: " + nodo.getRepuesto().getCodigo() + " (Factor de equilibrio: " + balance + ")");
        }

        // Caso Derecha Derecha (RR) -> Balance positivo (> 1) y clave mayor
        if (balance > 1 && repuesto.getCodigo().compareTo(nodo.getDerecha().getRepuesto().getCodigo()) > 0) {
            historialBalanceo.add("Caso identificado: Derecha-Derecha (RR)");
            return rotacionIzquierda(nodo);
        }
        // Caso Izquierda Izquierda (LL) -> Balance negativo (< -1) y clave menor
        if (balance < -1 && repuesto.getCodigo().compareTo(nodo.getIzquierda().getRepuesto().getCodigo()) < 0) {
            historialBalanceo.add("Caso identificado: Izquierda-Izquierda (LL)");
            return rotacionDerecha(nodo);
        }
        // Caso Derecha Izquierda (RL) -> Balance positivo (> 1) y clave menor
        if (balance > 1 && repuesto.getCodigo().compareTo(nodo.getDerecha().getRepuesto().getCodigo()) < 0) {
            historialBalanceo.add("Caso identificado: Derecha-Izquierda (RL) -> Requiere rotación doble");
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }
        // Caso Izquierda Derecha (LR) -> Balance negativo (< -1) y clave mayor
        if (balance < -1 && repuesto.getCodigo().compareTo(nodo.getIzquierda().getRepuesto().getCodigo()) > 0) {
            historialBalanceo.add("Caso identificado: Izquierda-Derecha (LR) -> Requiere rotación doble");
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
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
    public void recorrerInorden(Consumer<Repuesto> accion) {
        recorrerInorden(raiz, accion);
    }

    private void recorrerInorden(NodoRepuesto nodo, Consumer<Repuesto> accion) {
        if (nodo != null) {
            recorrerInorden(nodo.getIzquierda(), accion);
            accion.accept(nodo.getRepuesto());
            recorrerInorden(nodo.getDerecha(), accion);
        }
    }

    public void recorrerPreorden(Consumer<Repuesto> accion) {
        recorrerPreorden(raiz, accion);
    }

    private void recorrerPreorden(NodoRepuesto nodo, Consumer<Repuesto> accion) {
        if (nodo != null) {
            accion.accept(nodo.getRepuesto());
            recorrerPreorden(nodo.getIzquierda(), accion);
            recorrerPreorden(nodo.getDerecha(), accion);
        }
    }

    public void recorrerPostorden(Consumer<Repuesto> accion) {
        recorrerPostorden(raiz, accion);
    }

    private void recorrerPostorden(NodoRepuesto nodo, Consumer<Repuesto> accion) {
        if (nodo != null) {
            recorrerPostorden(nodo.getIzquierda(), accion);
            recorrerPostorden(nodo.getDerecha(), accion);
            accion.accept(nodo.getRepuesto());
        }
    }
    // --- ELIMINACIÓN AVL ---

    public boolean eliminarPredecesor(String codigo) {
        if (buscarIndexada(codigo) == null) {
            return false;
        }
        historialBalanceo.clear();
        raiz = eliminarNodo(raiz, codigo.trim().toUpperCase(java.util.Locale.ROOT), true);
        return true;
    }

    public boolean eliminarSucesor(String codigo) {
        if (buscarIndexada(codigo) == null) {
            return false;
        }
        historialBalanceo.clear();
        raiz = eliminarNodo(raiz, codigo.trim().toUpperCase(java.util.Locale.ROOT), false);
        return true;
    }

    private NodoRepuesto eliminarNodo(NodoRepuesto nodo, String codigo, boolean usarPredecesor) {
        if (nodo == null) {
            return null;
        }

        int comparacion = codigo.compareTo(nodo.getRepuesto().getCodigo());

        // 1. Fase de búsqueda y eliminación estándar estilo ABB
        if (comparacion < 0) {
            nodo.setIzquierda(eliminarNodo(nodo.getIzquierda(), codigo, usarPredecesor));
        } else if (comparacion > 0) {
            nodo.setDerecha(eliminarNodo(nodo.getDerecha(), codigo, usarPredecesor));
        } else {
            // Nodo a eliminar encontrado
            if (nodo.getIzquierda() == null) {
                return nodo.getDerecha();
            }
            if (nodo.getDerecha() == null) {
                return nodo.getIzquierda();
            }

            // Tiene dos hijos: aplicar estrategia
            if (usarPredecesor) {
                NodoRepuesto max = encontrarMaximo(nodo.getIzquierda());
                nodo.setRepuesto(max.getRepuesto());
                nodo.setIzquierda(eliminarNodo(nodo.getIzquierda(), max.getRepuesto().getCodigo(), usarPredecesor));
            } else {
                NodoRepuesto min = encontrarMinimo(nodo.getDerecha());
                nodo.setRepuesto(min.getRepuesto());
                nodo.setDerecha(eliminarNodo(nodo.getDerecha(), min.getRepuesto().getCodigo(), usarPredecesor));
            }
        }

        // Si el árbol tenía un solo nodo, retornar null directamente
        if (nodo == null) {
            return null;
        }

        // 2. Fase de rebalanceo (Retroceso recursivo)
        // Actualizamos altura
        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzquierda()), obtenerAltura(nodo.getDerecha())));

        // Obtenemos balance con la fórmula oficial (Derecha - Izquierda)
        int balance = obtenerBalance(nodo);

        if (balance > 1 || balance < -1) {
            historialBalanceo.add("Desbalance post-eliminación en nodo: " + nodo.getRepuesto().getCodigo() + " (Factor: " + balance + ")");
        }

        // Para la eliminación, los casos dependen del balance de los subárboles hijos
        // Caso Derecha Derecha (RR): Balance > 1 y el hijo derecho está inclinado a la derecha o balanceado (>= 0)
        if (balance > 1 && obtenerBalance(nodo.getDerecha()) >= 0) {
            historialBalanceo.add("Caso identificado: Derecha-Derecha (RR) post-eliminación");
            return rotacionIzquierda(nodo);
        }

        // Caso Izquierda Izquierda (LL): Balance < -1 y el hijo izquierdo está inclinado a la izquierda o balanceado (<= 0)
        if (balance < -1 && obtenerBalance(nodo.getIzquierda()) <= 0) {
            historialBalanceo.add("Caso identificado: Izquierda-Izquierda (LL) post-eliminación");
            return rotacionDerecha(nodo);
        }

        // Caso Derecha Izquierda (RL): Balance > 1 y el hijo derecho está inclinado a la izquierda (< 0)
        if (balance > 1 && obtenerBalance(nodo.getDerecha()) < 0) {
            historialBalanceo.add("Caso identificado: Derecha-Izquierda (RL) post-eliminación");
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        // Caso Izquierda Derecha (LR): Balance < -1 y el hijo izquierdo está inclinado a la derecha (> 0)
        if (balance < -1 && obtenerBalance(nodo.getIzquierda()) > 0) {
            historialBalanceo.add("Caso identificado: Izquierda-Derecha (LR) post-eliminación");
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        return nodo;
    }

    private NodoRepuesto encontrarMaximo(NodoRepuesto nodo) {
        while (nodo.getDerecha() != null) {
            nodo = nodo.getDerecha();
        }
        return nodo;
    }

    private NodoRepuesto encontrarMinimo(NodoRepuesto nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
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
