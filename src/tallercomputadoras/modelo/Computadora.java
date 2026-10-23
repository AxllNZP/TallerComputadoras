package tallercomputadoras.modelo;

public class Computadora {

    private final String codigo;
    private String marca;
    private String modelo;
    private String falla;
    private String cliente;
    private String telefono;

    public Computadora(String codigo, String marca, String modelo,
            String falla, String cliente, String telefono) {

        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.falla = falla;
        this.cliente = cliente;
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
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
}