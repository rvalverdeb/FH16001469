package model;

import java.util.List;

/**
 * Resultado de evaluar una permutación: la permutación (lista de 3 cartas)
 * y la cantidad de cartas a tomar si es sándwich (0,2,3,4).
 */
public class PermutationResult {
    private final List<Carta> permutation;
    private final int takeCount;

    public PermutationResult(List<Carta> permutation, int takeCount) {
        this.permutation = permutation;
        this.takeCount = takeCount;
    }

    public List<Carta> getPermutation() {
        return permutation;
    }

    public int getTakeCount() {
        return takeCount;
    }

    @Override
    public String toString() {
        return permutation.toString() + " => " + takeCount;
    }
}