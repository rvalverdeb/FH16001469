# The Sandwich Guy — Proyecto Final (Avance Final)

Curso: Estructuras de Datos (SC-304) — III Cuatrimestre 2025  
Profesor: Luis Andrés Rojas Matey

---

## Equipo / Contacto
- Reyner Valverde — Carné: FH16001469 — GitHub: rvalverdeb — Email: rey.ner25@hotmail.com  
- Valentina Garcia Marin — Carné: FI24036587 — GitHub: valentinagarcia — Email: valentinagarciamarin.vgm@gmail.com  
- Nahúm Mejía Castillo — Carné: FI24043887 — GitHub: Nahum99 — Email: nahumm775@gmail.com

---

## Resumen del proyecto
Implementación en Java (Swing) del juego "The Sandwich Guy". Incluye las estructuras de datos requeridas (Carta con id único, Caja, Mazo, Mano circular, Pozo), las reglas de evaluación de “sándwiches” (6 permutaciones) y persistencia de estado en XML preservando la identidad de cada carta (id 0..51).

Objetivo de este avance: entregar la estructura de datos y la interfaz mínima funcional, con persistencia y utilidades para probar la lógica.

---

## Archivos incluidos en este avance (principales)
- model/
  - Carta.java
  - Caja.java
  - Mazo.java
  - Mano.java
  - CircularList.java
  - Pozo.java
  - SandwichEvaluator.java
  - PermutationResult.java
  - TripletResult.java
  - Juego.java
  - CardCellRenderer.java (render coloreado de cartas)
- app/
  - SandwichGuyApp.java (GUI Swing)
  
- README.md (este archivo)


---

## Requisitos
- JDK 21 (Java SE 21)  
- No se requieren librerías externas (se utiliza java.xml para XML DOM/Transformer).

---

## Compilación (línea de comandos)
1) Compilar todas las clases (ajusta rutas si tu estructura difiere):
```bash
javac -d out src\main\java\model\*.java src\main\java\app\*.java
```

2) Ejecutar la aplicación GUI:
```bash
java -cp out app.SandwichGuyApp
```

---

## Ejecutar en Visual Studio Code (recomendado para la entrega)
1. Instalar la extensión "Extension Pack for Java" (o al menos "Language Support for Java(TM) by Red Hat" y "Debugger for Java").  
2. Abrir la carpeta del proyecto en VS Code.  
3. Compilar
4. Ejecutar

Esta entrega está preparada para ejecutarse directamente desde VS Code siguiendo los pasos anteriores.

---

## Cómo jugar (guía rápida — GUI)
1. Al iniciar verá cuatro áreas: Caja, Mazo, Mano y Pozo y botones en la parte superior.  
2. Flujo típico:
   - `Barajar Caja → Mazo`: mezcla las 52 cartas maestras y las coloca en el mazo.
   - `Repartir 8 a Mano`: reparte hasta 8 cartas desde el mazo a la mano (límite 8).
   - Seleccione exactamente 3 cartas en la lista de la mano (Ctrl/Shift para seleccionar).
   - `Evaluar 3 seleccionadas`: muestra las 6 permutaciones y cuántas cartas se podrían tomar en cada permutación (0/2/3/4). Si existe una permutación válida puede elegir "Enviar tripleta": el juego descartará las 3 cartas al Pozo y robará automáticamente la mayor cantidad posible respetando el tope de 8.
   - `Validar Mano`: busca todas las tripletas válidas y permite seleccionar una para enviar. Si no hay sándwiches y el mazo contiene cartas, la aplicación ofrece robar (1 carta o hasta llenar la mano) antes de declarar pérdida.
   - `Ordenar Mano`: ordena la mano por valor.
   - `Guardar partida` / `Cargar partida` (o menú Archivo → Guardar/Cargar): guarda/restaura estado completo en XML.
   - `Simular partida` (GUI): ejecuta automáticamente una partida aleatoria paso a paso (útil para comprender la lógica).
   - `Reiniciar`: inicia una partida nueva (restaura Caja, Mazo vacío, Mano y Pozo vacíos).  

3. Condiciones de fin:
   - Ganas si el mazo queda vacío.  
   - Se pierde si no hay sándwiches válidos en la mano y no es posible robar (mazo vacío o mano llena sin movimientos). El flujo actual permite intentar robar antes de declarar pérdida.

---

## Reglas implementadas (resumen técnico)
- Carta: id (0..51), nombre (A,2,..,K), palo (♣♦♥♠) y valor (1..13). Color deducido del palo.  
- Sándwich: para una tripleta se evalúan las 6 permutaciones; una permutación es válida si la distancia circular entre a→b es igual a b→c (rueda 1..13).  
- Recompensa por sándwich:
  - mismo palo → hasta 4 cartas
  - mismo color → hasta 3 cartas
  - distinto color → hasta 2 cartas
- Límite: máximo 8 cartas en la Mano.  
- Búsqueda: se generan todas las combinaciones i<j<k (n ≤ 8 → ≤56 combinaciones) y se evalúan sus permutaciones.

---

## Formato de persistencia (XML)
- Se guarda la lista de cartas maestras (id 0..51) y en cada sección (Caja/Mazo/Mano/Pozo) se escriben referencias por `<Ref id="..."/>`. Al cargar se recrean exactamente 52 instancias maestras y se redistribuyen por referencia entre las estructuras.

Ejemplo (simplificado):
```xml
<Juego terminado="false" ganado="false">
  <Jugador nombre="Jugador 1"/>
  <CartasMaestras>
    <Carta id="0" nombre="A" palo="♣" valor="1"/>
    ...
  </CartasMaestras>
  <Caja><Ref id="5"/></Caja>
  <Mazo><Ref id="12"/></Mazo>
  <Mano><Ref id="3"/></Mano>
  <Pozo><Ref id="40"/></Pozo>
</Juego>
```

---

## Pruebas sugeridas
- Casos límite de permutaciones: (Q,K,A), (K,A,2) para comprobar la distancia circular.  
- Guardar y cargar: barajar → repartir → guardar → reiniciar → cargar → comprobar mano/mazo/pozo.  
- Escenario sin movimientos: provocar mano sin sándwiches y mazo vacío para verificar pérdida.  
- Usar `Simular partida` (GUI) para observar flujo automático.

---

## Limitaciones conocidas / mejoras posibles
- Interfaz: se puede mejorar con iconos por palo y arrastrar/soltar cartas.  
- Estructura de búsqueda: actualmente las 6 permutaciones se manejan en lista; si la especificación exige literalmente un BST para permutaciones, se puede añadir.  
- Tests unitarios: no incluidos en este avance; se recomienda agregar JUnit para SandwichEvaluator y Juego.

---

## Reconocimientos / Ayuda externa
- Código, diseño y desarrollo principal: integrantes del equipo (Reyner Valverde, Valentina Garcia Marin, Nahúm Mejía Castillo).  
- Se utilizó asistencia de una Inteligencia Artificial (ChatGPT, OpenAI) para:
  - revisar y refactorizar piezas de código,
  - proponer e implementar persistencia XML por id,
  - mejorar la interfaz Swing y añadir opciones de guardado/carga,
  - redactar y pulir partes de la documentación (este README).
  
La IA fue utilizada como herramienta de apoyo; las decisiones de diseño, las integraciones finales y la autoría del trabajo en el repositorio corresponden a los integrantes del equipo.

---

## Cómo colaborar / extender
- Clonar el repo, abrir en VS Code / NetBeans / IntelliJ, compilar y ejecutar.  
- Para cambiar reglas o comportamiento (por ejemplo política de robo), editar `Juego.java` y/o `SandwichGuyApp.java`.  
- Para agregar pruebas, añadir JUnit en `src/test/java`.

---

