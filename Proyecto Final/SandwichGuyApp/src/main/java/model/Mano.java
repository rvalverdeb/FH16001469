package model;

/**
 * Wrapper sobre CircularList para la Mano del jugador.
 */
public class Mano {

    private final CircularList<Carta> lista = new CircularList<>();

    public void agregar(Carta c) {
        if (c != null)
            lista.add(c);
    }

    public Carta removerActual() {
        return lista.removeCurrent();
    }

    public void ordenar() {
        lista.ordenar();
    }

    public String mostrar() {
        return lista.mostrar();
    }

    public int size() {
        return lista.size();
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }
}
