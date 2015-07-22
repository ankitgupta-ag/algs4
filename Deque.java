import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
 private Node front;
 private Node rear;
 private int size;

 public Deque() {
  this.front = null;
  this.rear = null;
  this.size = 0;
 }

 private class Node {
  Item item;
  Node next;
  Node previous;
 }

 public boolean isEmpty() {
  return (this.size == 0);
 }
 
 public int size()
 {
     return size;
 }

 public void addFirst(Item item) {
  if (item == null) {
   throw new java.lang.NullPointerException();
  }

  Node newNode = new Node();
  newNode.item = item;
  newNode.next = this.front;
  newNode.previous = null;

  if (!isEmpty())
   this.front.previous = newNode;
  else
   this.rear = newNode;

  this.front = newNode;
  size++;
 }

 public void addLast(Item item) {
  if (item == null) {
   throw new java.lang.NullPointerException();
  }

  Node newNode = new Node();
  newNode.item = item;
  newNode.next = null;
  newNode.previous = this.rear;

  if (!isEmpty()) {
   this.rear.next = newNode;
  } else {
   this.front = newNode;
  }
  this.rear = newNode;
  size++;
 }

 public Item removeFirst() {
  if (isEmpty()) {
   throw new java.util.NoSuchElementException();
  }

  Item firstItem = this.front.item;

  if (front.next != null) {
   this.front.next.previous = null;
  }
  this.front = this.front.next;
  size--;
  if (isEmpty()) {
   this.rear = null;
  }

  return firstItem;
 }

 public Item removeLast() {
  if (isEmpty()) {
   throw new java.util.NoSuchElementException();
  }

  Item lastItem = this.rear.item;

  if (this.rear.previous != null)
   this.rear.previous.next = null;

  this.rear = rear.previous;

  size--;

  if (isEmpty()) {
   this.front = null;
  }

  return lastItem;
 }

 public Iterator<Item> iterator() {
  return new DequeIterator();
 }

 private class DequeIterator implements Iterator<Item> {
  private Node current = front;

  public boolean hasNext() {
   return current != null;
  }

  public void remove() {
   throw new java.lang.UnsupportedOperationException();
  }

  public Item next() {
   if (!hasNext()) {
    throw new java.util.NoSuchElementException();
   }

   Item item = current.item;
   current = current.next;
   return item;
  }
 }

 public static void main(String args[]) {
  Deque<Integer> d = new Deque<Integer>();

  Iterator<Integer> a = d.iterator();

  while (a.hasNext()) {
   StdOut.println(a.next());
  }

  for (int i = 0; i < 10; i++) {
   d.addFirst(i);
  }

  for (int i = 0; i < 10; i++) {
   StdOut.println(d.removeFirst());
  }

 }
}
