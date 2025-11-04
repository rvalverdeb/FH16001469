package model;

/**
 *
 * @author USER
 */
public class Carta {
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }

    private final Suit palo;
    private final int valor; // 1..13 (1=A, 11=J,12=Q,13=K)
    private final String nombre; // "As", "Dos", ...

    // Constructor correcto
    public Carta(Suit palo, int valor) {
        this.palo = palo;
        this.valor = valor;
        this.nombre = calcularNombre(valor);
    }

    private String calcularNombre(int v) {
        return switch (v) {
            case 1 -> "As";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> Integer.toString(v);
        };
    }

    public Suit getPalo() {
        return palo;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSimboloPalo() {
        return switch (palo) {
            case HEARTS -> "♥";
            case DIAMONDS -> "♦";
            case SPADES -> "♠";
            case CLUBS -> "♣";
        };
    }

    public String getColor() {
        return (palo == Suit.HEARTS || palo == Suit.DIAMONDS) ? "ROJO" : "NEGRO";
    }

    // Valor numérico para cálculos (1..13)
    public int valorNumerico() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("[%s|%s]", getNombre(), getSimboloPalo());
    }
}
