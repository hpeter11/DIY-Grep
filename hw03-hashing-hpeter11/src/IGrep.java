package src;

import java.util.Set;

/**
* An interface for the Grep class. It contains the lookup method
*/
public interface IGrep {

  /**
   * Takes as input a word and returns the line numbers on which that word
   * appears.
   * 
   * @param word - the word to look up
   * 
   * @return - a set of the line numbers on which that word appears
   */
  public Set<Integer> lookup(String word);

}
