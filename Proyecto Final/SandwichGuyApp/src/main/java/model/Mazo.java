package model;

import java.util.Stack;

public class Mazo {
    private Stack<Carta> pila = new Stack<>();

    public void push(Carta carta) {
        pila.push(carta);
    }

    public Carta pop() {
        if (pila.isEmpty()) {
            System.out.println("El mazo está vacío.");
            return null;
        }
        return pila.pop();
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public Stack<Carta> getCartas() {
        return pila;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta c : pila) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}