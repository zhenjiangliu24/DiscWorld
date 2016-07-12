
package Model;

import Controller.BasicFunction;
import Model.area.Area;
import Model.card.CityAreaCard;
import Model.card.PersonalityCard;
import Model.card.PlayerCard;
import Model.card.RandomEventCard;
import Model.coin.Coin;
import Model.piece.BuildingPiece;
import Model.piece.DemonPiece;
import Model.piece.MinionPiece;
import Model.piece.TrollPiece;
import Model.piece.TroubleMarker;
import Model.player.Player;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *  We might change this later, this is temp class for storing all necessary data
 *  for this game
 * @author yucunli
 */
public class DiscWorld {
    
    private int Cur_Player;
    
    private HashMap<Integer, Player> Player_HASH;
    
    //Hashtable is synchronized, whereas HashMap is not. So, here we use Hashmap
    private HashMap<Integer, Area> Area_HASH;
    //card
    private HashMap<Integer, CityAreaCard> CityAreaCard_HASH;
    private HashMap<Integer, PersonalityCard> PersonalityCard_HASH;
    private HashMap<Integer, PlayerCard> PlayerCard_HASH;
    private HashMap<Integer, RandomEventCard> RandomEventCard_HASH;
    //coin
    private HashMap<Integer, Coin> Coin_HASH;
    //Piece
    private HashMap<Integer, BuildingPiece> BuildingPiece_HASH;
    private HashMap<Integer, MinionPiece> MinionPiece_HASH;
    private HashMap<Integer, DemonPiece> DemonPiece_HASH;
    private HashMap<Integer, TrollPiece> TrollPiece_HASH;
    private HashMap<Integer, TroubleMarker>  TroubleMarker_HASH;
    
    // the shuffled cards
    private List<Integer> Shuffled_PlayerCard;
    private List<Integer> Shuffled_RandomEventCard;
    private List<Integer> Shuffled_CityAreaCard;
    

    /**
     *The constructor the generate all the data hashmap.
     */
    public DiscWorld(){
        this.Player_HASH = new HashMap<Integer, Player>();
        
        this.Area_HASH = new HashMap<Integer, Area>();
        this.CityAreaCard_HASH = new HashMap<Integer, CityAreaCard>();
        this.PersonalityCard_HASH = new HashMap<Integer, PersonalityCard>();
        this.PlayerCard_HASH = new HashMap<Integer, PlayerCard>();
        this.RandomEventCard_HASH = new HashMap<Integer, RandomEventCard>();
        
        this.Coin_HASH = new HashMap<Integer, Coin>();
        
        this.BuildingPiece_HASH = new HashMap<Integer, BuildingPiece>();
        this.DemonPiece_HASH = new HashMap<Integer, DemonPiece>();
        this.MinionPiece_HASH = new HashMap<Integer, MinionPiece>();
        this.TrollPiece_HASH = new HashMap<Integer, TrollPiece>();
        this.TroubleMarker_HASH = new HashMap<Integer, TroubleMarker>();
        
        
        this.Shuffled_PlayerCard = new LinkedList<Integer>();
        this.Shuffled_RandomEventCard = new LinkedList<Integer>();
        this.Shuffled_CityAreaCard = new LinkedList<Integer>();
    }
    
    /**
     *Function to get the coin total amount.
     * @return the coin amount.
     */
    public int getTotalCoinAmount(){
        int totalCoinAmount = 0;
        for(Coin coin : Coin_HASH.values()){
            if(coin.getPlayerID() == ConstantField.DEFAULT_UNDEFINED_PLAYID){
                totalCoinAmount++;
            } 
        }
        return totalCoinAmount;
    }

    /**
     *Function to get the current player.
     * @return current player ID.
     */
    public int getCur_Player() {
        return Cur_Player;
    }

    /**
     *Function to set the current player ID.
     * @param Cur_Player
     */
    public void setCur_Player(int Cur_Player) {
        this.Cur_Player = Cur_Player;
    }

    /**
     *Function to get the player hashmap.
     * @return player hashmap.
     */
    public HashMap<Integer, Player> getPlayer_HASH() {
        return Player_HASH;
    }

    /**
     *Function to get the area hashmap.
     * @return area hasmap.
     */
    public HashMap<Integer, Area> getArea_HASH() {
        return Area_HASH;
    }

    /**
     *Function to get the city area card hashmap.
     * @return city area card hashmap.
     */
    public HashMap<Integer, CityAreaCard> getCityAreaCard_HASH() {
        return CityAreaCard_HASH;
    }

    /**
     *Function to get the personality card hashmap.
     * @return personality card hashmap.
     */
    public HashMap<Integer, PersonalityCard> getPersonalityCard_HASH() {
        return PersonalityCard_HASH;
    }

    /**
     *Function to get the player card hashmap.
     * @return player card hashmap.
     */
    public HashMap<Integer, PlayerCard> getPlayerCard_HASH() {
        return PlayerCard_HASH;
    }

    /**
     *Function to get the random event card hashmap.
     * @return random event card hasmap.
     */
    public HashMap<Integer, RandomEventCard> getRandomEventCard_HASH() {
        return RandomEventCard_HASH;
    }

    /**
     *Function to get the coin hasmap.
     * @return coin hasmap.
     */
    public HashMap<Integer, Coin> getCoin_HASH() {
        return Coin_HASH;
    }

    /**
     *Function to get the building piece hashmap.
     * @return building piece hashmap.
     */
    public HashMap<Integer, BuildingPiece> getBuildingPiece_HASH() {
        return BuildingPiece_HASH;
    }

    /**
     *Function to get the demon piece hashmap.
     * @return demon piece hashmap.
     */
    public HashMap<Integer, DemonPiece> getDemonPiece_HASH() {
        return DemonPiece_HASH;
    }

    /**
     *Function to get the minion piece hashmap.
     * @return minion piece hashmap.
     */
    public HashMap<Integer, MinionPiece> getMinionPiece_HASH() {
        return MinionPiece_HASH;
    }

    /**
     *Function to get the troll piece hashmap.
     * @return troll piece hashmap.
     */
    public HashMap<Integer, TrollPiece> getTrollPiece_HASH() {
        return TrollPiece_HASH;
    }

    /**
     *Function to get the trouble marker hashmap.
     * @return trouble marker hashmap.
     */
    public HashMap<Integer, TroubleMarker> getTroubleMarker_HASH() {
        return TroubleMarker_HASH;
    }

    public List<Integer> getShuffled_PlayerCard() {
        return Shuffled_PlayerCard;
    }

    public void setShuffled_PlayerCard(List<Integer> Shuffled_PlayerCard) {
        this.Shuffled_PlayerCard = Shuffled_PlayerCard;
    }

    public List<Integer> getShuffled_RandomEventCard() {
        return Shuffled_RandomEventCard;
    }

    public void setShuffled_RandomEventCard(List<Integer> Shuffled_RandomEventCard) {
        this.Shuffled_RandomEventCard = Shuffled_RandomEventCard;
    }

    public List<Integer> getShuffled_CityAreaCard() {
        return Shuffled_CityAreaCard;
    }

    public void setShuffled_CityAreaCard(List<Integer> Shuffled_CityAreaCard) {
        this.Shuffled_CityAreaCard = Shuffled_CityAreaCard;
    }
    
    
}
