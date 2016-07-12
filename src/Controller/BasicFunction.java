

package Controller;

import Model.Color;
import Model.ConstantField;
import Model.DiscWorld;
import Model.area.Area;
import Model.card.BrownPlayerCard;
import Model.card.CityAreaCard;
import Model.card.GreenPlayerCard;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author yucunli
 */
public class BasicFunction {
    
    
    private int numPlayer;
    
    private DiscWorld discWorld;
    
    /**
     *Function to load CSV file.
     * @param filename
     */
    public void load(String filename){
        discWorld = new DiscWorld();
        
        LoadCSV loadCSV = new LoadCSV(ConstantField.INIT_DATA_FILE_DIR);
        
        loadCSV.loadArea(discWorld.getArea_HASH());
        
        loadCSV = new LoadCSV(ConstantField.STATUS_DATA_FILE_DIR+filename+"/");
        
        loadCSV.loadCityAreaCard(discWorld.getCityAreaCard_HASH());
        loadCSV.loadPersonalityCard(discWorld.getPersonalityCard_HASH());
        loadCSV.loadPlayerCard(discWorld.getPlayerCard_HASH(), ConstantField.PLAYER_CARD_FILENAME);
        loadCSV.loadRandomEventCard(discWorld.getRandomEventCard_HASH());
        
        loadCSV.loadBuildingPiece(discWorld.getBuildingPiece_HASH());
        loadCSV.loadMinionPiece(discWorld.getMinionPiece_HASH());
        loadCSV.loadDemonPiece(discWorld.getDemonPiece_HASH());
        loadCSV.loadTrollPiece(discWorld.getTrollPiece_HASH());
        loadCSV.loadTroubleMarker(discWorld.getTroubleMarker_HASH());
        
        loadCSV.loadPlayer(discWorld.getPlayer_HASH());
        
        loadCSV.loadCoin(discWorld.getCoin_HASH());
        
        this.numPlayer = discWorld.getPlayer_HASH().size();
        // decide cur_player
        for(Player player : discWorld.getPlayer_HASH().values()){
            if(player.getOrder() == 0){
                discWorld.setCur_Player(player.getID());
            }
        }
        // associate PersonalityCard to player
        for(PersonalityCard personalityCard : discWorld.getPersonalityCard_HASH().values()){
            if(personalityCard.getPlayerID() != ConstantField.DEFAULT_UNDEFINED_PLAYID){
                discWorld.getPlayer_HASH().get(personalityCard.getPlayerID()).
                        setPersonalityCardID(personalityCard.getId());
            }
        }
        // associate CityAreaCard to player
        for(CityAreaCard cityAreaCard : discWorld.getCityAreaCard_HASH().values()){
            if(cityAreaCard.getPlayerID() != ConstantField.DEFAULT_UNDEFINED_PLAYID) {
                discWorld.getPlayer_HASH().get(cityAreaCard.getPlayerID()).getCityAreaCardList().add(cityAreaCard.getId());
            }
        }
        // associate PlayerCard to player
        for(PlayerCard playerCard : discWorld.getPlayerCard_HASH().values()){
            if(playerCard.getPlayerID() != ConstantField.DEFAULT_UNDEFINED_PLAYID) {
                discWorld.getPlayer_HASH().get(playerCard.getPlayerID()).getPlayerCardList().add(playerCard.getId());
            }
        }
        // associate RandomEventCard to player
        for(RandomEventCard randomEventCard : discWorld.getRandomEventCard_HASH().values()) {
            if(randomEventCard.getPlayerID() != ConstantField.DEFAULT_UNDEFINED_PLAYID) {
                discWorld.getPlayer_HASH().get(randomEventCard.getPlayerID()).getRandomEventCardList().add(randomEventCard.getId());
            }
        }
        // associate Coin to player
        for(Coin coin : discWorld.getCoin_HASH().values()){
            if(coin.getPlayerID() != ConstantField.DEFAULT_UNDEFINED_PLAYID){
                discWorld.getPlayer_HASH().get(coin.getPlayerID()).getCoinList().add(coin.getId());
            }
        }
        // associate Building Piece to player and Area
        for(BuildingPiece buildingPiece : discWorld.getBuildingPiece_HASH().values()){
            discWorld.getPlayer_HASH().get(buildingPiece.getPlayerID()).getBuildingList().add(buildingPiece.getId());
            if(buildingPiece.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                discWorld.getArea_HASH().get(buildingPiece.getAreaNumber()).setBuildingNum(buildingPiece.getId());
            }
        }
        // associate Minion Piece to player and Area
        for(MinionPiece minionPiece : discWorld.getMinionPiece_HASH().values()){
            discWorld.getPlayer_HASH().get(minionPiece.getPlayerID()).getMinionList().add(minionPiece.getId());
            if(minionPiece.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                discWorld.getArea_HASH().get(minionPiece.getAreaNumber()).getMinionList().add(minionPiece.getId());
            }
        }
        // associate Demon Piece to Area
        for(DemonPiece demonPiece : discWorld.getDemonPiece_HASH().values()){
            if(demonPiece.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER) {
                discWorld.getArea_HASH().get(demonPiece.getAreaNumber()).getDemonList().add(demonPiece.getId());
            }
        }
        // associate Troll Piece to Area
        for(TrollPiece trollPiece : discWorld.getTrollPiece_HASH().values()){
            if(trollPiece.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER) {
                discWorld.getArea_HASH().get(trollPiece.getAreaNumber()).getTrollList().add(trollPiece.getId());
            }
        }
        // associate TroubleMarker to Area
        for(TroubleMarker troubleMarker : discWorld.getTroubleMarker_HASH().values()){
            if(troubleMarker.getAreaNumber() != ConstantField.DEFAULT_PIECE_AREA_NUMBER) {
                discWorld.getArea_HASH().get(troubleMarker.getAreaNumber()).setTroubleMarkerNum(troubleMarker.getId());
            }
        }
        
        
        shuffleAllCards();
    }
    
    /**
     *Function to save CSV file.
     * @param filename
     */
    public void save(String filename){
        SaveCSV saveCSV = new SaveCSV(ConstantField.STATUS_DATA_FILE_DIR+filename+"/");
        
        saveCSV.savePlayer(discWorld.getPlayer_HASH());
        
        saveCSV.saveCityAreaCard(discWorld.getCityAreaCard_HASH());
        saveCSV.savePersonalityCard(discWorld.getPersonalityCard_HASH());
        saveCSV.saveRandomEventCard(discWorld.getRandomEventCard_HASH());
        saveCSV.savePlayerCard(discWorld.getPlayerCard_HASH());
        
        saveCSV.saveCoin(discWorld.getCoin_HASH());
        
        saveCSV.saveBuildingPiece(discWorld.getBuildingPiece_HASH());
        saveCSV.saveMinionPiece(discWorld.getMinionPiece_HASH());
        saveCSV.saveDemonPiece(discWorld.getDemonPiece_HASH());
        saveCSV.saveTrollPiece(discWorld.getTrollPiece_HASH());
        saveCSV.saveTroubleMarker(discWorld.getTroubleMarker_HASH());
    }
    
    /**
     *Function to initial the game and all the data.
     * @param player
     * @param colorArray
     */
    public void init(String[] player, String[] playerOrder, Color[] colorArray){
        discWorld = new DiscWorld();
        this.numPlayer = player.length;
        discWorld.setCur_Player(Integer.parseInt(playerOrder[0]) - 1);
        
        LoadCSV loadCSV = new LoadCSV(ConstantField.INIT_DATA_FILE_DIR);
        
        loadCSV.loadArea(discWorld.getArea_HASH());
        loadCSV.loadCityAreaCard(discWorld.getCityAreaCard_HASH());
        loadCSV.loadPersonalityCard(discWorld.getPersonalityCard_HASH());
        loadCSV.loadRandomEventCard(discWorld.getRandomEventCard_HASH());
        loadCSV.loadPlayerCard(discWorld.getPlayerCard_HASH(), ConstantField.PLAYER_CARD_FILENAME);
        
        beforeShuffle();
        shuffleAllCards();
        
        for(int count = 0; count < ConstantField.NUM_COIN; count++){
            discWorld.getCoin_HASH().put(count, new Coin(count, ConstantField.DEFAULT_UNDEFINED_PLAYID));
        }
        
        // init Player, Building Piece, Minion Piece
        for(int index_of_player = 0; index_of_player < player.length; index_of_player++){
            
            // init player from id = 0
            discWorld.getPlayer_HASH().
                    put(index_of_player, new Player(index_of_player, player[index_of_player], colorArray[index_of_player], Integer.parseInt(playerOrder[index_of_player]), ConstantField.DEFAULT_PLAYER_PERSONALITYCARD));
        
            // create building piece
            for(int count = 0; count < ConstantField.NUM_OF_BUILDING_PIECE_SET; count++){
                BuildingPiece buildingPiece = new BuildingPiece(index_of_player*ConstantField.BUILDING_PIECE_ID_DIFF+count, index_of_player, ConstantField.DEFAULT_PIECE_AREA_NUMBER, colorArray[index_of_player]);
                discWorld.getBuildingPiece_HASH().put(buildingPiece.getId(), buildingPiece);
                
                // associate building with player
                discWorld.getPlayer_HASH().get(index_of_player).getBuildingList().add(buildingPiece.getId());
                
            }
            // create minion Piece
            for(int count = 0; count < ConstantField.NUM_OF_MINION_PIECE_SET; count++){
                MinionPiece minionPiece = new MinionPiece(index_of_player*ConstantField.MINION_PIECE_ID_DIFF+count, index_of_player, ConstantField.DEFAULT_PIECE_AREA_NUMBER, colorArray[index_of_player]);
                discWorld.getMinionPiece_HASH().put(minionPiece.getId(), minionPiece);
                
                // put one minion to 3 area
                if(count == 0){
                    discWorld.getMinionPiece_HASH().get(minionPiece.getId()).setAreaNumber(ConstantField.THE_SHADOW_AREA_NUM);
                    minionPiece.setAreaNumber(ConstantField.THE_SHADOW_AREA_NUM);
                    // associate minion with area
                    discWorld.getArea_HASH().get(ConstantField.THE_SHADOW_AREA_NUM).getMinionList().add(minionPiece.getId());
                } else if(count == 1){
                    discWorld.getMinionPiece_HASH().get(minionPiece.getId()).setAreaNumber(ConstantField.THE_SCOURS_AREA_NUM);
                    minionPiece.setAreaNumber(ConstantField.THE_SCOURS_AREA_NUM);
                    // associate minion with area
                    discWorld.getArea_HASH().get(ConstantField.THE_SCOURS_AREA_NUM).getMinionList().add(minionPiece.getId());
                } else if(count == 2){
                    discWorld.getMinionPiece_HASH().get(minionPiece.getId()).setAreaNumber(ConstantField.THE_DOLLY_SISTERS_AREA_NUM);
                    minionPiece.setAreaNumber(ConstantField.THE_DOLLY_SISTERS_AREA_NUM);
                    // associate minion with area
                    discWorld.getArea_HASH().get(ConstantField.THE_DOLLY_SISTERS_AREA_NUM).getMinionList().add(minionPiece.getId());
                }
                
                
                
                // associate minion with player
                discWorld.getPlayer_HASH().get(index_of_player).getMinionList().add(minionPiece.getId());
                
            }
            
            
            
            // give each player 10 coin
            for(Coin coin : discWorld.getCoin_HASH().values()){
                if(coin.getPlayerID() == ConstantField.DEFAULT_UNDEFINED_PLAYID){
                    discWorld.getPlayer_HASH().get(index_of_player).getCoinList().add(coin.getId());
                    coin.setPlayerID(index_of_player);
                    if(discWorld.getPlayer_HASH().get(index_of_player).getCoinList().size() == 10){
                        break;
                    }
                }
            }
            
            CardFunction cardFunction = new CardFunction();
            for(int i = 0; i< ConstantField.DEFAULT_NUM_PLAYER_CARD_FOR_PLAYER; i++){
                cardFunction.drawPlayerCard(discWorld, discWorld.getPlayer_HASH().get(index_of_player));
            }
            
        }
        
        // give each player a Personality Card
        Random rand = new Random();
        Set<Integer> generatedRandomNum = new LinkedHashSet<Integer>();
        while (generatedRandomNum.size() < numPlayer) {
            int randomNumber = rand.nextInt(ConstantField.NUM_PERSONALITY_CARD) + 1;
            generatedRandomNum.add(randomNumber);
        }
        Queue<Integer> generatedRandomList = new LinkedList<Integer>();
        for(Integer integer : generatedRandomNum){
            generatedRandomList.add(integer);
        }
        for(Player temp_player : discWorld.getPlayer_HASH().values()){
            // if two people, we dont have Chrysoprase
            if(player.length == 2){
                temp_player.setPersonalityCardID(generatedRandomList.poll());
                if(temp_player.getPersonalityCardID() == 6){
                    temp_player.setPersonalityCardID(generatedRandomList.poll());
                }
                discWorld.getPersonalityCard_HASH().get(temp_player.getPersonalityCardID()).setPlayerID(temp_player.getID());
            }else{
                temp_player.setPersonalityCardID(generatedRandomList.poll());
                discWorld.getPersonalityCard_HASH().get(temp_player.getPersonalityCardID()).setPlayerID(temp_player.getID());
            }
        }
        
        
        for(int count = 0; count < ConstantField.NUM_DEMON_PIECE; count++){
            DemonPiece demonPiece = new DemonPiece(count, ConstantField.DEFAULT_PIECE_AREA_NUMBER);
            discWorld.getDemonPiece_HASH().put(count, demonPiece);
        }
        
        for(int count = 0; count < ConstantField.NUM_TROLL_PIECE; count++){
            TrollPiece trollPiece = new TrollPiece(count, ConstantField.DEFAULT_PIECE_AREA_NUMBER);
            discWorld.getTrollPiece_HASH().put(count, trollPiece);
        }
        
        for(int count = 0; count < ConstantField.NUM_TROUBLE_MARKER; count++){
            TroubleMarker troubleMarker = new TroubleMarker(count, ConstantField.DEFAULT_PIECE_AREA_NUMBER);
            discWorld.getTroubleMarker_HASH().put(count, troubleMarker);
            
            // put one trouble marker to 3 area
                if(count == 0){
                    discWorld.getTroubleMarker_HASH().get(troubleMarker.getId()).setAreaNumber(ConstantField.THE_SHADOW_AREA_NUM);
                    troubleMarker.setAreaNumber(ConstantField.THE_SHADOW_AREA_NUM);
                    discWorld.getArea_HASH().get(ConstantField.THE_SHADOW_AREA_NUM).setTroubleMarkerNum(troubleMarker.getId());
                    
                } else if(count == 1){
                    discWorld.getTroubleMarker_HASH().get(troubleMarker.getId()).setAreaNumber(ConstantField.THE_SCOURS_AREA_NUM);
                    troubleMarker.setAreaNumber(ConstantField.THE_SCOURS_AREA_NUM);
                    discWorld.getArea_HASH().get(ConstantField.THE_SCOURS_AREA_NUM).setTroubleMarkerNum(troubleMarker.getId());
                    
                } else if(count == 2){
                    discWorld.getTroubleMarker_HASH().get(troubleMarker.getId()).setAreaNumber(ConstantField.THE_DOLLY_SISTERS_AREA_NUM);
                    troubleMarker.setAreaNumber(ConstantField.THE_DOLLY_SISTERS_AREA_NUM);
                    discWorld.getArea_HASH().get(ConstantField.THE_DOLLY_SISTERS_AREA_NUM).setTroubleMarkerNum(troubleMarker.getId());
                    
                }
        }
        
        
    }
    
    private void beforeShuffle(){
        //init order for palyerCard
        for(PlayerCard playerCard : discWorld.getPlayerCard_HASH().values()){
            if(playerCard.getId() < 100){
                playerCard.setOrder((int)(Math.random()*100));
            }else{
                playerCard.setOrder((int)(Math.random()*100+200));
            }
        }
        
        //init order for RandomEventCard
        for(RandomEventCard randomEventCard : discWorld.getRandomEventCard_HASH().values()){
            randomEventCard.setOrder((int)Math.random()*100);
        }
    }
    
    private void shuffleAllCards(){
        // Shuffle PlayerCard
        List<PlayerCard> playerCardList = new ArrayList<PlayerCard>();
        for(PlayerCard playerCard : discWorld.getPlayerCard_HASH().values()){
            if(playerCard.getUsed() != ConstantField.CARD_USED)
                playerCardList.add(playerCard);
        }
        Collections.sort(playerCardList, new Comparator<PlayerCard>(){

            @Override
            public int compare(PlayerCard lhs, PlayerCard rhs) {
                return lhs.getOrder() - rhs.getOrder();
            }
            
        });
        for(PlayerCard playerCard : playerCardList){
            discWorld.getShuffled_PlayerCard().add(playerCard.getId());
        }
        
        //Shuffle RandomEventCard
        List<RandomEventCard> randomEventCardList = new ArrayList<RandomEventCard>();
        for(RandomEventCard randomEventCard : discWorld.getRandomEventCard_HASH().values()){
            randomEventCardList.add(randomEventCard);
        }
        Collections.sort(randomEventCardList, new Comparator<RandomEventCard>(){

            @Override
            public int compare(RandomEventCard lhs, RandomEventCard rhs) {
                return lhs.getOrder() - rhs.getOrder();
            }
            
        });
        for(RandomEventCard randomEventCard : randomEventCardList){
            if(randomEventCard.getUsed() != ConstantField.CARD_USED)
                discWorld.getShuffled_RandomEventCard().add(randomEventCard.getId());
        }
        
    }

    /**
     *Function to get the player number.
     * @return player number.
     */
    public int getNumPlayer() {
        return numPlayer;
    }

    /**
     *Function to set player number.
     * @param numPlayer
     */
    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    /**
     *Function to get the game data.
     * @return  DiscWorld
     */
    public DiscWorld getDiscWorld() {
        return discWorld;
    }

    /**
     *Function to set the game data.
     * @param discWorld
     */
    public void setDiscWorld(DiscWorld discWorld) {
        this.discWorld = discWorld;
    }
    
    
}
