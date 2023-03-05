package es.udc.sistemasinteligentes.problemaCuadradoMagico;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class NodoA implements Comparable<NodoA> {
    private final Estado estado;
    private final NodoA padre;
    private final Accion accion;
    float coste = 0;
    float f = 0;

    public NodoA(Estado estado, NodoA padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Estado getEstado() {
        return estado;
    }

    public NodoA getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setF(float f){ this.f = f; }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    public float getCoste() {
        return coste;
    }

    public float getF() {
        return f;
    }


    @Override
    public int compareTo(NodoA o) {
        return -1*Float.compare(o.f, this.f);
    }
}
