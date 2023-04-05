package com.coach_o_matic_be;
import java.util.UUID;
import java.util.ArrayList;
/**
* <h1>Team</h1>
* Team interface provides methods to provide basic functionality of team
* classes.
* <h2>Generic Type</h2>
* When implementing the Team interface the generic type, <code>PlayerType</code>
* must be set to the type of player class that implements the Player interface .
* 
* 
* <br></br>
* Example: In SoccerTeam.java PlayerType is set to SoccerPlayer.
* 
* @version 1.1
* @since 2023-03-30
*/

public interface Team<PlayerType> {
 

 /**
  * Sets the name of the team.
  * 
  * @param teamName
  */
 public void setName(String teamName);

 /**
  * Gets the name of the team.
  * 
  * @return String team name
  */
 public String getName();

 /**
  * Sets a list of players to the player list of the team.
  * 
  * @param players list of players on the team
  * @throws java.lang.NullPointerException if a <code>null</code> reference is
  *                                        passed.
  */
 public void setPlayers(ArrayList<PlayerType> players);

 /**
  * Gets list of players on the team. 
  * 
  * @return ArrayList<Player>
  */
 public ArrayList<PlayerType> getPlayers();

 /**
  * Gets a player in a team's list of players.
  * 
  * @param playerName 
  * @return PlayerType if Player exists or null if Player does not exist
  */
 public PlayerType getPlayer(String playerName);

 /**
  * Gets the id of the team.
  * 
  * @return UUID unique identifier of team.
  */

 public UUID getId();

  /**
  * Adds player to a team's player list
  * @param playToAdd
  */
 public void addPlayer(PlayerType playerToAdd);
  /**
  * Removes player from team's player list
  * @param nameOfPlayerToRemove
  * @return boolean indicates if player was removed
  */
 public boolean removePlayer(String nameOfPlayerToRemove);
}
