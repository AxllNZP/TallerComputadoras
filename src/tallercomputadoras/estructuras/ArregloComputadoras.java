package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.Computadora;

public class ArregloComputadoras {

    private final Computadora[] computadoras;
    private int cantidad;

    public ArregloComputadoras() {
        computadoras = new Computadora[50];
        cantidad = 0;
        precargar();
    }

    private void precargar() {
        computadoras[cantidad++] = new Computadora(
                "PC-001", "Lenovo", "ThinkPad",
                "No enciende", "Cliente Demo 1", "000000001"
        );

        computadoras[cantidad++] = new Computadora(
                "PC-002", "HP", "Pavilion",
                "Se apaga durante el uso", "Cliente Demo 2", "000000002"
        );

        computadoras[cantidad++] = new Computadora(
                "PC-003", "Dell", "Inspiron",
                "Teclado no responde", "Cliente Demo 3", "000000003"
        );

        computadoras[cantidad++] = new Computadora(
                "PC-004", "Asus", "VivoBook",
                "Pantalla sin imagen", "Cliente Demo 4", "000000004"
        );

        computadoras[cantidad++] = new Computadora(
                "PC-005", "Acer", "Aspire",
                "Solicita limpieza preventiva", "Cliente Demo 5", "000000005"
        );
    }

    public int getCantidad() {
        return cantidad;
    }

    public Computadora obtener(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }

        return computadoras[posicion];
    }

    public int buscarPorCodigo(String codigo) {
        String codigoNormalizado = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);
        int posicionEncontrada = -1;

        for (int i = 0; i < cantidad; i++) {
            if (computadoras[i].getCodigo().equals(codigoNormalizado)) {
                posicionEncontrada = i;
                break;
            }
        }

        return posicionEncontrada;
    }

    public void insertar(Computadora computadora) {
        if (cantidad >= computadoras.length) {
            throw new IllegalStateException(
                    "El arreglo está lleno. Máximo 50 computadoras."
            );
        }

        if (buscarPorCodigo(computadora.getCodigo()) != -1) {
            throw new IllegalArgumentException(
                    "Ya existe una computadora con ese código."
            );
        }

        computadoras[cantidad] = computadora;
        cantidad++;
    }

    public boolean eliminar(String codigo) {
        int posicion = buscarPorCodigo(codigo);

        if (posicion == -1) {
            return false;
        }

        for (int i = posicion; i < cantidad - 1; i++) {
            computadoras[i] = computadoras[i + 1];
        }

        cantidad--;
        computadoras[cantidad] = null;

        return true;
    }

    public boolean actualizar(String codigo, String marca, String modelo,
            String falla, String cliente, String telefono) {

        int posicion = buscarPorCodigo(codigo);

        if (posicion == -1) {
            return false;
        }

        Computadora computadora = computadoras[posicion];

        computadora.setMarca(marca);
        computadora.setModelo(modelo);
        computadora.setFalla(falla);
        computadora.setCliente(cliente);
        computadora.setTelefono(telefono);

        return true;
    }
}
