/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ConstantField;
import Model.DiscWorld;
import Model.card.Card;
import Model.card.PlayerCard;
import Model.player.Player;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yucunli
 */
public class CardFunction {
    /**
     * player draw a player card from the shuffled card heap
     * @param discWorld
     * @param player 
     */
    public void drawPlayerCard(DiscWorld discWorld, Player player){
        if(discWorld.getShuffled_PlayerCard().size() > 0){
            int cardID = discWorld.getShuffled_PlayerCard().remove(0);
            // if one player draw a card, which means this card is being used
            discWorld.getPlayerCard_HASH().get(cardID).setUsed(ConstantField.CARD_USED);
            discWorld.getPlayerCard_HASH().get(cardID).setPlayerID(player.getID());
            player.getPlayerCardList().add(cardID);
        }
        
    }
    /**
     * when and the end of a player's turn, call this function to make sure player have 5 cards.
     * @param discWorld
     * @param player 
     */
    public void drawToFiveCards(DiscWorld discWorld, Player player){
        int playerCardNumber = player.getPlayerCardList().size();
        if(playerCardNumber<5){
            int addCardNumber = 5-playerCardNumber;
            for(int i = 0;i<addCardNumber;i++){
                this.drawPlayerCard(discWorld, player);               
            }
        }
    }
    /**
     * player draw a random event card from shuffled card heap
     * @param discWorld
     * @param player 
     */
    public void drawRandomEventCard(DiscWorld discWorld, Player player){
        if(discWorld.getShuffled_RandomEventCard().size()>0){
            int cardID = discWorld.getShuffled_RandomEventCard().remove(0);
            //discWorld.getRandomEventCard_HASH().get(cardID).getEffect();
            player.getRandomEventCardList().add(cardID);
        }
    }
    /**
     * player put out a player card
     * @param discWorld
     * @param player
     * @param playerCard 
     */
    public void playPlayerCard(DiscWorld discWorld, Player player, PlayerCard playerCard){
        if(player.getPlayerCardList().size() > 0){
            playerCard.setPlayerID(ConstantField.DEFAULT_UNDEFINED_PLAYID);
            Iterator<Integer> listIterator = player.getPlayerCardList().iterator();
            while(listIterator.hasNext()){
                int currentElement = listIterator.next();
                if(currentElement == playerCard.getId()){
                    listIterator.remove();
                    break;
                }
            }
            
        }
    }
    /**
     * fromplayer grap player card from toplayer
     * @param discWorld
     * @param fromPlayer
     * @param toPlayer
     * @param playerCardID 
     */
    public void grapPlayerCard(DiscWorld discWorld, Player fromPlayer, Player toPlayer, int playerCardID){
        PlayerCard playerCard = discWorld.getPlayerCard_HASH().get(playerCardID);
        playerCard.setPlayerID(toPlayer.getID());
        // add to toPlayer
        toPlayer.getPlayerCardList().add(playerCardID);
        // delete from fromPlayer
        Iterator<Integer> listIterator = fromPlayer.getPlayerCardList().iterator();
        while(listIterator.hasNext()){
            int currentElement = listIterator.next();
            if(currentElement == playerCard.getId()){
                listIterator.remove();
                break;
            }
        }
    }
    /**
     * draw one player card.
     * @param discWorld
     * @param playerID 
     */
    public void autoDrawCard(DiscWorld discWorld, int playerID){
        Player player = discWorld.getPlayer_HASH().get(playerID);
        while(player.getPlayerCardList().size() < ConstantField.DEFAULT_NUM_PLAYER_CARD_FOR_PLAYER){
            drawPlayerCard(discWorld, player);
        }
    }
    
}
