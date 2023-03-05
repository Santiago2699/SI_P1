package es.udc.sistemasinteligentes.problemaCuadradoMagico;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BusquedaAEstrella implements EstrategiaBusquedaInformada {
    @Override
    public ArrayList<NodoA> soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        ArrayList<NodoA> explorados = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        Queue<NodoA> frontera = new PriorityQueue<>();
        ArrayList<NodoA> hijos = new ArrayList<>();
        NodoA padre = new NodoA(estadoActual, null,null);
        NodoA nodo = null;

        frontera.offer(padre);
        int expandidos = 0;
        int creados = 0;
        System.out.println(" - Empezando búsqueda en " + "\n"+estadoActual);

        while(!frontera.isEmpty()){
            nodo = frontera.poll();
            expandidos++;
            estadoActual = nodo.getEstado();
            if(p.esMeta(estadoActual)) {
                System.out.println("NODOS expandidos: " +expandidos);
                System.out.println("NODOS creados: " + creados);
                return reconstruye_sol(nodo);
            }
            explorados.add(nodo);
            hijos = sucesores(nodo, p);
            creados += hijos.size();

            for(NodoA nh: hijos){
                nh.setCoste(nodo.getCoste()+1);
                nh.setF(nh.getCoste()+ h.evalua(nh.getEstado()));
                boolean noInsertar = false;
                for (NodoA e: explorados){
                    noInsertar = e.getEstado().equals(nh.getEstado());
                    if(noInsertar) break;
                }
                for (NodoA f: frontera) {


                }
            }

        }
        return null;
    }

    public static ArrayList<NodoA> reconstruye_sol(NodoA nodo) {
        ArrayList<NodoA> solucion = new ArrayList<>();
        NodoA actual = nodo;

        while (actual != null) {
            solucion.add(actual);
            actual = actual.getPadre();
        }
        return solucion;
    }

    public static ArrayList<NodoA> sucesores(NodoA nodo, ProblemaBusqueda p){
        ArrayList<NodoA> sucesores = new ArrayList<>();
        Estado estado = nodo.getEstado();
        Estado prueba = null;
        Accion[] accionesDisp = p.acciones(estado);
        for(Accion acc : accionesDisp){
            prueba = p.result(estado,acc);
            sucesores.add(new NodoA(prueba, nodo, acc));
        }
        return sucesores;
    }
}
