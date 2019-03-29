package ce_mymap_hash;

public class TestMyHashMap {
	
	  public static void main(String[] args) {
		    // Create a map
		    MyMap<String, Integer> map = new MyHashMap<>();
		    map.put("Smith", 30);
		    map.put("Anderson", 31);
		    map.put("Lewis", 29);
		    map.put("Cook", 29);
		    map.put("Smith", 65); // Add Smith with age 65 to map

		    System.out.println("Entries in map: " + map);

		    System.out.println("The age for " + "Lewis is " +
		      map.get("Lewis"));

		    System.out.println("Is Smith in the map? " + 
		      map.containsKey("Smith"));
		    System.out.println("Is age 33 in the map? " + 
		      map.containsValue(33));

		    map.remove("Smith"); // Remove Smith from map
		    System.out.println("Entries in map: " + map);

		    map.clear();
		    System.out.println("Entries in map: " + map);
	  }
}
