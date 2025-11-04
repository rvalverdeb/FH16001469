package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Caja {
    private List<Carta> cartas = new ArrayList<>();

    // Inicializa la caja con las 52 cartas
    public void inicializar() {
        Carta.Suit[] palos = {
                Carta.Suit.HEARTS,
                Carta.Suit.DIAMONDS,
                Carta.Suit.SPADES,
                Carta.Suit.CLUBS
        };

        // 1..13 (1 = A, 11 = J, 12 = Q, 13 = K)
        for (Carta.Suit palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                cartas.add(new Carta(palo, valor));
            }
        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    // Baraja aleatoriamente las cartas de la caja
    // y las transfiere al mazo, dejando la caja vacía.
    public void barajar(Mazo mazo) {
        if (cartas.isEmpty()) {
            System.out.println("La caja está vacía, no hay cartas para barajar.");
            return;
        }

        List<Carta> copia = new ArrayList<>(cartas);
        Collections.shuffle(copia);

        for (Carta c : copia) {
            mazo.push(c);
        }

        cartas.clear(); // Vacía la caja
    }
}
