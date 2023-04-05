package com.coach_o_matic_be;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <h1>Soccer Team</h1>
 * SoccerTeam implements the Team interface.
 * Added funcitonality to manage the Team's formation.
 * 
 * 
 * @version 1.1
 * @since 2023-03-30
 */

 public class SoccerTeam implements Team<SoccerPlayer> {
  private String name;
  private UUID id;
  private ArrayList<SoccerPlayer> playerList;
  private SoccerFormations formation;
  private int gameShifts;

  /**
   * Sets Properties for SoccerTeam object
   * <br>
   * </br>
   * Defaults:
   * <br>
   * </br>
   * playerList = [] (empty ArrayList)
   * <br>
   * </br>
   * formation = TWO_THREE_ONE
   * <br>
   * </br>
   * gameshifts = 6
   * 
   * @param name
   */
  public SoccerTeam(String name) {
    ArrayList<SoccerPlayer> emptyPlayerList = new ArrayList<SoccerPlayer>();
    setFormation(new TwoThreeOneFormation());
    setGameShifts(6);

    setName(name);
    setPlayers(emptyPlayerList);

    this.id = UUID.randomUUID();
  }
    /**
   * Sets Properties for SoccerTeam object
   * <br>
   * </br>
   * Defaults:
   * <br>
   * </br>
   * playerList = [] (empty ArrayList)
   * 
   * @param teamName
   * @param teamFormation
   * @param gameShifts
   */
  public SoccerTeam(String teamName, SoccerFormations  teamFormation,int  gameShifts) {
    ArrayList<SoccerPlayer> emptyPlayerList = new ArrayList<SoccerPlayer>();
    setFormation(teamFormation);
    setGameShifts(gameShifts);

    setName(name);
    setPlayers(emptyPlayerList);

    this.id = UUID.randomUUID();

  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setPlayers(ArrayList<SoccerPlayer> players) throws NullPointerException {
    if (players == null) {
      throw new NullPointerException("Null pointer passed in");
    } else {
      this.playerList = players;
    }
  }

  public ArrayList<SoccerPlayer> getPlayers() {
    return playerList;
  }

  public SoccerPlayer getPlayer(String playerName) {

    for (SoccerPlayer player : this.playerList) {
      if (playerName == player.getName()) {
        return player;
      }
    }
    return null;
  }

  public UUID getId() {
    return this.id;
  }

  public void addPlayer(SoccerPlayer playerToAdd) {
    this.playerList.add(playerToAdd);
  }

  public boolean removePlayer(String nameOfPlayerToRemove) {
	
    Iterator<SoccerPlayer> itr = playerList.iterator();

    while (itr.hasNext()) {

      String name = (itr.next()).getName();

      if (nameOfPlayerToRemove == name) {
        itr.remove();
        return true;
      }
    }
    /*
     * False is returned if there isn't a SoccerPlayer
     * with the same name in playerList.
     */
    return false;
  }

  /**
   * Creates a SoccerPlayer
   * 
   * @param name      name of player
   * @param positions SoccerPlayer's prefered positions.
   * @return SoccerPlayer
   */
  public SoccerPlayer createPlayer(String name, SoccerPositions[] positions) {
    return new SoccerPlayer(name, positions);
  }

  /**
   * Updates an existing SoccerPlayer
   * 
   * @param playerToUpdate
   * @param updatedName
   * @param updatedPositions
   * @return void
   */
  public void updatePlayer(SoccerPlayer playerToUpdate, String updatedName,
      SoccerPositions[] updatedPositions) {
    playerToUpdate.setName(updatedName);
    playerToUpdate.setPositions(updatedPositions);

    return;
  }

  /**
   * Sets the team's formation
   * 
   * @param formation formation of team
   */
  public void setFormation(SoccerFormations formation) {
    this.formation = formation;
  }

  /**
   * Gets the team's formation
   * 
   * @return SoccerFormation This returns the team's set formation
   */
  public SoccerFormations getFormation() {
    return this.formation;
  }

  /**
   * Sets the number of shifts that a team will have in a game.
   * 
   * @param numberOfShifts
   * @throws IllegalArgumentException if the param is less than 1.
   */
  public void setGameShifts(int numberOfShifts) throws IllegalArgumentException {
    if (numberOfShifts <= 0) {
      throw new IllegalArgumentException("Game shifts must be greater than 0");
    } else {
      this.gameShifts = numberOfShifts;
    }
  }

  /**
   * Gets the number of shifts a team will have in a game
   * 
   * @return int number of shifts
   */
  public int getGameShifts() {
    return this.gameShifts;
  }

 }