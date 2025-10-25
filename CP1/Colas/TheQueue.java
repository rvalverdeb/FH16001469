import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class TheQueue<Type> implements TheQueueInterface<Type> {

    // Atributo privado que representa la cola. No debe modificarse según las
    // indicaciones.
    private Deque<Type> _queue;

    // Constructor de la clase: inicializa la cola como ArrayDeque.
    public TheQueue() {
        _queue = new ArrayDeque<Type>();
    }
    // Encolar un elemento al final de la cola

    public void enqueue(Type item) {
        // Método: enqueue
        // Función: Agrega un elemento al final de la cola.
        // Referencia: La operación respeta la naturaleza FIFO de las colas. Revisado
        // con ayuda de ChatGPT (OpenAI, 2025).
        _queue.add(item); // add() agrega al final de la cola
    }
    // Desencolar un elemento desde el frente de la cola

    // Retorna y elimina el primer elemento de la cola, null si está vacía
    public Type dequeue() {
        return _queue.poll(); // poll() maneja null automáticamente
    }

    // Obtener el elemento en el frente sin eliminarlo

    // Retorna el primer elemento sin eliminarlo, null si está vacía
    public Type getFront() {
        return _queue.peek(); // peek() maneja null automáticamente
    }

    // Comprobar si la cola está vacía
    public boolean isEmpty() {
        // Método: isEmpty
        // Función: Indica si la cola está vacía.
        return _queue.isEmpty();
    }

    // Retornar el tamaño actual de la cola
    public int getSize() {
        return _queue.size();
    }

    // Obtener codones (tripletas de nucleótidos) vaciando la cola
    public String[] getCodons() {
        // Se define un arreglo de tamaño fijo, un tercio de la cola inicial
        String[] codons = new String[_queue.size() / 3];

        // Variable para almacenar cada codón temporalmente
        StringBuilder codon = new StringBuilder();

        // Índice del arreglo de codones
        int index = 0;

        // Mientras queden elementos en la cola, se forman codones de tres nucleótidos
        while (!_queue.isEmpty()) {
            // Se obtiene y elimina el primer nucleótido de la cola
            codon.append(dequeue());

            // Cada vez que se completan tres nucleótidos, se agrega al arreglo
            if (codon.length() == 3) {
                codons[index++] = codon.toString();
                codon.setLength(0); // Reinicia el StringBuilder para el siguiente codón
            }
        }

        // Retorna el arreglo completo de codones
        return codons;
    }

    public String print() {
        return _queue.toString();
    }

    public static void main(String[] args) {
        var amount = Integer.parseInt(args[0]);
        String[] nucleotides = {
                "A", // [0]
                "C", // [1]
                "G", // [2]
                "T", // [3]
        };
        TheQueueInterface<String> queue = new TheQueue<String>();
        var random = new Random();
        for (; amount > 0; amount--) {
            var index = random.nextInt(4);
            var amino = nucleotides[index];
            queue.enqueue(amino);
        }
        System.out.println("Filled {full}");
        System.out.println(" ↳ print() → " + queue.print());
        System.out.println("   ↳ getFront() → " + queue.getFront());
        System.out.println("   ↳ getSize() → " + queue.getSize());
        System.out.println("   ↳ isEmpty() → " + queue.isEmpty());
        System.out.println("\ngetCodons() → " + Arrays.toString(queue.getCodons()));
        System.out.println("\nEnd {empty}");
        System.out.println(" ↳ print() → " + queue.print());
        System.out.println("   ↳ getFront() → " + queue.getFront());
        System.out.println("   ↳ getSize() → " + queue.getSize());
        System.out.println("   ↳ isEmpty() → " + queue.isEmpty());
    }
}

/*
 * Referencias académicas:
 *
 * 1. ChatGPT, OpenAI.
 * "Asistencia en programación y explicaciones de estructuras de datos: colas, pilas, recursividad y algoritmos iterativos."
 * Disponible en: https://chat.openai.com
 *
 * 2. ChatGPT, OpenAI.
 * "Explicación paso a paso de la implementación de colas con ArrayDeque en Java y simulación de codones de ADN."
 * Disponible en: https://chat.openai.com
 *
 * 3. ChatGPT, OpenAI.
 * "Soporte académico para la definición de funciones recursivas y su comparación con implementaciones iterativas en Java."
 * Disponible en: https://chat.openai.com
 */