
package Controller;

import Model.Color;
import Model.ConstantField;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yucunli
 */
public class BasicFunctionTest {
    
    BasicFunction basicFunction = new BasicFunction();
    
    public BasicFunctionTest() {
        
        
    }

    /**
     * Test of load method, of class BasicFunction.
     */
    @Test
    public void testLoad() {
        /*basicFunction.load();
        
        Assert.assertEquals(basicFunction.NUM_AREA, basicFunction.getDiscWorld().getArea_HASH().size());
        Assert.assertEquals(basicFunction.NUM_SILVER_COIN+basicFunction.NUM_GOLD_COIN, basicFunction.getDiscWorld().getCoin_HASH().size());
        Assert.assertEquals(basicFunction.NUM_AREA, basicFunction.getDiscWorld().getCityAreaCard_HASH().size());
        Assert.assertEquals(basicFunction.NUM_PERSONALITY_CARD, basicFunction.getDiscWorld().getPersonalityCard_HASH().size());
        Assert.assertEquals(basicFunction.NUM_RANDOM_EVENT_CARD, basicFunction.getDiscWorld().getRandomEventCard_HASH().size());
        
        Assert.assertEquals(basicFunction.NUM_OF_BUILDING_PIECE_SET*basicFunction.getNumPlayer(), basicFunction.getDiscWorld().getBuildingPiece_HASH().size());
        Assert.assertEquals(basicFunction.NUM_OF_MINION_PIECE_SET*basicFunction.getNumPlayer(), basicFunction.getDiscWorld().getMinionPiece_HASH().size());
        Assert.assertEquals(basicFunction.NUM_DEMON_PIECE, basicFunction.getDiscWorld().getDemonPiece_HASH().size());
        Assert.assertEquals(basicFunction.NUM_TROLL_PIECE, basicFunction.getDiscWorld().getTrollPiece_HASH().size());
        Assert.assertEquals(basicFunction.NUM_TROUBLE_MARKER, basicFunction.getDiscWorld().getTroubleMarker_HASH().size());
        */
    }

    /**
     * Test of save method, of class BasicFunction.
     */
    @Test
    public void testSave() {
        /*String[] player_list = {"li", "yu", "cun"};
        Color[] colorArray = {Color.BLACK, Color.BLUE, Color.RED};
        basicFunction.init(player_list, colorArray);
        
        basicFunction.save();*/
        
    }

    /**
     * Test of init method, of class BasicFunction.
     */
    @Test
    public void testInit() {
        
        String[] player_list = {"li", "yu", "cun"};
        String[] player_order = {"3","2","1"};
        Color[] colorArray = {Color.BLACK, Color.BLUE, Color.RED};
        basicFunction.init(player_list,player_order, colorArray);
        
        Assert.assertEquals(ConstantField.NUM_AREA, basicFunction.getDiscWorld().getArea_HASH().size());
        Assert.assertEquals(ConstantField.NUM_SILVER_COIN+ConstantField.NUM_GOLD_COIN, basicFunction.getDiscWorld().getCoin_HASH().size());
        Assert.assertEquals(ConstantField.NUM_CITY_AREA_CARD,basicFunction.getDiscWorld().getCityAreaCard_HASH().size());
        Assert.assertEquals(ConstantField.NUM_PERSONALITY_CARD, basicFunction.getDiscWorld().getPersonalityCard_HASH().size());
        Assert.assertEquals(ConstantField.NUM_RANDOM_EVENT_CARD, basicFunction.getDiscWorld().getRandomEventCard_HASH().size());
        
        Assert.assertEquals(ConstantField.NUM_OF_BUILDING_PIECE_SET*basicFunction.getNumPlayer(), basicFunction.getDiscWorld().getBuildingPiece_HASH().size());
        Assert.assertEquals(ConstantField.NUM_OF_MINION_PIECE_SET*basicFunction.getNumPlayer(), basicFunction.getDiscWorld().getMinionPiece_HASH().size());
        Assert.assertEquals(ConstantField.NUM_DEMON_PIECE, basicFunction.getDiscWorld().getDemonPiece_HASH().size());
        Assert.assertEquals(ConstantField.NUM_TROLL_PIECE, basicFunction.getDiscWorld().getTrollPiece_HASH().size());
        Assert.assertEquals(ConstantField.NUM_TROUBLE_MARKER, basicFunction.getDiscWorld().getTroubleMarker_HASH().size());

    }
    
    
    
}
