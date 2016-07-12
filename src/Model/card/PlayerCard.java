
package Model.card;

import Behavior.Action;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yucunli
 */
public class PlayerCard extends Card{

    /**
     *Player card name.
     */
    protected String name;

    protected String text;
    
    protected List<Action> actions;
    
    protected String action_text;
    
    protected int order;

    /**
     *Player card type.
     */
    protected String type;

    /**
     *Player card color: Green.
     */
    public static final String GREEN = "Green";

    /**
     *Player card colot: Brown.
     */
    public static final String Brown = "Brown";
    
    /**
     *Constructor, set the player card ID, name, action and player ID.
     * @param id
     * @param name
     * @param text
     * @param playerID
     */
    public PlayerCard(int id, String name, String text, int playerID){
        super(id, playerID);
        this.name = name;
        this.text = text;
    }
    
    public PlayerCard(){
        super();
        actions = new LinkedList<Action>();
    }

    /**
     *Function to get the player card name.
     * @return player card name.
     */
    public String getName() {
        return name;
    }

    /**
     *Function to set the player card name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Function to get the player card type.
     * @return player card type.
     */
    public String getType() {
        return type;
    }

    /**
     *Function to set the player card type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getAction_text() {
        return action_text;
    }

    public void setAction_text(String action_text) {
        this.action_text = action_text;
    }
    
    
}
