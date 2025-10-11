import java.io.*;
import java.util.Stack;

public class Revert {

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (FileReader reader = new FileReader(inputFile)) {
            int data;
            // Leer carácter por carácter y empujarlo a la pila
            while ((data = reader.read()) != -1) {
                stack.push((char) data);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            // Vaciar la pila (invirtiendo el contenido)
            while (!stack.isEmpty()) {
                writer.write(stack.pop());
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }

        System.out.println("El archivo se ha invertido correctamente.");
    }
}
