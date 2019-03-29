package ce_hash_tables;


import java.util.LinkedList;
import ce_mymap_hash.MyMap;

public class MyHashMap<K, V> implements MyMap<K, V> {

  // Define the default hash table size. Must be a power of 2
  private static int DEFAULT_INITIAL_CAPACITY = 16;

  
  // Define the maximum hash table size. 1 << 30 is same as 2^30
  private static int MAXIMUM_CAPACITY = 1 << 30; 

  
  // Current hash table capacity. Capacity is a power of 2
  private int capacity;
 

  // Define default load factor
  private static float DEFAULT_LOAD_FACTOR = 0.75f; 


  // Specify a load factor used in the hash table
  private float loadFactor; 

  
  // It is loadFactor * capacity, updated when capacity increases
  private int threshold; 
 

  // The number of entries in the map
  private int size = 0; 

 
  // Hash table is an array with each cell a linked list
  private LinkedList<MyMap.Entry<K,V>>[] table;


  /** Construct a map with the default capacity and load factor */
  public MyHashMap() {  

    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);    

  }

  
  /** Construct a map with the specified initial capacity and 
   * default load factor */
  public MyHashMap(int initialCapacity) { 

    this(initialCapacity, DEFAULT_LOAD_FACTOR);    
  }

  
  /** Construct a map with the specified initial capacity 
   * and load factor */
  @SuppressWarnings("unchecked")
public MyHashMap(int initialCapacity, float loadFactor) { 

    if (initialCapacity > MAXIMUM_CAPACITY) {
    	this.capacity = MAXIMUM_CAPACITY;
    } else { this.capacity = trimToPowerOf2(initialCapacity); }

    this.loadFactor = loadFactor;    
    threshold = (int)(capacity * loadFactor);
    table = new LinkedList[DEFAULT_INITIAL_CAPACITY];
  }

  
  /** Remove all of the entries from this map */ 
  public void clear() {

    size = 0;
    removeEntries();

  }


  /** Return true if the specified key is in the map */
  public boolean containsKey(K key) {

    int bucketIndex = h(key.hashCode());

    if (table[bucketIndex] != null) {

      LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 

      for (Entry<K, V> entry: bucket)

        if (entry.getKey().equals(key)) { return true; }
    }

    	return false;
  }

  
  /** Returns true if this map contains the specified value */ 
  public boolean containsValue(V value) {

    for (int i = 0; i < capacity; i++) {

      if (table[i] != null) {

        LinkedList<Entry<K, V>> bucket = table[i]; 

        for (Entry<K, V> entry: bucket)

          if (entry.getValue().equals(value)) 

            return true;
      }
    }

    return false;
  }

  
  /** Returns a set of entries in the map */
  public java.util.Set<MyMap.Entry<K,V>> entrySet() {

    java.util.Set<MyMap.Entry<K, V>> set = 

      new java.util.HashSet<MyMap.Entry<K, V>>();

    
    for (int i = 0; i < capacity; i++) {

      if (table[i] != null) {

        LinkedList<Entry<K, V>> bucket = table[i]; 

        for (Entry<K, V> entry: bucket)

          set.add(entry); 

      }
    }

    return set;
  }


  /** Returns the first value that matches the specified key */
  public V get(K key) {

    int bucketIndex = h(key.hashCode());

    if (table[bucketIndex] != null) {

      LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 

      for (Entry<K, V> entry: bucket)

        if (entry.getKey().equals(key)) 

          return entry.getValue();
    }

    return null;
  }

  
  /** Returns all values for the specified key in this map */
  public java.util.Set<V> getAll(K key) {

    java.util.Set<V> set = new java.util.HashSet<V>();

    int bucketIndex = h(key.hashCode());

    if (table[bucketIndex] != null) {

      LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 

      for (Entry<K, V> entry: bucket)

        if (entry.getKey().equals(key)) 

          set.add(entry.getValue());
    }

    return set;
  }


  /** Returns true if this map contains no entries */
  public boolean isEmpty() {

    return size == 0;

  }  

  
  /** Returns a set consisting of the keys in this map */
  public java.util.Set<K> keySet() {

    java.util.Set<K> set = new java.util.HashSet<K>();

    for (int i = 0; i < capacity; i++) {

      if (table[i] != null) {

        LinkedList<Entry<K, V>> bucket = table[i]; 

        for (Entry<K, V> entry: bucket)

          set.add(entry.getKey()); 
      }
    }

    return set;
  }

      
  /** Add an entry (key, value) into the map */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public V put(K key, V value) {

    if (size > threshold) {

      if (capacity == MAXIMUM_CAPACITY)

        throw new RuntimeException("Exceeding maximum capacity");

      capacity <<= 1;

      // Update threshold
      threshold = (int)(capacity * loadFactor);

      rehash();
    }

    int bucketIndex = h(key.hashCode());

    
    // Create a linked list for the bucket if it is not created
    if (table[bucketIndex] == null) {

      table[bucketIndex] = new LinkedList();

    }

    // Add an entry (key, value) to hashTable[index]
    table[bucketIndex].add(new MyMap.Entry(key, value));

    size++; // Increase size
    
    return value;  
  } 


  /** Remove the entries for the specified key */
  public void remove(K key) {

    int bucketIndex = h(key.hashCode());

    // Create a linked list for the bucket if it is not created
    if (table[bucketIndex] != null) {

      LinkedList<Entry<K, V>> bucket = table[bucketIndex]; 

      for (Entry<K, V> entry: bucket)

        if (entry.getKey().equals(key)) 

          bucket.remove(entry);
    }

    size--; // Decrease size
  }

  
  /** Returns the number of mappings in this map */
  public int size() {

    return size;
  }

  
  /** Return a set consisting of the values in this map */
  public java.util.Set<V> values() {

    java.util.Set<V> set = new java.util.HashSet<V>();

    for (int i = 0; i < capacity; i++) {

      if (table[i] != null) {

        LinkedList<Entry<K, V>> bucket = table[i]; 

        for (Entry<K, V> entry: bucket)

          set.add(entry.getValue()); 
      }
    }

    return set;
  }

  
  /** Hash function */
  private int h(int hashCode) {

    return supplementalHash(hashCode) & (capacity - 1);

  }

  
  /** Ensure the hashing is evenly distributed */
  private static int supplementalHash(int h) {

    h ^= (h >>> 20) ^ (h >>> 12);

    return h ^ (h >>> 7) ^ (h >>> 4);
  }


  /** Return a power of 2 for initialCapacity */
  private int trimToPowerOf2(int initialCapacity) {

    int capacity = 1;

    while (capacity < initialCapacity) {

      capacity <<= 1;
    }  

    return capacity;
  }

  
  /** Remove all entries from each bucket */
  private void removeEntries() {

    for (int i = 0; i < capacity; i++) {

      if (table[i] != null) {

        table[i].clear();

      }
    }
  }

  
  /** Rehash the map */
  private void rehash() {

    java.util.Set<Entry<K, V>> set = entrySet();

    for (Entry<K, V> entry: set) {

      put(entry.getKey(), entry.getValue());
    }
  }


  /** Return a string representation for this map */
  public String toString() {

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < capacity; i++) {

      if (table[i] != null) 

        builder.append(table[i].toString());
    }

    return builder.toString();
  }
}
