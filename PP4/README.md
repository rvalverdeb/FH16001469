# Práctica Programada 4 – Estructuras de Datos

**Curso:** SC-304  
**Profesor:** Luis Andrés Rojas Matey  
**Valor:** 5%  
**Fecha de entrega:** Lunes 17 de noviembre antes de las 6 pm.  

---

## 👨‍💻 Datos del estudiante
- **Nombre:** Reyner Valverde Barboza  
- **Carné:** FH16001469  
- **IDE o editor utilizado:** Visual Studio Code  

---

## 🧠 Descripción del programa
Esta práctica implementa un **árbol binario ordenado (Binary Search Tree)** utilizando un **arreglo fijo de tipo `Integer`**, definido en la clase `BinTreeIntArray`.  
El objetivo es comprender cómo funciona un árbol binario representado mediante índices y desarrollar los recorridos de forma **iterativa**, sin recursividad.

Los métodos completados fueron:

- `insert(int value)` – Inserta un valor siguiendo la regla del árbol binario:
  - Valores menores → hijo izquierdo: `2i + 1`
  - Valores mayores → hijo derecho: `2i + 2`

- `preOrderTraversal()` – Recorre el árbol en **preorden (root → left → right)**.

- `postOrderTraversal()` – Recorre el árbol en **posorden (left → right → root)**.

Al ejecutar el programa sin argumentos, se muestra cómo se va llenando el árbol y luego los tres recorridos cuando el arreglo está completo.

---

## 📌 Funcionalidad desarrollada
- Inserción **sin recursión**, desplazándose por índices.
- Recorridos **iterativos** utilizando estructuras `Stack`.
- Uso obligatorio de las fórmulas `2i + 1` (left) y `2i + 2` (right).
- Cumplimiento estricto de las restricciones:
  - No modificar `main`.
  - No agregar constructores ni atributos.
  - No modificar `_tree`.
  - No agregar impresiones adicionales.
  - No usar `try/catch`.
  - Utilizar solo código iterativo.

---

## 🔗 Fuentes consultadas
- Documentación de Java SE 21  
  https://docs.oracle.com/en/java/javase/21/docs/api/

- GeeksforGeeks – Iterative Tree Traversals  
  https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

- StackOverflow – Binary Tree with array representation  
- W3Schools – Java Arrays & Stack  
- ChatGPT (GPT-5.1)

---

## 🤖 Prompts utilizados con IA
- **ChatGPT (GPT-5.1):**  
  “Implementa de forma iterativa los métodos insert, preOrderTraversal y postOrderTraversal para un árbol binario ordenado representado con un arreglo, siguiendo las restricciones del enunciado del profesor.”

- **ChatGPT (GPT-5.1):**  
  “Corrige este código para que coincida exactamente con la salida requerida de la práctica programada 4 en Java.”

- **ChatGPT (GPT-5.1):**  
  “Hazme el README como el de la PP3 pero para la PP4.”

---

## 🧪 Ejecución del programa

Compilar:

```bash
javac BinTreeIntArray.java
```
---

Ejecutar:

```bash
java BinTreeIntArray
