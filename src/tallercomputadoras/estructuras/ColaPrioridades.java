package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.AtencionDiagnostico;

public class ColaPrioridades {

    private NodoAtencion primero;
    private NodoAtencion ultimo;

    public ColaPrioridades() {
        primero = null;
        ultimo = null;
        precargar();
    }

    public void recorrer(java.util.function.Consumer<AtencionDiagnostico> accion) {
        NodoAtencion actual = primero;

        while (actual != null) {
            accion.accept(actual.getAtencion());
            actual = actual.getSiguiente();
        }
    }

    public AtencionDiagnostico buscarPorCodigo(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        NodoAtencion actual = primero;
        AtencionDiagnostico encontrada = null;

        while (actual != null && encontrada == null) {
            if (actual.getAtencion().getCodigo().equals(clave)) {
                encontrada = actual.getAtencion();
            } else {
                actual = actual.getSiguiente();
            }
        }

        return encontrada;
    }

    public void encolar(AtencionDiagnostico atencion) {
        if (buscarPorCodigo(atencion.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una atención pendiente con ese código."
            );
        }

        NodoAtencion nuevo = new NodoAtencion(atencion);

        /*
         * Inserción ordenada por prioridad (1 = Alta, 2 = Media, 3 = Baja).
         * Entre prioridades iguales se respeta el orden de llegada (FIFO).
         */
        if (primero == null) {
            // Cola vacía: el nuevo nodo es el primero y el último.
            primero = nuevo;
            ultimo = nuevo;
        } else if (atencion.getPrioridad() < primero.getAtencion().getPrioridad()) {
            // Inserción al inicio.
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else if (atencion.getPrioridad() >= ultimo.getAtencion().getPrioridad()) {
            // Inserción al final: se enlaza directamente con 'ultimo'.
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        } else {
            // Inserción intermedia: avanza tras los de prioridad igual o mayor.
            NodoAtencion actual = primero;

            while (actual.getSiguiente() != null
                    && actual.getSiguiente().getAtencion().getPrioridad()
                    <= atencion.getPrioridad()) {

                actual = actual.getSiguiente();
            }

            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }

    public AtencionDiagnostico atender() {
        AtencionDiagnostico atencionAtendida = null;

        // Se extrae desde 'primero'; si la cola queda vacía, 'ultimo' vuelve a null.
        if (primero != null) {
            NodoAtencion atendido = primero;
            primero = atendido.getSiguiente();
            if (primero == null) {
                ultimo = null;
            }
            atendido.setSiguiente(null);
            atencionAtendida = atendido.getAtencion();
        }

        return atencionAtendida;
    }

    private void precargar() {
        encolar(new AtencionDiagnostico(
                "AT-001", "Cliente Demo 1",
                "Laptop HP Pavilion",
                "Se apaga durante el uso", 2
        ));

        encolar(new AtencionDiagnostico(
                "AT-002", "Cliente Demo 2",
                "Laptop Lenovo ThinkPad",
                "No enciende", 1
        ));

        encolar(new AtencionDiagnostico(
                "AT-003", "Cliente Demo 3",
                "Laptop Acer Aspire",
                "Limpieza preventiva", 3
        ));

        encolar(new AtencionDiagnostico(
                "AT-004", "Cliente Demo 4",
                "PC de escritorio",
                "Posible falla del disco", 1
        ));

        encolar(new AtencionDiagnostico(
                "AT-005", "Cliente Demo 5",
                "Laptop Dell Inspiron",
                "Teclado funciona parcialmente", 2
        ));
    }
}
