package sol;

import src.IDictionary;
import src.IGrep;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.util.Set;

/**
 * A class that tests whether your submission meets
 * the specifications of our autograder.
 */
public class AutograderCompatibility {

    /**
     * The main method of the autograder compatibility check.
     * TODO: Uncomment the body of the main method.
     * (in IntelliJ, you can select all commented lines
     * and press COMMAND+/ or CONTROL+/)
     * TODO: Check that code compiles and runs.
     */
    public static void main(String[] args) throws KeyAlreadyExistsException, KeyNotFoundException {
        //-----------------------------
        //  Grep
        //-----------------------------
        IGrep grep = new Grep("grep-test-files/ozymandias.txt");
        Set<Integer> wordCount = grep.lookup("traveller");
//
//        //-----------------------------
//        //  Chaining
//        //-----------------------------
        IDictionary<Integer, Integer> myHashTable = new Chaining<>(10);
        myHashTable.insert(1, 1);
        myHashTable.delete(1);
//
        System.out.println("Your code works with the autograder!");
    }
}

