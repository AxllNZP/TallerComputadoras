package tallercomputadoras.modelo;

public class FichaTrabajo {

    private final String codigo;
    private final String cliente;
    private final String equipo;

    private final String[] estados = {
        "Recibido",
        "En diagnóstico",
        "En reparación",
        "Reparado",
        "Entregado"
    };

    private int posicionEstado;

    public FichaTrabajo(String codigo, String cliente, String equipo) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.equipo = equipo;
        posicionEstado = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEquipo() {
        return equipo;
    }

    public String getEstado() {
        return estados[posicionEstado];
    }

    public boolean puedeAvanzar() {
        return posicionEstado < estados.length - 1;
    }

    public void avanzar() {
        if (!puedeAvanzar()) {
            throw new IllegalStateException(
                    "La ficha ya está en Entregado."
            );
        }

        posicionEstado++;
    }

    public void restaurarEstado(String estado) {
        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equals(estado)) {
                posicionEstado = i;
                return;
            }
        }

        throw new IllegalArgumentException("Estado inválido.");
    }
}