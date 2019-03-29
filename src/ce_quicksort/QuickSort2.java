package ce_quicksort;

public class QuickSort2 {
	
	public int partion(int arr[], int left, int right) {
		
		int pivot = arr[left];
		int i = left;
		
		for(int j = left+1; j<=right; j++) {
			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		
		swap(arr, i, left);
		return i;
	}
	
	public void quickSort(int arr[], int low, int high) {
		
		if(low<high) {
			int p = partion(arr, low, high);
			quickSort(arr, low, p-1);
			quickSort(arr, p+1, high);
		}
	}
	
	public void swap(int arr[], int i, int j) {
		
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String args[]) {
		
		int arr[] = {11, 8, 5, 4, 3, 2, 1, 9};
		QuickSort2 obj = new QuickSort2();
		
		System.out.print("Elements before sorting: ");
		for(int i=0; i< arr.length; i++) {
			System.out.print(arr[i] +" ");
		}
		
		obj.quickSort(arr, 0, 7);
		System.out.print("\nElements after sorting using QuickSort: ");
		for(int i=0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
