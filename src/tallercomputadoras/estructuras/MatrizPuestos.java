package tallercomputadoras.estructuras;

public class MatrizPuestos {

    private final boolean[][] ocupados = new boolean[3][2];

    private void validarPosicion(int puesto, int turno) {
        if (puesto < 0 || puesto >= 3 || turno < 0 || turno >= 2) {
            throw new IllegalArgumentException("Puesto o turno inválido.");
        }
    }

    /*
     * Acceso Directo Indexado.
     * Complejidad: O(1).
     * Criterio: Recuperación inmediata mediante coordenadas espaciales fijas (fila, columna).
     */
    public boolean estaOcupado(int puesto, int turno) {
        validarPosicion(puesto, turno);
        return ocupados[puesto][turno];
    }

    /*
     * Acceso Directo Indexado.
     * Complejidad: O(1).
     * Criterio: Mutación inmediata del estado mediante coordenadas espaciales fijas (fila, columna).
     */
    public boolean ocupar(int puesto, int turno) {
        validarPosicion(puesto, turno);
        boolean operacionExitosa = false;

        if (!ocupados[puesto][turno]) {
            ocupados[puesto][turno] = true;
            operacionExitosa = true;
        }

        return operacionExitosa;
    }

    /*
     * Acceso Directo Indexado.
     * Complejidad: O(1).
     * Criterio: Mutación inmediata del estado mediante coordenadas espaciales fijas (fila, columna).
     */
    public boolean liberar(int puesto, int turno) {
        validarPosicion(puesto, turno);
        boolean operacionExitosa = false;

        if (ocupados[puesto][turno]) {
            ocupados[puesto][turno] = false;
            operacionExitosa = true;
        }

        return operacionExitosa;
    }
}
