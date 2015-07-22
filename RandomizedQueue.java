import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
 private Item[] q;
 private int size;

 public RandomizedQueue() {
  this.q = (Item[]) new Object[1];
  this.size = 0;
 }

 public boolean isEmpty() {
  return (size == 0);
 }

 public int size() {
  return size;
 }

 public void enqueue(Item item) {
  if (item == null) {
   throw new java.lang.NullPointerException();
  }

  if (size == q.length)
   resize(2 * q.length);

  q[size++] = item;
 }

 private void resize(int capacity) {
  Item[] copy = (Item[]) new Object[capacity];
  for (int i = 0; i < size; i++)
   copy[i] = q[i];
  q = copy;
 }

 public Item dequeue() {
  if (isEmpty()) {
   throw new java.util.NoSuchElementException();
  }

  int randomIndex = StdRandom.uniform(size);
  Item item = q[randomIndex];

  
  q[randomIndex] = q[size - 1];
  q[size-1] = null;
 
  
  size--;

  if (size > 0 && size == q.length / 4)
   resize(q.length / 2);

  return item;
 }

 public Item sample() {
  if (isEmpty()) {
   throw new java.util.NoSuchElementException();
  }

  int randomIndex = StdRandom.uniform(size);
  Item item = q[randomIndex];

  return item;
 }

 public Iterator<Item> iterator() {
  return new RandomizedIterator();
 }

 private class RandomizedIterator implements Iterator<Item> {
 
  private int iterationQueueSize = size;
  private Item[] iterationQueue = (Item[]) new Object[size];

  private RandomizedIterator(){
      for(int i=0 ; i<size ; i++)
      {
          iterationQueue[i] = q[i];
      }
      
  }
  public boolean hasNext() {
   return iterationQueueSize > 0;
  }

  public void remove() {
   throw new java.lang.UnsupportedOperationException();
  }

  public Item next() {
   if (!hasNext()) {
    throw new java.util.NoSuchElementException();
   }

   int randomIndex = StdRandom.uniform(iterationQueueSize);
   Item item = iterationQueue[randomIndex];
   
   iterationQueue[randomIndex] = iterationQueue[iterationQueueSize - 1];
   iterationQueue[iterationQueueSize - 1] = null;
   iterationQueueSize--;
   
   return item;
  }
 }

 public static void main(String args[]) {
  RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
  
  for (int i = 0; i < 10; i++) {
   d.enqueue(i);
  }

  Iterator<Integer> a = d.iterator();
  while (a.hasNext()) {
   StdOut.println(a.next());

 }
 }
}
