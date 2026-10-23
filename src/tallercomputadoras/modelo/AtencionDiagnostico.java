package tallercomputadoras.modelo;

public class AtencionDiagnostico {

    private final String codigo;
    private final String cliente;
    private final String equipo;
    private final String falla;
    private final int prioridad;

    public AtencionDiagnostico(String codigo, String cliente,
            String equipo, String falla, int prioridad) {

        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("Prioridad inválida.");
        }

        this.codigo = codigo.trim()
                .toUpperCase(java.util.Locale.ROOT);
        this.cliente = cliente;
        this.equipo = equipo;
        this.falla = falla;
        this.prioridad = prioridad;
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

    public String getFalla() {
        return falla;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getPrioridadTexto() {
        switch (prioridad) {
            case 1:
                return "Alta";
            case 2:
                return "Media";
            default:
                return "Baja";
        }
    }
}