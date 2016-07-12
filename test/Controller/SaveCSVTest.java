
package Controller;

import Model.DiscWorld;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import Controller.fileIsExist;

/**
 *
 * @author Zhenjiang Liu
 */
public class SaveCSVTest {
    String filePosition = "./Data/statusData/"+"test"+"/";
    BasicFunction basicFunction = new BasicFunction();
    DiscWorld discWorld = new DiscWorld();
    SaveCSV saveCSV = new SaveCSV(filePosition);
    
    public SaveCSVTest() {
    }

    /**
     * Test of savePlayer method, of class SaveCSV.
     */
    @Test
    public void testSavePlayer() {
        String fileName = "player.csv";
        fileIsExist f = new fileIsExist(filePosition+fileName);
        saveCSV.savePlayer(discWorld.getPlayer_HASH());
        Assert.assertEquals(true,f.isExist());
    }

    /**
     * Test of saveCityAreaCard method, of class SaveCSV.
     */
    @Test
    public void testSaveCityAreaCard() {
        String filecity = "CityAreaCard.csv";
        fileIsExist f2 = new fileIsExist(filePosition+filecity);
        saveCSV.saveCityAreaCard(discWorld.getCityAreaCard_HASH());
        Assert.assertEquals(true,f2.isExist());
    }

    /**
     * Test of savePersonalityCard method, of class SaveCSV.
     */
    @Test
    public void testSavePersonalityCard() {
        String personality = "PersonalityCard.csv";
        fileIsExist f2 = new fileIsExist(filePosition+personality);
        saveCSV.savePersonalityCard(discWorld.getPersonalityCard_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveRandomEventCard method, of class SaveCSV.
     */
    @Test
    public void testSaveRandomEventCard() {
        String random = "RandomEventCard.csv";
        fileIsExist f2 = new fileIsExist(filePosition+random);
        saveCSV.saveRandomEventCard(discWorld.getRandomEventCard_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveBuildingPiece method, of class SaveCSV.
     */
    @Test
    public void testSaveBuildingPiece() {
        String building = "BuildingPiece.csv";
        fileIsExist f2 = new fileIsExist(filePosition+building);
        saveCSV.saveBuildingPiece(discWorld.getBuildingPiece_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveMinionPiece method, of class SaveCSV.
     */
    @Test
    public void testSaveMinionPiece() {
        String minion = "MinionPiece.csv";
        fileIsExist f2 = new fileIsExist(filePosition+minion);
        saveCSV.saveMinionPiece(discWorld.getMinionPiece_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveDemonPiece method, of class SaveCSV.
     */
    @Test
    public void testSaveDemonPiece() {
        String demon = "DemonPiece.csv";
        fileIsExist f2 = new fileIsExist(filePosition+demon);
        saveCSV.saveDemonPiece(discWorld.getDemonPiece_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveTrollPiece method, of class SaveCSV.
     */
    @Test
    public void testSaveTrollPiece() {
        String troll = "TrollPiece.csv";
        fileIsExist f2 = new fileIsExist(filePosition+troll);
        saveCSV.saveTrollPiece(discWorld.getTrollPiece_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveTroubleMarker method, of class SaveCSV.
     */
    @Test
    public void testSaveTroubleMarker() {
        String trouble = "TroubleMarker.csv";
        fileIsExist f2 = new fileIsExist(filePosition+trouble);
        saveCSV.saveTroubleMarker(discWorld.getTroubleMarker_HASH());
        Assert.assertEquals(true, f2.isExist());
    }

    /**
     * Test of saveCoin method, of class SaveCSV.
     */
    @Test
    public void testSaveCoin() {
        String coin = "Coin.csv";
        fileIsExist f2 = new fileIsExist(filePosition+coin);
        saveCSV.saveCoin(discWorld.getCoin_HASH());
        Assert.assertEquals(true,f2.isExist());
    }
    
}
