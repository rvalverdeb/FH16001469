/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
import java.util.Iterator;
import java.util.NoSuchElementException;


// Implementación simple de lista circular basada en nodos (suficiente para Avance I)
public class CircularList<T> implements Iterable<T> {
private static class Node<E> { E value; Node<E> next; Node(E v){value=v;} }
private Node<T> tail; // tail.next = head
private int size = 0;


public void addLast(T value){
Node<T> n = new Node<>(value);
if(tail==null){ tail = n; tail.next = n; }
else { n.next = tail.next; tail.next = n; tail = n; }
size++;
}


public T removeAt(int index){
if(size==0) throw new NoSuchElementException();
index = ((index % size) + size) % size;
Node<T> prev = tail;
for(int i=0;i<index;i++) prev = prev.next;
Node<T> target = prev.next;
if(target==tail) tail = (target==tail && size==1) ? null : prev;
prev.next = target.next;
size--;
return target.value;
}


public T get(int index){
if(size==0) throw new NoSuchElementException();
index = ((index % size) + size) % size;
Node<T> cur = tail.next;
for(int i=0;i<index;i++) cur = cur.next;
return cur.value;
}


public int size(){ return size; }


public boolean isEmpty(){ return size==0; }


@Override
public Iterator<T> iterator(){
return new Iterator<>(){
private Node<T> current = (tail==null)? null : tail.next;
private int remaining = size;
@Override public boolean hasNext(){ return remaining>0; }
@Override public T next(){ if(!hasNext()) throw new NoSuchElementException(); T v = current.value; current = current.next; remaining--; return v; }
};
}
}
