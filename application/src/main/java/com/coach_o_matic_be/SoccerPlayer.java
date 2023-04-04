package com.coach_o_matic_be;

import java.util.UUID;

/**
* <h1>SoccerPlayer</h1>
* SoccerPlayer class contains data about a soccer player and methods to edit data about a soccer player. 
*<br></br>
* Implements Player interface.
* 
* 
* 
* 
*
* @author  Michael McCarthy
* @version 1.0
* @since   2023-03-30 
*/
public class SoccerPlayer implements Player
{
    final UUID id;
    private String name;
    private SoccerPositions[] positions;

    /**
     * Default constructor sets SoccerPlayer's name to blank string and sets all positions.
     */
    public SoccerPlayer()
    {
        this.name = " ";
        this.id = UUID.randomUUID();
        setPositions(SoccerPositions.values());
    }
     /**
     * Sets SoccerPlayer's name and positions to the inputted params.
     * @param name
     * @param positions
     */
    public SoccerPlayer(String name, SoccerPositions[] positions)
    {
        this.name = name;
        this.id = UUID.randomUUID();
        setPositions(positions);
    }

        /**
   * Sets a SoccerPlayer's positions
   * @param positions
   */
    public void setPositions(SoccerPositions[] positions)
    {
        this.positions  = positions;
    }


    /**
   * Gets the array of positions associated with the player.
   * @return SoccerPositions[]
   */
    public SoccerPositions[]  getPositions()
    {
        return positions;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public UUID getId()
    {
        return this.id;
    }
}

