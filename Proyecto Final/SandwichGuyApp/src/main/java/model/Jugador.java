package model;

/**
 * Jugador simple: posee una Mano.
 */
public class Jugador {

    private final Mano mano = new Mano();
    private String nombre = "Jugador 1";

    public Jugador() {
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCarta(Carta c) {
        mano.agregar(c);
    }

    public Carta jugarCarta() {
        return mano.removerActual();
    }

    public Mano getMano() {
        return mano;
    }

    public String mostrarMano() {
        return mano.mostrar();
    }

    public void ordenarMano() {
        mano.ordenar();
    }

    public int manoSize() {
        return mano.size();
    }
}
