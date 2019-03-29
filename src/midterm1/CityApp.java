package midterm1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class CityApp {
	
	static Stack<City> cityStack = new Stack<City>();
	
	public static void main(String[] agrs) {
		City[] cities = {
				new City("Chicago", 2715000, 5498),
				new City("Denver", 634000, 1292),
				new City("New York", 8337000, 8683),
				new City("San Francisco", 826000, 1365),
				new City("Seattle", 635000, 2470)
		};
		
// Part 1
		System.out.println("...............");
		System.out.println("Part 1");
		System.out.println("...............");
		System.out.println("Cities: ");

		for(City c : cities) {
			System.out.println(c);
		}

		System.out.println();

		Arrays.sort(cities); 
		System.out.println("Cities sorted by population density: ");
		for(City c : cities) {
			System.out.println(c);
		}

// Part 2
		for (City c : cities) {
			cityStack.push(c);
		}

		System.out.println();

		reverse();

		System.out.println("...............");
		System.out.println("Part 2");
		System.out.println("...............");
		System.out.println("Cities: ");
		
		for(City c : cityStack) {
			System.out.println(c);
		}

		System.out.println();

		//Creates a new stack to move large cities into
		Stack<City> largeCity = new Stack<City>();
		
		System.out.println("Large cities listed in reverse order: ");
		
		for(int i = 0; i < 2; i++) {
			cityStack.pop();
		}
		
		City chic = cityStack.peek();
		largeCity.push(chic);
		reverse();
		City york = cityStack.peek();
		largeCity.push(york);
		
		for(int i = 0; i < 3; i++) {
			cityStack.pop();
		}
		
		for(City l : largeCity) {
			System.out.println(l);
		}		
	}	
	
	static void insert_at_bottom(City x){ 
  
        if(cityStack.isEmpty()) 
            cityStack.push(x); 
  
        else{ 
              
            City a = cityStack.peek(); 
            cityStack.pop(); 
            insert_at_bottom(x); 
  
            cityStack.push(a); 
        } 
    } 
      
    static void reverse(){
    	
        if(cityStack.size() > 0){ 
        	
            City x = cityStack.peek(); 
            cityStack.pop(); 
            
            reverse();              
            insert_at_bottom(x); 
        } 
    } 
}