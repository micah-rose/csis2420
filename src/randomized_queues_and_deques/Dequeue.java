package randomized_queues_and_deques;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<Item> implements Iterable<Item> {
	private int N;
	private Node first;
	private Node last;
	
	// Helper dequeue class. Contains the current item, the next item, and the previous item.
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}
	
	/**
	 * Constructs an empty deque.
	 */
	public Dequeue() {
		N = 0;
		first = null;
		last = null;
	}
	
	/**
	 * Checks if the deque is empty
	 * @return returns true if the deque is empty, false if it's not.
	 */
    public boolean isEmpty() {
		return N == 0;
    }
    
    /**
     * Returns the number of items in the deque
     * @return returns the number of items in the deque
     */
    public int size() {
		return N;
    }
    
    /**
     * Insert an item at the front of the deque
     * @param item to put in the front of the deque
     * @throws java.lang.NullPointerException if the item to add is null
     */
    public void addFirst(Item item) {
    	if (item == null) throw new NullPointerException("item can't be null");
    	Node oldFirst = first;
    	first = new Node();
    	first.item = item;
    	first.previous = null;
    	if (isEmpty()) {
    		last = first;
    		first.next = null;
    		last.next = null;
    		last.previous = null;
    	} else {
    		oldFirst.previous = first;
    		first.next = oldFirst;
    	}
    	N++;
    }
    
    /**
     * Insert an item at the end of the deque
     * @param item to put in the back of the deque
     * @throws java.lang.NullPointerException if the item to add is null
     */
    public void addLast(Item item) {
    	if (item == null) throw new NullPointerException("item can't be null");
    	Node oldLast = last;
    	last = new Node();
    	last.item = item;
    	last.next = null;
    	if (isEmpty()) first = last;
    	else {
    		oldLast.next = last;
    		last.previous = oldLast;
    	}
    	N++;
    }
    
    /**
     * Removes and returns the item at the front
     * @return item that has been deleted from the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public Item removeFirst() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item returnItem = first.item;
    	first = first.next;
    	if (first != null) first.previous = null;
    	N--;
    	if (isEmpty()) last = null;
    	return returnItem;
    }
    
    /**
     * Removes and returns the item at the end
     * @return item that has been deleted from the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public Item removeLast() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	Item returnItem = last.item;
    	last = last.previous;
    	if (last != null) last.next = null;
    	N--;
    	if (isEmpty()) first = null;
    	return returnItem;
    }
	
	/**
	 * Return an iterator over items in order from front to end
	 * @Return an iterator over items in order from front to end
	 */
    @Override
    public Iterator<Item> iterator() {
		return new DequeueIterator();
	}
	
	// Iterator. Doesn't implement remove() or forEachRemaining().
	private class DequeueIterator implements Iterator<Item> {
		private Node current = first;
		
		/**
		 * @throws java.lang.UnsupportedOperationException
		 */
		@Override
		public void remove() { throw new UnsupportedOperationException(); }

		/**
		 * Returns whether there is another element in the iterator
		 * @return whether there is another element in the iterator
		 */
		@Override
		public boolean hasNext() { return current != null; }

		/**
		 * Returns the next item in the iterator
		 * @return the next item in the iterator
		 */
		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
    public static void main(String[] args) {
    	Dequeue<String> myDequeue = new Dequeue<>();
    	myDequeue.addFirst("Two");
    	myDequeue.addFirst("Three");
    	myDequeue.addFirst("Four");
    	myDequeue.addLast("One");
    	myDequeue.addLast("Zero");
    	
    	for (String text : myDequeue) System.out.print(text + " ");
    	
    	System.out.println("\n" + myDequeue.removeFirst());
    	System.out.println(myDequeue.removeFirst());
    	System.out.println(myDequeue.removeLast());
    	System.out.println(myDequeue.removeFirst());
    	System.out.println(myDequeue.removeFirst() + "\n");
    	
    	myDequeue.addFirst("Two");
    	myDequeue.addFirst("Three");
    	myDequeue.addFirst("Four");
    	myDequeue.addLast("One");
    	myDequeue.addLast("Zero");
    	
    	for (String text : myDequeue) {
    		text += " potato";
    		System.out.println(text);
    	}
    	
    	Iterator<String> iterator = myDequeue.iterator();
    	
    	System.out.println();
    	
    	System.out.println(iterator.next());
    	System.out.println(iterator.next());
    	System.out.println(iterator.next());
    	System.out.println(iterator.hasNext());
    	
    	System.out.println("\n" + myDequeue.removeLast());
    	System.out.println(myDequeue.removeLast());
    	System.out.println(myDequeue.removeFirst());
    	System.out.println(myDequeue.removeLast());
    	System.out.println(myDequeue.removeLast());
    	
    	System.out.println("\n" + myDequeue.size());
    }
}
