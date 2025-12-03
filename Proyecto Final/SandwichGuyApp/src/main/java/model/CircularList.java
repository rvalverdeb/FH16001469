package model;

/**
 * Lista circular genérica básica que requiere T extends Comparable<T>.
 * Implementación optimizada con tail para inserciones O(1).
 */
public class CircularList<T extends Comparable<T>> {

    private class Nodo {
        T dato;
        Nodo sig;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo actual = null;
    private Nodo tail = null; // último nodo (su .sig = actual)
    private int size = 0;

    public void add(T dato) {
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

    public T removeCurrent() {
        if (actual == null)
            return null;
        T eliminado = actual.dato;

        if (size == 1) {
            actual = null;
            tail = null;
            size = 0;
            return eliminado;
        }

        // buscar anterior (O(n))
        Nodo ant = actual;
        while (ant.sig != actual)
            ant = ant.sig;

        // si actual es tail, actualizar tail
        if (actual == tail)
            tail = ant;

        ant.sig = actual.sig;
        actual = actual.sig;
        size--;
        return eliminado;
    }

    public void ordenar() {
        if (size < 2)
            return;
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) new Comparable[size];
        Nodo temp = actual;
        for (int i = 0; i < size; i++) {
            arr[i] = temp.dato;
            temp = temp.sig;
        }
        java.util.Arrays.sort(arr);
        // reconstruir lista
        actual = null;
        tail = null;
        size = 0;
        for (T x : arr)
            add(x);
        // mantener actual en el primer elemento (tail.sig)
        if (tail != null)
            actual = tail.sig;
    }

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
}
