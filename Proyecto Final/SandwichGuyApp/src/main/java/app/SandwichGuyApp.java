/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import model.*;
import java.awt.*;
import javax.swing.*;

/**
 * Interfaz Swing para el juego (avances).
 */
public class SandwichGuyApp extends JFrame {

    private Juego juego;

    private JTextArea areaCaja;
    private JTextArea areaMazo;
    private JTextArea areaMano;
    private JTextArea areaPozo;

    public SandwichGuyApp() {
        setTitle("The Sandwich Guy - Proyecto Final");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        juego = new Juego();

        inicializarGUI();
        actualizarGUI();
    }

    private void inicializarGUI() {

        JPanel panelBotones = new JPanel(new GridLayout(1, 5));

        JButton btnBarajar = new JButton("Barajar Caja → Mazo");
        JButton btnRepartir = new JButton("Repartir 8 a Mano");
        JButton btnJugarCarta = new JButton("Enviar 1 carta al Pozo");
        JButton btnOrdenarMano = new JButton("Ordenar Mano");
        JButton btnReiniciar = new JButton("Reiniciar");

        panelBotones.add(btnBarajar);
        panelBotones.add(btnRepartir);
        panelBotones.add(btnJugarCarta);
        panelBotones.add(btnOrdenarMano);
        panelBotones.add(btnReiniciar);

        add(panelBotones, BorderLayout.NORTH);

        JPanel panelAreas = new JPanel(new GridLayout(2, 2));

        areaCaja = crearArea("Caja");
        areaMazo = crearArea("Mazo (oculto)");
        areaMano = crearArea("Mano del Jugador");
        areaPozo = crearArea("Pozo");

        panelAreas.add(new JScrollPane(areaCaja));
        panelAreas.add(new JScrollPane(areaMazo));
        panelAreas.add(new JScrollPane(areaMano));
        panelAreas.add(new JScrollPane(areaPozo));

        add(panelAreas, BorderLayout.CENTER);

        // Listeners
        btnBarajar.addActionListener(e -> {
            juego.barajar();
            actualizarGUI();
        });

        btnRepartir.addActionListener(e -> {
            juego.repartirInicial(8);
            actualizarGUI();
        });

        btnJugarCarta.addActionListener(e -> {
            juego.jugadorJuega();
            actualizarGUI();
        });

        btnOrdenarMano.addActionListener(e -> {
            juego.getJugador().ordenarMano();
            actualizarGUI();
        });

        btnReiniciar.addActionListener(e -> {
            juego.reiniciar();
            actualizarGUI();
        });
    }

    private JTextArea crearArea(String titulo) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBorder(BorderFactory.createTitledBorder(titulo));
        return area;
    }

    private void actualizarGUI() {
        Caja c = juego.getCaja();
        Mazo m = juego.getMazo();
        Jugador j = juego.getJugador();
        Pozo p = juego.getPozo();

        areaCaja.setText(c.getCartas().toString());
        areaMazo.setText("Cartas en mazo: " + m.size());
        areaMano.setText(j.mostrarMano());
        areaPozo.setText(p.mostrar());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SandwichGuyApp().setVisible(true));
    }
}
