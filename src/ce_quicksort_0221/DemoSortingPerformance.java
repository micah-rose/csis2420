package ce_quicksort_0221;

//TODO - Still needs work in order to run properly.

import java.util.Arrays;
import java.util.Random;

public class DemoSortingPerformance {
	
	private static int[] getRandomNumberArray(int arraySize, int numberOfDigits) {
		Random rand = new Random();
		
	    int[] randArray = new int[arraySize];
	    for(int i=0; i<randArray.length; i++)
	    {
	    	randArray[i] = rand.nextInt(numberOfDigits);
	    }
	    
	    return randArray;		
	}
	
//	long startTime = System.nanoTime();
//	methodToTime();
//	long endTime = System.nanoTime();
//
//	long duration = (endTime - startTime); 
	
	public static void main(String[] args) {  
		
		int[] testArray = getRandomNumberArray(7, 10);
		
		System.out.println(Arrays.toString(testArray));
		
	}

}
