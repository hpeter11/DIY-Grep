package sol;

import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * A class that implements hash tables using chaining.
 */
public class Chaining<K, V> implements IDictionary<K, V> {

    @SuppressWarnings("unchecked")
    public LinkedList<KVPair<K,V>>[] data;
    public int size;

    /**
     * A class that represents key-value pairs.
     */
    private static class KVPair<K, V> {
        public K key;
        public V value;

        /**
         * @param key - key in the pair
         * @param value - value in the pair
         */
        public KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     *
     * Constructor for chaining hashtable objects that creates a new array of
     * input size made up of linked lists of KV pairs
     *
     * @param size
     */

    public Chaining(int size) {
        this.size = size;
        this.data = (LinkedList<KVPair<K,V>>[]) new LinkedList[size];
        for (int i = 0; i < this.size; i++) {
            this.data[i] = new LinkedList<>();
        }
    }

    /**
     * Finds a key in the hashtable.
     *
     * @param key - the key to look up
     *
     * @return the key-value pair found
     * @throws KeyNotFoundException if the key is not found
     */
    private KVPair<K, V> findKVPair(K key) throws KeyNotFoundException {
        int hash = Math.abs(key.hashCode() % this.size);
        for (KVPair<K, V> x : this.data[hash]) {
            if (x.key.equals(key)) {
                return x;
            }
        }
        throw new KeyNotFoundException("key not found");
    }

    /**
     *
     * Returns the value of a KV pair (if it exists) within a chaining object,
     * otherwise throwing an exception
     *
     * @param key - the key to look up
     *
     * @return
     * @throws KeyNotFoundException
     */
    @Override
    public V lookup(K key) throws KeyNotFoundException {
        KVPair<K, V> pair = findKVPair(key);

        return pair.value;
    }

    /**
     *
     * Given a key and a new value, updates the pair if a matching key exists,
     * returning the old value and otherwise throwing an exception.
     *
     * @param key   - the key whose value is to be updated
     * @param value - the updated value
     *
     * @return
     * @throws KeyNotFoundException
     */

    @Override
    public V update(K key, V value) throws KeyNotFoundException {
        KVPair<K, V> pair = findKVPair(key);

        V oldValue = pair.value;
        pair.value = value;

        return oldValue;
    }

    /**
     *
     * Based on an input KV pair, attempts to locate it in the chaining object.
     * If the pair is not found, it is added. If a matching key exists, an
     * exception is thrown.
     *
     * @param key   - the key to insert into the table
     * @param value - the value to associate with the key
     *
     * @throws KeyAlreadyExistsException
     */
    @Override
    public void insert(K key, V value) throws KeyAlreadyExistsException {
        try {
            this.findKVPair(key);
            throw new KeyAlreadyExistsException("Key Already Exists");
        }
        catch (KeyNotFoundException e) {
            int hash = Math.abs(key.hashCode() % this.size);
            KVPair<K, V> x = new KVPair<K, V>(key, value);
            this.data[hash].add(x);
        }
    }

    /**
     *
     * Uses hash code to find the correct array slot in the list. Then,
     * uses built-in linked list remove feature to pull the kv pair from the
     * list, after which it returns the old value
     *
     * @param key - key whose key-value pair is to be deleted
     *
     * @return
     * @throws KeyNotFoundException
     */
    @Override
    public V delete(K key) throws KeyNotFoundException {
            KVPair<K, V> kv = this.findKVPair(key);
            int hash = Math.abs(key.hashCode() % this.size);
            this.data[hash].remove(kv);
            return kv.value;
    }

    // Feel free to write additional helper methods :)

    /**
     *
     * This method compares two chaining objects one way - it looks at the
     * elements of
     *
     * @param ht
     * @return
     */

    public boolean compareHT(Chaining<K, V> ht) {
        for (int i = 0; i < this.size; i++) {
            for (KVPair<K, V> kv : this.data[i]) {
                try {
                    ht.findKVPair(kv.key);
                } catch (KeyNotFoundException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * Given an object (cast to a chaining object), equals makes sure both
     * this and the input have all of the same matching KV values
     *
     * @param ht
     * @return
     */

    @Override
    public boolean equals(Object ht) { //need to check values as well
        Chaining<K, V> htChainingCast = (Chaining<K, V>) ht;
        if (htChainingCast.compareHT(this) && this.compareHT(htChainingCast)) {
            return true;
        }
        return false;
    }

    /**
     *
     * Iterates through each slot in an array (and the lists in each) and adds
     * the string representation of each KV pair, returning the final result.
     *
     * @return
     */

    @Override
    public String toString() {
        String s = "";
        int count = 1;
        for (LinkedList<KVPair<K, V>> l : this.data) { //change to a for each
            for (KVPair<K, V> x : l) { //lookup, equals
                s = s + ("key" + count + ": " + x.key +
                ", value" + count + ": " + x.value + ", ");
                count++;
            }
        }
        return "[" + s + "]";
    }

}
