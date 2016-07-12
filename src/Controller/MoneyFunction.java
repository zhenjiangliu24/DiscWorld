/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ConstantField;
import Model.DiscWorld;
import Model.coin.Coin;
import Model.player.Player;
import java.util.Iterator;

/**
 *
 * @author yucunli
 */
public class MoneyFunction {
    /**
     * get money from bank
     * @param discWorld
     * @param player
     * @param amount 
     */
    public void grabBankMoney(DiscWorld discWorld, Player player, int amount){
       
        // set coin
        for(int count = 0; count < amount; count++){
            for(Coin coin : discWorld.getCoin_HASH().values()){
                if(coin.getPlayerID() == ConstantField.DEFAULT_UNDEFINED_PLAYID){
                    coin.setPlayerID(player.getID());
                    player.getCoinList().add(coin.getId());
                    break;
                }
            }
        }
        
        
    }
    /**
     * give money to bank
     * @param discWorld
     * @param player
     * @param amount 
     */
    public void giveBankMoney(DiscWorld discWorld, Player player, int amount){
        
        // set coin
        for(int count = 0; count < amount; count++){
            for(Integer coinID : player.getCoinList()){
                int coin_id = coinID;
                Coin coin = discWorld.getCoin_HASH().get(coin_id);
                
                // set coin
                coin.setPlayerID(ConstantField.DEFAULT_UNDEFINED_PLAYID);
                // delete from player
                Iterator<Integer> listIterator = player.getCoinList().iterator();
                while(listIterator.hasNext()){
                    int currentElement = listIterator.next();
                    if(currentElement == coin_id){
                        listIterator.remove();
                        break;
                    }
                }
                break;
                
            }
        }
        
        
    }
    /**
     * transfer money from one player to another player
     * @param discWorld
     * @param fromPlayer
     * @param toPlayer
     * @param amount 
     */
    public void grabPlayerMoney(DiscWorld discWorld, Player fromPlayer, Player toPlayer, int amount){
        
        
         // set coin
        for(int count = 0; count < amount; count++){
            for(Integer coinID : fromPlayer.getCoinList()){
                int coin_id = coinID;
                Coin coin = discWorld.getCoin_HASH().get(coin_id);
                
                // add to toPlayer
                toPlayer.getCoinList().add(coin_id);

                // delete from fromPlayer
                Iterator<Integer> listIterator = fromPlayer.getCoinList().iterator();
                while(listIterator.hasNext()){
                    int currentElement = listIterator.next();
                    if(currentElement == coin_id){
                        listIterator.remove();
                        break;
                    }
                }

                // set coin playerID
                coin.setPlayerID(toPlayer.getID());

                break;
                
            }
        }
        
        
        
    }
}
