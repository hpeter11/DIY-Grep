package sol;

import src.IGrep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

/**
 * Class for finding the line numbers where a given word appears in a file.
 *
 * Constructor initializes a hashmap field of string values (keys) and a set
 * of integers that represent all the line numbers that the key word appears
 * on. The constructor reads the filename path, checking each line for an
 * instance of the key word. It builds a set of all the line numbers in the
 * file, otherwise catching a filenotfound IO exception.
 *
 */
public class Grep implements IGrep {

    private HashMap<String, HashSet<Integer>> lines;

    public Grep(String filename) {

        //initializes lines field
        HashMap<String, HashSet<Integer>> hM = new HashMap<String, HashSet<Integer>>();
        FileReader fR = null;

        try {
           fR = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        }
        try {
            BufferedReader reader = new BufferedReader(fR);
            String line = reader.readLine(); //reads one line at a time
            int i = 1; //establishes a count of line nums
            while (line != null) { //only runs when the lines exist

                String[] words = line.split(" ");

                //iterates through an array of words representing a line
                for (String word : words) {

                    //for new words only
                    if (!(hM.containsKey(word))) {
                        HashSet<Integer> lineNum = new HashSet<>();
                        hM.put(word, lineNum);

                        //adds the line regardless
                    } hM.get(word).add(i);

                    }

                //increases line count, recurses through file
                i++;
                line = reader.readLine();
            }

            reader.close();
        }

        catch (IOException e) {
            System.out.println("Encountered an error: " + e.getMessage());
            System.exit(0);
        }
        this.lines = hM;
    }

    /**
     *
     * Uses hashcode to find an appropriate array slot and then iterates
     * through the linked list to find the appropriate word. If the word
     * isn't found, returns an empty list.
     *
     * @param word - the word to look up
     *
     * @return
     */
    @Override
    public Set<Integer> lookup(String word) {
        Set<Integer> s = this.lines.get(word);
        if (s == null) {
            Set<Integer> x = new HashSet<Integer>(0);
            return x;
        } else {
            return s;
        }
    }

    // Feel free to write additional helper methods :)

    /**
     *
     * Makes a grep out of input configuration arguments and then outputs a
     * string representation of the lookup set
     *
     * @param args
     */

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments");
        }
        Grep g = new Grep(args[0]);
        Set<Integer> hS = new HashSet<>();
        hS = g.lookup(args[1]);
        System.out.println(args[1] + " was found on lines " + hS);
    }
}
