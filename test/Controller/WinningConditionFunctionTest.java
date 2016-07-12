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
 * @author yucunli
 */
public class WinningConditionFunctionTest {

    BasicFunction basicFunction = new BasicFunction();
    WinningConditionFunction winningConditionFunction;

    public WinningConditionFunctionTest() {

    }

    @Test
    public void isWin_LordVetinariTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1","2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_LordVetinari());
    }

    @Test
    public void isWin_LordSelachiiTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_LordSelachii());
    }

    @Test
    public void isWin_LordRustTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_LordRust());
    }

    @Test
    public void isWin_LordDeWordeTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_LordDeWorde());
    }

    @Test
    public void isWin_DragonKingOfArmsTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_DragonKingOfArms());
    }

    @Test
    public void isWin_ChrysopraseTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_Chrysoprase());
    }

    @Test
    public void isWin_CommanderVimesTest() {
        Player player1 = new Player(1, "p1", BLACK, 1, 1);
        Player player2 = new Player(2,"p2",BLUE,2,2);
        Player player3 = new Player(3, "p3", RED, 3, 3);   
        String[] player_list ={"p1", "p2","p3"};
        Color[] colorArray = {Color.BLACK,Color.BLUE, Color.RED};   
        String[] player_order = {"1", "2","3"};
        basicFunction.init(player_list, player_order, colorArray);
        winningConditionFunction = new WinningConditionFunction(basicFunction.getDiscWorld());
        
        Assert.assertEquals(false,winningConditionFunction.isWin_CommanderVimes());
    }

}
