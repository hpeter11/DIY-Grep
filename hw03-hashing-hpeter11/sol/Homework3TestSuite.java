package sol;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

public class Homework3TestSuite {

    // This example is here to show you how to input the filenames for your Grep
    // tests

    /**
     *
     * Example test that prints out grep lookup sets
     *
     */

    @Test
    public void testGrepExample() {
        Grep grep = new Grep("grep-test-files/ozymandias.txt");
        Set<Integer> result = grep.lookup("and");
        System.out.println(result);
    }

    /**
     *
     * Tests different edge cases of chaining.delete()
     *
     */

    @Test
    public void testDelete() {
        try {
            Chaining a = new Chaining<>(4);
            a.insert("2", "two");
            a.insert("3", "three");
            a.insert("4", "four");
            a.insert("5", "five");

            Chaining c = new Chaining<>(4);
            c.insert("1", "one");
            c.insert("2", "two");
            c.insert("3", "three");
            c.insert("4", "four");
            c.insert("5", "five");
            c.lookup("1");
            c.delete("1");
            Assert.assertTrue(a.equals(c));

            Chaining b = new Chaining<>(1);
            b.insert("bye", "then");
            b.delete("bye");
            Chaining d = new Chaining<>(1);
            Assert.assertTrue(b.equals(d));
            d.delete("bye");
            Assert.assertTrue(b.equals(d));
        }

        catch (KeyAlreadyExistsException e) {
            System.out.println("Key already exists");
            }
        catch (KeyNotFoundException e) {
            System.out.println("Key not found");
        }
    }

    /**
     *
     * Tests different edge cases of Chaining.insert() including
     * what happens after you insert and delete certain items
     *
     */

    @Test
    public void testInsert() {
        try {
            Chaining a = new Chaining<>(4);
            a.insert("2", "two");
            a.insert("3", "three");
            Assert.assertEquals(a.lookup("2"), "two");
            a.delete("2");
            Assert.assertEquals(a.lookup("3"), "three");
        }

        catch (KeyAlreadyExistsException e) {
            System.out.println("Key already exists");
        }
        catch (KeyNotFoundException e) {
            System.out.println("Key not found");
        }
    }

    /**
     *
     * Tests the equals method, checking that it works both ways and that
     * it functions with insert and delete
     *
     */

    @Test
    public void testEquals() {
        Chaining c1 = new Chaining<>(4);
        Chaining c2 = new Chaining<>(3);
        try {
            c1.insert("hey", "hi");
            c2.insert("hey", "hi");
            c1.insert("hello", "world");
            c2.insert("hello", "world");
            Assert.assertTrue(c1.equals(c2));
            c1.insert("uh", "oh");
            Assert.assertFalse(c1.equals(c2));
            Assert.assertFalse(c2.equals(c1));
            c1.delete("uh");
            Assert.assertTrue(c1.equals(c2));

        } catch (KeyAlreadyExistsException e) {
            System.out.println("Key already exists");
        } catch (KeyNotFoundException e) {
        System.out.println("Key already exists");
    }
    }

    /**
     *
     * Tests lookup to verify that it is functioning correctly and returning
     * the proper value pairs
     *
     */


    @Test
    public void testLookupChaining() {
        try {
            Chaining a = new Chaining<>(4);
            a.insert("2", "two");
            Assert.assertEquals(a.lookup("2"), "two");
            a.update("2", "three");
            Assert.assertEquals(a.lookup("2"), "three");

        }

        catch (KeyAlreadyExistsException e) {
            System.out.println("Key already exists");
        }
        catch (KeyNotFoundException e) {
            System.out.println("Key not found");
        }
    }

    /**
     *
     * Simple toString test to confirm toString is returning a string value
     *
     */

    @Test
    public void testToString() {
        Chaining c = new Chaining<>(1);
        Assert.assertEquals(c.toString().getClass(), "".getClass());
    }

    /**
     *
     * Tests that insert, lookup, and delete edge cases all throw keynotfound
     * exceptions
     *
     */

    @Test(expected = KeyNotFoundException.class)
    public void testKeyNotFound() throws KeyNotFoundException {
        Chaining<String, String> c = new Chaining<>(1);
        try {
            c.insert("hi", "there");
            c.lookup("DNE");
            c.update("DNE", "DNE");
            c.delete("DNE");
            c.delete("hi");
            c.lookup("hi");

        } catch (KeyAlreadyExistsException e) {
            System.out.println("key already exists");
        }

    }

    /**
     *
     * Tests that insert properly throws a keyalreadyexists exception if the
     * key is already present in the set
     *
     */

    @Test(expected = KeyAlreadyExistsException.class)
    public void testKeyAlreadyExists() throws KeyAlreadyExistsException {
        Chaining<String, String> c = new Chaining<>(1);
        c.insert("hi", "there");
        c.insert("hi", "dude");
        c.insert("hi", "there");
    }

    /**
     *
     * a set of simple tests to verify the only public Grep method (lookup)
     * and review its edge cases.
     *
     */

    @Test
    public void testLookupGrep() {
        Grep g1 = new Grep("grep-test-files/ozymandias.txt");
        Grep g2 = new Grep("grep-test-files/waldo.txt");
        Grep g3 = new Grep("grep-test-files/one-line.txt");
        Grep g4 = new Grep("grep-test-files/nada.txt");
        Set<Integer> emptySet = new HashSet<Integer>(0);
        Set<Integer> s1 = new HashSet<>();
        Assert.assertEquals(g1.lookup("Ozymandias"), s1);
        Assert.assertEquals(g1.lookup("ozymandias"), s1);
        Assert.assertEquals(g3.lookup("this"), g3.lookup("is"));
        Assert.assertEquals(g2.lookup("wAldo"), emptySet);
        Assert.assertFalse(g2.lookup("waldo").equals(g2.lookup("Waldo")));
        Assert.assertFalse(g2.lookup("Waldo").equals(g2.lookup("Waldo?")));
        Assert.assertFalse(g4.lookup("").contains(1));
        Assert.assertEquals(g2.lookup("find"), g3.lookup("this"));

    }

}
