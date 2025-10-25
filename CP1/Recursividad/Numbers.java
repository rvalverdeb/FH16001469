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

/**
 * Numbers.java
 *
 * Proyecto: Recursividad – Números Metálicos
 *
 * Descripción:
 * Este programa imprime los denominados Números Metálicos utilizando tres
 * estrategias:
 * 1. Fórmula directa
 * 2. Algoritmo recursivo
 * 3. Algoritmo iterativo
 *
 * Se calcula la razón f(z,25) / f(z,24) para cada índice z del arreglo de
 * metales.
 *
 * Cambios y mejoras realizados:
 * 1. Método recursivo privado 'recursive(double z, double n)' corregido según
 * definición:
 * f(z,0) = 1
 * f(z,1) = 1
 * f(z,n>1) = z * f(z,n-1) + f(z,n-2)
 * -> Ahora devuelve correctamente los valores de la sucesión metálica.
 *
 * 2. Método iterativo privado 'iterative(double z, double n)' implementado
 * usando bucle for:
 * f(z,0) = 1, f(z,1) = 1
 * f(z,n>1) = z*f(z,n-1) + f(z,n-2)
 * -> Evita recursión profunda y calcula el mismo resultado que la versión
 * recursiva.
 *
 * 3. Método 'round(double value)' hecho estático para poder llamarlo desde
 * métodos públicos:
 * Redondea los resultados a 10 dígitos decimales según el enunciado.
 *
 * 4. Métodos públicos y main permanecen intactos, cumpliendo con la indicación
 * de no modificarlos.
 *
 * Referencias:
 * - Definición de Números Metálicos: f(z,n) = z*f(z,n-1) + f(z,n-2)
 * - Redondeo: 10 dígitos decimales para consistencia con el enunciado.
 *
 * Ejecución esperada:
 * java Numbers
 * Muestra cada metal con los resultados aproximados de formula, recursive e
 * iterative.
 */

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

    private static double recursive(double z, double n) {
        if (n == 0 || n == 1)
            return 1;
        return z * recursive(z, n - 1) + recursive(z, n - 2);
    }

    private static double iterative(double z, double n) {
        if (n == 0 || n == 1)
            return 1;

        double prev2 = 1;
        double prev1 = 1;
        double curr = 1;

        for (int i = 2; i <= n; i++) {
            curr = z * prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }

    private static double round(double value) {
        double factor = 1e10;
        return Math.round(value * factor) / factor;
    }

    public static void main(String[] args) {
        String[] metallics = {
                "Platinum", "Golden", "Silver", "Bronze", "Copper",
                "Nickel", "Aluminum", "Iron", "Tin", "Lead"
        };
        for (var z = 0; z < metallics.length; z++) {
            System.out.println("\n[" + z + "] " + metallics[z]);
            System.out.println(" ↳ formula(" + z + ")   ≈ " + formula(z));
            System.out.println(" ↳ recursive(" + z + ") ≈ " + recursive(z));
            System.out.println(" ↳ iterative(" + z + ") ≈ " + iterative(z));
        }
    }
}

/**
 * Numbers.java
 *
 * Recursividad – Números Metálicos
 *
 * Descripción:
 * Este programa calcula los denominados Números Metálicos utilizando tres
 * estrategias:
 * 1. Fórmula directa (closed-form)
 * 2. Algoritmo recursivo
 * 3. Algoritmo iterativo
 *
 * Cada número metálico se calcula como la razón f(z,25) / f(z,24), donde f(z,n)
 * se define como:
 * f(z,0) = 1
 * f(z,1) = 1
 * f(z,n>1) = z * f(z,n-1) + f(z,n-2)
 *
 * Referencias académicas:
 * - ChatGPT, “Implementación de sucesiones recursivas y iterativas en Java”,
 * OpenAI, 2025.
 * - Material de curso de Estructuras de Datos, ejemplos de recursión.
 * - Concepto de Números Metálicos basado en progresiones lineales combinadas.
 * https://es.wikipedia.org/wiki/N%C3%BAmero_met%C3%A1lico
 *
 * Notas:
 * - No se modifica ningún método público ni el main.
 * - Los métodos privados han sido actualizados para corregir errores de
 * compilación
 * y garantizar la correcta ejecución de la sucesión.
 * - Se mantiene la precisión de 10 dígitos decimales usando el método round().
 */