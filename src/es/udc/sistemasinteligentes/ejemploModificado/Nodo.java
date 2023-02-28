package es.udc.sistemasinteligentes.ejemploModificado;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {
    private Estado estado;
    private Nodo padre;
    private Accion accion;

    public Nodo(Estado estado, Nodo padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }
}
