package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.AtencionDiagnostico;

public class ColaPrioridades {

    private NodoAtencion primero;

    public ColaPrioridades() {
        primero = null;
        precargar();
    }

    public NodoAtencion getPrimero() {
        return primero;
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
        boolean vaAlFrente = primero == null
                || atencion.getPrioridad() < primero.getAtencion().getPrioridad();

        if (vaAlFrente) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else {
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

        if (primero != null) {
            NodoAtencion atendido = primero;
            primero = atendido.getSiguiente();
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
