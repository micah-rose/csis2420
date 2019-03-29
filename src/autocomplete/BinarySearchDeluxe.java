package autocomplete;

import java.util.Comparator;

public class BinarySearchDeluxe {

	// Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) {
			throw new IllegalArgumentException("ERROR! Arguments can't be null.");
		}
    	int low = 0;
    	int hi = a.length - 1;
    	if (comparator.compare(a[0], key) == 0) {
			return 0;
		}
    	while (low <= hi) {
    		int mid = low + (hi - low) / 2;
    		if (comparator.compare(key, a[mid]) < 0) {
				hi = mid - 1;
			}
    		else if (comparator.compare(key, a[mid]) > 0) {
				low = mid + 1;
			}
    		else if (comparator.compare(a[mid - 1], a[mid]) == 0) {
				hi = mid - 1;
			}
    		else {
				return mid;
			}
    	}
		return -1;
    }

    // Return the index of the last key in a[] that equals the serach key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) {
			throw new IllegalArgumentException("ERROR! Arguments cannot be null.");
		}
    	int low = 0;
    	int hi = a.length - 1;
    	if (comparator.compare(a[hi], key) == 0) {
			return hi;
		}
    	while (low <= hi) {
    		int mid = low + (hi - low) / 2;

    		if (comparator.compare(key, a[mid]) < 0) {
				hi = mid - 1;
			}
    		else if (comparator.compare(key, a[mid]) > 0) {
				low = mid + 1;
			}
    		else if (comparator.compare(a[mid + 1], a[mid]) == 0) {
				low = mid + 1;
			}
    		else return mid;
    	}
		return -1;
    }
}

