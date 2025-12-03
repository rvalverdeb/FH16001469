package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Mazo: pila de cartas (se muestran ocultas en la GUI).
 * Implementado con LinkedList; push/pop por el frente.
 */
public class Mazo {

    private final LinkedList<Carta> pila = new LinkedList<>();

    public void push(Carta c) {
        if (c != null)
            pila.addFirst(c);
    }

    public Carta pop() {
        if (pila.isEmpty())
            return null;
        return pila.removeFirst();
    }

    public int size() {
        return pila.size();
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public void vaciar() {
        pila.clear();
    }

    /**
     * Cargar desde una lista (mantiene el orden de la lista) y barajar.
     * Útil si decides pasar la lista completa.
     */
    public void cargarYBarajar(List<Carta> cartas) {
        pila.clear();
        pila.addAll(cartas);
        Collections.shuffle(pila);
    }

    @Override
    public String toString() {
        return "Mazo(" + pila.size() + " cartas)"; // no revela contenido
    }
}
