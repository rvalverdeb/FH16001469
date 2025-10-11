# Práctica Programada 1 – Estructuras de Datos (SC-304)

## Datos del estudiante
- Reyner Valverde Barboza
- FH16001469

## Descripción
Este programa recibe un número natural como argumento en consola y muestra dicho número junto con su inverso, calculado mediante un algoritmo recursivo que utiliza cociente y residuo.

Ejemplo:
```bash
$ java Inverso 123
123 → 321
```
## Referencias
- [Documentación oficial de Java](https://docs.oracle.com/en/java/javase/21/)
- [GeeksforGeeks – Reverse digits of a number using recursion](https://www.geeksforgeeks.org/reverse-digits-of-a-number-using-recursion/)

## Uso de IA
Prompts realizados a ChatGPT para generar y mejorar el algoritmo en Java.

- **Consulta:** "Generar código Java que invierta un número usando recursión, cociente y residuo."  
- **Respuesta:** Se obtuvo el algoritmo recursivo con validación y presentación en consola.  

## Preguntas
1. **¿Es posible implementar una solución equivalente pero de comportamiento iterativo?**  
   Sí, se puede hacer con un ciclo `while` que vaya extrayendo dígitos con `%` y acumulando con `*10`.  

2. **¿Cree que hay alguna otra manera recursiva de generar el mismo resultado?**  
   Sí, por ejemplo, contando primero la cantidad de dígitos y luego usando potencias de 10 para reconstruir el inverso.  

3. **¿Qué relación observa entre el algoritmo para invertir un número natural (calculando y utilizando los cocientes y residuos de las divisiones) con las estrategias para pasar de una base numérica a otra?**  
   Ambos procesos utilizan divisiones sucesivas y residuos para descomponer un número en sus dígitos o cifras en otra base.  
