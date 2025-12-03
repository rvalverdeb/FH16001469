package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Caja: contiene las 52 cartas iniciales. Permite inicializar y transferir al
 * mazo.
 */
public class Caja {

    private final List<Carta> cartas = new ArrayList<>();

    public Caja() {
        // no inicializar aquí para que el flujo de la app decida cuándo
    }

    public void inicializar() {
        cartas.clear();
        String[] palos = { "♣", "♦", "♥", "♠" };
        String[] nombres = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        for (String palo : palos) {
            for (int i = 0; i < nombres.length; i++) {
                cartas.add(new Carta(nombres[i], palo, i + 1));
            }
        }
    }

    /**
     * Baraja la caja y transfiere las referencias de Carta al mazo (vacía la caja).
     * Evita crear nuevas instancias.
     */
    public void barajar(Mazo mazo) {
        Collections.shuffle(cartas);
        mazo.vaciar(); // evita duplicados si se llama múltiples veces
        for (Carta c : cartas)
            mazo.push(c);
        cartas.clear();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int size() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return new ArrayList<>(cartas);
    } // copia segura para GUI
}
