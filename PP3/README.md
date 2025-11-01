# Práctica Programada 2 – Estructuras de Datos

**Curso:** SC-304  
**Profesor:** Luis Andrés Rojas Matey  
**Valor:** 5%  
**Fecha de entrega:** Lunes 3 de noviembre antes de las 6 pm  

---

## 👨‍💻 Datos del estudiante
- **Nombre: Reyner Valverde Barboza  
- **Carné:FH16001469
- **IDE o editor utilizado: Visual Studio Code  

---

## 🧠 Descripción del programa
Esta práctica implementa una **lista circular doblemente enlazada** en **Java**, completando los métodos `addFirst`, `addLast`, `removeFirst` y `removeLast` dentro de la clase `ListDoublyCircular`.  
El objetivo es comprender el funcionamiento interno de las listas enlazadas y reforzar el manejo de punteros/referencias entre nodos.


---



## 💭 Preguntas solicitadas

### 1️⃣ Si tuviera que implementar una estructura tipo **Cola (Queue)**, ¿qué tipo de lista utilizaría y por qué?

Utilizaría una **lista circular**.  
 
- En una **lista circular**, se aprovecha que el último nodo apunta al primero, lo cual facilita el manejo continuo de elementos sin necesidad de reiniciar punteros.

### 2️⃣ Si tuviera que implementar una estructura tipo **Pila (Stack)**, ¿qué tipo de lista utilizaría y por qué?

Usaría una **lista enlazada simple**.  
- Solo se necesita acceso al tope de la pila para operaciones **push** y **pop**, lo que la hace más sencilla y eficiente en memoria que una doblemente enlazada.

### 3️⃣ ¿Cuál sería una ventaja y una desventaja de usar una lista creada manualmente en vez de las clases estándar de Java?

- **Ventaja:**  
  Permite un control total sobre cómo se manejan los nodos, punteros y memoria. 

- **Desventaja:**  
  Requiere más tiempo de desarrollo y es más propensa a errores.

---

## 🔗 Fuentes consultadas
- [Documentación oficial de Java SE 21](https://docs.oracle.com/en/java/javase/21/docs/api/)
- [GeeksforGeeks – Doubly Circular Linked List](https://www.geeksforgeeks.org/doubly-circular-linked-list-set-1-introduction-and-insertion/)
- [W3Schools – Java Generics](https://www.w3schools.com/java/java_generics.asp)
- [ChatGPT] )(https://chatgpt.com/)
---

## 🤖 Prompts utilizados con IA
- Prompt realizado en ChatGPT (GPT-5):  
  “Crea el código completo en Java para la práctica programada 3 del curso Estructuras de Datos (SC-304), basada en el enunciado del profesor Luis Andrés Rojas Matey y explicamelo.”

---

## 🧪 Ejecución del programa

Para ejecutar el programa con un parámetro `n` (número natural ≥ 0):

```bash
javac ListDoublyCircular.java
java ListDoublyCircular 2

**Fin del README.md**
