package model;

import java.util.LinkedList;
import java.util.Queue;

public class Pozo {
    private Queue<Carta> cartas = new LinkedList<>();

    // Agrega una carta al final del pozo (enqueue)
    public void enqueue(Carta c) {
        cartas.add(c);
    }

    // Saca la carta del frente del pozo (dequeue)
    public Carta dequeue() {
        if (cartas.isEmpty()) {
            System.out.println("El pozo está vacío.");
            return null;
        }
        return cartas.poll();
    }

    // Verifica si el pozo está vacío
    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    // Muestra el contenido del pozo en consola (para depuración)
    public void show() {
        if (cartas.isEmpty()) {
            System.out.println("No hay cartas en el pozo.");
        } else {
            cartas.forEach(System.out::println);
        }
    }

    // Devuelve el contenido del pozo como texto (para JTextArea o consola)
    @Override
    public String toString() {
        if (cartas.isEmpty()) {
            return "Pozo vacío";
        }

        StringBuilder sb = new StringBuilder();
        for (Carta c : cartas) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
