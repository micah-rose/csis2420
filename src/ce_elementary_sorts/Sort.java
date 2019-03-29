package ce_elementary_sorts;

public abstract class Sort<T> implements Comparable<T> {
	
	public Sort() {
		
	}

	//Function to sort array using insertion
	public static void sort(Comparable[] array)
    {
        Comparable temp;
        for(int i = 1; i < array.length; i++) // ar[i] is element to insert
        {
             temp = array[i];
             int j = 0;
             for(j = i; j > 0; j--)
                  if(temp.compareTo(array[j - 1]) < 0)
                       array[j] = array[j - 1];
                  else
                      break;
             array[j] = temp;
        }
   }
	
	//Function to sort array using selection
	void selectionSort(Comparable[] array)
    {
        Comparable temp;
        for(int i = 1; i < array.length; i++) // ar[i] is element to insert
        {
             temp = array[i];
             int j = 0;
             for(j = i; j > 0; j--)
                  if(temp.compareTo(array[j - 1]) < 0)
                       array[j] = array[j - 1];
                  else
                      break;
             array[j] = temp;
        }
   }
			
			//swap(arr[min_idx], arr[i]);

	//}

//	private void exch(Comparable[] array, int i, int min) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	private boolean less(Comparable comparable, Comparable comparable2) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public void swap(int i, int j) {
		
        int temp = i; 
        i = j; 
        j = temp;
		
	}
	
	public static void main(String[] args) {
		
		//Sort<T> sort = new Sort<T>();
		String[] sortArray = {"f","e","d","c","b","a"};
		
		sort(sortArray);
		System.out.println(sortArray);
		
		//selectionSort(sortArray, 6);
		
	}

}
