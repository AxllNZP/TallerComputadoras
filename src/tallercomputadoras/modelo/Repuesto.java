package tallercomputadoras.modelo;

public class Repuesto {

    private final String codigo;
    private final String nombre;
    private final int stock;

    public Repuesto(String codigo, String nombre, int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo."
            );
        }

        this.codigo = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }
}