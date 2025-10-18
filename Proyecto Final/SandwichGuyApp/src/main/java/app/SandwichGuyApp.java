/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author USER
 */

import model.Carta;
import model.Carta.Suit;
import model.CircularList;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class SandwichGuyApp {
// Estructuras según especificación
private final LinkedList<Carta> caja = new LinkedList<>(); // lista doble
private final ArrayDeque<Carta> mazo = new ArrayDeque<>(); // pila
private final CircularList<Carta> mano = new CircularList<>(); // lista circular
private final LinkedList<Carta> pozo = new LinkedList<>(); // cola


// UI
private JFrame frame;
private JPanel cajaPanel, mazoPanel, manoPanel, pozoPanel;
private JLabel cajaInfo, mazoInfo, manoInfo, pozoInfo;


public SandwichGuyApp(){
crearCartasEnCaja();
buildUI();
refreshUI();
}


private void crearCartasEnCaja(){
for(Suit s : Suit.values()){
for(int v=1; v<=13; v++){
caja.add(new Carta(s, v));
}
}
}


private void buildUI(){
frame = new JFrame("The Sandwich Guy - Avance I");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(900, 500);
frame.setLayout(new BorderLayout());


JPanel center = new JPanel(new GridLayout(2,2,8,8));


cajaPanel = createAreaPanel("Caja");
mazoPanel = createAreaPanel("Mazo");
manoPanel = createAreaPanel("Mano");
pozoPanel = createAreaPanel("Pozo");


center.add(cajaPanel);
center.add(mazoPanel);
center.add(manoPanel);
center.add(pozoPanel);


frame.add(center, BorderLayout.CENTER);


JPanel controls = new JPanel();
JButton shuffleBtn = new JButton("Barajar / Crear Mazo");
shuffleBtn.addActionListener(this::onShuffle);
JButton dealBtn = new JButton("Repartir 8 a Mano");
dealBtn.addActionListener(e -> repartir8());
JButton ordenarBtn = new JButton("Ordenar Mano");
ordenarBtn.addActionListener(e -> ordenarMano());


controls.add(shuffleBtn);
controls.add(dealBtn);
controls.add(ordenarBtn);


frame.add(controls, BorderLayout.SOUTH);
frame.setVisible(true);
}

private JPanel createAreaPanel(String title){
JPanel p = new JPanel(new BorderLayout());
p.setBorder(new TitledBorder(title));
JTextArea text = new JTextArea();
text.setEditable(false);
p.add(new JScrollPane(text), BorderLayout.CENTER);


switch(title){
case "Caja" -> cajaInfo = new JLabel();
case "Mazo" -> mazoInfo = new JLabel();
case "Mano" -> manoInfo = new JLabel();
case "Pozo" -> pozoInfo = new JLabel();
}
JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
south.add((title.equals("Caja")? cajaInfo : title.equals("Mazo")? mazoInfo : title.equals("Mano")? manoInfo : pozoInfo));
p.add(south, BorderLayout.SOUTH);
// guardamos el JTextArea como client property para actualizar su contenido
p.putClientProperty("textArea", text);
return p;
}


private void refreshUI(){
updatePanel(cajaPanel, listaToString(caja));
updatePanel(mazoPanel, listaToString(mazo));
updatePanel(manoPanel, circularToString(mano));
updatePanel(pozoPanel, listaToString(pozo));


cajaInfo.setText("Cartas en Caja: " + caja.size());
mazoInfo.setText("Cartas en Mazo: " + mazo.size());
manoInfo.setText("Cartas en Mano: " + mano.size());
pozoInfo.setText("Cartas en Pozo: " + pozo.size());
}


private void updatePanel(JPanel panel, String content){
JTextArea ta = (JTextArea) panel.getClientProperty("textArea");
ta.setText(content);
}


private String listaToString(Iterable<Carta> it){
StringBuilder sb = new StringBuilder();
for(Carta c: it) sb.append(c.toString()).append(" ");
return sb.toString();
}


private String listaToString(ArrayDeque<Carta> deque){
StringBuilder sb = new StringBuilder();
for(Carta c: deque) sb.append(c.toString()).append(" ");
return sb.toString();
}


private String circularToString(CircularList<Carta> cl){
StringBuilder sb = new StringBuilder();
int i=0;
for(Carta c: cl){ sb.append(String.format("%d:%s ", ++i, c.toString())); }
return sb.toString();
}


private void onShuffle(ActionEvent e){
// Mover todo de Caja a una lista temporal, barajar y meter al Mazo
List<Carta> temp = new ArrayList<>(caja);
Collections.shuffle(temp);
mazo.clear();
for(Carta c: temp) mazo.push(c);
// vaciamos la caja (simula que se sacaron las cartas de la Caja)
caja.clear();
// limpiamos mano y pozo
// (mantener cartas instanciadas; solo movemos referencias)
// para Avance I, reseteamos
while(!mano.isEmpty()) mano.removeAt(0);
pozo.clear();
refreshUI();
}


private void repartir8(){
for(int i=0;i<8;i++){
if(mazo.isEmpty()) break;
Carta c = mazo.pop();
mano.addLast(c);
}
refreshUI();
}


private void ordenarMano(){
// Para ordenar convertimos a lista temporal, ordenamos por valor
List<Carta> temp = new ArrayList<>();
while(!mano.isEmpty()) temp.add(mano.removeAt(0));
temp.sort((a,b)-> Integer.compare(a.valorNumerico(), b.valorNumerico()));
for(Carta c: temp) mano.addLast(c);
refreshUI();
}


public static void main(String[] args){
SwingUtilities.invokeLater(SandwichGuyApp::new);
}
}
