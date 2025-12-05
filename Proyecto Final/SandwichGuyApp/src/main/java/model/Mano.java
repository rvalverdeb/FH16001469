package model;

import java.util.List;
import java.util.Objects;

/**
 * Wrapper sobre CircularList para la Mano del jugador.
 * Proporciona operaciones útiles para la lógica del juego:
 * - agregar / agregarTodas
 * - removerActual / removerEn
 * - obtener por índice
 * - rotar / siguiente / peekActual
 * - ordenar / mostrar / size / estaVacia / toList
 */
public class Mano {

    private final CircularList<Carta> lista = new CircularList<>();

    public Mano() {
    }

    public void agregar(Carta c) {
        if (c != null)
            lista.add(c);
    }

    /**
     * Agrega todas las cartas de la lista en el orden dado.
     */
    public void agregarTodas(List<Carta> cartas) {
        if (cartas == null || cartas.isEmpty())
            return;
        for (Carta c : cartas) {
            if (c != null)
                lista.add(c);
        }
    }

    /**
     * Remueve y devuelve la carta en la posición "actual" (cursor).
     */
    public Carta removerActual() {
        return lista.removeCurrent();
    }

    /**
     * Remueve y devuelve la carta en la posición index (0 = actual).
     * Lanza IndexOutOfBoundsException si index inválido.
     */
    public Carta removerEn(int index) {
        return lista.removeAt(index);
    }

    /**
     * Devuelve la carta en la posición index (0 = actual) sin removerla.
     */
    public Carta get(int index) {
        return lista.get(index);
    }

    /**
     * Ordena la mano por valor (según compareTo de Carta).
     */
    public void ordenar() {
        lista.ordenar();
    }

    /**
     * Devuelve la representación textual de la mano.
     */
    public String mostrar() {
        return lista.mostrar();
    }

    public int size() {
        return lista.size();
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }

    /**
     * Rota el cursor 'steps' posiciones (positivo = avanzar).
     */
    public void rotar(int steps) {
        lista.rotate(steps);
    }

    /**
     * Avanza el cursor una posición.
     */
    public void siguiente() {
        lista.next();
    }

    /**
     * Devuelve la carta en la posición "actual" sin removerla.
     */
    public Carta peekActual() {
        return lista.peekCurrent();
    }

    /**
     * Devuelve una copia de los elementos de la mano en orden desde "actual".
     */
    public List<Carta> toList() {
        return lista.toList();
    }
}
