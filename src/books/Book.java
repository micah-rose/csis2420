/**************************
* Name: Micah Lund
* Assignment: A00 - Book
***************************/

package books;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

public class Book implements Comparable<Book> {
	
	private String title;
	private String author;
	private int year;

	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public String getTitle( ) {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public int getYear() {
		return this.year;
	}
	
	@Override
	public String toString() {
		return this.title + " by " + this.author + " (" + this.year + ")";
	}
	
	public static List<Book> getList(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<Book> bookList = new ArrayList<>();
		
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			String[] parsedLine = line.split(","); 
			if (parsedLine.length != 3) {
					System.err.println("Error reading in \"" + line + "\"");
					continue;
			}
			int year = Integer.parseInt(parsedLine[2]);
			Book book = new Book(parsedLine[0], parsedLine[1], year);
			bookList.add(book);
		} 
		br.close();
		return bookList;
	} 
	
	@Override
	public int compareTo(Book o) {
		String string = this.toString();
		String other = o.toString();
		return string.compareTo(other);
	}
}