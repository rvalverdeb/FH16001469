package com.mycompany.inverso;

public class Inverso {

    public static void main(String[] args) {
        // Validación mínima de argumento
        if (args.length != 1) {
            System.out.println("Uso: java Inverso <numero>");
            return;
        }

        try {
            int numero = Integer.parseInt(args[0]);
            int inverso = invertir(numero, 0);

            // Presentación de resultados
            System.out.println(numero + " → " + inverso);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número natural.");
        }
    }

    /**
     * Método recursivo que invierte un número natural usando cociente y residuo.
     * 
     * @param n          Número original
     * @param acumulador Construye el número invertido en cada paso
     * @return Inverso de n
     */
    public static int invertir(int n, int acumulador) {
        if (n == 0) {
            return acumulador;
        }
        int residuo = n % 10; // último dígito
        int cociente = n / 10; // número sin el último dígito
        return invertir(cociente, acumulador * 10 + residuo);
    }
}
