
package Model.card;

/**
 *
 * @author yucunli
 */
public class CityAreaCard extends Card{
    private String name; //necessary? we can access area class to get the name
    private String ability;
    
    /**
     *Constructor, set the city area card ID, player ID, name and ability.
     * @param id
     * @param playerID
     * @param name
     * @param ability
     */
    public CityAreaCard(int id, int playerID, String name, String ability){
        super(id, playerID);
        this.name = name;
        this.ability = ability;
    }
    
    public CityAreaCard(){
        super();
    }

    /**
     *Function to get the city area card name.
     * @return city area name.
     */
    public String getName() {
        return name;
    }

    /**
     *Function to set the city area card name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Function to get the city area card ability.
     * @return city area card ability.
     */
    public String getAbility() {
        return ability;
    }

    /**
     *Function to set the city area card ability.
     * @param ability
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }
    
    
}
