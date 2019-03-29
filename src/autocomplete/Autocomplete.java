package autocomplete;

import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Autocomplete {
	public Term[] queries;
	
	// Initialize the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {
		if (terms == null) {
			throw new java.lang.NullPointerException();
		}
		this.queries = terms;
		Quick3way.sort(queries);
	}
	
	// Return all terms that start with the given prefix, in descending order of weight.
	public Term[] allMatches(String prefix) {
		if (prefix == null) {
			throw new NullPointerException();
		}
		int firstIndex = BinarySearchDeluxe.firstIndexOf(queries, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(queries, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		if (firstIndex == -1 || lastIndex == -1) {
			throw new java.lang.NullPointerException();
		}
		Term[] matches = new Term[lastIndex - firstIndex + 1];
		
		for (int i = 0; i < matches.length; i++) {
			matches[i] = queries[firstIndex++];
		}
		Arrays.sort(matches, Term.byReverseOrder()); //made changes here - may not work
		return matches;
	}
	
	// Return the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {
		if (prefix == null) {
			throw new NullPointerException();
		}
		int firstIndex = BinarySearchDeluxe.firstIndexOf(queries, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int lastIndex = BinarySearchDeluxe.lastIndexOf(queries, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		return 1 + lastIndex - firstIndex;
	}
	
	// The main method.
	public static void main(String[] args) {

	    // read in the terms from a file
	    String filename = args[0];
		//String filename = "src/autocomplete/cities.txt";
	    In in = new In(filename);
	    int N = in.readInt();
	    Term[] terms = new Term[N];
	    for (int i = 0; i < N; i++) {
	        double weight = in.readDouble();       // read the next weight
	        in.readChar();                         // scan past the tab
	        String query = in.readLine();          // read the next query
	        terms[i] = new Term(query, weight);    // construct the term
	    }

	    // read in queries from standard input and print out the top k matching terms
	    int k = Integer.parseInt(args[1]);
	    Autocomplete autocomplete = new Autocomplete(terms);
	    while (StdIn.hasNextLine()) {
	        String prefix = StdIn.readLine();
	        Term[] results = autocomplete.allMatches(prefix);
	        for (int i = 0; i < Math.min(k, results.length); i++)
	            StdOut.println(results[i]);
	    }
	}
}
