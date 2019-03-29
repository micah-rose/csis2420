package autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {
	private String query;
	private double weight;

	// Initialize a term with the given query string and weight.
	public Term(String query, double weight) {
		if (query == null) {
			throw new NullPointerException("ERROR! Query can't be null.");
		}
		if (weight < 0) {
			throw new IllegalArgumentException("ERROR! Weight can't be less than 0.");
		}
		this.query = query;
		this.weight = weight;
	}

	// Compare the terms in descending order by weight.
	public static Comparator<Term> byReverseOrder() {
		return new ReverseOrderWeightHelper();
	}

	// Compare the terms in lexicographic order but using only the first r characters of each query.
	public static Comparator<Term> byPrefixOrder(int r) {
		if (r < 0) {
			throw new IllegalArgumentException("ERROR! r must not be less than 0.");
		}
		return new PrefixOrderQueryHelper(r);
	}

	// Compare the terms in lexicographic order by query.
	@Override
	public int compareTo(Term that) {
		return this.query.compareTo(that.query);
	}

	// Return a string representation of the term in the following format: the weight, followed by a tab, followed by the query.
	@Override
	public String toString() {
		return weight + "\t" + query;
	}

	// Helper funciton for reverseorderweight
	private static class ReverseOrderWeightHelper implements Comparator<Term> {
		@Override
		public int compare(Term a, Term b) {
			if (a.weight == b.weight) {
				return 0;
			}
			if (a.weight > b.weight) {
				return -1;
			}
			return 1;
		}
	}

	// Helper funciton for prefixorderquery
	private static class PrefixOrderQueryHelper implements Comparator<Term> {
		private int r;
		private PrefixOrderQueryHelper(int r) {
			this.r = r;
		}

		@Override 
		public int compare(Term a, Term b) {
			String prefix;
			String other;
			
			if (a.query.length() < r) {
				prefix = a.query;
			}
			else {
				prefix = a.query.substring(0, r);
			}
			
			if (b.query.length() < r) {
				other = b.query;
			}
			else {
				other = b.query.substring(0, r);
			}
			return prefix.compareTo(other);
		}
	}
}
