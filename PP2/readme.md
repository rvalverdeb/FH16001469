# Práctica Programada 2 – Estructuras de Datos

**Curso:** SC-304  
**Profesor:** Luis Andrés Rojas Matey  
**Valor:** 5%  
**Fecha de entrega:** Lunes 13 de octubre antes de las 6 pm  

---

## 👨‍💻 Datos del estudiante
- **Nombre: Reyner Valverde Barboza  
- **Carné:FH16001469
- **IDE o editor utilizado: Visual Studio Code  

---

## 🧠 Descripción del programa
El programa `Revert` lee el contenido del archivo `input.txt`, lo guarda carácter por carácter en una pila (`Stack<Character>`), y luego lo extrae en orden inverso para escribirlo en `output.txt`.

De esta forma, el texto original se invierte completamente, demostrando el uso de la estructura de datos tipo **pila (Stack)**.

---

## ⚙️ Ejemplo
**Archivo de entrada (`input.txt`):**


## 💭 Preguntas solicitadas

### 1️⃣ ¿Cree que exista otra forma de efectuar la inversión del contenido de un archivo sin usar una pila?
Sí. Se podría leer el contenido completo del archivo en una cadena (`String` o `StringBuilder`) y luego invertirla utilizando el método `reverse()`.  
Por ejemplo, `new StringBuilder(texto).reverse().toString()`.  
Sin embargo, este método no hace uso de la estructura **Stack**, por lo que no cumple el objetivo académico de la práctica.

---

### 2️⃣ ¿En qué ejemplo de la vida real (no informático) se puede ver reflejado el uso de una pila?
Un ejemplo cotidiano es una **pila de platos**: el último plato en colocarse es el primero que se retira.  
Este comportamiento “último en entrar, primero en salir” (LIFO) es idéntico al de una pila en programación.

---

## 🔗 Fuentes consultadas
- [Documentación oficial de Java – Stack](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Stack.html)
- [GeeksforGeeks – Stack in Java](https://www.geeksforgeeks.org/stack-class-in-java/)
- [W3Schools Java File Handling](https://www.w3schools.com/java/java_files.asp)

---

## 🤖 Prompts utilizados con IA
- Prompt realizado en ChatGPT (GPT-5):  
  “Crea el código completo en Java y el README.md para la práctica programada 2 del curso Estructuras de Datos (SC-304), basada en el enunciado del profesor Luis Andrés Rojas Matey.”

---

## 🧾 Notas adicionales
- Los archivos `input.txt` y `output.txt` **no deben incluirse** en el repositorio.  
- Se recomienda copiar el `.gitignore` del repositorio del profesor para excluir `.class` y archivos `.txt`.

---

**Fin del README.md**
