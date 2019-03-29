package ce_arraylist_vs_linkedlist;

/**
 * WordList is a singly linked list of Strings.
 * It is designed to demonstrate how linked structures work.
 * 
 * @author ..........
 */
public class WordList {
	private Node head;
	private Node tail;
	private int n; // number of words in the list

	/**
	 * Node of LinkedList that stores the item and a
	 * single reference to the next node.
	 */
	private class Node {
		private String item;
		private Node next;
	}
	
	/** 
	 * Adds a node containing the new item at the
	 * end of the list.
	 * 
	 * @param newItem
	 */
	public void append(String newItem) {
		// create a new node based on the word provided by the user
		Node newNode = new Node();
		newNode.item = newItem;
		
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		n++;
	}
	
	/** 
	 * Adds a node containing the new item at the
	 * front of the list.
	 * 
	 * @param newItem
	 */
	public void prepend(String newItem) {		
		// TODO 2
		Node newNode = new Node();
		newNode.item = newItem;
		
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
	}
	
	/** 
	 * Returns the index of the first occurrence of the specified item.
	 * If the specified item in not part of the list
	 * the method indexOf returns -1
	 * 
	 * @param item
	 * @return index of the first occurrence of the item; -1 if the word was not found.
	 */
	public int indexOf(String item) {	
		Node index = head;
		int trackIndex = 0;
		
		while(index != null) {
			
			if(index.item.equals(item)) {
				return trackIndex;
			}
			index = index.next;
			trackIndex++;
		}
	    return -1; 
	    	// TODO 3
	}
	
	/** 
	 * Checks whether the list contains the given item.
	 * 
	 * @param item
	 * @return true if the item is contained in the list; false otherwise.
	 */
	public boolean contains(String item) {	
		Node index = head;
		
		while(index != null) {
			
			if(index.item.equals(item)) {
				return true;
			}
			index = index.next;

		}
	    return false; 
	}
	
	/**
	 * Returns the number of elements in the list
	 * @return the number of elements
	 */
	public int size() {
		return n;
	}
	
	/** 
	 * Determines whether the list is empty or not.
	 * @return true if there are no elements in the list.
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		
		while(current != null) {
			sb.append(current.item).append(" ");
			current = current.next;
		}
		
		return sb.toString();
	}
	
	/* * * * * * * * Test Client * * * * * * */
	public static void main(String[] args) {
		WordList list = new WordList();
		System.out.println("size: " + list.size());
		
		// TODO 1 print The list is empty. or The list is not empty.
		//      use a ternary operator to check whether the list is empty 
		
		System.out.println(list.isEmpty() ? "The list is empty" : "The list is not empty");
		 
		list.append("ant");
		list.append("bat");
		list.append("cow");
		list.append("dog");
		
		list.prepend("cat");
		System.out.println(list.indexOf("dog"));
		System.out.println(list.contains("ant"));
		System.out.println(list.isEmpty() ? "The list is empty" : "The list is not empty");
		
		System.out.println("list: " + list);
		


	}

}

