
package Model.card;

/**
 *
 * @author yucunli
 */
public class RandomEventCard extends Card{
    private String name;
    private String effect;
    
    /**
     *Constructor, set the random event card ID, player ID,name and effect.
     * @param id
     * @param playerID
     * @param name
     * @param effect
     */
    public RandomEventCard(int id, int playerID, String name, String effect){
        super(id, playerID);
        this.name = name;
        this.effect = effect;
    }
    
    public RandomEventCard(){
        super();
    }

    /**
     *Function to get the random event card name.
     * @return random event card name.
     */
    public String getName() {
        return name;
    }

    /**
     *Function to set the random event card name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Function to get the random event card effect.
     * @return random event card effect.
     */
    public String getEffect() {
        return effect;
    }

    /**
     *Function to set the the random event card effect.
     * @param effect
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }
    
    
}
