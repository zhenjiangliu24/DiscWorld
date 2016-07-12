
package Model.card;

/**
 *
 * @author yucunli
 */
public class GreenPlayerCard extends PlayerCard{
    
    /**
     *Constructor, set the green player card ID, name, action and player ID.
     * @param id
     * @param name
     * @param action
     * @param playerID
     */
    public GreenPlayerCard(int id, String name, String action, int playerID){
        super(id, name, action, playerID);
        this.type = PlayerCard.GREEN;
    }
    
    public GreenPlayerCard(){
        super();
        this.type = PlayerCard.GREEN;
    }
}
