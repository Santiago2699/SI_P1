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

        Nodo padre = new Nodo(estadoActual, null,null);
        Nodo nodo = null;

        System.out.println(" - Empezando búsqueda en " + "\n"+estadoActual);

        if((nodo = sucesores(padre, p, explorados)) != null)
            return reconstruye_sol(nodo);
        else
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

    public static Nodo sucesores(Nodo nodo, ProblemaBusqueda p, ArrayList<Nodo> explorados){
        ProblemaCuadradoMagico.EstadoCuadradoMagico estado = (ProblemaCuadradoMagico.EstadoCuadradoMagico) nodo.getEstado();
        if(p.esMeta(nodo.getEstado()))
            return nodo;
        for (Nodo e: explorados){
            if(e.getEstado().equals(nodo.getEstado())) //existe en explorados
                return null;
        }
        if (estado.tableroLleno())
            return null;

        Estado estaduwu = nodo.getEstado();
        Estado prueba = null;
        Accion[] accionesDisp = p.acciones(estaduwu);
        Nodo meta = null;
        explorados.add(nodo);
        for(Accion acc : accionesDisp){
            prueba = p.result(estaduwu,acc);
            Nodo neuwu = new Nodo(prueba, nodo, acc);
            meta = sucesores(neuwu, p, explorados);
            if(meta != null)
                break;

        }
        return meta;
    }

    /*public static ArrayList<Nodo> sucesores(Nodo nodo, ProblemaBusqueda p){
        ArrayList<Nodo> sucesores = new ArrayList<>();
        Estado estado = nodo.getEstado();
        Estado prueba = null;
        Accion[] accionesDisp = p.acciones(estado);
        for(Accion acc : accionesDisp){
            prueba = p.result(estado,acc);
            sucesores.add(new Nodo(prueba, nodo, acc));
        }
        return sucesores;
    }*/


}
