/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.card.PlayerCard;
import Model.player.Player;
import java.util.List;

/**
 *
 * @author ADMINISTRATOR
 */

public class InterruptCard {
    BasicFunction basicFunction;
    /**
     * constructor.
     * @param basicFunction 
     */
    public InterruptCard(BasicFunction basicFunction){
        this.basicFunction = basicFunction;
    }
    /**
     * return a player's interrupt card number who's ID is playerNumber.
     * @param playerNumber
     * @return 
     */
    public int hasInterruptCard(int playerNumber){
        int result = 0;
        Player player = basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber);
        List<Integer> playerCardList = player.getPlayerCardList();
        for(Integer i : playerCardList){            
            PlayerCard playerCard=basicFunction.getDiscWorld().getPlayerCard_HASH().get(i);
            if (playerCard.getAction_text().equalsIgnoreCase("Interrupt")) {
                result = i;
                break;
            }
            
        }
        return result;
    }
}
