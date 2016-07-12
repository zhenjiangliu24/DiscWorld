
package Model.player;

import Model.Color;
import Model.coin.Coin;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yucunli
 */
public class Player {
    private String name;
    private int ID;
    private Color color;
    private int order;
    private int personalityCardID; //deduct from PersonalityCard
    private List<Integer> coinList;
    
    private List<Integer> buildingList;
    private List<Integer> minionList;
    private List<Integer> cityAreaCardList;
    private List<Integer> playerCardList;
    private List<Integer> randomEventCardList;

    /**
     *Constructor, set the player ID, name, color, order, personality card ID.
     * @param ID
     * @param name
     * @param color
     * @param order
     * @param personalityCardID
     */
    public Player(int ID, String name, Color color, int order, int personalityCardID) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.order = order;
        this.personalityCardID = personalityCardID;
        
        coinList = new LinkedList<Integer>();
        buildingList = new LinkedList<Integer>();
        minionList = new LinkedList<Integer>();
        cityAreaCardList = new LinkedList<Integer>();
        playerCardList = new LinkedList<Integer>();
        randomEventCardList = new LinkedList<Integer>();
    }
    
    /**
     *Function to get total coin amount.
     * @param Coin_HASH
     * @return player total coin.
     */
    public int getTotalCoinAmount(){
        return coinList.size()*Coin.DENOMINATION;
    }

    /**
     *Function to get the player name.
     * @return player name.
     */
    public String getName() {
        return name;
    }

    /**
     *Function to set the player name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Function to get the player ID.
     * @return player ID.
     */
    public int getID() {
        return ID;
    }

    /**
     *Function to set the player ID.
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *Function to get the player color.
     * @return player color.
     */
    public Color getColor() {
        return color;
    }

    /**
     *Function to set the player color.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *Function to get the player order.
     * @return player order.
     */
    public int getOrder() {
        return order;
    }

    /**
     *Function to set the player order.
     * @param order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     *Function to get the peronality card ID.
     * @return personality card ID.
     */
    public int getPersonalityCardID() {
        return personalityCardID;
    }

    /**
     *Function to set the personality card ID.
     * @param personalityCardID
     */
    public void setPersonalityCardID(int personalityCardID) {
        this.personalityCardID = personalityCardID;
    }

    /**
     *Function to get the player's coin list.
     * @return player coin list.
     */
    public List<Integer> getCoinList() {
        return coinList;
    }

    /**
     *Function to get the player's building list.
     * @return building list.
     */
    public List<Integer> getBuildingList() {
        return buildingList;
    }

    /**
     *Function to get the player's minion list.
     * @return minion list.
     */
    public List<Integer> getMinionList() {
        return minionList;
    }

    /**
     *Function to get the player's city area card lsit.
     * @return city area card list.
     */
    public List<Integer> getCityAreaCardList() {
        return cityAreaCardList;
    }

    /**
     *Function to get the player's player card list.
     * @return player card list.
     */
    public List<Integer> getPlayerCardList() {
        return playerCardList;
    }

    /**
     *Function to get the player's random event card list.
     * @return random event card list.
     */
    public List<Integer> getRandomEventCardList() {
        return randomEventCardList;
    }

    /**
     *Function to set the player's random event card list.
     * @param randomEventCardList
     */
    public void setRandomEventCardList(List<Integer> randomEventCardList) {
        this.randomEventCardList = randomEventCardList;
    }
    
    
}
