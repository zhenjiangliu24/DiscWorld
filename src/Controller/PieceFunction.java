/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.ConstantField;
import Model.DiscWorld;
import Model.area.Area;
import Model.piece.BuildingPiece;
import Model.piece.DemonPiece;
import Model.piece.MinionPiece;
import Model.piece.TrollPiece;
import Model.piece.TroubleMarker;
import Model.player.Player;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author yucunli
 */
public class PieceFunction {
    /**
     * determine whether the player can add minion to an area 
     * @param discWorld
     * @param playerID
     * @param areaID
     * @return 
     */
    public boolean canAddMinion(DiscWorld discWorld, int playerID, int areaID){
        boolean canAddMinion = false;
        List<Integer> adjacentArea = discWorld.getArea_HASH().get(areaID).getAdjacentArea();
        String playerColor = discWorld.getPlayer_HASH().get(playerID).getColor().toString();
        int minionNumber = Utils.getMinionNumberInArea(discWorld.getArea_HASH().get(areaID).getMinionList(), discWorld.getMinionPiece_HASH(), playerColor);
        if(minionNumber>0){
            canAddMinion = true;
        }else{
            
            for (Integer area : adjacentArea) {
                int adjMinionNumber = Utils.getMinionNumberInArea(discWorld.getArea_HASH().get(area).getMinionList(), discWorld.getMinionPiece_HASH(), playerColor);
                if(adjMinionNumber>0){
                    canAddMinion = true;
                }
            }
        }
        return canAddMinion;
    }
    /**
     * add a minion piece to an area
     * @param discWorld
     * @param playerID
     * @param areaID
     * @return 
     */
    public boolean minionAdd(DiscWorld discWorld, int playerID, int areaID){
        if (canAddMinion(discWorld, playerID, areaID)) {
            for (Integer minionID : discWorld.getPlayer_HASH().get(playerID).getMinionList()) {
                int minion_id = minionID;
                MinionPiece minion = discWorld.getMinionPiece_HASH().get(minion_id);
                if (minion.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER) {
                    // set piece area num
                    minion.setAreaNumber(areaID);

                    // add to area
                    Area area = discWorld.getArea_HASH().get(areaID);
                    area.getMinionList().add(minion_id);
                    break;
                }
            }
        return true;
        }else{
            return false;
        }
            
        

    }
    /**
     * delete a minion piece from an area
     * @param discWorld
     * @param playerID
     * @param areaID 
     */
    public void minionDelete(DiscWorld discWorld, int playerID, int areaID){
        for(Integer minionID : discWorld.getPlayer_HASH().get(playerID).getMinionList()){
            int minion_id = minionID;
            MinionPiece minion = discWorld.getMinionPiece_HASH().get(minion_id);
            if(minion.getAreaNumber() == areaID){
                // set minion
                minion.setAreaNumber(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
                
                // delete from area
                Area area = discWorld.getArea_HASH().get(areaID);
                Iterator<Integer> listIterator = area.getMinionList().iterator();
                while(listIterator.hasNext()){
                    int currentElement = listIterator.next();
                    if(currentElement == minion.getId()){
                        listIterator.remove();
                        break;
                    }
                }
                
                break;
            }
        }
    }
    /**
     * move a player's minion from fromAreaID to AreaID.
     * @param discWorld
     * @param playerID
     * @param fromAreaID
     * @param toAreaID 
     */
    public void minionMove(DiscWorld discWorld, int playerID, int fromAreaID, int toAreaID){
        //what time we cant move minio into that area
        Area fromArea = discWorld.getArea_HASH().get(fromAreaID);
        Area toArea = discWorld.getArea_HASH().get(toAreaID);
        List<Integer> adjacentArea = discWorld.getArea_HASH().get(fromAreaID).getAdjacentArea();
        if(adjacentArea.contains(toAreaID)){
            for(Integer minionID : fromArea.getMinionList()){
            //if this minion is belong to playerID => there is one playerID's minion in area
                if (discWorld.getMinionPiece_HASH().get(minionID).getPlayerID() == playerID) {
                    MinionPiece minion = discWorld.getMinionPiece_HASH().get(minionID);
                    //removed from fromArea
                    Iterator<Integer> listIterator = fromArea.getMinionList().iterator();
                    while (listIterator.hasNext()) {
                        Integer currentElement = listIterator.next();
                        if (currentElement.equals(minionID)) {
                            listIterator.remove();
                            break;
                        }
                    }
                    //set minion
                    minion.setAreaNumber(toAreaID);
                    //add to toArea
                    toArea.getMinionList().add(minionID);
                    break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "You can not move minion to area:"+toAreaID);
        }
        
    }
    
    
    /**
     * function determine whether a player number playerID can add
     * a building piece to area areaID
     * @param discWorld
     * @param playerID
     * @param areaID
     * @return 
     */
    public boolean canAddBuilding(DiscWorld discWorld, int playerID, int areaID){
        
        String playerColor = discWorld.getPlayer_HASH().get(playerID).getColor().toString();
        Area area = discWorld.getArea_HASH().get(areaID);
        int minionNumber = Utils.getMinionNumberInArea(area.getMinionList(), discWorld.getMinionPiece_HASH(), playerColor);
        
        int playerMoney = discWorld.getPlayer_HASH().get(playerID).getTotalCoinAmount();
        int areaPrice = area.getBuildingCost();
        boolean hasBuildingPiece = false;
        for(Integer buildingID : discWorld.getPlayer_HASH().get(playerID).getBuildingList()){
            BuildingPiece building = discWorld.getBuildingPiece_HASH().get(buildingID);
                // if this building is not being used
                if(building.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                    hasBuildingPiece = true;
                }
        }
        if((minionNumber>0)&&(area.getTroubleMarkerNum()==ConstantField.DEFAULT_PIECE_AREA_NUMBER)&&(playerMoney>=areaPrice)&&(hasBuildingPiece)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * player add a building piece to an area
     * @param discWorld
     * @param playerID
     * @param areaID 
     */
    public void buildingAdd(DiscWorld discWorld, int playerID, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        Player player = discWorld.getPlayer_HASH().get(playerID);
        // if there is no building in this area
        if(area.getBuildingNum() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
            for(Integer buildingID : discWorld.getPlayer_HASH().get(playerID).getBuildingList()){
                BuildingPiece building = discWorld.getBuildingPiece_HASH().get(buildingID);
                
                // if this building is not being used
                if(building.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                    // set building
                    building.setAreaNumber(areaID);
                    // set area
                    area.setBuildingNum(building.getId());
                    // set player(because areaID equals with cityAreaCard)
                    player.getCityAreaCardList().add(areaID);
                    
                    break;
                }
            }
            
        }
    }
    /**
     * delete a buildng piece from an area
     * @param discWorld
     * @param areaID 
     */
    public void buildingDelete(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        // if there is a building in this area
        if(area.getBuildingNum() != ConstantField.DEFAULT_PIECE_AREA_NUMBER){
            BuildingPiece building = discWorld.getBuildingPiece_HASH().get(area.getBuildingNum());
            // set building
            building.setAreaNumber(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
            // set area
            area.setBuildingNum(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
            // set player
            for (Player player : discWorld.getPlayer_HASH().values()) {
                if (player.getCityAreaCardList().contains(areaID)) {
                    Iterator<Integer> listIterator = player.getCityAreaCardList().iterator();
                    while (listIterator.hasNext()) {
                        int currentElement = listIterator.next();
                        if (currentElement == areaID) {
                            listIterator.remove();
                            break;
                        }
                    }
                    break;
                }
            }
            
            
        }
    }
    /**
     * determine an area can add troublemarker or not.
     * @param discWorld
     * @param areaID
     * @return 
     */
    public boolean canAddTroubleMarker(DiscWorld discWorld, int areaID){
        
        Area area = discWorld.getArea_HASH().get(areaID);
        if(area.getMinionList().size()>1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * add a troublemarker piece to an area
     * @param discWorld
     * @param areaID 
     */
    public void troubleMarkerAdd(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        for(TroubleMarker troubleMarker : discWorld.getTroubleMarker_HASH().values()){
            if(troubleMarker.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                // set troubleMarker
                troubleMarker.setAreaNumber(areaID);
                // set area
                area.setTroubleMarkerNum(troubleMarker.getId());
                break;
            }
        }
    }
    /**
     * delete a troublemarker piece from an area
     * @param discWorld
     * @param areaID 
     */
    public void troubleMarkerDelete(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        // if there is a troubleMarker in this area
        if(area.getTroubleMarkerNum() != ConstantField.DEFAULT_PIECE_AREA_NUMBER){
            TroubleMarker troubleMarker = discWorld.getTroubleMarker_HASH().get(area.getTroubleMarkerNum());
            // set troubleMarker
            troubleMarker.setAreaNumber(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
            // set area
            area.setTroubleMarkerNum(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
        }
    }
    /**
     * add a troll to an area
     * @param discWorld
     * @param areaID 
     */
    public void trollAdd(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        for(TrollPiece troll : discWorld.getTrollPiece_HASH().values()){
            if(troll.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                // set area
                area.getTrollList().add(troll.getId());
                // set troll
                troll.setAreaNumber(areaID);
                
                break;
            }
        }
    }
    /**
     * delete a troll from an area
     * @param discWorld
     * @param areaID 
     */
    public void trollDelete(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        if(area.getTrollList().size() > 0){
            // set area
            Iterator<Integer> listIterator = area.getTrollList().iterator();
            int trollID = area.getTrollList().get(0);
            listIterator.next();
            listIterator.remove();  
            
            // set troll
            discWorld.getTrollPiece_HASH().get(trollID).setAreaNumber(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
        }
    }
    /**
     * add a demon to an area
     * @param discWorld
     * @param areaID 
     */
    public void demonAdd(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        for(DemonPiece demon : discWorld.getDemonPiece_HASH().values()){
            if(demon.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                // set area
                area.getDemonList().add(demon.getId());
                // set demon
                demon.setAreaNumber(areaID);
                break;
            }
        }
    }
    /**
     * determine whether a demon can be added.
     * @param discWorld
     * @return 
     */
    public boolean canAddDemon(DiscWorld discWorld){
        int demonCount = 0;
        for (DemonPiece demon : discWorld.getDemonPiece_HASH().values()) {
            if (demon.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER) {
                demonCount++;
            }
        }
        if(demonCount>0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * delete a demon from an area
     * @param discWorld
     * @param areaID 
     */
    public void demonDelete(DiscWorld discWorld, int areaID){
        Area area = discWorld.getArea_HASH().get(areaID);
        if(area.getDemonList().size() > 0){
            // set area
            Iterator<Integer> listIterator = area.getDemonList().iterator();
            int demonID = area.getDemonList().get(0);
            listIterator.next();
            listIterator.remove(); 
            
            // set demon
            discWorld.getDemonPiece_HASH().get(demonID).setAreaNumber(ConstantField.DEFAULT_PIECE_AREA_NUMBER);
        }
    }
    
    
}
