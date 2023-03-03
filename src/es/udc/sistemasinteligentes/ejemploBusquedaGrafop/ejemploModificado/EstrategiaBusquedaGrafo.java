package es.udc.sistemasinteligentes.ejemploBusquedaGrafop.ejemploModificado;

import es.udc.sistemasinteligentes.ejemploBusquedaGrafop.Accion;
import es.udc.sistemasinteligentes.ejemploBusquedaGrafop.Estado;
import es.udc.sistemasinteligentes.ejemploBusquedaGrafop.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ejemploBusquedaGrafop.ProblemaBusqueda;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    public EstrategiaBusquedaGrafo() {
    }

    @Override
    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> explorados = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        Queue<Nodo> frontera = new ArrayDeque<>();
        ArrayList<Nodo> hijos = new ArrayList<>();
        Nodo padre = new Nodo(estadoActual, null,null);
        Nodo nodo = null;

        explorados.add(padre);

        frontera.offer(padre);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()) {
            nodo = frontera.poll();
            estadoActual = nodo.getEstado();
            if(p.esMeta(estadoActual))
                return reconstruye_sol(nodo);
            explorados.add(nodo);
            hijos = sucesores(nodo, p);
            for (Nodo nh : hijos) {
                boolean noInsertar = false;
                for (Nodo e: explorados){
                    noInsertar = e.getEstado().equals(nh.getEstado());
                   if(noInsertar) break;
                }
                if(!noInsertar){
                    for(Nodo f: frontera){
                        noInsertar = f.getEstado().equals(nh.getEstado());
                        if(noInsertar) break;
                    }
                }
                if(!noInsertar) frontera.offer(nh);
            }
            padre = nodo;
        }
        throw new Exception("No se ha encontrado solucion");
    }

    public static ArrayList<Nodo> reconstruye_sol(Nodo nodo) {
       ArrayList<Nodo> solucion = new ArrayList<>();
       Nodo actual = nodo;

        while (actual != null) {
            solucion.add(actual);
            actual = actual.getPadre();
        }
        return solucion;
    }

    public static ArrayList<Nodo> sucesores(Nodo nodo, ProblemaBusqueda p){
        ArrayList<Nodo> sucesores = new ArrayList<>();
        Estado estado = nodo.getEstado();
        Accion[] accionesDisp = p.acciones(estado);
        for(Accion acc : accionesDisp){
            sucesores.add(new Nodo(p.result(estado, acc), nodo, acc));
        }
        return sucesores;
    }
}
