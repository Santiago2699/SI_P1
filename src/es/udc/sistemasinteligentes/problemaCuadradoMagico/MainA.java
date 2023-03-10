package es.udc.sistemasinteligentes.problemaCuadradoMagico;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.EstrategiaBusquedaInformada;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class MainA {
    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        int[][] tablero = {{4,0,0},{0,0,0},{0,0,0}};
        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =
                new ProblemaCuadradoMagico.EstadoCuadradoMagico(tablero, 3);

        ProblemaBusqueda cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusquedaInformada buscador = new BusquedaAEstrella();
        Heuristica heuristica = new ProblemaCuadradoMagico.HeuristicaCuadradoMagico();

        NodoA nodo = null;
        ArrayList<NodoA> solucion = buscador.soluciona(cuadradoMagico, heuristica);
        System.out.println("Camino para llegar a la solucion");
        for (int i = solucion.size()-1; i >= 0; i--){
            nodo = solucion.get(i);
            try{
                System.out.println( "Accion a realizar:" + nodo.getAccion().toString());
            }catch (NullPointerException ignored) {}
            System.out.println("Estado actual: " +"\n"+ nodo.getEstado().toString());

        }
        System.out.println(((System.currentTimeMillis()- time)/1000)/60);
    }
}
