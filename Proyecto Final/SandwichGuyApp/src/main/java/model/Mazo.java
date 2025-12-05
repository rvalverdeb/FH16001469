package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Mazo: pila de cartas (se muestran ocultas en la GUI).
 * Implementado con LinkedList; push/pop por el frente.
 */
public class Mazo {

    private final LinkedList<Carta> pila = new LinkedList<>();

    public Mazo() {
    }

    /**
     * Empuja una carta al tope del mazo (primer elemento).
     */
    public void push(Carta c) {
        if (c != null) {
            pila.addFirst(c);
        }
    }

    /**
     * Empuja todas las cartas de la lista, manteniendo el orden de la lista
     * (el primer elemento de la lista quedará más profundo en la pila).
     */
    public void pushAll(List<Carta> cartas) {
        if (cartas == null || cartas.isEmpty())
            return;
        // Añadir en orden: el primer elemento de 'cartas' debe quedar abajo,
        // así que añadimos desde el final al comienzo.
        for (int i = cartas.size() - 1; i >= 0; i--) {
            Carta c = cartas.get(i);
            if (c != null)
                pila.addFirst(c);
        }
    }

    /**
     * Saca y devuelve la carta del tope. Devuelve null si está vacío.
     */
    public Carta pop() {
        if (pila.isEmpty())
            return null;
        return pila.removeFirst();
    }

    /**
     * Devuelve la carta del tope sin retirarla, o null si está vacío.
     */
    public Carta peek() {
        return pila.peekFirst();
    }

    /**
     * Extrae hasta n cartas del mazo y las devuelve en una lista en el
     * orden en que fueron extraídas (primera extraída en índice 0).
     * Si n <= 0 devuelve lista vacía.
     */
    public List<Carta> draw(int n) {
        List<Carta> salida = new ArrayList<>();
        if (n <= 0)
            return salida;
        for (int i = 0; i < n && !pila.isEmpty(); i++) {
            salida.add(pila.removeFirst());
        }
        return salida;
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
     * Cargar sin barajar: conserva el orden de la lista recibida
     * (primer elemento de la lista quedará más profundo en la pila).
     */
    public void cargar(List<Carta> cartas) {
        pila.clear();
        if (cartas == null || cartas.isEmpty())
            return;
        pushAll(cartas);
    }

    /**
     * Cargar y barajar: carga las cartas y las baraja aleatoriamente.
     */
    public void cargarYBarajar(List<Carta> cartas) {
        pila.clear();
        if (cartas == null || cartas.isEmpty())
            return;
        List<Carta> copia = new ArrayList<>(cartas);
        Collections.shuffle(copia);
        // Después de barajar, queremos que el primer elemento de 'copia'
        // quede en el tope del mazo; añadimos en orden inverso para mantener
        // la semántica de addFirst.
        for (int i = copia.size() - 1; i >= 0; i--) {
            pila.addFirst(copia.get(i));
        }
    }

    /**
     * Baraja las cartas que ya están en el mazo.
     */
    public void shuffle() {
        if (pila.size() <= 1)
            return;
        List<Carta> temp = new ArrayList<>(pila);
        Collections.shuffle(temp);
        pila.clear();
        // Mantener la semántica de tope en removeFirst(): añadimos desde el final
        for (int i = temp.size() - 1; i >= 0; i--) {
            pila.addFirst(temp.get(i));
        }
    }

    /**
     * Devuelve una copia del contenido del mazo en orden desde el tope (índice 0 =
     * tope).
     */
    public List<Carta> toList() {
        return new ArrayList<>(pila);
    }

    @Override
    public String toString() {
        return "Mazo(" + pila.size() + " cartas)"; // no revela contenido
    }
}
