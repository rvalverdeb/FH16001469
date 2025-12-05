package model;

import java.util.List;

/**
 * Jugador simple: posee una Mano.
 */
public class Jugador {

    private final Mano mano = new Mano();
    private String nombre = "Jugador 1";

    public Jugador() {
    }

    public Jugador(String nombre) {
        if (nombre != null && !nombre.isBlank())
            this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank())
            this.nombre = nombre;
    }

    /**
     * Agrega una carta a la mano.
     */
    public void agregarCarta(Carta c) {
        mano.agregar(c);
    }

    /**
     * Agrega varias cartas a la mano en el orden dado.
     */
    public void agregarCartas(List<Carta> cartas) {
        mano.agregarTodas(cartas);
    }

    /**
     * Jugar (remover) la carta en la posición "actual" de la mano.
     */
    public Carta jugarCarta() {
        return mano.removerActual();
    }

    /**
     * Jugar (remover) la carta en el índice dado (0 = actual).
     * Lanza IndexOutOfBoundsException si el índice es inválido.
     */
    public Carta jugarCartaEn(int index) {
        return mano.removerEn(index);
    }

    /**
     * Devuelve la carta en la posición actual sin removerla.
     */
    public Carta peekCartaActual() {
        return mano.peekActual();
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

    public boolean isManoVacia() {
        return mano.estaVacia();
    }

    /**
     * Devuelve una copia de la mano en orden desde actual.
     */
    public List<Carta> manoToList() {
        return mano.toList();
    }
}