

package Model.card;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
/**
 *
 * @author yucunli
 */
public class Card implements Comparable{

    /**
     *Card ID.
     */
    protected int id;

    /**
     *Player ID.
     */
    protected int playerID;
    
    protected int used;
    protected int order;
    
    /**
     *Construtor, set the card ID, player ID.
     * @param id
     * @param playerID
     */
    public Card(int id, int playerID){
        this.id = id;
        this.playerID = playerID;
    }

    public Card(){
    
    }

    // Used for shuffle cards
    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *Function to get the card ID.
     * @return card ID.
     */
    public int getId() {
        return id;
    }

    /**
     *Function to set the card ID.
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

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
}
