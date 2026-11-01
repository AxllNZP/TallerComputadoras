package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.OrdenReparacion;

public class ListaOrdenes {

    private NodoOrden primero;

    public ListaOrdenes() {
        primero = null;
        precargar();
    }

    public NodoOrden getPrimero() {
        return primero;
    }

    public OrdenReparacion buscarPorCodigo(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        NodoOrden actual = primero;
        OrdenReparacion ordenEncontrada = null;

        while (actual != null) {
            if (actual.getOrden().getCodigo().equals(clave)) {
                ordenEncontrada = actual.getOrden();
                break;
            }

            actual = actual.getSiguiente();
        }

        return ordenEncontrada;
    }

    public void insertar(OrdenReparacion orden) {
        if (buscarPorCodigo(orden.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una orden con ese código."
            );
        }

        NodoOrden nuevo = new NodoOrden(orden);

        if (primero == null) {
            primero = nuevo;
            return;
        }

        NodoOrden actual = primero;

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevo);
    }

    private void precargar() {
        insertar(new OrdenReparacion(
                "ORD-001", "Cliente Demo 1", "000000001",
                "Laptop Lenovo ThinkPad", "No enciende",
                "Recibido"
        ));

        insertar(new OrdenReparacion(
                "ORD-002", "Cliente Demo 2", "000000002",
                "Laptop HP Pavilion", "Se apaga durante el uso",
                "En diagnóstico"
        ));

        insertar(new OrdenReparacion(
                "ORD-003", "Cliente Demo 3", "000000003",
                "Laptop Dell Inspiron", "Teclado no responde",
                "En reparación"
        ));

        insertar(new OrdenReparacion(
                "ORD-004", "Cliente Demo 4", "000000004",
                "Laptop Asus VivoBook", "Pantalla sin imagen",
                "Reparado"
        ));

        insertar(new OrdenReparacion(
                "ORD-005", "Cliente Demo 5", "000000005",
                "Laptop Acer Aspire", "Limpieza preventiva",
                "Entregado"
        ));
    }

    public boolean eliminar(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        NodoOrden actual = primero;
        NodoOrden anterior = null;

        while (actual != null) {
            if (actual.getOrden().getCodigo().equals(clave)) {

                if (anterior == null) {
                    primero = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                actual.setSiguiente(null);
                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public boolean actualizar(String codigo, String cliente, String telefono,
            String equipo, String falla, String estado) {

        OrdenReparacion orden = buscarPorCodigo(codigo);

        if (orden == null) {
            return false;
        }

        orden.setCliente(cliente);
        orden.setTelefono(telefono);
        orden.setEquipo(equipo);
        orden.setFalla(falla);
        orden.setEstado(estado);

        return true;
    }
}
