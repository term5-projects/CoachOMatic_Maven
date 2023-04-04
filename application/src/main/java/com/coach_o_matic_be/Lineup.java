package com.coach_o_matic_be;
/**
 * <h1>Lineup</h1>
 * Lineup class contains the title of a Lineup and the Lineup in a 2D array.
 * Upon initializing the Lineup, the title and lineup cannot be changed.
 * If a lineup needs to be edited, a new Lineup object must be made.
 * If a Lineup is edited you can store the previous lineup object in case the
 * user wants
 * to undo their changes.
 * 
 *
 * @author Michael McCarthy
 * @version 1.0
 * @since 2023-03-30
 */

 public class Lineup {
  private final String title;
  private final Player[][] lineup;

  Lineup(String lineupTitle, Player[][] lineup)throws IllegalArgumentException, NullPointerException{
    // Initialize title
    if (lineupTitle == " " || lineupTitle == ""){
      throw new IllegalArgumentException("Empty title");
    }else{
      this.title = lineupTitle;
    }

    //initialize lineup
    if (lineup == null){
      throw new NullPointerException("Null pointer passed in");
    }
    else{
      this.lineup = lineup;
    }
  }

  /**
   * Gets the title of the lineup.
   * 
   * @return String lineup title
   */
  public String getTitle() {
    return this.title;

  }

  /**
   * Gets the lineup.
   * 
   * @return Player[][] lineup
   */
  public Player[][] getLineup() {
    return lineup;
  }
}
