package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author kevin Johanson
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  //gets the value at index i
  public V getValueAt(int i) {
    return pairs[i].value;
  }

  //gets the key at index i
  public K getKeyAt(int i) {
    return pairs[i].key;
  }


  /**
   * Create a copy of this AssociativeArray.
   */
  
  public AssociativeArray<K, V> clone() {
	 
     AssociativeArray<K,V> clonedArr = new AssociativeArray<>();
     
     while (clonedArr.pairs.length < this.pairs.length) {
    	 clonedArr.expand();
    	 
     }
     
     for (int i =0; i < this.pairs.length; i++) {
    	 KVPair<K,V> tempPair =  this.pairs[i];
    	 						   // makes a deep clone
    	 if (!(tempPair == null)){ // if value is not not set the key and val to its the clone to its equiv in the original array
    	 	System.out.println(tempPair + "findout-------------" + i);
    		K tempKey =  tempPair.key;
    	 	V tempVal = tempPair.value;
    	 
    	 	KVPair<K,V> Kpair = new KVPair<>(tempKey,tempVal); 
    	 	clonedArr.pairs[i] = Kpair;
     	}else { 
     		clonedArr.pairs[i] = tempPair; 
     	} // else()
     }// for()
  		
        // clonedArr.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length);
         clonedArr.size = this.size; 

	  return clonedArr;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
	  int tempSize = 0;
	  String returnString = "{}";
	  if (this.size > 0) {
		   tempSize = this.size;
		  returnString = "{ ";
	  } // if ()


	  for (int i = 0; i < pairs.length; i++) { // iterates through the whole array
		  if (!(pairs[i] == null)) { // checks if there is a key at the position thats not null
			  String tempKey = (String) pairs[i].key.toString();
			  String tempValue = (String) pairs[i].value.toString();
			  if (tempSize != 1) {
				  returnString = returnString.concat(tempKey + ": " + tempValue + ", "); 	
				  tempSize--;
			  }else {
				  returnString = returnString.concat( tempKey + ": " + tempValue + " }");
			  } // else ()
			 // return returnString;
		  } // if()
	  } // for()
    return returnString; // "{ key0: value0, key1: value1, ... keyn: aluen }"
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value){
	 try {
	  if (hasKey(key)) {
		  this.pairs[find(key)].value = value;
		  return;
	  } else if (size == pairs.length) { // checks if assoc array is full
		  this.expand();
		  this.pairs[size] = new KVPair<K,V>(key,value); // sets the first null value in the new expanded array to the K V pair
		  size++;
		  return;
	  } else { // if assoc array isn't full and doesn't have the key
		  for (int i = 0; i < this.pairs.length; i++) { // iterates through the array
			  if (this.pairs[i] == null) {
				  
				  this.pairs[i] = new KVPair<>(key, value);

				  this.size++;
				  return;
			  } // if ()
		  } // for()
	  } // else
	
	 }  // try
	 catch (KeyNotFoundException e ) {
		 System.err.println("The Key doesn't exist");
	 } // catch
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {
    return this.pairs[find(key)].value;
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
	  for (int i = 0; i < this.pairs.length; i++) { // loops through all the key value value pairs
		  KVPair<K,V> curPair = this.pairs[i];
		 if (!(curPair == null)) {
		  if (curPair.key.equals(key)) {
			  return true;
		  } // if()
		 } // if()
	  } // for()
    return false; 
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key){
	  if (hasKey(key)) {
		  try {
			  this.pairs[find(key)] = null;
			  size--;
		  } //try
		  catch (KeyNotFoundException e) {
			  System.err.println("The Key doesn't Exist");
		  }
	  } // if()
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()
  
  
  /*
   *  gets all the keys in the array and returns it in a string array
   */
  public String[] getKeys() {
	  String[] keys = new String[this.size];
	  for (int i = 0; i < this.pairs.length; i++) {
		  if (!(pairs[i] == null)) {
		   keys[i] = pairs[i].key.toString(); 
      } //if
	  }// for()
	  
	  return keys;
  } // getKeys

  /*
   *  gets all the keys in the array and returns it in a string array
   */
  public String[] getValues() {
	  String[] values = new String[this.size];
	  for (int i = 0; i < this.pairs.length; i++) {
		  if (!(pairs[i] == null)) {
		   values[i] = pairs[i].value.toString(); 
      }// if
	  }// for()
	  
	  return values;
  } // getKeys
  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
	  for (int i = 0; i < this.pairs.length; i++) { // loops through all the key value value pairs
		  KVPair<K,V> curPair = pairs[i];
		  if (!(curPair == null)) {
			  if (curPair.key.equals(key)) {
				  return i; // returns index of the pair with the key
			  }	 // if() 
		  } // if()
	  } // for()	  
	  throw new KeyNotFoundException();   // if no key is found 
  } // find(K)

} // class AssociativeArray