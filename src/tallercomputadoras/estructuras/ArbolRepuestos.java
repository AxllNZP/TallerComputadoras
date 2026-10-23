package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Repuesto;

public class ArbolRepuestos {

    private NodoRepuesto raiz;

    public ArbolRepuestos() {
        raiz = null;
        precargar();
    }

    public NodoRepuesto getRaiz() {
        return raiz;
    }

    public void insertar(Repuesto repuesto) {
        if (buscarIndexada(repuesto.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un repuesto con ese código."
            );
        }

        raiz = insertarNodo(raiz, repuesto);
    }

    private NodoRepuesto insertarNodo(
            NodoRepuesto nodo, Repuesto repuesto) {

        if (nodo == null) {
            return new NodoRepuesto(repuesto);
        }

        int comparacion = repuesto.getCodigo()
                .compareTo(nodo.getRepuesto().getCodigo());

        if (comparacion < 0) {
            nodo.setIzquierda(
                    insertarNodo(nodo.getIzquierda(), repuesto)
            );
        } else if (comparacion > 0) {
            nodo.setDerecha(
                    insertarNodo(nodo.getDerecha(), repuesto)
            );
        }

        return nodo;
    }

    public Repuesto buscarIndexada(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        NodoRepuesto actual = raiz;

        while (actual != null) {
            int comparacion = clave.compareTo(
                    actual.getRepuesto().getCodigo()
            );

            if (comparacion == 0) {
                return actual.getRepuesto();
            }

            if (comparacion < 0) {
                actual = actual.getIzquierda();
            } else {
                actual = actual.getDerecha();
            }
        }

        return null;
    }

    public Repuesto buscarSecuencial(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        return buscarPreorden(raiz, clave);
    }

    private Repuesto buscarPreorden(
            NodoRepuesto nodo, String codigo) {

        if (nodo == null) {
            return null;
        }

        if (nodo.getRepuesto().getCodigo().equals(codigo)) {
            return nodo.getRepuesto();
        }

        Repuesto encontrado = buscarPreorden(
                nodo.getIzquierda(), codigo
        );

        if (encontrado != null) {
            return encontrado;
        }

        return buscarPreorden(nodo.getDerecha(), codigo);
    }

    private void precargar() {
        insertar(new Repuesto(
                "REP-030", "Memoria RAM 8 GB", 6
        ));

        insertar(new Repuesto(
                "REP-010", "Teclado USB", 8
        ));

        insertar(new Repuesto(
                "REP-050", "Disco SSD 500 GB", 4
        ));

        insertar(new Repuesto(
                "REP-020", "Ventilador", 5
        ));

        insertar(new Repuesto(
                "REP-040", "Fuente de poder", 3
        ));
    }
}