
package Controller;

import Controller.LoadCSV;
import Model.Color;
import Model.ConstantField;
import Model.DiscWorld;
import Model.area.Area;
import java.util.HashMap;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author yucunli
 */
public class LoadCSVTest {
    
    private LoadCSV loadCSV;
    BasicFunction basicFunction = new BasicFunction();
    DiscWorld discWorld = new DiscWorld();
    LoadCSV test1 = new LoadCSV("./Data/initData/");
    public LoadCSVTest() {
    }

    /**
     * Test of loadArea method, of class LoadCSV.
     */
    @Test
    public void testLoadArea() {
        
        //this.Area_HASH=new HashMap<Integer,Area>();
        
        test1.loadArea(discWorld.getArea_HASH());
        Assert.assertEquals(ConstantField.NUM_AREA,discWorld.getArea_HASH().size());
    }

    /**
     * Test of loadCityAreaCard method, of class LoadCSV.
     */
    @Test
    public void testLoadCityAreaCard() {
        test1.loadCityAreaCard(discWorld.getCityAreaCard_HASH());
        Assert.assertEquals(ConstantField.NUM_CITY_AREA_CARD, discWorld.getCityAreaCard_HASH().size());
    }

    /**
     * Test of loadPersonalityCard method, of class LoadCSV.
     */
    @Test
    public void testLoadPersonalityCard() {
        test1.loadPersonalityCard(discWorld.getPersonalityCard_HASH());
        Assert.assertEquals(ConstantField.NUM_PERSONALITY_CARD, discWorld.getPersonalityCard_HASH().size());
    }

    /**
     * Test of loadRandomEventCard method, of class LoadCSV.
     */
    @Test
    public void testLoadRandomEventCard() {
        test1.loadRandomEventCard(discWorld.getRandomEventCard_HASH());
        Assert.assertEquals(ConstantField.NUM_RANDOM_EVENT_CARD, discWorld.getRandomEventCard_HASH().size());
    }

    /**
     * Test of loadPlayerCard method, of class LoadCSV.
     */
    @Test
    public void testLoadPlayerCard() {
        //test1.loadPlayerCard(discWorld.getPlayerCard_HASH());
        //Assert.assertEquals(basicFunction.NUM_GREEN_PLAYER_CARD+basicFunction.NUM_BROWN_PLAYER_CARD, discWorld.getPlayerCard_HASH().size());
    }
    
}
