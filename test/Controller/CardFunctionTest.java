/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Color;
import static Model.Color.*;
import Model.DiscWorld;
import Model.player.Player;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Controller.BasicFunction.*;
/**
 *
 * @author Dehui
 */
public class CardFunctionTest {
    CardFunction cardFunction = new CardFunction();
    BasicFunction basicFunction = new BasicFunction();
    //DiscWorld discWorld = new DiscWorld();
    
    @Test
    public void testDrawPlayerCard() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        
        Assert.assertEquals(0, player1.getPlayerCardList().size());
        
        cardFunction.drawPlayerCard(basicFunction.getDiscWorld(), player1);
        Assert.assertEquals(1, player1.getPlayerCardList().size());
    }
    
    @Test
    public void testPlayPlayerCard() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        
        cardFunction.drawPlayerCard(basicFunction.getDiscWorld(), player1);
        //cardFunction.playPlayerCard(basicFunction.getDiscWorld(), player1, player1.getPlayerCardList().;
        Assert.assertEquals(1, player1.getPlayerCardList().size());
    }
 
    @Test
    public void testDrawRandomEventCard()
    {
         Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
       
       cardFunction.drawRandomEventCard(basicFunction.getDiscWorld(), player1);
       Assert.assertEquals(1, player1.getRandomEventCardList().size());
       Assert.assertEquals(11, basicFunction.getDiscWorld().getShuffled_RandomEventCard().size());
    }
    
    @Test
    public void testGrapPlayerCard()
    {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        int cardID = basicFunction.getDiscWorld().getPlayer_HASH().get(1).getPlayerCardList().get(0);
        
        //get first player card of player1 to player2
        cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), basicFunction.getDiscWorld().getPlayer_HASH().get(1), basicFunction.getDiscWorld().getPlayer_HASH().get(2), cardID);
        Assert.assertTrue(basicFunction.getDiscWorld().getPlayer_HASH().get(2).getPlayerCardList().contains(cardID));
        Assert.assertEquals(4,basicFunction.getDiscWorld().getPlayer_HASH().get(1).getPlayerCardList().size());
    }
}
