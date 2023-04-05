package com.coach_o_matic_be;
import java.util.UUID;

/**
* <h1>Player</h1>
* Player inteface provides properties and methods that player classes will use. 
* This includes a name and an id.
* <br></br>
* Methods:
*<br></br>
* get and set player name
* <br></br>
* get player id. The ID is a UUID used for database searching.
* 
* 
* 
*
* @version 1.0
* @since   2023-03-29 
*/
public interface Player
{
        /**
   * Sets the name of the soccer player.
   * @param name name of soccer player
   * @return void
   */
    public void setName(String name);
       /**
   * Gets the name of the soccer player.
   * @return String player name.
   */
    public String getName();
       /**
   * Gets the id of the soccer player.
   * @return UUID unique identifier of player.
   */
    public UUID getId();

}