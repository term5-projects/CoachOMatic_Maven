package com.coach_o_matic_be;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

/**
 * <h1>SoccerLineupGenerator7v7</h1>
 * SoccerLineupGenerator7v7 provides the functionality to generate a lineup object based on
 * a list of players, number of shifts, and formation.
 * 
 *
 * @author Michael McCarthy
 * @version 1.0
 * @since 2023-03-30
 */
public class SoccerLineupGenerator7v7{


	/**
   * generates lineup object for 7v7 soccer match.
   * @param playerList list of players included in the lineup
   * @param formation formation the lineup needs to be in
   * @param numberOfShifts
   * @return Lineup generated Lineup
   */
  static ArrayList<ArrayList<String>> generateLineup(ArrayList<Player> playerList, SoccerFormations formation, int numberOfShifts){
	 
	  Collections.shuffle(playerList, new Random());
	  
	  ArrayList<ArrayList<String>> lineuprows  = new ArrayList<>();
	  
	  for (int i = 0; i < numberOfShifts; i++) {
		    Player last = playerList.remove(playerList.size() - 1);
		    playerList.add(0, last);
		    
			ArrayList<String> PlayerNames = new ArrayList<String>();
			  
			for (int i1 = 0; i1 < playerList.size(); i1++) {
					  PlayerNames.add(playerList.get(i1).getName());
			}
				  lineuprows.add(PlayerNames);  
	  }	
		   
    return lineuprows;
  }
  
  static ArrayList<ArrayList<Player>> generateLineupPlayers(ArrayList<Player> playerList, SoccerFormations formation, int numberOfShifts){
		 
	  Collections.shuffle(playerList, new Random());
	  
	  ArrayList<ArrayList<Player>> lineuprows  = new ArrayList<>();
	  
	  for (int i = 0; i < numberOfShifts; i++) {
		    Player last = playerList.remove(playerList.size() - 1);
		    playerList.add(0, last);
		    
			ArrayList<Player> PlayersShifted = new ArrayList<Player>();
			  
			for (int i1 = 0; i1 < playerList.size(); i1++) {
				PlayersShifted.add(playerList.get(i1));
			}
				  lineuprows.add(PlayersShifted);  
	  }	
		   
    return lineuprows;
  }
  
  	public static void main(String args[]) {
      ArrayList<Player> playerList = new ArrayList<Player>();
      SoccerPlayer player1 = new SoccerPlayer();
      
      player1.setName("Michael");
      
      SoccerPlayer player2 = new SoccerPlayer();
      
      player2.setName("Grace");
      
      SoccerPlayer player3 = new SoccerPlayer();
      
      player3.setName("David");
      
      SoccerPlayer player4 = new SoccerPlayer();
      
      player4.setName("Momison");
      
      SoccerPlayer player5 = new SoccerPlayer();
      
      player5.setName("Eli");
      
      SoccerPlayer player6 = new SoccerPlayer();
      
      player6.setName("Sam");
      
      SoccerPlayer player7 = new SoccerPlayer();
      
      player7.setName("Colin");
      
      SoccerPlayer player8 = new SoccerPlayer();
      
      player8.setName("Charlie");
      
      playerList.add(player1);
      playerList.add(player2);
      playerList.add(player3);
      playerList.add(player4);
      playerList.add(player5);
      playerList.add(player6);
      playerList.add(player7);
      playerList.add(player8);
      
      
      
      ArrayList<ArrayList<String>> lineup = SoccerLineupGenerator7v7.generateLineup(playerList, SoccerFormations.TWO_THREE_ONE ,6);
      
      
      for (int i = 0; i < lineup.size(); i++) {
          System.out.println("Lineup for shift " + (i+1) + ": " + lineup.get(i));
      }
  }
      
      
      
      

   }


