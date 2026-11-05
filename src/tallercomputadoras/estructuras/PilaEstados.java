package tallercomputadoras.estructuras;

public class PilaEstados {

    private NodoEstado cima;

    public PilaEstados() {
        cima = null;
    }

    public void recorrer(java.util.function.Consumer<String> accion) {
        NodoEstado actual = cima;

        while (actual != null) {
            accion.accept(actual.getEstado());
            actual = actual.getSiguiente();
        }
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void apilar(String estado) {
        NodoEstado nuevo = new NodoEstado(estado);

        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    public String desapilar() {
        String estadoRetirado = null;

        if (!estaVacia()) {
            NodoEstado retirado = cima;
            cima = retirado.getSiguiente();
            retirado.setSiguiente(null);
            estadoRetirado = retirado.getEstado();
        }

        return estadoRetirado;
    }
}
