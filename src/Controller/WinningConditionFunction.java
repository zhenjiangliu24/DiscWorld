/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ConstantField;
import Model.DiscWorld;
import Model.area.Area;
import Model.card.CityAreaCard;
import Model.piece.BuildingPiece;
import Model.piece.MinionPiece;
import Model.piece.TroubleMarker;
import Model.player.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author yucunli
 */
public class WinningConditionFunction {
    private DiscWorld discWorld;
    
    /**
     *Constructor
     * @param discWorld
     */
    public WinningConditionFunction(DiscWorld discWorld){
        this.discWorld = discWorld;
    }
    
    /**
     *Lord Vetinari winning test
     * @return
     */
    public boolean isWin_LordVetinari(){
        // player got lordVetinari card
        Player playerHoldCard = null;
        for(Player player : discWorld.getPlayer_HASH().values()){
            if(player.getPersonalityCardID() == 1){
                playerHoldCard = player;
                break;
            }
        }
        
        if (playerHoldCard != null) {
            int numPlayers = discWorld.getPlayer_HASH().size();

            Set<Integer> areaList = new HashSet<Integer>();
            for (Integer minionID : playerHoldCard.getMinionList()) {
                int minion_ID = minionID;
                MinionPiece minion = discWorld.getMinionPiece_HASH().get(minion_ID);
                if (minion.getAreaNumber() != ConstantField.DEFAULT_UNDEFINED_PLAYID) {
                    int areaID = minion.getAreaNumber();
                    Area area = discWorld.getArea_HASH().get(areaID);
                    // make sure free of demon
                    if (area.getDemonList().size() == 0) {
                        areaList.add(minion.getAreaNumber());
                    }
                }

            }

            if (numPlayers == 2) {

                if (areaList.size() >= 11) {
                    return true;
                }

            } else if (numPlayers == 3) {

                if (areaList.size() >= 10) {
                    return true;
                }

            } else if (numPlayers == 4) {

                if (areaList.size() >= 9) {
                    return true;
                }

            }
        }

        return false;
    }
    
    /**
     *Lord Selachii winning test
     * @return
     */
    public boolean isWin_LordSelachii(){
        // player got LordSelachii card
        Player playerHoldCard = null;
        for(Player player : discWorld.getPlayer_HASH().values()){
            if(player.getPersonalityCardID() == 2){
                playerHoldCard = player;
                break;
            }
        }
        
        if(playerHoldCard != null){
            int numPlayers = discWorld.getPlayer_HASH().size();
            
            int numCityAreaCard = 0;
            
            for(Integer areaID : playerHoldCard.getCityAreaCardList()){
                Area area = discWorld.getArea_HASH().get(areaID);
                if(area.getDemonList().size() == 0){
                    numCityAreaCard++;
                }
            }
            
            if (numPlayers == 2) {
                if(numCityAreaCard >= 7){
                    return true;
                }
            } else if (numPlayers == 3) {
                if(numCityAreaCard >= 5){
                    return true;
                }
            } else if (numPlayers == 4) {
                if(numCityAreaCard >= 4){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     *Lord Rust winning test
     * @return
     */
    public boolean isWin_LordRust(){
        // player got LordSelachii card
        Player playerHoldCard = null;
        for(Player player : discWorld.getPlayer_HASH().values()){
            if(player.getPersonalityCardID() == 3){
                playerHoldCard = player;
                break;
            }
        }
        
        if(playerHoldCard != null){
            int numPlayers = discWorld.getPlayer_HASH().size();
            
            int numCityAreaCard = 0;
            
            for(Integer areaID : playerHoldCard.getCityAreaCardList()){
                Area area = discWorld.getArea_HASH().get(areaID);
                if(area.getDemonList().size() == 0){
                    numCityAreaCard++;
                }
            }
            
            if (numPlayers == 2) {
                if(numCityAreaCard >= 7){
                    return true;
                }
            } else if (numPlayers == 3) {
                if(numCityAreaCard >= 5){
                    return true;
                }
            } else if (numPlayers == 4) {
                if(numCityAreaCard >= 4){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     *Lord De Worde winning test
     * 
     * @return
     */
    public boolean isWin_LordDeWorde(){
        // player got LordSelachii card
        Player playerHoldCard = null;
        for(Player player : discWorld.getPlayer_HASH().values()){
            if(player.getPersonalityCardID() == 4){
                playerHoldCard = player;
                break;
            }
        }
        
        if(playerHoldCard != null){
            int numPlayers = discWorld.getPlayer_HASH().size();
            
            int numCityAreaCard = 0;
            
            for(Integer areaID : playerHoldCard.getCityAreaCardList()){
                Area area = discWorld.getArea_HASH().get(areaID);
                if(area.getDemonList().size() == 0){
                    numCityAreaCard++;
                }
            }
            
            if (numPlayers == 2) {
                if(numCityAreaCard >= 7){
                    return true;
                }
            } else if (numPlayers == 3) {
                if(numCityAreaCard >= 5){
                    return true;
                }
            } else if (numPlayers == 4) {
                if(numCityAreaCard >= 4){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     *Dragon King of Arms winning test
     * @return
     */
    public boolean isWin_DragonKingOfArms(){
        // player got LordSelachii card
        /*Player playerHoldCard = null;
        for (Player player : discWorld.getPlayer_HASH().values()) {
            if (player.getPersonalityCardID() == 5) {
                playerHoldCard = player;
                break;
            }
        }*/
        int count = 0;
        for(TroubleMarker troubleMarker : discWorld.getTroubleMarker_HASH().values()){
            if(troubleMarker.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                count++;
            }
        }
        
        if(count >= 8){
            return true;
        }else{
            return false;
        }
        
    }
    
    /**
     *Chrysoparse winning test
     * @return
     */
    public boolean isWin_Chrysoprase(){
        // player got LordSelachii card
        Player playerHoldCard = null;
        for (Player player : discWorld.getPlayer_HASH().values()) {
            if (player.getPersonalityCardID() == 6) {
                playerHoldCard = player;
                break;
            }
        }
        
        if(playerHoldCard != null){
            int total_amount = playerHoldCard.getTotalCoinAmount();
            for(Integer cityAreaCardID : playerHoldCard.getCityAreaCardList()){
                int cityAreaCard_ID = cityAreaCardID;
                CityAreaCard cityAreaCard = discWorld.getCityAreaCard_HASH().get(cityAreaCard_ID);
                if(discWorld.getArea_HASH().get(cityAreaCard.getId()).getDemonList().size() == 0)
                    total_amount += discWorld.getArea_HASH().get(cityAreaCard.getId()).getBuildingCost();
            }
            
            if(total_amount >= 50){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     *Commander Vimes winning test
     * @return
     */
    public boolean isWin_CommanderVimes(){
        // player got LordSelachii card
        Player playerHoldCard = null;
        for (Player player : discWorld.getPlayer_HASH().values()) {
            if (player.getPersonalityCardID() == 7) {
                playerHoldCard = player;
                break;
            }
        }
        
        if(playerHoldCard != null){
            if(discWorld.getShuffled_PlayerCard().size() == 0){
                return true;
            }
        } 
        
        return false;
    }
    
    public int getPlayerPoints(Player player){
        int result = 0;
        
        for(Integer minionID : player.getMinionList()){
            MinionPiece minion = discWorld.getMinionPiece_HASH().get(minionID);
            if(minion.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                if(discWorld.getArea_HASH().get(minion.getAreaNumber()).getDemonList().size() == 0)
                    result = result + 5;
            }
        }
        
        for(Integer cityID : player.getCityAreaCardList()){
            if(discWorld.getArea_HASH().get(cityID).getDemonList().size() == 0){
                result = result + discWorld.getArea_HASH().get(cityID).getBuildingCost();
            }
        }
        
        result = result + player.getTotalCoinAmount();
        
        return result;
    }
    /**
     * when running out of player card, test which player win the game by calculating points.
     * @return 
     */
    public int whoWin(){
        int winner = -1;
        
        int highest = 0;
        
        for(Player player : discWorld.getPlayer_HASH().values()){
            if(getPlayerPoints(player) > highest){
                highest = getPlayerPoints(player);
                winner = player.getID();
            }
                
        }
        
        return winner;
    }
}
