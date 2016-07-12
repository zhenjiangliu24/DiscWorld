
package Model.card;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import java.awt.Toolkit;
import java.awt.Image;
/**
 *
 * @author yucunli
 */
public class PersonalityCard extends Card{
    private String name;
    private String victoryCondition;

    /**
     *Constructor, set the personality card ID, player ID, name, victory condition.
     * @param id
     * @param playerID
     * @param name
     * @param victoryCondition
     */
    public PersonalityCard(int id, int playerID, String name, String victoryCondition){
        super(id, playerID);
        this.name = name;
        this.victoryCondition = victoryCondition;
    }

    /**
     *Function to get the personality card name.
     * @return personality card name.
     */
    public String getName() {
        return name;
    }

    /**
     *Function to set the personality card name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Function to get the personality card victory condition.
     * @return victory condition.
     */
    public String getVictoryCondition() {
        return victoryCondition;
    }

    /**
     *Function to set the personality card victory condition.
     * @param victoryCondition
     */
    public void setVictoryCondition(String victoryCondition) {
        this.victoryCondition = victoryCondition;
    }
    
    
}
