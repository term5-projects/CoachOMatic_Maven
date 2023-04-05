package com.coach_o_matic_be;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * <h1>SoccerLineupGenerator7v7</h1>
 * SoccerLineupGenerator7v7 provides the functionality to generate a lineup object based on
 * a list of players, number of shifts, and formation.
 * 
 *
 * @version 1.0
 * @since 2023-03-30
 */
public class SoccerLineupGenerator7v7{


	/**
   * generates lineup object for 7v7 soccer match.
   * @param playerList list of strings included in the lineup
   * @param formation formation the lineup needs to be in
   * @param numberOfShifts
   * @return an arraylist that with has arralist of strings representing a shift
   */
  public static ArrayList<ArrayList<String>> generateLineup(ArrayList<String> playerList, SoccerFormations formation, int numberOfShifts){
	 
	  Collections.shuffle(playerList, new Random());
	  ArrayList<ArrayList<String>> lineuprows  = new ArrayList<>();
	  
	  for (int i = 0; i < numberOfShifts; i++) {
		    String last = playerList.remove(playerList.size() - 1);
		    playerList.add(0, last);
		    
			ArrayList<String> PlayerNames = new ArrayList<String>();
			  
			for (int i1 = 0; i1 < playerList.size(); i1++) {
					  PlayerNames.add(playerList.get(i1));
			}
			PlayerNames.add(0,Integer.toString(i+1));
			for (int i2 = 0; i2 < PlayerNames.size(); i2++) {
			    String str = PlayerNames.get(i2);
			    str = str.replace(" ", ""); // remove the space
			    PlayerNames.set(i2, str); // update the element in the list
			}
	    	 lineuprows.add(PlayerNames);  
	  }		   
    return lineuprows;
  }


}


