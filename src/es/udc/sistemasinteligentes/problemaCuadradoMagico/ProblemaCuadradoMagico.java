package es.udc.sistemasinteligentes.problemaCuadradoMagico;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadradoMagico extends Estado{
         int[][] estadoCuadrado;
         int tamano;

        public EstadoCuadradoMagico(int[][] estadoCuadrado, int tamano){
            this.estadoCuadrado = estadoCuadrado;
            this.tamano = tamano;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            for (int[] arr: estadoCuadrado){
                str.append(Arrays.toString(arr) + "\n");
            }

            return  str.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EstadoCuadradoMagico that = (EstadoCuadradoMagico) o;
            return tamano == that.tamano && Arrays.deepEquals(estadoCuadrado, that.estadoCuadrado);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(tamano);
            result = 31 * result + Arrays.deepHashCode(estadoCuadrado);
            return result;
        }

        public boolean tableroLleno(){
            for (int i = 0; i < tamano ; i++) {
                for (int j = 0; j < tamano; j++) {
                    if(estadoCuadrado[i][j] == 0)
                        return false;
                }
            }
            return true;
        }

    }

    public static class AccionesCuadradoMagico extends Accion{

        int fila;
        int columna;
        int contenido;

        public AccionesCuadradoMagico(int fila, int columna, int contenido) {
            this.fila = fila;
            this.columna = columna;
            this.contenido = contenido;
        }

        @Override
        public String toString() {
            return "(fila: " + fila +", columna: " + columna + ", " + contenido + ")";

        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico s = (EstadoCuadradoMagico) es;
            int column = 0, row = 0, diag1 = 0, diag2 = 0;
            boolean columnB = true, rowB = true, diag1B = true, diag2B = true;
            int numMagico = s.tamano *(s.tamano*s.tamano + 1)/2;

           if(s.estadoCuadrado[fila][columna] == 0){
               s.estadoCuadrado[fila][columna] = contenido;
               for (int i = 0; i < s.tamano; i++){
                   rowB = rowB && s.estadoCuadrado[fila][i] != 0;
                   row += s.estadoCuadrado[fila][i];
                   columnB = columnB && s.estadoCuadrado[i][columna] != 0;
                   column += s.estadoCuadrado[i][columna];
                   diag1B = diag1B && s.estadoCuadrado[i][i] != 0;
                   diag1 += s.estadoCuadrado[i][i];
                   diag2B = diag2B && s.estadoCuadrado[i][s.tamano-i-1] != 0;
                   diag2 += s.estadoCuadrado[i][s.tamano-i-1];
                   for (int j = 0; j < s.tamano; j++){
                       if(s.estadoCuadrado[i][j] == contenido && (i != fila || j != columna)) {
                           s.estadoCuadrado[fila][columna] = 0;
                           return false;
                       }
                   }
               }
               s.estadoCuadrado[fila][columna] = 0;

               return fila <= numMagico && columna <= numMagico && diag2 <= numMagico && diag1 <= numMagico &&
                       !(rowB && (row < numMagico)) && !(columnB && (column < numMagico)) && !(diag1B && (diag1 < numMagico))
                      && !(diag2B && (diag2 < numMagico));
           }else

               return false;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico s = (EstadoCuadradoMagico) es;
            int tamano = s.tamano;
            int [][] copia = deepCopy(s.estadoCuadrado);
            copia[fila][columna] = contenido;
            return new EstadoCuadradoMagico(copia, tamano);
        }

        public static int[][] deepCopy(int[][] original) {
            if (original == null) {
                return null;
            }
            int[][] copy = new int[original.length][];
            for (int i = 0; i < original.length; i++) {
                copy[i] = Arrays.copyOf(original[i], original[i].length);
            }
            return copy;
        }
    }

    public static class HeuristicaCuadradoMagico extends Heuristica {
        public float evalua(Estado e) {
            EstadoCuadradoMagico s = (EstadoCuadradoMagico) e;
            int[][] state = s.estadoCuadrado;
            int n = state.length;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int value = state[i][j];
                    if (value != 0) { // skip the empty cell
                        int goalX = (value - 1) / n;
                        int goalY = (value - 1) % n;
                        sum += Math.abs(i - goalX) + Math.abs(j - goalY);
                    }
                }
            }
            return sum;
        }
        /*@Override
        public float evalua(Estado e) {
            EstadoCuadradoMagico s = (EstadoCuadradoMagico) e;
            float heuristica = 0, filas = 0, columnas = 0, diagonal1 = 0, diagonal2 = 0;
            int suma = (s.tamano*(s.tamano*s.tamano + 1))/2;
            for (int i = 0; i < s.tamano; i++) {
                diagonal1 += s.estadoCuadrado[i][i];
                diagonal2 += s.estadoCuadrado[i][s.tamano-i-1];
                for (int j = 0; j < s.tamano; j++) {
                    filas += s.estadoCuadrado[i][j];
                    columnas += s.estadoCuadrado[j][i];
                }
                heuristica += Math.abs(suma-filas) + Math.abs(suma-columnas);
                filas = 0; columnas = 0;

            }
            heuristica += Math.abs(suma - diagonal1) + Math.abs(suma-diagonal2);
            return -1*heuristica;
        }*/
    }

    Accion[] acciones = null;

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico s = (EstadoCuadradoMagico) es;
        int columna = 0;
        int fila = 0;
        int diagonal1 = 0;
        int diagonal2 = 0;
        int suma = (s.tamano*(s.tamano*s.tamano + 1))/2;

        for(int i = 0; i < s.tamano; i++){
            diagonal1 = diagonal1 + s.estadoCuadrado[i][i];
            diagonal2 = diagonal2 + s.estadoCuadrado[i][s.tamano-i-1];
            for(int j = 0; j < s.tamano; j++){
                fila = fila +s.estadoCuadrado[i][j];
                columna = columna + s.estadoCuadrado[j][i];
            }
            if (fila != suma || columna != suma)
                return false;
            fila = 0;
            columna = 0;
        }
        return diagonal1 == suma && diagonal2 == suma;
    }

    @Override
    public Accion[] acciones(Estado es) {
        EstadoCuadradoMagico s = (EstadoCuadradoMagico) es;
        ArrayList<Accion> accs = new ArrayList<>();
        Accion acc = null;
        for(int i = 0; i < s.tamano; i++) {
            for (int j = 0; j < s.tamano; j++) {
                for (int k = 1; k <= s.tamano*s.tamano; k++) {
                    acc = new AccionesCuadradoMagico(i, j, k);
                    if (acc.esAplicable(s))
                        accs.add(acc);
                }
            }
        }
        acciones = new Accion[accs.size()];
        return accs.toArray(acciones);
    }
}
