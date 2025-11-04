package model;

import java.util.Stack;

public class Mano {
    private Stack<Carta> cartas = new Stack<>();

    // Agrega una carta al mazo (parte superior de la pila)
    public void push(Carta c) {
        cartas.push(c);
    }

    // Saca la carta superior del mazo
    public Carta pop() {
        if (cartas.isEmpty()) {
            System.out.println("El mazo está vacío.");
            return null;
        }
        return cartas.pop();
    }

    // Verifica si el mazo está vacío
    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    // Muestra todas las cartas en consola (solo para depuración)
    public void show() {
        if (cartas.isEmpty()) {
            System.out.println("No hay cartas en el mazo.");
        } else {
            cartas.forEach(System.out::println);
        }
    }

    // Devuelve las cartas como texto (para mostrarlas en JTextArea)
    @Override
    public String toString() {
        if (cartas.isEmpty()) {
            return "Mazo vacío";
        }

        StringBuilder sb = new StringBuilder();
        for (Carta c : cartas) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}