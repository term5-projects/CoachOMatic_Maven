package com.coach_o_matic_be;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <h1>User</h1>
 * User class is a top-level class that is used to track data about a User
 * as well as provides funcitonality to work with teams and generate lineups
 * 
 *
 * @author Michael McCarthy
 * @version 1.1
 * @since 2023-04-01
 */

public class User {
  private String username;
  private String password;
  private ArrayList<SoccerTeam> teams;

  /**
   * Sets username and password of User and makes team list an empty array.
   * 
   * @param username
   * @param password
   */
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    teams = new ArrayList<SoccerTeam>();
    System.out.println("New user created with username: " + username + " and password: " + password);
  }

  /**
   * gets username of User
   * 
   * @return String
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Sets username of User
   * 
   * @param newUsername
   * 
   */
  public void setUsername(String newUsername) {
    this.username = newUsername;
  }

  /**
   * gets password of User
   * 
   * @return String
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets password of User
   * 
   * @param newPassword
   * 
   */
  public void setPassword(String newPassword) {
    this.password = newPassword;
  }

  /**
   * Creates SoccerTeam object.
   * 
   * @param teamName
   * @param playerList
   * @param teamFormation
   * @param gameShifts
   */
  public void createTeam(String teamName, SoccerFormations teamFormation, int gameShifts) {

  }

  /**
   * Returns a list of the user's teams
   * @return ArrayList<SoccerTeam>
   */
  public ArrayList<SoccerTeam> getTeams() {
	  return teams;
  }
  
  public void addTeam(SoccerTeam teamToAdd) {
    teams.add(teamToAdd);
    System.out.println("New team added: " + teamToAdd.getName());
  }

  /**
   * Searches the User's team list using the team name and removes it.
   * 
   * @param teamName
   * @return boolean indicates if the team was successfully
   *         removed
   */
  public boolean removeTeam(String teamNameToRemove) {
    Iterator<SoccerTeam> itr = teams.iterator();

    while (itr.hasNext()) {

      String name = (itr.next()).getName();

      if (teamNameToRemove == name) {
        itr.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Searches the User's team list and returns a team object with the same name.
   * 
   * @param teamName
   * @return SoccerTeam Team in User team list that has the corresponding name or
   *         <code>null</code> if team doesn't exist
   */
  public SoccerTeam getTeam(String teamName) {
    Iterator<SoccerTeam> itr = teams.iterator();

    while (itr.hasNext()) {
      SoccerTeam team = itr.next();
      String name = team.getName();

      if (teamName.equals(name)) {
        return team;
      }
    }
    return null;
  }

  /**
   * Updates an existing SoccerTeam object'
   * <br>
   * </br>
   * DOES NOT update player list. This must be updated using methods in SoccerTeam
   * class.
   * 
   * @param teamToUpdate
   * @param updatedTeamName
   * @param updatedTeamFormation
   * @param updatedGameShifts
   * @return void
   */
  public void updateTeam(SoccerTeam teamToUpdate, String updatedTeamName,
      SoccerFormations updatedTeamFormation, int updatedGameShifts) {
    teamToUpdate.setName(updatedTeamName);
    teamToUpdate.setFormation(updatedTeamFormation);
    teamToUpdate.setGameShifts(updatedGameShifts);
    System.out.println("Updated team: " + teamToUpdate.getName());

    return;
    
  }

}
