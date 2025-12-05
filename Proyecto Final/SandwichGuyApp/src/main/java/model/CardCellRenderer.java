package app;

import model.Carta;

import javax.swing.*;
import java.awt.*;

/**
 * Renderizador para JList<Carta> que pinta la carta en rojo o negro según su
 * color,
 * muestra la carta como texto y aplica estilo cuando está seleccionada.
 */
public class CardCellRenderer extends DefaultListCellRenderer {

    private final Color selectionBg = new Color(0x3366CC);
    private final Color selectionFg = Color.WHITE;
    private final Color oddRowBg = new Color(0xFFFFFF);
    private final Color evenRowBg = new Color(0xF7FBFF);

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Carta) {
            Carta c = (Carta) value;
            lbl.setText(c.toString()); // e.g. [A|♠]
            // Color de texto según palo
            if ("rojo".equalsIgnoreCase(c.getColor())) {
                lbl.setForeground(new Color(0xC01A1A)); // rojo oscuro
            } else {
                lbl.setForeground(new Color(0x1A1A1A)); // casi negro
            }
        } else {
            lbl.setText(value == null ? "" : value.toString());
            lbl.setForeground(Color.DARK_GRAY);
        }

        // Fondo alternado cuando no está seleccionado
        if (isSelected) {
            lbl.setBackground(selectionBg);
            lbl.setForeground(selectionFg);
            lbl.setOpaque(true);
        } else {
            lbl.setBackground((index % 2 == 0) ? evenRowBg : oddRowBg);
            lbl.setOpaque(true);
        }

        lbl.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        return lbl;
    }
}