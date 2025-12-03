package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Pozo: cola FIFO de descartes.
 */
public class Pozo {

    private final Queue<Carta> cartas = new LinkedList<>();

    public void enqueue(Carta c) {
        if (c != null)
            cartas.add(c);
    }

    /** alias por compatibilidad con versiones previas */
    public void colocar(Carta c) {
        enqueue(c);
    }

    public Carta dequeue() {
        return cartas.poll();
    }

    public int size() {
        return cartas.size();
    }

    public String mostrar() {
        if (cartas.isEmpty())
            return "(Pozo vacío)";
        StringBuilder sb = new StringBuilder();
        for (Carta c : cartas)
            sb.append(c.toString()).append(" ");
        return sb.toString().trim();
    }
}
