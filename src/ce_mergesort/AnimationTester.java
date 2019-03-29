package ce_mergesort;

import java.util.Comparator;

public class AnimationTester {
/********************************** A test method ******************************************/
	public static void main(String[] args) {
		
		/******List given by example code******/
		//Integer[] values = new Integer[] {1,2,7,3,5};
		Integer[] values = new Integer[]{ 87, 57, 370, 110, 90, 610, 02, 710, 140, 203, 150 };
		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer d1, Integer d2) {
				return d1.compareTo(d2);
			}
		};
		
		MergeSorter.sort(values, comp);
		for(int i = 0; i < values.length; i++) {
			System.out.print(values[i] + " ");
		}
	}
}

class MergeSorter{
/***************************** The method for sorting the numbers ******************************/
	public static <E> void sort(E[] a, Comparator<? super E> comp) {
		mergeSort(a, 0, a.length-1, comp);
	}
	
	private static <E> void mergeSort(E[] a, int from, int to, Comparator<? super E> comp) {
		if(from == to)
			return;
		int mid = (from+to)/2;
		
		mergeSort(a, from, mid, comp);
		mergeSort(a, mid+1, to, comp);
		merge(a, from, mid, to, comp);		
	}

/***************************** Merge two sorted lists ****************************************/
	@SuppressWarnings("unchecked")
	private static <E> void merge(E[] a, int from, int mid, int to, Comparator<? super E> comp) {
		int n = to-from+1;
		Object[] values = new Object[n];
		
		int fromValue = from;
		int middleValue = mid+1;
		int index = 0;
		
		while (fromValue <= mid && middleValue <= to) {
			if (comp.compare(a[fromValue], a[middleValue]) < 0) {
				values[index] = a[fromValue];
				fromValue++;
			} else {
				values[index] = a[middleValue];
				middleValue++;
			}
			index++;
		}
		
		while (fromValue <= mid) {
			values[index] = a[fromValue];
			fromValue++;
			index++;
		}
		
		while (middleValue <= to) {
			values[index] = a[middleValue];
			middleValue++;
			index++;
		}
		
		for (index = 0; index < n; index++)
			a[from + index] = (E) values[index];
	}
}
