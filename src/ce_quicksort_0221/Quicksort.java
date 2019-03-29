package ce_quicksort_0221;

import java.util.Arrays;
import java.util.Comparator;

public class Quicksort {
	
	/** Generic quick sort using Comparable */
	public static <E extends Comparable<E>> void quickSort(E[] list) {
		
		quickSort(list, 0, list.length - 1);
	}

	public static <E extends Comparable<E>> 
			void quickSort(E[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	public static <E extends Comparable<E>> 
			int partition(E[] list, int first, int last) {
		
		E pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low].compareTo(pivot) <= 0)
				low++;

			// Search backward from right
			while (low <= high && list[high].compareTo(pivot) > 0)
				high--;

			// Swap two elements in the list
			if (high > low) {
				E temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high].compareTo(pivot) >= 0)
			high--;

		// Swap pivot with list[high]
		if (pivot.compareTo(list[high]) > 0) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		}
		else {
			return first;
		}
	}

	public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
		
		quickSort(list, 0, list.length - 1, comparator);
	}

	public static <E> void quickSort(
			E[] list, int first, int last, Comparator<? super E> comparator) {
		
		if (last > first) {
			int pivotIndex = partition(list, first, last, comparator);
			quickSort(list, first, pivotIndex - 1, comparator);
			quickSort(list, pivotIndex + 1, last, comparator);
		}
	}

	/** Partition the array list[first.. last] */
	public static <E> int partition(
			E[] list, int first, int last, Comparator<? super E> comparator) {
		
		E pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && comparator.compare(list[low], pivot) <= 0)
				low++;

			// Search backward from right
			while (low <= high && comparator.compare(list[high], pivot) > 0)
				high--;

			// Swap two elements in the list
			if (high > low) {
				E temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && comparator.compare(list[high], pivot) >= 0)
			high--;

		// Swap pivot with list[high]
		if (comparator.compare(pivot, list[high]) > 0) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		}
		else {
			return first;
		}
	}
	

	/** A test method */
	public static void main(String[] args) {
		
//		Collections.sort(list, new Comparator<String>() {
//		    @Override
//		    public int compare(String s1, String s2) {
//		        return s1.compareToIgnoreCase(s2);
//		    }
//		});
		
		// Create an Integer array
		Integer[] intArray = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};

		// Create a String array
		String[] stringArray = {"abm", "Bad", "ABC", "nice", "abc", "Anf", "Good"};

		// Sort the arrays
		quickSort(intArray);
		quickSort(stringArray);
		Arrays.sort(stringArray, String.CASE_INSENSITIVE_ORDER);

		// Display the sorted arrays
		printList(intArray);
		printList(stringArray);

	}

	/** Print an array elements */
	public static void printList(Object[] list) {
		
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}

}