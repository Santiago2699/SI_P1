package es.udc.sistemasinteligentes.problemaCuadradoMagico;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.problemaCuadradoMagico.ProblemaCuadradoMagico.*;
import java.util.ArrayList;
import java.util.Stack;

public class BusquedaBackTracking implements EstrategiaBusqueda {
    static long explorados = 1;
    @Override
    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception {
        Estado estadoActual = p.getEstadoInicial();
        Stack<Nodo> frontera = new Stack<>();
        Nodo padre = new Nodo(estadoActual, null,null);
        Nodo nodo = null;
        frontera.push(padre);
        System.out.println(" - Empezando búsqueda en " + "\n"+estadoActual);

        if((nodo = sucesores( p, frontera)) != null) {
            System.out.println(explorados);
            return reconstruye_sol(nodo);
        }
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

    public static Nodo sucesores(ProblemaBusqueda p, Stack<Nodo> frontera){
        Nodo nodo = frontera.peek();
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) nodo.getEstado();
        if(p.esMeta(nodo.getEstado()))
            return nodo;

        if (estado.tableroLleno())
            return null;


        Estado prueba = null;
        //Accion[] accionesDisp = p.acciones(estaduwu);
        Nodo meta = null;
        boolean estaFron= false;
        for(int i = 0; i < estado.tamano; i++) {
            for (int j = 0; j < estado.tamano; j++) {
                for (int k = 1; k <= estado.tamano* estado.tamano; k++) {
                    AccionesCuadradoMagico acc = new AccionesCuadradoMagico(i, j, k);
                    if (acc.esAplicable(estado)){
                        prueba = p.result(estado,acc);
                        Nodo neuwu = new Nodo(prueba, nodo, acc);
                        for (Nodo n: frontera) {
                            estaFron = n.getEstado().equals(neuwu.getEstado());
                            if(estaFron)
                                break;
                        }
                        if(!estaFron) {
                            frontera.push(neuwu);
                            explorados++;
                            meta = sucesores(p, frontera);
                            if (meta != null) {
                                frontera.pop();
                                return meta;
                            }
                        }
                    }

                }
            }
        }
        frontera.pop();
       /* for(Accion acc : accionesDisp){
            prueba = p.result(estaduwu,acc);
            Nodo neuwu = new Nodo(prueba, nodo, acc);
            meta = sucesores(neuwu, p);
            if(meta != null)
                break;

        }*/
        return null;
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
