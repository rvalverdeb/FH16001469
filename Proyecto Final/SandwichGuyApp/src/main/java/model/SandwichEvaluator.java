package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilidades para evaluar si una tripleta es un "sándwich" y para generar
 * las permutaciones y sus resultados.
 */
public final class SandwichEvaluator {

    private SandwichEvaluator() {
    }

    /**
     * Evalúa una permutación concreta (a,b,c).
     * Retorna:
     * - 0: no es sándwich
     * - 2: sándwich válido (distintos colores)
     * - 3: sándwich válido (mismo color)
     * - 4: sándwich válido (mismo palo)
     */
    public static int evaluatePermutation(Carta a, Carta b, Carta c) {
        int v1 = a.getValor();
        int v2 = b.getValor();
        int v3 = c.getValor();

        int d1 = circularDiff(v1, v2);
        int d2 = circularDiff(v2, v3);

        if (d1 != d2)
            return 0;

        // Es sándwich: decidir cuántas cartas tomar según palo/color
        boolean samePalo = a.getPalo().equals(c.getPalo()) && a.getPalo().equals(b.getPalo());
        if (samePalo)
            return 4;

        boolean sameColor = a.getColor().equals(b.getColor()) && b.getColor().equals(c.getColor());
        if (sameColor)
            return 3;

        return 2;
    }

    /**
     * Distancia circular hacia adelante en una rueda de 13 valores (1..13).
     * Ejemplo: circularDiff(12, 3) = 4 (12->13->1->2->3)
     * Si iguales retorna 0.
     */
    private static int circularDiff(int from, int to) {
        int diff = (to - from) % 13;
        if (diff < 0)
            diff += 13;
        return diff;
    }

    /**
     * Genera las 6 permutaciones de la tripleta (lista de 3 cartas).
     */
    public static List<List<Carta>> permutationsOfThree(List<Carta> trip) {
        if (trip == null || trip.size() != 3)
            throw new IllegalArgumentException("Se requieren 3 cartas");
        List<List<Carta>> res = new ArrayList<>(6);
        Carta x = trip.get(0), y = trip.get(1), z = trip.get(2);
        res.add(Arrays.asList(x, y, z));
        res.add(Arrays.asList(x, z, y));
        res.add(Arrays.asList(y, x, z));
        res.add(Arrays.asList(y, z, x));
        res.add(Arrays.asList(z, x, y));
        res.add(Arrays.asList(z, y, x));
        return res;
    }

    /**
     * Evalúa todas las permutaciones y retorna los resultados (par permutación ->
     * takeCount).
     */
    public static List<PermutationResult> evaluateAllPermutations(List<Carta> trip) {
        List<List<Carta>> perms = permutationsOfThree(trip);
        List<PermutationResult> out = new ArrayList<>(perms.size());
        for (List<Carta> p : perms) {
            int cnt = evaluatePermutation(p.get(0), p.get(1), p.get(2));
            out.add(new PermutationResult(p, cnt));
        }
        return out;
    }
}