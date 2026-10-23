package tallercomputadoras.estructuras;

public class MatrizPuestos {

    private final boolean[][] ocupados = new boolean[3][2];

    private void validarPosicion(int puesto, int turno) {
        if (puesto < 0 || puesto >= 3 || turno < 0 || turno >= 2) {
            throw new IllegalArgumentException("Puesto o turno inválido.");
        }
    }

    public boolean estaOcupado(int puesto, int turno) {
        validarPosicion(puesto, turno);
        return ocupados[puesto][turno];
    }

    public boolean ocupar(int puesto, int turno) {
        validarPosicion(puesto, turno);

        if (ocupados[puesto][turno]) {
            return false;
        }

        ocupados[puesto][turno] = true;
        return true;
    }

    public boolean liberar(int puesto, int turno) {
        validarPosicion(puesto, turno);

        if (!ocupados[puesto][turno]) {
            return false;
        }

        ocupados[puesto][turno] = false;
        return true;
    }
}