package com.coach_o_matic_be;
/**
* <h1>TwoThreeOneFormation</h1>
* Implments SoccerFormations with data set specific to a 2-3-1 soccer formation.
* 
* 
* 
* 
*
* @version 1.0
* @since   2023-04-04
*/
public class TwoThreeOneFormation implements SoccerFormations{
  final static String formationAsString = "2-3-1";
  final static  int minPlayers = 7;
  final static SoccerPositions[] positions = SoccerPositions.values();

  public String formationAsString(){
    return formationAsString;
  }

  public SoccerPositions[] getPositions(){
    return positions;
  }

  public int minPlayers(){
    return minPlayers;
  }
}
