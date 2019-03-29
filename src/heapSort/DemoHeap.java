package heapSort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class DemoHeap<Key> implements Iterable<Key> {
	
	private Key[] pq;                    
    private int n;                       
    private Comparator<Key> comparator; 
    
   @SuppressWarnings("unchecked")
public DemoHeap(int initCapacity) {
       pq = (Key[]) new Object[initCapacity + 1];
       n = 0;
   }

   /**
    * Initializes an empty priority queue.
    */
   public DemoHeap() {
       this(1);
   }

   /**
    * Initializes an empty priority queue with the given initial capacity,
    * using the given comparator.
    *
    * @param  initCapacity the initial capacity of this priority queue
    * @param  comparator the order in which to compare the keys
    */
   @SuppressWarnings("unchecked")
public DemoHeap(int initCapacity, Comparator<Key> comparator) {
       this.comparator = comparator;
       pq = (Key[]) new Object[initCapacity + 1];
       n = 0;
   }

   /**
    * Initializes an empty priority queue using the given comparator.
    *
    * @param  comparator the order in which to compare the keys
    */
   public DemoHeap(Comparator<Key> comparator) {
       this(1, comparator);
   }

   /**
    * Initializes a priority queue from the array of keys.
    * <p>
    * Takes time proportional to the number of keys, using sink-based heap construction.
    *
    * @param  keys the array of keys
    */
   @SuppressWarnings("unchecked")
public DemoHeap(Key[] keys) {
       n = keys.length;
       pq = (Key[]) new Object[keys.length + 1];
       for (int i = 0; i < n; i++)
           pq[i+1] = keys[i];
       for (int k = n/2; k >= 1; k--)
           sink(k);
       assert isMinHeap();
   }

/**
    * Returns true if this priority queue is empty.
    *
    * @return {@code true} if this priority queue is empty;
    *         {@code false} otherwise
    */
   public boolean isEmpty() {
       return n == 0;
   }

   /**
    * Returns the number of keys on this priority queue.
    *
    * @return the number of keys on this priority queue
    */
   public int size() {
       return n;
   }

   /**
    * Returns a smallest key on this priority queue.
    *
    * @return a smallest key on this priority queue
    * @throws NoSuchElementException if this priority queue is empty
    */
   public Key min() {
       if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
       return pq[1];
   }

   // helper function to double the size of the heap array
   private void resize(int capacity) {
       assert capacity > n;
       @SuppressWarnings("unchecked")
	Key[] temp = (Key[]) new Object[capacity];
       for (int i = 1; i <= n; i++) {
           temp[i] = pq[i];
       }
       pq = temp;
   }

   /**
    * Adds a new key to this priority queue.
    *
    * @param  x the key to add to this priority queue
    */
   public void insert(Key x) {
       // double size of array if necessary
       if (n == pq.length - 1) resize(2 * pq.length);

       // add x, and percolate it up to maintain heap invariant
       pq[++n] = x;
       swim(n);
       assert isMinHeap();
   }

   public Key delMin() {
       if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
       Key min = pq[1];
       exch(1, n--);
       sink(1);
       pq[n+1] = null;     // to avoid loiterig and help with garbage collection
       if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
       assert isMinHeap();
       return min;
   }


  /***************************************************************************
   * Helper functions to restore the heap invariant.
   ***************************************************************************/

   private void swim(int k) {
       while (k > 1 && greater(k/2, k)) {
           exch(k, k/2);
           k = k/2;
       }
   }

   private void sink(int k) {
       while (2*k <= n) {
           int j = 2*k;
           if (j < n && greater(j, j+1)) j++;
           if (!greater(k, j)) break;
           exch(k, j);
           k = j;
       }
   }

  /***************************************************************************
   * Helper functions for compares and swaps.
   ***************************************************************************/
   @SuppressWarnings("unchecked")
private boolean greater(int i, int j) {
       if (comparator == null) {
           return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
       }
       else {
           return comparator.compare(pq[i], pq[j]) > 0;
       }
   }

   private void exch(int i, int j) {
       Key swap = pq[i];
       pq[i] = pq[j];
       pq[j] = swap;
   }

   private boolean isMinHeap() {
       return isMinHeap(1);
   }

   private boolean isMinHeap(int k) {
       if (k > n) return true;
       int left = 2*k;
       int right = 2*k + 1;
       if (left  <= n && greater(k, left))  return false;
       if (right <= n && greater(k, right)) return false;
       return isMinHeap(left) && isMinHeap(right);
   }

   public Iterator<Key> iterator() {
       return new HeapIterator();
   }

   private class HeapIterator implements Iterator<Key> {

       private MinPQ<Key> copy;

       public HeapIterator() {
           if (comparator == null) copy = new MinPQ<Key>(size());
           else                    copy = new MinPQ<Key>(size(), comparator);
           for (int i = 1; i <= n; i++)
               copy.insert(pq[i]);
       }

       public boolean hasNext()  { return !copy.isEmpty();                     }
       public void remove()      { throw new UnsupportedOperationException();  }

       public Key next() {
           if (!hasNext()) throw new NoSuchElementException();
           return copy.delMin();
       }
   }

   /***************************************************************************
    * Main Test Client
    ***************************************************************************/
	public static void main (String[] args) {
		
		MinPQ<Mail> pq = new MinPQ<Mail>();
		
//		int numOB = 25;
		
//		for(int i = 0; i <= numOB; i++) {
//			Mail newMail = new Mail();
//			pq.insert(newMail);
//		}
		
		Mail[] newMail = { //change to randomly generating the objects
			new Mail(DeliveryType.AIR, "YTWEW"),
			new Mail(DeliveryType.GROUND, "ASDFF"),
			new Mail(DeliveryType.AIR, "ASDFG"),
			new Mail(DeliveryType.TWO_DAY, "UIOPY"),
			new Mail(DeliveryType.ONE_DAY, "ASDFG"),
			new Mail(DeliveryType.GROUND, "ZXCVB"),
			new Mail(DeliveryType.GROUND, "AAABB"),
			new Mail(DeliveryType.AIR, "IUYTR"),
			new Mail(DeliveryType.TWO_DAY, "SKDIU"),
			new Mail(DeliveryType.PRIORITY, "PQOWI"),
	};
		 
		 for(Mail n : newMail) {
			 pq.insert(n);
		 }
		 
			StdOut.println("Elements in the priority queue:");
			for (Mail p : pq) {
				StdOut.println(p);
			}
			StdOut.println();
			
			StdOut.println("Remove elements one by one from the priority queue:");
	        while (!pq.isEmpty()) {
	        	StdOut.println(pq.delMin() + "- removed");
	        }
	        StdOut.println();
		}
}
