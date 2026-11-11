package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.PasoDiagnostico;
import java.util.function.Consumer;

public class ArbolDiagnostico {

    private NodoDiagnostico raiz;

    public ArbolDiagnostico() {
        precargar();
    }

    private void precargar() {
        // Nivel 0 (Raíz)
        raiz = new NodoDiagnostico(new PasoDiagnostico("P1", "¿El equipo enciende?"));

        // Nivel 1
        NodoDiagnostico n2 = new NodoDiagnostico(new PasoDiagnostico("P2", "¿Da video?"));
        NodoDiagnostico n3 = new NodoDiagnostico(new PasoDiagnostico("P3", "¿Hace algún pitido al intentar encender?"));
        raiz.setHijoSi(n2);
        raiz.setHijoNo(n3);

        // Nivel 2 (Hijos de P2 - Sí enciende)
        n2.setHijoSi(new NodoDiagnostico(new PasoDiagnostico("S1", "Iniciar SO y verificar lentitud o virus.")));
        n2.setHijoNo(new NodoDiagnostico(new PasoDiagnostico("S2", "Revisar flex de pantalla, monitor externo o tarjeta gráfica.")));

        // Nivel 2 (Hijos de P3 - No enciende)
        n3.setHijoSi(new NodoDiagnostico(new PasoDiagnostico("S3", "Falla de Memoria RAM o Placa Base (Revisar código de pitidos).")));
        n3.setHijoNo(new NodoDiagnostico(new PasoDiagnostico("S4", "Revisar fuente de poder o cable de alimentación.")));
    }

    // --- RECORRIDOS ---
    public void recorrerPreorden(Consumer<PasoDiagnostico> accion) {
        recorrerPreorden(raiz, accion);
    }

    private void recorrerPreorden(NodoDiagnostico nodo, Consumer<PasoDiagnostico> accion) {
        if (nodo != null) {
            accion.accept(nodo.getPaso());
            recorrerPreorden(nodo.getHijoSi(), accion);
            recorrerPreorden(nodo.getHijoNo(), accion);
        }
    }

    public void recorrerInorden(Consumer<PasoDiagnostico> accion) {
        recorrerInorden(raiz, accion);
    }

    private void recorrerInorden(NodoDiagnostico nodo, Consumer<PasoDiagnostico> accion) {
        if (nodo != null) {
            recorrerInorden(nodo.getHijoSi(), accion);
            accion.accept(nodo.getPaso());
            recorrerInorden(nodo.getHijoNo(), accion);
        }
    }

    public void recorrerPostorden(Consumer<PasoDiagnostico> accion) {
        recorrerPostorden(raiz, accion);
    }

    private void recorrerPostorden(NodoDiagnostico nodo, Consumer<PasoDiagnostico> accion) {
        if (nodo != null) {
            recorrerPostorden(nodo.getHijoSi(), accion);
            recorrerPostorden(nodo.getHijoNo(), accion);
            accion.accept(nodo.getPaso());
        }
    }
}
