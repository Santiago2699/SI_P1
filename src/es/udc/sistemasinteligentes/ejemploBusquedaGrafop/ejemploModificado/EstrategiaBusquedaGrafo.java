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
        ArrayList<Estado> explorados = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        Queue<Nodo> frontera = new ArrayDeque<>();

        explorados.add(estadoActual);
        Nodo padre = new Nodo(estadoActual, null,null);
        Nodo nodo = null;
        frontera.offer(padre);
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)) {
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc : accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                nodo = new Nodo(sc, padre, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                } else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
            padre = nodo;
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return reconstruye_sol(nodo);
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
}
