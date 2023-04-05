
package com.coach_o_matic_be;

/**
* <h1>SoccerFormation</h1>
* SoccerFormation inteface provides getter methods to provide information regarding a soccer formation.
*This includes a formation's name as a string, the list of positions associated with the formation and the minimum number of players required to use the formation.
* 
* 
* 
* 
*
* @author  Michael McCarthy
* @version 1.0
* @since   2023-04-04
*/
public interface SoccerFormations
{
        /**
   * Returns a formations name as a string.
   * @return String
   */
    public String formationAsString();
       /**
   * Returns the list of positions associated with the formation.
   * @return SoccerPositions[]
   */
    public SoccerPositions[] getPositions();
       /**
   * Returns the minimum number of players required to use the formation.
   * @return int
   */
    public int minPlayers();

}