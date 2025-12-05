package model;

import java.util.List;

/**
 * Resultado para una tripleta encontrada en la mano:
 * - índices en la mano (i,j,k) con 0 = carta "actual"
 * - mejor permutación detectada (la que da más cartas a tomar)
 * - bestTakeCount: 0/2/3/4
 */
public class TripletResult {
    private final int i, j, k;
    private final List<Carta> originalTriplet;
    private final List<Carta> bestPermutation;
    private final int bestTakeCount;

    public TripletResult(int i, int j, int k, List<Carta> originalTriplet, List<Carta> bestPermutation,
            int bestTakeCount) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.originalTriplet = originalTriplet;
        this.bestPermutation = bestPermutation;
        this.bestTakeCount = bestTakeCount;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getK() {
        return k;
    }

    public List<Carta> getOriginalTriplet() {
        return originalTriplet;
    }

    public List<Carta> getBestPermutation() {
        return bestPermutation;
    }

    public int getBestTakeCount() {
        return bestTakeCount;
    }

    @Override
    public String toString() {
        return String.format("indices=(%d,%d,%d) best=%s take=%d", i, j, k, bestPermutation, bestTakeCount);
    }
}