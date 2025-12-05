package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Pozo: cola FIFO de descartes.
 * Implementación basada en LinkedList para permitir operaciones útiles
 * como peek del primero/último y devolver una lista con el contenido.
 */
public class Pozo {

    private final LinkedList<Carta> cartas = new LinkedList<>();

    /**
     * Encola una carta al final del pozo (descartar).
     */
    public void enqueue(Carta c) {
        if (c != null)
            cartas.addLast(c);
    }

    /** alias por compatibilidad con versiones previas */
    public void colocar(Carta c) {
        enqueue(c);
    }

    /**
     * Encola todas las cartas de la lista, en el orden recibido
     * (el primer elemento de la lista quedará más profundo en el pozo).
     */
    public void colocarTodas(List<Carta> cs) {
        if (cs == null || cs.isEmpty())
            return;
        for (Carta c : cs) {
            if (c != null)
                cartas.addLast(c);
        }
    }

    /**
     * Extrae y devuelve la carta del frente del pozo (FIFO). Devuelve null si está
     * vacío.
     */
    public Carta dequeue() {
        return cartas.isEmpty() ? null : cartas.removeFirst();
    }

    /**
     * Devuelve la carta del frente sin extraerla, o null si está vacío.
     */
    public Carta peek() {
        return cartas.peekFirst();
    }

    /**
     * Devuelve la carta al final del pozo (la más recientemente descartada), o null
     * si está vacío.
     */
    public Carta peekUltimo() {
        return cartas.peekLast();
    }

    public int size() {
        return cartas.size();
    }

    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    /**
     * Vacía el pozo.
     */
    public void vaciar() {
        cartas.clear();
    }

    /**
     * Devuelve una copia del contenido del pozo en orden FIFO (índice 0 = frente).
     */
    public List<Carta> toList() {
        return new ArrayList<>(cartas);
    }

    /**
     * Representación legible del pozo. Muestra todas las cartas en orden FIFO.
     */
    public String mostrar() {
        if (cartas.isEmpty())
            return "(Pozo vacío)";
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        for (Carta c : cartas) {
            sb.append(idx++).append(": ").append(c.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
