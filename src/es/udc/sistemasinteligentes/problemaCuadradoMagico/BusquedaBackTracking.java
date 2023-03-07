package es.udc.sistemasinteligentes.problemaCuadradoMagico;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Stack;

public class BusquedaBackTracking implements EstrategiaBusqueda {
    @Override
    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> explorados = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        Stack<Nodo> frontera = new Stack<>();
        ArrayList<Nodo> hijos = new ArrayList<>();
        Nodo padre = new Nodo(estadoActual, null,null);
        Nodo nodo = null;

        explorados.add(padre);
        frontera.push(padre);
        int expandidos = 0;
        int creados = 0;

        System.out.println(" - Empezando búsqueda en " + "\n"+estadoActual);

        while (!frontera.isEmpty()) {
            nodo = frontera.pop();
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
                if(!noInsertar) frontera.push(nh);
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
        Estado prueba = null;
        Accion[] accionesDisp = p.acciones(estado);
        for(Accion acc : accionesDisp){
            prueba = p.result(estado,acc);
            sucesores.add(new Nodo(prueba, nodo, acc));
        }
        return sucesores;
    }
}
