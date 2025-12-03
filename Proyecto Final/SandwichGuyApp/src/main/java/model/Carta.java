package model;

/**
 * Representa una carta con nombre (A,2,..,K), palo (♣ ♦ ♥ ♠) y valor numérico
 * 1..13
 */
public class Carta implements Comparable<Carta> {

    private final String nombre;
    private final String palo;
    private final int valor;

    public Carta(String nombre, String palo, int valor) {
        this.nombre = nombre;
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPalo() {
        return palo;
    }

    @Override
    public int compareTo(Carta o) {
        int cmp = Integer.compare(this.valor, o.valor);
        if (cmp != 0)
            return cmp;
        return this.palo.compareTo(o.palo);
    }

    @Override
    public String toString() {
        return "[" + nombre + "|" + palo + "]";
    }
}