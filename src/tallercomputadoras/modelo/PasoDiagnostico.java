package tallercomputadoras.modelo;

public class PasoDiagnostico {

    private final String id;
    private final String mensaje; // Puede ser una pregunta (nodo rama) o una soluciÃ³n (nodo hoja)

    public PasoDiagnostico(String id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public String toString() {
        return id + ": " + mensaje;
    }
}