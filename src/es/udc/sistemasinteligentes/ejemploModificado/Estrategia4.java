package es.udc.sistemasinteligentes.ejemploModificado;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<>();
        ArrayList<Nodo> solucion = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        solucion.add(new Nodo(estadoActual, null, null));
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)) {
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            Nodo padre = solucion.get(solucion.size() - 1);
            boolean modificado = false;
            for (Accion acc : accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                solucion.add(new Nodo(sc, padre, acc));
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
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return solucion;
    }

    public static ArrayList<Nodo> reconstruye_sol(ArrayList<Nodo> solucion) {
        Nodo actual = solucion.get(solucion.size() - 1);

        while (actual != null) {
            actual.getEstado().toString())
            actual = actual.getPadre();
        }
    }
}
