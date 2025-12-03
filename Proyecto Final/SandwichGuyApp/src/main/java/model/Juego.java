package model;

public class Juego {

    private Caja caja;
    private Mazo mazo;
    private Jugador jugador;
    private Pozo pozo;

    public Juego() {
        reiniciar();
    }

    /** Reinicia los componentes del juego */
    public void reiniciar() {
        caja = new Caja();
        caja.inicializar(); // crea 52 cartas
        mazo = new Mazo();
        jugador = new Jugador();
        pozo = new Pozo();
    }

    /** Pasa las cartas de la caja al mazo y las mezcla */
    public void barajar() {
        caja.barajar(mazo);
    }

    /** Reparte 'cantidad' cartas al jugador */
    public void repartirInicial(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            if (!mazo.isEmpty()) {
                Carta c = mazo.pop();
                jugador.agregarCarta(c);
            } else {
                break;
            }
        }
    }

    /** Hace que el jugador juegue su carta actual al pozo */
    public void jugadorJuega() {
        Carta c = jugador.jugarCarta();
        if (c != null) {
            pozo.colocar(c);
        }
    }

    /**
     * Inicia el juego completo:
     * - reinicia todo
     * - baraja las cartas
     * - coloca una carta inicial en el pozo
     * - reparte cartas iniciales al jugador
     */
    public void iniciarJuego(int cartasIniciales) {

        // 1. Reiniciar componentes (NO quitar)
        reiniciar();

        // 2. Barajar mazo desde la caja
        barajar();

        // 3. Colocar carta inicial en el pozo
        if (!mazo.isEmpty()) {
            Carta inicial = mazo.pop();
            pozo.colocar(inicial);
            System.out.println("Carta inicial en el pozo: " + inicial);
        } else {
            System.out.println("No hay cartas suficientes para iniciar el pozo.");
        }

        // 4. Repartir al jugador
        repartirInicial(cartasIniciales);
    }

    // ======== GETTERS ========

    public Caja getCaja() {
        return caja;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Pozo getPozo() {
        return pozo;
    }
}
