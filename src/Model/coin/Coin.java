
package Model.coin;

/**
 *
 * @author yucunli
 */
public class Coin {

    /**
     *Coin ID.
     */
    protected int id;

    /**
     *Player ID.
     */
    protected int playerID;

    public static final int DENOMINATION = 1;

    /**
     *Constructor, set the coin ID, name.
     * @param id
     * @param playerID
     */
    public Coin(int id, int playerID) {
        this.id = id;
        this.playerID = playerID;
    }
    
    public Coin(){
    
    }

    /**
     *Function to get the coin ID.
     * @return coin ID.
     */
    public int getId() {
        return id;
    }

    /**
     *Function to set the coin ID.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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

    
    
}
