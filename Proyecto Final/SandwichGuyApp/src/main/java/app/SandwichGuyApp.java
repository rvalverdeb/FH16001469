package app;

import model.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * SandwichGuyApp con tema de colores y renderizado coloreado de cartas.
 *
 * Reemplaza tu SandwichGuyApp.java por esta versión. Mantiene toda la lógica
 * previa (guardar/cargar, simular, validar, etc.) y simplemente aplica un tema
 * visual y usa CardCellRenderer para colorear las cartas según su color.
 */
public class SandwichGuyApp extends JFrame {

    private Juego juego;

    private JTextArea areaCaja;
    private JTextArea areaMazo;
    private DefaultListModel<Carta> manoListModel;
    private JList<Carta> listaMano;
    private JTextArea areaPozo;
    private JLabel lblEstado;

    private boolean dirty = false;
    private boolean simulating = false;

    private JButton btnBarajar;
    private JButton btnRepartir;
    private JButton btnJugarCarta;
    private JButton btnOrdenarMano;
    private JButton btnReiniciar;
    private JButton btnEvaluarSeleccion;
    private JButton btnValidarMano;
    private JButton btnGuardar;
    private JButton btnCargar;
    private JButton btnSimular;

    // Tema: colores principales
    private static final Color BG_WINDOW = new Color(0xF1F8FF);
    private static final Color PANEL_BG = new Color(0xE9F5FF);
    private static final Color AREA_BG = Color.WHITE;
    private static final Color ACCENT = new Color(0x2B6CB0);
    private static final Color BTN_BG = new Color(0x4C9BD6);
    private static final Color BTN_FG = Color.WHITE;

    public SandwichGuyApp() {
        super("The Sandwich Guy - Proyecto Final");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        juego = new Juego();

        createMenuBar();
        inicializarGUI();
        aplicarTema();
        actualizarGUI();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onExitRequested();
            }
        });
    }

    private void createMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("Archivo");
        JMenuItem miGuardar = new JMenuItem("Guardar...");
        JMenuItem miCargar = new JMenuItem("Cargar...");
        JMenuItem miSalir = new JMenuItem("Salir");

        miGuardar.addActionListener(e -> guardarPartidaDialog());
        miCargar.addActionListener(e -> cargarPartidaDialog());
        miSalir.addActionListener(e -> onExitRequested());

        mFile.add(miGuardar);
        mFile.add(miCargar);
        mFile.addSeparator();
        mFile.add(miSalir);
        mb.add(mFile);
        setJMenuBar(mb);
    }

    private void inicializarGUI() {

        JPanel panelBotones = new JPanel(new GridLayout(3, 4, 6, 6));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        panelBotones.setBackground(PANEL_BG);

        btnBarajar = mkButton("Barajar Caja → Mazo");
        btnRepartir = mkButton("Repartir 8 a Mano");
        btnJugarCarta = mkButton("Enviar 1 carta al Pozo");
        btnOrdenarMano = mkButton("Ordenar Mano");
        btnReiniciar = mkButton("Reiniciar");
        btnEvaluarSeleccion = mkButton("Evaluar 3 seleccionadas");
        btnValidarMano = mkButton("Validar Mano (buscar sándwiches)");
        btnGuardar = mkButton("Guardar partida");
        btnCargar = mkButton("Cargar partida");
        btnSimular = mkButton("Simular partida");

        panelBotones.add(btnBarajar);
        panelBotones.add(btnRepartir);
        panelBotones.add(btnJugarCarta);
        panelBotones.add(btnOrdenarMano);

        panelBotones.add(btnEvaluarSeleccion);
        panelBotones.add(btnValidarMano);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCargar);

        panelBotones.add(btnSimular);
        panelBotones.add(btnReiniciar);
        panelBotones.add(new JLabel()); // filler
        panelBotones.add(new JLabel()); // filler

        add(panelBotones, BorderLayout.NORTH);

        JPanel panelAreas = new JPanel(new GridLayout(2, 2, 6, 6));
        panelAreas.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        panelAreas.setBackground(PANEL_BG);

        areaCaja = crearArea("Caja");
        areaMazo = crearArea("Mazo (oculto)");

        manoListModel = new DefaultListModel<>();
        listaMano = new JList<>(manoListModel);
        listaMano.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listaMano.setVisibleRowCount(8);
        listaMano.setCellRenderer(new CardCellRenderer());
        listaMano.setBorder(BorderFactory.createTitledBorder("Mano del Jugador (seleccione hasta 3)"));

        areaPozo = crearArea("Pozo");

        panelAreas.add(new JScrollPane(areaCaja));
        panelAreas.add(new JScrollPane(areaMazo));
        panelAreas.add(new JScrollPane(listaMano));
        panelAreas.add(new JScrollPane(areaPozo));
        add(panelAreas, BorderLayout.CENTER);

        lblEstado = new JLabel("Listo");
        lblEstado.setOpaque(true);
        lblEstado.setBackground(PANEL_BG);
        lblEstado.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        add(lblEstado, BorderLayout.SOUTH);

        // --- Listeners (mantengo la lógica previa) ---
        btnBarajar.addActionListener(e -> {
            try {
                juego.barajar();
                dirty = true;
                lblEstado.setText("Caja barajada y volcadas al mazo.");
            } catch (Exception ex) {
                lblEstado.setText("Error al barajar: " + ex.getMessage());
            }
            actualizarGUI();
            checkGameEndAndNotify();
        });

        btnRepartir.addActionListener(e -> {
            int repartidas = juego.repartirInicial(8);
            if (repartidas > 0)
                dirty = true;
            lblEstado.setText("Repartidas " + repartidas + " cartas al jugador.");
            actualizarGUI();
            checkGameEndAndNotify();
        });

        btnJugarCarta.addActionListener(e -> {
            boolean jugado = juego.jugadorJuega();
            if (jugado)
                dirty = true;
            lblEstado.setText(jugado ? "Carta enviada al pozo." : "No hay carta para jugar.");
            actualizarGUI();
            checkGameEndAndNotify();
        });

        btnOrdenarMano.addActionListener(e -> {
            juego.getJugador().ordenarMano();
            dirty = true;
            lblEstado.setText("Mano ordenada.");
            actualizarGUI();
        });

        btnReiniciar.addActionListener(e -> {
            int opt = JOptionPane.showConfirmDialog(this,
                    "Reiniciar la partida actual? (se perderán cambios no guardados)", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                juego.reiniciar();
                dirty = false;
                lblEstado.setText("Juego reiniciado.");
                actualizarGUI();
            }
        });

        btnEvaluarSeleccion.addActionListener(e -> {
            int[] sel = listaMano.getSelectedIndices();
            if (sel.length != 3) {
                JOptionPane.showMessageDialog(this, "Seleccione exactamente 3 cartas en la mano para evaluar.",
                        "Selección inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            java.util.Arrays.sort(sel);
            List<Carta> manoList = juego.getJugador().manoToList();
            List<Carta> trip = List.of(manoList.get(sel[0]), manoList.get(sel[1]), manoList.get(sel[2]));
            List<PermutationResult> perms = SandwichEvaluator.evaluateAllPermutations(trip);

            StringBuilder sb = new StringBuilder();
            int best = 0;
            int bestIdx = -1;
            for (int i = 0; i < perms.size(); i++) {
                PermutationResult pr = perms.get(i);
                int take = pr.getTakeCount();
                sb.append((i + 1)).append(": ").append(pr.getPermutation().toString())
                        .append(" => ").append(take).append(" cartas a tomar\n");
                if (take > best) {
                    best = take;
                    bestIdx = i;
                }
            }
            if (best == 0) {
                sb.append("\nNinguna permutación forma un sándwich válido.");
            } else {
                sb.append("\nMejor permutación: #").append(bestIdx + 1).append(" => ").append(best).append(" cartas.");
            }

            int option = JOptionPane.showOptionDialog(this, new JScrollPane(new JTextArea(sb.toString(), 12, 40)),
                    "Permutaciones de la tripleta seleccionada",
                    best == 0 ? JOptionPane.DEFAULT_OPTION : JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    best == 0 ? new Object[] { "Cerrar" } : new Object[] { "Enviar tripleta", "Cancelar" },
                    null);

            if (best > 0 && option == JOptionPane.YES_OPTION) {
                boolean ok = juego.enviarTripletaAlPozo(sel[0], sel[1], sel[2]);
                if (ok) {
                    dirty = true;
                    lblEstado.setText("Tripleta enviada al pozo. Robadas hasta " + best + " cartas (según reglas).");
                } else {
                    lblEstado.setText("No fue posible enviar la tripleta (validación inesperada).");
                }
                actualizarGUI();
                checkGameEndAndNotify();
            }
        });

        btnValidarMano.addActionListener(e -> {
            Juego.ValidationResult res = juego.validarMano();

            if (res == Juego.ValidationResult.HAS_SANDWICH) {
                List<TripletResult> validas = juego.encontrarTripletasValidas();
                String[] opciones = new String[validas.size()];
                for (int i = 0; i < validas.size(); i++) {
                    TripletResult tr = validas.get(i);
                    opciones[i] = String.format("%d) índices=(%d,%d,%d) bestTake=%d trip=%s",
                            i + 1, tr.getI(), tr.getJ(), tr.getK(), tr.getBestTakeCount(), tr.getOriginalTriplet());
                }
                String seleccion = (String) JOptionPane.showInputDialog(this,
                        "Tripletas válidas (seleccione una para enviar):",
                        "Tripletas válidas", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
                if (seleccion != null) {
                    int idx = -1;
                    for (int i = 0; i < opciones.length; i++)
                        if (opciones[i].equals(seleccion)) {
                            idx = i;
                            break;
                        }
                    if (idx >= 0) {
                        TripletResult elegido = validas.get(idx);
                        boolean ok = juego.enviarTripletaAlPozo(elegido.getI(), elegido.getJ(), elegido.getK());
                        if (ok) {
                            dirty = true;
                            lblEstado.setText("Tripleta enviada (válida). Robadas hasta " + elegido.getBestTakeCount()
                                    + " cartas.");
                        } else
                            lblEstado.setText("No fue posible enviar la tripleta seleccionada.");
                        actualizarGUI();
                        checkGameEndAndNotify();
                    }
                }
                return;
            }

            if (res == Juego.ValidationResult.NO_SANDWICH_MAZO_NOT_EMPTY) {
                int espacio = Math.max(0, 8 - juego.getJugador().manoSize());
                if (espacio == 0) {
                    JOptionPane.showMessageDialog(this,
                            "No hay sándwiches válidos y la mano está llena (8 cartas). No es posible robar. La partida se pierde.",
                            "Partida perdida", JOptionPane.INFORMATION_MESSAGE);
                    Juego.ValidationResult finalRes = juego.validarMano();
                    if (finalRes == Juego.ValidationResult.NO_SANDWICH_MAZO_EMPTY)
                        checkGameEndAndNotify();
                    return;
                }

                Object[] options = new Object[] { "Robar 1 carta", "Robar hasta llenar mano", "Cancelar" };
                int choice = JOptionPane.showOptionDialog(this,
                        "No hay sándwiches válidos en la mano. ¿Desea tomar cartas del mazo?", "Robar cartas",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (choice == JOptionPane.CLOSED_OPTION || choice == 2) {
                    lblEstado.setText("Validación cancelada. No se realizaron cambios.");
                    return;
                }
                int toTake = (choice == 0) ? 1 : espacio;
                int realmenteTomadas = juego.tomarCartasAlJugador(toTake);
                if (realmenteTomadas > 0) {
                    dirty = true;
                    lblEstado.setText("Se robaron " + realmenteTomadas + " carta(s) del mazo.");
                } else
                    lblEstado.setText("No fue posible robar cartas del mazo.");
                actualizarGUI();
                Juego.ValidationResult res2 = juego.validarMano();
                if (res2 == Juego.ValidationResult.HAS_SANDWICH) {
                    JOptionPane.showMessageDialog(this, "Después de robar, ahora hay al menos un sándwich en la mano.",
                            "Validación", JOptionPane.INFORMATION_MESSAGE);
                } else if (res2 == Juego.ValidationResult.NO_SANDWICH_MAZO_NOT_EMPTY) {
                    JOptionPane.showMessageDialog(this,
                            "Después de robar, aún no hay sándwiches. Puede intentar validar nuevamente.", "Validación",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    checkGameEndAndNotify();
                }
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "No hay sándwiches válidos en la mano y el mazo está vacío. La partida se pierde.",
                    "Partida perdida", JOptionPane.INFORMATION_MESSAGE);
            checkGameEndAndNotify();
        });

        btnGuardar.addActionListener(e -> guardarPartidaDialog());
        btnCargar.addActionListener(e -> cargarPartidaDialog());

        btnSimular.addActionListener(e -> {
            if (simulating)
                return;
            simulating = true;
            disableControlsForSimulation(true);
            new Thread(() -> {
                try {
                    juego.reiniciar();
                    juego.barajar();
                    juego.repartirInicial(8);
                    dirty = true;
                    SwingUtilities.invokeLater(() -> {
                        lblEstado.setText("Simulación iniciada.");
                        actualizarGUI();
                    });

                    java.util.Random rnd = new java.util.Random();
                    while (!juego.isTerminado()) {
                        List<TripletResult> validas = juego.encontrarTripletasValidas();
                        if (validas.isEmpty()) {
                            Juego.ValidationResult vr = juego.validarMano();
                            if (vr == Juego.ValidationResult.NO_SANDWICH_MAZO_EMPTY) {
                                SwingUtilities.invokeLater(() -> {
                                    lblEstado.setText("Simulación: no hay sándwiches y mazo vacío (pierde).");
                                    actualizarGUI();
                                });
                                break;
                            }
                            int tomadas = juego.tomarCartasAlJugador(1);
                            if (tomadas == 0)
                                break;
                            SwingUtilities.invokeLater(() -> {
                                lblEstado.setText("Simulación: robada 1 carta");
                                actualizarGUI();
                            });
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException ex) {
                                /* ignore */ }
                            continue;
                        }
                        TripletResult elegido = validas.get(rnd.nextInt(validas.size()));
                        boolean ok = juego.enviarTripletaAlPozo(elegido.getI(), elegido.getJ(), elegido.getK());
                        if (ok) {
                            SwingUtilities.invokeLater(() -> {
                                lblEstado.setText("Simulación: enviada tripleta " + elegido.getOriginalTriplet());
                                actualizarGUI();
                            });
                        } else {
                            SwingUtilities.invokeLater(() -> {
                                lblEstado.setText("Simulación: error enviando tripleta.");
                                actualizarGUI();
                            });
                            break;
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            /* ignore */ }
                    }
                    SwingUtilities.invokeLater(() -> checkGameEndAndNotify());
                } finally {
                    simulating = false;
                    disableControlsForSimulation(false);
                }
            }).start();
        });

        // limitar selección a 3
        listaMano.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int[] sel = listaMano.getSelectedIndices();
                if (sel.length > 3) {
                    int[] keep = new int[] { sel[0], sel[1], sel[2] };
                    listaMano.setSelectedIndices(keep);
                    JOptionPane.showMessageDialog(this, "Sólo puede seleccionar hasta 3 cartas.", "Selección",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // crea botones con estilo consistente
    private JButton mkButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(BTN_BG);
        b.setForeground(BTN_FG);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBorder(BorderFactory.createLineBorder(ACCENT.darker()));
        return b;
    }

    private JTextArea crearArea(String titulo) {
        JTextArea area = new JTextArea(10, 30);
        area.setEditable(false);
        area.setBackground(AREA_BG);
        area.setBorder(BorderFactory.createTitledBorder(titulo));
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        area.setForeground(new Color(0x1A1A1A));
        return area;
    }

    private void aplicarTema() {
        getContentPane().setBackground(BG_WINDOW);
        // algunos ajustes visuales: título de bordes en color
        UIManager.put("TitledBorder.titleColor", ACCENT.darker());
    }

    private void guardarPartidaDialog() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar partida como...");
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos XML", "xml"));
        int rv = chooser.showSaveDialog(this);
        if (rv == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (!f.getName().toLowerCase().endsWith(".xml"))
                f = new File(f.getAbsolutePath() + ".xml");
            if (f.exists()) {
                int ok = JOptionPane.showConfirmDialog(this, "El archivo ya existe. Sobrescribir?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                if (ok != JOptionPane.YES_OPTION)
                    return;
            }
            try {
                juego.save(f);
                dirty = false;
                JOptionPane.showMessageDialog(this, "Partida guardada en:\n" + f.getAbsolutePath(), "Guardar OK",
                        JOptionPane.INFORMATION_MESSAGE);
                lblEstado.setText("Partida guardada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error guardando partida:\n" + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                lblEstado.setText("Error al guardar partida.");
            }
        }
    }

    private void cargarPartidaDialog() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Abrir partida...");
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos XML", "xml"));
        int rv = chooser.showOpenDialog(this);
        if (rv == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (!f.exists()) {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado: " + f.getAbsolutePath(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                juego.load(f);
                dirty = false;
                actualizarGUI();
                JOptionPane.showMessageDialog(this, "Partida cargada desde:\n" + f.getAbsolutePath(), "Cargar OK",
                        JOptionPane.INFORMATION_MESSAGE);
                lblEstado.setText("Partida cargada.");
                checkGameEndAndNotify();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error cargando partida:\n" + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                lblEstado.setText("Error al cargar partida.");
            }
        }
    }

    private void onExitRequested() {
        if (dirty) {
            int opt = JOptionPane.showConfirmDialog(this, "Hay cambios sin guardar. ¿Desea guardar antes de salir?",
                    "Confirmar salida", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opt == JOptionPane.CANCEL_OPTION || opt == JOptionPane.CLOSED_OPTION)
                return;
            if (opt == JOptionPane.YES_OPTION) {
                guardarPartidaDialog();
                if (dirty)
                    return;
            }
        }
        dispose();
        System.exit(0);
    }

    private void checkGameEndAndNotify() {
        if (juego.isTerminado()) {
            // deshabilitamos controles, PERO dejamos Reiniciar (y Cargar) habilitados
            disableAllControls(true);
            if (btnReiniciar != null)
                btnReiniciar.setEnabled(true);
            if (btnCargar != null)
                btnCargar.setEnabled(true);

            String msg;
            if (juego.isGanado()) {
                msg = "¡Felicidades! Ganaste la partida (mazo vacío).";
                lblEstado.setText("Partida finalizada: GANADA");
            } else {
                msg = "Lo siento — perdiste la partida (no hay sándwiches válidos).";
                lblEstado.setText("Partida finalizada: PERDIDA");
            }
            JOptionPane.showMessageDialog(this, msg, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void disableAllControls(boolean disable) {
        btnBarajar.setEnabled(!disable);
        btnRepartir.setEnabled(!disable);
        btnJugarCarta.setEnabled(!disable);
        btnOrdenarMano.setEnabled(!disable);
        btnEvaluarSeleccion.setEnabled(!disable);
        btnValidarMano.setEnabled(!disable);
        btnGuardar.setEnabled(!disable);
        btnCargar.setEnabled(!disable);
        btnReiniciar.setEnabled(!disable);
        btnSimular.setEnabled(!disable);
    }

    private void disableControlsForSimulation(boolean disable) {
        // deshabilitar los controles principales durante la simulación
        disableAllControls(disable);
    }

    private void actualizarGUI() {
        Caja c = juego.getCaja();
        Mazo m = juego.getMazo();
        Jugador j = juego.getJugador();
        Pozo p = juego.getPozo();

        areaCaja.setText(String.join("\n", c.getCartas().stream().map(Object::toString).toList()));
        areaMazo.setText("Cartas en mazo: " + m.size());

        manoListModel.clear();
        List<Carta> mano = j.manoToList();
        for (Carta carta : mano)
            manoListModel.addElement(carta);

        areaPozo.setText(p.mostrar());

        boolean terminado = juego.isTerminado();
        btnBarajar.setEnabled(!terminado && !c.estaVacio());
        btnRepartir.setEnabled(!terminado && m.size() > 0 && j.manoSize() < 8);
        btnJugarCarta.setEnabled(!terminado && j.manoSize() > 0);
        btnOrdenarMano.setEnabled(!terminado && j.manoSize() > 1);
        btnEvaluarSeleccion.setEnabled(!terminado && j.manoSize() >= 3);
        btnValidarMano.setEnabled(!terminado && j.manoSize() >= 1);
        btnGuardar.setEnabled(!terminado);
        btnCargar.setEnabled(true);
        btnSimular.setEnabled(!terminado && !simulating);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SandwichGuyApp());
    }
}