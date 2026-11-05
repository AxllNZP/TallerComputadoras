package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.OrdenReparacion;

public class ListaOrdenes {

    private NodoOrden primero;

    public ListaOrdenes() {
        primero = null;
        precargar();
    }

    public void recorrer(java.util.function.Consumer<OrdenReparacion> accion) {
        NodoOrden actual = primero;

        while (actual != null) {
            accion.accept(actual.getOrden());
            actual = actual.getSiguiente();
        }
    }

    /*
     * Búsqueda Secuencial (Lineal).
     * Complejidad: O(n).
     * Criterio: Itera secuencialmente nodo por nodo desde el puntero inicial ('primero') de la estructura.
     */
    public OrdenReparacion buscarPorCodigo(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        NodoOrden actual = primero;
        OrdenReparacion ordenEncontrada = null;

        while (actual != null && ordenEncontrada == null) {
            if (actual.getOrden().getCodigo().equals(clave)) {
                ordenEncontrada = actual.getOrden();
            } else {
                actual = actual.getSiguiente();
            }
        }

        return ordenEncontrada;
    }

    /*
     * Operación de Inserción.
     * Reglas de punteros: Se recorre la lista hasta el último nodo y se reconecta su referencia 'siguiente' hacia el nuevo nodo creado.
     */
    public void insertar(OrdenReparacion orden) {
        if (buscarPorCodigo(orden.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una orden con ese código."
            );
        }

        NodoOrden nuevo = new NodoOrden(orden);

        if (primero == null) {
            primero = nuevo;
        } else {
            NodoOrden actual = primero;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);
        }
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

    /*
     * Operación de Eliminación.
     * Reglas de punteros: Se enlaza la referencia 'siguiente' del nodo 'anterior' con el nodo 'siguiente' del actual, dejando al nodo actual sin referencias para ser recolectado por el Garbage Collector.
     */
    public boolean eliminar(String codigo) {
        String clave = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);

        NodoOrden actual = primero;
        NodoOrden anterior = null;
        boolean exito = false;

        while (actual != null && !exito) {
            if (actual.getOrden().getCodigo().equals(clave)) {

                if (anterior == null) {
                    primero = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                actual.setSiguiente(null);
                exito = true;
            } else {
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }

        return exito;
    }

    public boolean actualizar(String codigo, String cliente, String telefono,
            String equipo, String falla, String estado) {

        OrdenReparacion orden = buscarPorCodigo(codigo);
        boolean exito = false;

        if (orden != null) {
            orden.setCliente(cliente);
            orden.setTelefono(telefono);
            orden.setEquipo(equipo);
            orden.setFalla(falla);
            orden.setEstado(estado);
            exito = true;
        }

        return exito;
    }
}
