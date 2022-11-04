package src;
/**
 * An interface for a very basic dictionary.
 *
 * @param <K> - the type of keys in this dictionary
 * @param <V> - the type of the value in this dictionary
 */
public interface IDictionary<K, V> {

  /**
   * Looks up a value in the dictionary, given its key.
   *
   * @param key - the key to look up
   *
   * @return the value associated with that key
   * @throws KeyNotFoundException if the key is not found
   */
  public V lookup(K key) throws KeyNotFoundException;

  /**
   * Updates the value associated with the given key.
   *
   * @param key   - the key whose value is to be updated
   * @param value - the updated value
   *
   * @return the old value that was associated with the key
   * @throws KeyNotFoundException if the key is not found
   */
  public V update(K key, V value) throws KeyNotFoundException;

  /**
   * Inserts a key-value pair into the dictionary.
   *
   * @param key   - the key to insert into the table
   * @param value - the value to associate with the key
   *
   * @throws KeyAlreadyExistsException if the key already exists
   */
  public void insert(K key, V value) throws KeyAlreadyExistsException;

  /**
   * Deletes a key-value pair from the dictionary.
   *
   * @param key - key whose key-value pair is to be deleted
   *
   * @return the value associated with that key
   * @throws KeyNotFoundException if the key is not found
   */
  public V delete(K key) throws KeyNotFoundException;



}
