package midterm2;

import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;

public class PerformanceTest {
	private static final Random rand = new Random();

	public static void main(String[] args) {
		StdOut.printf("%-12s  %-12s %-12s %n", "number of", "time to", "time to");
		StdOut.printf("%-12s  %-12s %-12s %n", "integers", "mergesort", "mergesort slow");
		StdOut.println("------------------------------------------");

		for (int i = 1000; i <= 128000; i *= 2) {
			Integer[] numbers = getRandomIntArray(i);
			
			Stopwatch stopwatch = new Stopwatch();
			Merge.sort(numbers);
			double timeToSort = stopwatch.elapsedTime();
			
			Stopwatch stopwatch2 = new Stopwatch();
			MergeSlow.sort(numbers);
			double timeToSort2 = stopwatch2.elapsedTime();
			
			StdOut.printf("%-12d  %-12.3f %-12.3f %n", i, timeToSort, timeToSort2);
	
		}
		
		StdOut.println("T H E   E N D");
		StdOut.println();
		StdOut.println("C O M M E N T:");
		StdOut.println("The reason the MergeSlow time is significantly different from");
		StdOut.println("the Merge time is because when you create an auxiliary array");
		StdOut.println("inside a recursive method it leads to extensive cost due to the");
		StdOut.println("extra array creation and results in poor performace.");
	}

	private static Integer[] getRandomIntArray(int size) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(size);
		}
		return array;
	}
}