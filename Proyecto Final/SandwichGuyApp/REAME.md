# Proyecto Final: The Sandwich Guy - Avance II


**Curso:** Estructuras de Datos (SC-304)
**Periodo:** III Cuatrimestre 2025
**Profesor:** Luis Andrés Rojas Matey
**Valor Avance I:** 15%
**Valor Avance II:** 10%


## Integrantes
- Reyner Valverde — Carné: FH16001469 — GitHub: rvalverdeb — Email: rey.ner25@hotmail.com
- Valentina Garcia Marin — Carné: FI24036587 — GitHub: valentinagarcia — Email: valentinagarciamarin.vgm@gmail.com
- Nahúm Mejía Castillo — Carné: FI24043887 — GitHub: Nahum99 — Email: nahumm775@gmail.com


---


## Entregables incluidos en este avance
- `model/Carta.java`
- `model/CircularList.java`
- `model/Mazo.java`
- `model/Mano.java`
- `model/Pozo.java`
- `app/SandwichGuyApp.java` (interfaz gráfica básica con Swing)
- `README.md` (este archivo)


---


## Instructivo


### Requisitos
- Java SE 21 (JDK 21)

### Compilación

```javac -d out src/main/java/model/Carta.java src/main/java/model/CircularList.java src/main/java/app/SandwichGuyApp.java```

### Ejecución

```java -cp out app.SandwichGuyApp```

Al correr abre una ventana gráfica con botones como:

"Crear Mazo"

"Repartir 8 a Mano"

"Ordenar Mano"

Y áreas con títulos "Caja", "Mazo", "Mano" y "Pozo"

### Editores

-NetBeans
-Visual Studio Code

---

## 🧮 Funcionalidades implementadas (Avance II)

| **Requisito** | **Descripción** | **Estado** |
|----------------|-----------------|-------------|
| Clases `Caja`, `Mazo`, `Mano` y `Pozo` | Implementadas correctamente con sus estructuras de datos respectivas. | ✅ |
| Funcionalidad de **barajar** | Las cartas se transfieren de la Caja al Mazo de forma aleatoria. | ✅ |
| **Visualización en GUI** | Las cartas en Caja y Mazo se muestran en la interfaz con sus símbolos y valores. | ✅ |
| **Documentación en Markdown** | Se incluye este archivo README con instructivo y descripción del avance. | ✅ |

**Total:** 25 puntos (10 del avance × 2.5 cada criterio)

---

## Notas técnicas (Avance I)
- Implementamos la clase `Carta` propia con atributos: palo (Suit), valor (1..13), nombre y color.
- Las 52 cartas se instancian una sola vez y se colocan en la estructura `Caja` (LinkedList).
- `Mazo` se maneja como `ArrayDeque` (uso como pila/stack).
- `Mano` tiene una implementación simple de `CircularList` (lista circular) para cumplir la especificación técnica.
- `Pozo` es una `LinkedList` que se usa como cola FIFO.

## 🧠 Notas técnicas (Avance II)

- Se corrigió e implementó correctamente la clase `Carta`, incluyendo símbolos, nombre y color.  
- `Caja` utiliza una `ArrayList` para almacenar las 52 cartas originales.  
- `Mazo` se maneja con `Stack<Carta>` para simular una pila.  
- `Pozo` emplea una `Queue<Carta>` (`LinkedList`) para funcionamiento tipo cola.  
- `Mano` usa una estructura circular para futuras operaciones.  
- Se añadió la funcionalidad `barajar()` con `Collections.shuffle()` para mezclar cartas.  
- La interfaz Swing permite visualizar la distribución de cartas en cada zona.



---
