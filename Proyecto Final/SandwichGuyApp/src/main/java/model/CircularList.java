package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Lista circular genérica básica que requiere T comparable.
 * Implementación con referencia a 'actual' (cursor) y 'tail' (último nodo).
 *
 * Notas:
 * - actual puede entenderse como la "cabeza" lógica; tail.sig == actual cuando
 * la lista no está vacía.
 * - La clase NO es segura para acceso concurrente.
 */
public class CircularList<T extends Comparable<? super T>> {

    private class Nodo {
        T dato;
        Nodo sig;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo actual = null;
    private Nodo tail = null; // último nodo (tail.sig = actual)
    private int size = 0;

    public CircularList() {
    }

    /**
     * Añade el elemento al final de la lista (después de tail).
     */
    public void add(T dato) {
        Objects.requireNonNull(dato, "dato no puede ser null");
        Nodo nuevo = new Nodo(dato);
        if (actual == null) {
            actual = nuevo;
            tail = nuevo;
            nuevo.sig = nuevo;
        } else {
            nuevo.sig = actual;
            tail.sig = nuevo;
            tail = nuevo;
        }
        size++;
    }

    /**
     * Devuelve el elemento en la posición "actual" sin eliminarlo.
     */
    public T peekCurrent() {
        return actual == null ? null : actual.dato;
    }

    /**
     * Avanza el cursor "actual" una posición (hacia sig).
     * No hace nada si la lista está vacía.
     */
    public void next() {
        if (actual != null) {
            actual = actual.sig;
        }
    }

    /**
     * Rota el cursor actual 'steps' veces (positivo para avanzar).
     */
    public void rotate(int steps) {
        if (actual == null || steps == 0)
            return;
        int s = Math.floorMod(steps, size);
        for (int i = 0; i < s; i++) {
            actual = actual.sig;
        }
    }

    /**
     * Elimina el nodo en la posición actual y devuelve su dato.
     * Si la lista está vacía devuelve null.
     */
    public T removeCurrent() {
        if (actual == null)
            return null;
        // Si hay un solo elemento
        if (size == 1) {
            T eliminado = actual.dato;
            actual = null;
            tail = null;
            size = 0;
            return eliminado;
        }
        // Buscar anterior al actual (no mantenemos prev explícito)
        Nodo ant = actual;
        while (ant.sig != actual) {
            ant = ant.sig;
        }
        // ant es anterior a actual
        if (actual == tail) {
            tail = ant;
        }
        T eliminado = actual.dato;
        ant.sig = actual.sig;
        actual = actual.sig;
        size--;
        return eliminado;
    }

    /**
     * Elimina y devuelve el elemento en la posición index (0 = actual).
     * Lanza IndexOutOfBoundsException si index fuera de rango.
     */
    public T removeAt(int index) {
        checkIndex(index);
        if (index == 0)
            return removeCurrent();
        Nodo prev = nodeAt(index - 1);
        Nodo target = prev.sig;
        prev.sig = target.sig;
        if (target == tail) {
            tail = prev;
        }
        size--;
        return target.dato;
    }

    /**
     * Devuelve el elemento en la posición index (0 = actual). Lanza
     * IndexOutOfBoundsException si fuera de rango.
     */
    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).dato;
    }

    private Nodo nodeAt(int index) {
        checkIndex(index);
        Nodo temp = actual;
        for (int i = 0; i < index; i++) {
            temp = temp.sig;
        }
        return temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    }

    /**
     * Ordena los elementos según su compareTo natural.
     */
    public void ordenar() {
        if (size < 2)
            return;
        List<T> list = toList();
        Collections.sort(list);
        // reconstruir
        actual = null;
        tail = null;
        size = 0;
        for (T x : list)
            add(x);
        // actual ya apunta al primer elemento tras las adds
    }

    /**
     * Devuelve una lista (ArrayList) con los elementos en el orden desde 'actual'.
     */
    public List<T> toList() {
        List<T> res = new ArrayList<>(size);
        if (actual == null)
            return res;
        Nodo temp = actual;
        for (int i = 0; i < size; i++) {
            res.add(temp.dato);
            temp = temp.sig;
        }
        return res;
    }

    /**
     * Representación legible de los elementos con índice empezando en 1.
     */
    public String mostrar() {
        if (actual == null)
            return "(vacía)";
        StringBuilder sb = new StringBuilder();
        Nodo temp = actual;
        int idx = 1;
        do {
            sb.append(idx++).append(": ").append(temp.dato.toString()).append("\n");
            temp = temp.sig;
        } while (temp != actual);
        return sb.toString().trim();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Busca si existe un elemento equals() al dado.
     */
    public boolean contains(T dato) {
        return indexOf(dato) != -1;
    }

    /**
     * Índice del primer elemento igual al dado, o -1 si no existe.
     */
    public int indexOf(T dato) {
        if (actual == null)
            return -1;
        Nodo temp = actual;
        for (int i = 0; i < size; i++) {
            if (temp.dato.equals(dato))
                return i;
            temp = temp.sig;
        }
        return -1;
    }

    /**
     * Vacía la lista.
     */
    public void clear() {
        actual = null;
        tail = null;
        size = 0;
    }
}