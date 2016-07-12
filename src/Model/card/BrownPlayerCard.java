
package Model.card;

/**
 *
 * @author yucunli
 */
public class BrownPlayerCard extends PlayerCard{
    
    /**
     *Constructor, set the brown card ID, name, action and player ID.
     * @param id
     * @param name
     * @param action
     * @param playerID
     */
    public BrownPlayerCard(int id, String name, String action, int playerID){
        super(id, name, action, playerID);
        this.type = PlayerCard.Brown;
    }
    
    public BrownPlayerCard(){
        super();
        this.type = PlayerCard.Brown;
    }
}
