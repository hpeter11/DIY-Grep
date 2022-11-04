package src;

/**
 * A class to represent an Exception that is thrown when a key 
 * is not found in a hash table
 */
 @SuppressWarnings("serial")
 public class KeyNotFoundException extends Exception {

 	/**
 	* Basic constructor for KeyNotFoundException
 	*/
    public KeyNotFoundException() {
      super("Key not found!");
    }

    /**
     * An additionaly constructor for the KeyNotFoundException 
     * that uses the key as input to populate the error message
     *
     * @param key - the key not found
     */
    public <K> KeyNotFoundException(K key) {
      super(key + " not found!");
    }

}