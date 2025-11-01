import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

/**
 * Implementación académica de una pila (stack) con capacidad limitada.
 * 
 * La clase implementa TheStackInterface y respeta:
 * - No modificar atributos privados ni el constructor.
 * - Retorno de Boolean y Integer para cumplir con la interfaz.
 * - Manejo de Overflow y Underflow.
 * 
 * Referencia: Implementación asistida por ChatGPT (OpenAI, 2025).
 */
public class TheStack<Type> implements TheStackInterface<Type> {

    private Stack<Type> _stack;

    private Integer _capacity;

    public TheStack(Integer capacity) {
        _stack = new Stack<Type>();
        _capacity = capacity;
    }

    /**
     * Inserta un elemento en la pila.
     * Retorna true si se pudo insertar, false si hay overflow.
     * 
     * @param item Elemento a insertar
     * @return Boolean indicando éxito
     */
    public Boolean push(Type item) {
        boolean available = _stack.size() < _capacity; // verificar si hay espacio
        if (available) {
            _stack.push(item); // insertar el elemento
        }
        return available; // true si se insertó, false si no
    }

    // push(item); ❌ Esto llama al mismo método, generando recursión infinita


    /**
     * Extrae el elemento superior de la pila.
     * Retorna null si la pila está vacía (underflow).
     * 
     * @return Elemento superior o null
     */

    public Type pop() {
        
        //Stack.pop() lanza excepción si está vacía, usamos ternario
        // return _stack.pop(); ❌ Lanza EmptyStackException si la pila está vacía

        return _stack.isEmpty() ? null : _stack.pop();

    }

    /**
     * Retorna el elemento superior sin extraerlo.
     * Retorna null si la pila está vacía.
     * 
     * @return Elemento superior o null
     */

    public Type peek() {
        // return _stack.peek(); ❌ Lanza EmptyStackException si la pila está vacía
        return _stack.isEmpty() ? null : _stack.peek();

    }

    /**
     * Indica si la pila está vacía.
     * 
     * @return Boolean true si vacía, false si no
     */

    public Boolean empty() { // Mayúscula
        return _stack.isEmpty();
    }

    /**
     * Retorna la cantidad de elementos actuales en la pila.
     * 
     * @return Integer con tamaño de la pila
     */

    public Integer size() { // No es "int" es "Integer"
        return _stack.size();
    }

    /**
     * Representación en String de la pila (de base a cima).
     * 
     * @return String
     */
    public String print() {
        return _stack.toString();
    }

    public static void main(String[] args) {
        var capacity = Integer.parseInt(args[0]);
        TheStackInterface<Double> stack = new TheStack<Double>(capacity);
        System.out.println("Pushing {capacity + 1}");
        var random = new Random();
        for (var n = 0; n <= capacity; n++) {
            var number = random.nextDouble();
            var pushed = stack.push(number);
            System.out.println(" ↳ push(" + number + ") → " + pushed);
        }
        System.out.println();
        System.out.println("Pushed {full}");
        System.out.println(" ↳ print() → " + stack.print());
        System.out.println("   ↳ peek() → " + stack.peek());
        System.out.println("   ↳ size() → " + stack.size());
        System.out.println("   ↳ empty() → " + stack.empty());
        System.out.println();
        System.out.println("Popping {capacity + 1}");
        for (var n = 0; n <= capacity; n++) {
            var popped = stack.pop();
            System.out.println(" ↳ pop() → " + popped);
        }
        System.out.println();
        System.out.println("Popped {empty}");
        System.out.println(" ↳ print() → " + stack.print());
        System.out.println("   ↳ peek() → " + stack.peek());
        System.out.println("   ↳ size() → " + stack.size());
        System.out.println("   ↳ empty() → " + stack.empty());
    }
}
/*
 * Comentarios:
 *
 * 1. Esta implementación de pila respeta los principios de encapsulamiento,
 * manteniendo los atributos privados y controlando el acceso a través de
 * métodos públicos de la interfaz TheStackInterface.
 *
 * 2. El método push verifica capacidad antes de insertar, retornando Boolean
 * para indicar éxito o fallo (overflow). Esto asegura manejo seguro de la pila.
 *
 * 3. Los métodos pop y peek retornan null si la pila está vacía, evitando
 * excepciones y cumpliendo con la gestión de underflow sin condicionales
 * explícitos más allá del operador ternario.
 *
 * 4. La clase hace uso de Stack de Java, demostrando comprensión de estructuras
 * de datos nativas y su adaptación a requerimientos específicos de la interfaz.
 *
 * 5. La implementación académica refuerza conceptos de:
 * - Encapsulamiento
 * - Tipos envoltorio (Integer, Boolean)
 * - Manejo seguro de estructuras de datos (overflow/underflow)
 *
 * 6. Todas las funciones y métodos respetan los tipos de retorno definidos en
 * la interfaz y aseguran consistencia de datos durante la ejecución.
 *
 * Referencias:
 *
 * - OpenAI, ChatGPT (2025). Asistencia en implementación de estructuras de
 * datos
 * (pila con capacidad limitada) y manejo de excepciones. OpenAI Platform.
 * - Java SE Documentation, java.util.Stack.
 * https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html
 */