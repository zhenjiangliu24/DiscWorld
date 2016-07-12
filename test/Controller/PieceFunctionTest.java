/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Color;
import static Model.Color.BLACK;
import static Model.Color.BLUE;
import static Model.Color.RED;
import Model.player.Player;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laetetia
 */
public class PieceFunctionTest {
    
    BasicFunction basicFunction = new BasicFunction();
    PieceFunction pieceFunction = new PieceFunction();
    Player player1;
    Player player2;
    Player player3;
    String[] player_list ={"p1", "p2","p3"};
    Color[] colorArray = {Color.BLACK, Color.BLUE,Color.RED};   
    String[] player_order = {"1", "2","3"};
    
    public PieceFunctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player1 = new Player(1, "p1", BLACK, 1, 1);
        player2 = new Player(2, "p2", BLUE, 2, 2);
        player3 = new Player(3, "p3",RED,3,3);
        basicFunction.init(player_list, player_order, colorArray);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBuildingAdd() 
    {
        pieceFunction.minionAdd(basicFunction.getDiscWorld(), player1.getID(), 9);
        pieceFunction.buildingAdd(basicFunction.getDiscWorld(), player1.getID(), 9);
        Assert.assertTrue((basicFunction.getDiscWorld().getArea_HASH().get(9).getBuildingNum()!=-1));
    }
    
    @Test
    public void testBuildingDelete() 
    {
        pieceFunction.minionAdd(basicFunction.getDiscWorld(), player1.getID(), 9);
        pieceFunction.buildingAdd(basicFunction.getDiscWorld(), player1.getID(), 9);
        pieceFunction.buildingDelete(basicFunction.getDiscWorld(), 9);
        Assert.assertEquals(-1, basicFunction.getDiscWorld().getArea_HASH().get(9).getBuildingNum());
    }
    
    @Test
    public void testDemonAdd() 
    {
        pieceFunction.demonAdd(basicFunction.getDiscWorld(), 9);
        Assert.assertEquals(1, basicFunction.getDiscWorld().getArea_HASH().get(9).getDemonList().size());
    }
    
    @Test
    public void testDemonDelete() 
    {
        pieceFunction.demonAdd(basicFunction.getDiscWorld(), 9);
        pieceFunction.demonAdd(basicFunction.getDiscWorld(), 9);
        pieceFunction.demonDelete(basicFunction.getDiscWorld(), 9);
        Assert.assertEquals(1, basicFunction.getDiscWorld().getArea_HASH().get(9).getDemonList().size());
    }
    
    @Test
    public void testTrollAdd() 
    {
        pieceFunction.trollAdd(basicFunction.getDiscWorld(), 9);
        Assert.assertEquals(1, basicFunction.getDiscWorld().getArea_HASH().get(9).getTrollList().size());
    }
    
    @Test
    public void testTrollDelete() 
    {
        pieceFunction.trollAdd(basicFunction.getDiscWorld(), 9);
        pieceFunction.trollAdd(basicFunction.getDiscWorld(), 9);
        pieceFunction.trollDelete(basicFunction.getDiscWorld(), 9);
        Assert.assertEquals(1, basicFunction.getDiscWorld().getArea_HASH().get(9).getTrollList().size());
    }
    
    @Test
    public void testMinionAdd() 
    {
        pieceFunction.minionAdd(basicFunction.getDiscWorld(), player1.getID(), 3);
        Assert.assertEquals(1, basicFunction.getDiscWorld().getArea_HASH().get(3).getMinionList().size());
//        Assert.assertEquals(11, player1.getMinionList().size());
    }
    
    @Test
    public void testMinionDelete() 
    {
        pieceFunction.minionAdd(basicFunction.getDiscWorld(), player1.getID(), 3);
        pieceFunction.minionAdd(basicFunction.getDiscWorld(), player1.getID(), 3);
        pieceFunction.minionDelete(basicFunction.getDiscWorld(), player1.getID(), 3);
        Assert.assertEquals(1, basicFunction.getDiscWorld().getArea_HASH().get(3).getMinionList().size());
        
        pieceFunction.minionDelete(basicFunction.getDiscWorld(), player1.getID(), 1);
        Assert.assertEquals(2, basicFunction.getDiscWorld().getArea_HASH().get(1).getMinionList().size());
    }
    
    @Test
    public void testTroubleMarkerAdd() 
    {
        pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), 1);
        Assert.assertTrue((basicFunction.getDiscWorld().getArea_HASH().get(1).getTroubleMarkerNum()!=-1));
    }
    
    @Test
    public void testTroubleMarkerDelete() 
    {
        //test area 4, default no troublemarker , value should be -1
        Assert.assertEquals(-1, basicFunction.getDiscWorld().getArea_HASH().get(4).getTroubleMarkerNum());
        pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), 4);
        pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), 4);
        Assert.assertEquals(-1, basicFunction.getDiscWorld().getArea_HASH().get(4).getTroubleMarkerNum());
        
        //test area 1, default has troublemarkder, value is TM's ID, not -1
        pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), 1);
        Assert.assertEquals(-1, basicFunction.getDiscWorld().getArea_HASH().get(1).getTroubleMarkerNum());
    }
}
