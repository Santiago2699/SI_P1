package es.udc.sistemasinteligentes.ejemploBusquedaGrafop;

public abstract class Estado {
    /* El estado deberá sobreescribir estos métodos para mostrarse correctamente y permitir comparaciones. */

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

}
