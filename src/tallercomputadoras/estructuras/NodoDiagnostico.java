package tallercomputadoras.estructuras;

import tallercomputadoras.modelo.PasoDiagnostico;

class NodoDiagnostico {

    private final PasoDiagnostico paso;
    private NodoDiagnostico hijoSi; // Izquierda
    private NodoDiagnostico hijoNo; // Derecha

    NodoDiagnostico(PasoDiagnostico paso) {
        this.paso = paso;
    }

    PasoDiagnostico getPaso() {
        return paso;
    }

    NodoDiagnostico getHijoSi() {
        return hijoSi;
    }

    void setHijoSi(NodoDiagnostico hijoSi) {
        this.hijoSi = hijoSi;
    }

    NodoDiagnostico getHijoNo() {
        return hijoNo;
    }

    void setHijoNo(NodoDiagnostico hijoNo) {
        this.hijoNo = hijoNo;
    }
}
