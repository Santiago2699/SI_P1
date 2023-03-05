package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.problemaCuadradoMagico.NodoA;

import java.util.ArrayList;
import java.util.Arrays;

public interface EstrategiaBusquedaInformada {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @param h Heurística que asigna a un estado un valor de utilidad
     * @return Estado meta obtenido
     */
    public abstract ArrayList<NodoA> soluciona(ProblemaBusqueda p, Heuristica h) throws Exception;
}
