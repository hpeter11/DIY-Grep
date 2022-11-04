  package src;
  
  /**
   * A class that represents exceptions for when a key intended to be inserted
   * into a hash table is already in the hash table.
   */
  @SuppressWarnings("serial")
  public class KeyAlreadyExistsException extends Exception {

    /**
    * A basic constructor for the KeyAlreadyExistsException
    */
    public KeyAlreadyExistsException() {
      super("Key already exists.");
    }

    /**
     * Another contructor for the KeyAlreadyExistsException
     * that uses the key in the error reporting
     *
     * @param key - the key that already exists
     */
    public <K> KeyAlreadyExistsException(K key) {
      super(key + " already exists.");
    }

}