package tallercomputadoras.modelo;

public class OrdenReparacion {

    private final String codigo;
    private String cliente;
    private String telefono;
    private String equipo;
    private String falla;
    private String estado;

    public OrdenReparacion(String codigo, String cliente, String telefono,
            String equipo, String falla, String estado) {

        this.codigo = codigo.trim().toUpperCase(java.util.Locale.ROOT);
        this.cliente = cliente;
        this.telefono = telefono;
        this.equipo = equipo;
        this.falla = falla;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}