
package Model.piece;

import Model.Color;

/**
 *
 * @author yucunli
 */
public class BuildingPiece extends Piece{
    private int playerID;
    private Color color;

    /**
     *Constructor, set the building piece ID, name, color.
     * @param id
     * @param playerID
     * @param areaNumber
     * @param color
     */
    public BuildingPiece(int id, int playerID, int areaNumber, Color color) {
        super(id, areaNumber);
        this.playerID = playerID;
        this.color = color;
    }

    /**
     *Function to get the player ID.
     * @return player ID.
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     *Function to set the player ID.
     * @param playerID
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     *Function to get the building piece color.
     * @return building piece color.
     */
    public Color getColor() {
        return color;
    }

    /**
     *Function to set the building piece color.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
