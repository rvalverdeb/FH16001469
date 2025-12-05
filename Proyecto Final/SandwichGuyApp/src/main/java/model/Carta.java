package model;

/**
 * Representa una carta de la baraja con un id único (0..51) para persistencia
 * y preservación de referencias entre estructuras.
 *
 * id: identificador único asignado por Caja.inicializar()
 * nombre: "A","2",...,"K"
 * palo: "♣","♦","♥","♠"
 * valor: 1..13
 */
public class Carta implements Comparable<Carta> {

    private final int id;
    private final String nombre;
    private final String palo;
    private final int valor;

    /**
     * Constructor con id (recomendado para criar la baraja maestra).
     */
    public Carta(int id, String nombre, String palo, int valor) {
        if (nombre == null)
            throw new IllegalArgumentException("nombre no puede ser null");
        if (palo == null)
            throw new IllegalArgumentException("palo no puede ser null");
        if (valor < 1 || valor > 13)
            throw new IllegalArgumentException("valor debe estar entre 1 y 13");
        this.id = id;
        this.nombre = nombre;
        this.palo = palo;
        this.valor = valor;
    }

    /**
     * Constructor sin id (no recomendado para la lógica de la partida; útil
     * solo para casos puntuales). Las cartas de la partida deben tener id único.
     */
    public Carta(String nombre, String palo, int valor) {
        this(-1, nombre, palo, valor);
    }

    public int getId() {
        return id;
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

    /**
     * Devuelve el color del palo: "rojo" para ♥ y ♦, "negro" para ♠ y ♣.
     */
    public String getColor() {
        if ("♥".equals(palo) || "♦".equals(palo) || "CORAZONES".equalsIgnoreCase(palo)
                || "DIAMANTES".equalsIgnoreCase(palo)) {
            return "rojo";
        }
        return "negro";
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

    /**
     * Igualdad basada en id si está presente (id >= 0). Si id == -1 compara
     * por contenido (nombre,palo,valor).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Carta))
            return false;
        Carta c = (Carta) o;
        if (this.id >= 0 && c.id >= 0) {
            return this.id == c.id;
        }
        return this.valor == c.valor && this.nombre.equals(c.nombre) && this.palo.equals(c.palo);
    }

    @Override
    public int hashCode() {
        if (id >= 0)
            return Integer.hashCode(id);
        int result = nombre.hashCode();
        result = 31 * result + palo.hashCode();
        result = 31 * result + Integer.hashCode(valor);
        return result;
    }
}