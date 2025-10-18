# Proyecto Final: The Sandwich Guy - Avance I


**Curso:** Estructuras de Datos (SC-304)
**Periodo:** III Cuatrimestre 2025
**Profesor:** Luis Andrés Rojas Matey
**Valor Avance I:** 15%


## Integrantes
- Reyner Valverde — Carné: FH16001469 — GitHub: rvalverdeb — Email: rey.ner25@hotmail.com
- Valentina Garcia Marin — Carné: FI24036587 — GitHub: rvalverdeb — Email: rey.ner25@hotmail.com
- Nahúm Mejía Castillo — Carné: FI24043887 — GitHub: rvalverdeb — Email: rey.ner25@hotmail.com


---


## Entregables incluidos en este avance
- `src/`:
- `model/Carta.java`
- `model/CircularList.java`
- `app/SandwichGuyApp.java` (interfaz gráfica básica con Swing)
- `README.md` (este archivo)


---


## Instructivo


### Requisitos
- Java SE 21 (JDK 21)

### Compilación
Solamente le damos Run file sobre la clase SandwichGuyApp.java


### Ejecución

Al correr abre una ventana gráfica con botones como:

"Crear Mazo"

"Repartir 8 a Mano"

"Ordenar Mano"

Y áreas con títulos "Caja", "Mazo", "Mano" y "Pozo"

---


## Notas técnicas (Avance I)
- Implementamos la clase `Carta` propia con atributos: palo (Suit), valor (1..13), nombre y color.
- Las 52 cartas se instancian una sola vez y se colocan en la estructura `Caja` (LinkedList).
- `Mazo` se maneja como `ArrayDeque` (uso como pila/stack).
- `Mano` tiene una implementación simple de `CircularList` (lista circular) para cumplir la especificación técnica.
- `Pozo` es una `LinkedList` que se usa como cola FIFO.





---
