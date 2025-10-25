public class Numbers {

    private static double N = 25;

    public static double formula(double z) {
        return round((z + Math.sqrt(4 + Math.pow(z, 2))) / 2);
    }

    public static double recursive(double z) {
        return round(recursive(z, N) / recursive(z, N - 1));
    }

    public static double iterative(double z) {
        return round(iterative(z, N) / iterative(z, N - 1));
    }

    // Cálculo recursivo de la sucesión metálica
    private static double recursive(double z, double n) {
        if (n == 0 || n == 1)
            return 1;
        return z * recursive(z, n - 1) + recursive(z, n - 2);
    }

    // Cálculo iterativo de la sucesión metálica
    private static double iterative(double z, double n) {
        if (n == 0 || n == 1)
            return 1;

        double prev2 = 1; // f(z,0)
        double prev1 = 1; // f(z,1)
        double curr = 1;

        for (int i = 2; i <= n; i++) {
            curr = z * prev1 + prev2; // fórmula iterativa: f(z,i) = z*f(z,i-1) + f(z,i-2)
            prev2 = prev1; // actualizar f(z,i-2)
            prev1 = curr; // actualizar f(z,i-1)
        }

        return curr;
    }

    // Método round ahora es estático para poder llamarlo desde métodos públicos
    private static double round(double value) {
        double factor = 1e10;
        return Math.round(value * factor) / factor;
    }

    public static void main(String[] args) {
        String[] metallics = {
                "Platinum", // [0]
                "Golden", // [1]
                "Silver", // [2]
                "Bronze", // [3]
                "Copper", // [4]
                "Nickel", // [5]
                "Aluminum", // [6]
                "Iron", // [7]
                "Tin", // [8]
                "Lead", // [9]
        };
        for (var z = 0; z < metallics.length; z++) {
            System.out.println("\n[" + z + "] " + metallics[z]);
            System.out.println(" ↳ formula(" + z + ")   ≈ " + formula(z));
            System.out.println(" ↳ recursive(" + z + ") ≈ " + recursive(z));
            System.out.println(" ↳ iterative(" + z + ") ≈ " + iterative(z));

        }
    }
}
