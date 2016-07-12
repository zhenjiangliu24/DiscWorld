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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dehui
 */
public class MoneyFunctionTest {
    
    BasicFunction basicFunction = new BasicFunction();
    MoneyFunction moneyFunction = new MoneyFunction();
    
    public MoneyFunctionTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testGiveBankMoney() 
    {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        
        moneyFunction.giveBankMoney(basicFunction.getDiscWorld(), basicFunction.getDiscWorld().getPlayer_HASH().get(1), 5);
        //Assert.assertEquals(95,basicFunction.getDiscWorld().getTotalCoinAmount());
        Assert.assertEquals(5,basicFunction.getDiscWorld().getPlayer_HASH().get(1).getTotalCoinAmount(basicFunction.getDiscWorld().getCoin_HASH()));
    }
    
    @Test
    public void testGrabBankMoney()
    {
         Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        
        moneyFunction.grabBankMoney(basicFunction.getDiscWorld(), player2, 5);
        Assert.assertEquals(85,basicFunction.getDiscWorld().getTotalCoinAmount());
        //Assert.assertEquals(15,player2.getTotalCoinAmount(basicFunction.getDiscWorld().getCoin_HASH()));
    }
    
    @Test
    public void testGrabPlayerMoney()
    {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        
        moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(), basicFunction.getDiscWorld().getPlayer_HASH().get(1), basicFunction.getDiscWorld().getPlayer_HASH().get(2), 5);
       // Assert.assertEquals(15,player2.getTotalCoinAmount(basicFunction.getDiscWorld().getCoin_HASH()));
        Assert.assertEquals(5,basicFunction.getDiscWorld().getPlayer_HASH().get(1).getTotalCoinAmount(basicFunction.getDiscWorld().getCoin_HASH()));
    }
}
