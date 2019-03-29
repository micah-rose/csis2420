/**************************
* Name: Micah Lund
* Assignment: A00 - Book
***************************/

package books;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class BookApp {
	public static void main(String[] args) {
		try {
			// initialize a Book list
			List<Book> parsedBooks = Book.getList("src/books/books.csv");
			
			// print the length of list
			int parsedBookSize = parsedBooks.size();
			System.out.printf("Number of books read in: %d\n", parsedBookSize);
			
			// sort the list in natural order.
			// print that list
			System.out.println("\nSorted book list:");
			Collections.sort(parsedBooks);
			BookApp.printList(parsedBooks);;
			
			// sort the books in reverse order
			// print that list
			System.out.println("\nReverse order:");
			Collections.sort(parsedBooks, Collections.reverseOrder());
			BookApp.printList(parsedBooks);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printList(List<Book> books) {
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i).toString());
		}
	}
}