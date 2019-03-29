package randomized_queues_and_deques;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

	public static void main(String[] args) {
//***************Get the argument - Subset only runs in the command line ****************************************/
		int count = Integer.parseInt(args[0]); 
		
		//Create a queue and enqueue stuff
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		for(String s : StdIn.readLine().split(" "))
			queue.enqueue(s);
		
		//Print out X elements, where X is the parameter passed
		for(int i = 0; i < count && !queue.isEmpty(); i++)
			StdOut.println(queue.dequeue());
	}
}