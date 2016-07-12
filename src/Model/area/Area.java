

package Model.area;

import Controller.BasicFunction;
import Model.ConstantField;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yucunli
 */
public class Area {
    private String namePlate;
    private int number;
    private int buildingCost;
    
    // encapulate way ? 
    private List<Integer> adjacentArea;
    
    private List<Integer> minionList;
    private List<Integer> demonList;
    private List<Integer> trollList;
    
    //default is -1, which meaning null;
    private int buildingNum;
    private int troubleMarkerNum;

    /**
     *Constructor to generate all the piece list in the map area.
     */
    public Area(){
        adjacentArea = new ArrayList<Integer>();
        minionList = new LinkedList<Integer>();
        demonList = new LinkedList<Integer>();
        trollList = new LinkedList<Integer>();
        
        buildingNum = ConstantField.DEFAULT_PIECE_AREA_NUMBER;
        troubleMarkerNum = ConstantField.DEFAULT_PIECE_AREA_NUMBER;
        
    }

    /**
     *Function to get the name plate.
     * @return name plate.
     */
    public String getNamePlate() {
        return namePlate;
    }

    /**
     *Function to set the name plate.
     * @param namePlate
     */
    public void setNamePlate(String namePlate) {
        this.namePlate = namePlate;
    }

    /**
     *Function to get the area number.
     * @return area number.
     */
    public int getNumber() {
        return number;
    }

    /**
     *Function to set the area number.
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     *Function to get the building cost.
     * @return building cost.
     */
    public int getBuildingCost() {
        return buildingCost;
    }

    /**
     *Function to set the building cost.
     * @param buildingCost
     */
    public void setBuildingCost(int buildingCost) {
        this.buildingCost = buildingCost;
    }

    /**
     *Function to get the building number.
     * @return building number.
     */
    public int getBuildingNum() {
        return buildingNum;
    }

    /**
     *Function to set the building number.
     * @param buildingNum
     */
    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    /**
     *Function to get the trouble marker number.
     * @return trouble marker number.
     */
    public int getTroubleMarkerNum() {
        return troubleMarkerNum;
    }

    /**
     *Function to set the trouble marker number.
     * @param troubleMarkerNum
     */
    public void setTroubleMarkerNum(int troubleMarkerNum) {
        this.troubleMarkerNum = troubleMarkerNum;
    }

    /**
     *Function to get the adjacent area list.
     * @return adjacent area list.
     */
    public List<Integer> getAdjacentArea() {
        return adjacentArea;
    }

    /**
     *Function to get the minion list from an area.
     * @return minion list.
     */
    public List<Integer> getMinionList() {
        return minionList;
    }

    /**
     *Function to get the demon list from an area.
     * @return demon list.
     */
    public List<Integer> getDemonList() {
        return demonList;
    }

    /**
     *Function to get the troll list from an area.
     * @return troll list.
     */
    public List<Integer> getTrollList() {
        return trollList;
    }
    
    
}
