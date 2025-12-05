package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Caja: contiene las 52 cartas iniciales (maestra) y la lista actual de cartas
 * que aún están en la caja (cuando aún no se barajaron / movieron).
 *
 * Mantiene:
 * - maestra: las 52 instancias originales (siempre presentes después de
 * inicializar)
 * - cartas: la sublista actual que representa las cartas aún en la caja
 *
 * La existencia de la lista 'maestra' permite persistencia por id y restaurar
 * exactamente las mismas instancias al cargar.
 */
public class Caja {

    private final List<Carta> maestra = new ArrayList<>(); // 52 cartas maestras
    private final List<Carta> cartas = new ArrayList<>(); // contenido actual de la caja (subset)

    public Caja() {
        // no inicializar aquí para que el flujo de la app decida cuándo
    }

    /**
     * Inicializa la baraja maestra (52 cartas) y coloca una copia en la caja
     * actual.
     * Asigna ids 0..51 en un orden consistente.
     */
    public void inicializar() {
        maestra.clear();
        cartas.clear();
        String[] palos = { "♣", "♦", "♥", "♠" };
        String[] nombres = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        int id = 0;
        for (String palo : palos) {
            for (int i = 0; i < nombres.length; i++) {
                Carta c = new Carta(id++, nombres[i], palo, i + 1);
                maestra.add(c);
                cartas.add(c);
            }
        }
    }

    /**
     * Baraja la caja (usando la baraja maestra) y transfiere las referencias al
     * mazo.
     * No destruye la lista maestra; vacía la lista 'cartas' actual.
     */
    public void barajar(Mazo mazo) {
        Objects.requireNonNull(mazo, "mazo no puede ser null");
        List<Carta> copia = new ArrayList<>(maestra);
        Collections.shuffle(copia);
        mazo.cargarYBarajar(copia);
        cartas.clear();
    }

    /**
     * Transfiere las cartas al Mazo sin barajar (manteniendo el orden actual de la
     * lista).
     * Útil para casos de restauración/recuperación donde quieres conservar el
     * orden.
     */
    public void transferirSinBarajar(Mazo mazo) {
        Objects.requireNonNull(mazo, "mazo no puede ser null");
        mazo.cargar(new ArrayList<>(cartas));
        cartas.clear();
    }

    /**
     * Carga la caja desde una lista (sobrescribe el contenido actual de la caja).
     * No modifica la baraja maestra.
     */
    public void cargar(List<Carta> nuevasCartas) {
        cartas.clear();
        if (nuevasCartas == null || nuevasCartas.isEmpty())
            return;
        cartas.addAll(nuevasCartas);
    }

    /**
     * Reemplaza la baraja maestra (usado al cargar desde XML con ids).
     * También puede opcionalmente establecer el contenido actual de la caja.
     */
    public void setMaestra(List<Carta> maestraNueva, List<Carta> contenidoActual) {
        maestra.clear();
        if (maestraNueva != null)
            maestra.addAll(maestraNueva);
        cartas.clear();
        if (contenidoActual != null)
            cartas.addAll(contenidoActual);
    }

    /**
     * Devuelve una copia de la baraja maestra (las 52 cartas en orden de id).
     */
    public List<Carta> getMaestra() {
        return new ArrayList<>(maestra);
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int size() {
        return cartas.size();
    }

    /**
     * Devuelve una copia del contenido actual de la caja (segura para la GUI).
     */
    public List<Carta> getCartas() {
        return new ArrayList<>(cartas);
    }

    @Override
    public String toString() {
        return "Caja(contenido=" + cartas.size() + ", maestra=" + maestra.size() + ")";
    }
}