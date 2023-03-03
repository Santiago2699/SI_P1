package es.udc.sistemasinteligentes.ejemploBusquedaGrafop.ejemploModificado;

import es.udc.sistemasinteligentes.ejemploBusquedaGrafop.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ejemploBusquedaGrafop.ProblemaBusqueda;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();
        Nodo nodo = null;
        ArrayList <Nodo> solucion = buscador.soluciona(aspiradora);
        System.out.println("Camino para llegar a la solucion");
        for (int i = solucion.size()-1; i >= 0; i--){
            nodo = solucion.get(i);
            try{
                System.out.println( "Accion a realizar:" + nodo.getAccion().toString());
            }catch (NullPointerException ignored) {}
            System.out.println("Estado actual: " + nodo.getEstado().toString());

        }
    }
}
