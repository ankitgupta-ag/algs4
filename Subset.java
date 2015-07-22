import java.util.Iterator;

public class Subset {
 public static void main(String[] args) {
  int k = Integer.parseInt(args[0]);
  int stringsReadSoFar = 0;

  RandomizedQueue<String> d = new RandomizedQueue<String>();

  while (!StdIn.isEmpty()) {
   stringsReadSoFar++;
   String str = StdIn.readString();

   if (d.size() < k)
    d.enqueue(str);

   else {
    int randomIndex = StdRandom.uniform(0, stringsReadSoFar);
    if (randomIndex < k) {
     d.dequeue();
     d.enqueue(str);
    }
   }
  }

  Iterator<String> a = d.iterator();
  while (a.hasNext()) {
   StdOut.println(a.next());
  }
 }
}
