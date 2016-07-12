
package Controller;

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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author yucunli
 */
public class SaveCSV {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private String file_position;
    
    /**
     *Function to save the CSV file.
     * @param file_position
     */
    public SaveCSV(String file_position){
        this.file_position = file_position;
        File file = new File(file_position);
        file.mkdir();
    }

    /**
     *Function to save the player information.
     * @param Player_HASH
     */
    public void savePlayer(HashMap<Integer, Player> Player_HASH) {
        String fileName = file_position+"player.csv";
        String FILE_HEADER = "id,name,color,order" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(Player player : Player_HASH.values()){
                fileWriter.append(String.valueOf(player.getID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(player.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(player.getColor().toString());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(player.getOrder()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
        
    }

    /**
     *Function to save the city area card.
     * @param CityAreaCard_HASH
     */
    public void saveCityAreaCard(HashMap<Integer, CityAreaCard> CityAreaCard_HASH){
        String fileName = file_position+"CityAreaCard.csv";
        String FILE_HEADER = "id,name,ability,playerID" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName, false);
            fileWriter.append(FILE_HEADER.toString());
            
            for(CityAreaCard cityAreaCard : CityAreaCard_HASH.values()){
                fileWriter.append(String.valueOf(cityAreaCard.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(cityAreaCard.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(cityAreaCard.getAbility());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(cityAreaCard.getPlayerID()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the personality card.
     * @param PersonalityCard_HASH
     */
    public void savePersonalityCard(HashMap<Integer, PersonalityCard> PersonalityCard_HASH){
        String fileName = file_position+"PersonalityCard.csv";
        String FILE_HEADER = "id,name,victoryCondition,playerID" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(PersonalityCard personalityCard : PersonalityCard_HASH.values()){
                fileWriter.append(String.valueOf(personalityCard.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(personalityCard.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(personalityCard.getVictoryCondition());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(personalityCard.getPlayerID()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the random event card.
     * @param RandomEventCard_HASH
     */
    public void saveRandomEventCard(HashMap<Integer, RandomEventCard> RandomEventCard_HASH){
        String fileName = file_position+"RandomEventCard.csv";
        String FILE_HEADER = "id,name,effect,playerID,order,used" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(RandomEventCard randomEventCard : RandomEventCard_HASH.values()){
                fileWriter.append(String.valueOf(randomEventCard.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(randomEventCard.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(randomEventCard.getEffect());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(randomEventCard.getPlayerID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(randomEventCard.getOrder()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(randomEventCard.getUsed()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the player card.
     * @param PlayerCard_HASH
     */
    public void savePlayerCard(HashMap<Integer, PlayerCard> PlayerCard_HASH){
        String fileName = file_position+"PlayerCard.csv";
        String FILE_HEADER = "id,name,text,action,playerID,type,order,used" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
    
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(PlayerCard playerCard : PlayerCard_HASH.values()){
                fileWriter.append(String.valueOf(playerCard.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(playerCard.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(playerCard.getText());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(playerCard.getAction_text());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(playerCard.getPlayerID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(playerCard.getType());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(playerCard.getOrder()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(playerCard.getUsed()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the building piece.
     * @param BuildingPiece_HASH
     */
    public void saveBuildingPiece(HashMap<Integer, BuildingPiece> BuildingPiece_HASH){
        String fileName = file_position+"BuildingPiece.csv";
        String FILE_HEADER = "id,areaNumber,color,playerID" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(BuildingPiece buildingPiece : BuildingPiece_HASH.values()){
                fileWriter.append(String.valueOf(buildingPiece.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(buildingPiece.getAreaNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(buildingPiece.getColor().toString());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(buildingPiece.getPlayerID()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the minion piece.
     * @param MinionPiece_HASH
     */
    public void saveMinionPiece(HashMap<Integer, MinionPiece> MinionPiece_HASH){
        String fileName = file_position+"MinionPiece.csv";
        String FILE_HEADER = "id,areaNumber,color,playerID" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(MinionPiece minionPiece : MinionPiece_HASH.values()){
                fileWriter.append(String.valueOf(minionPiece.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(minionPiece.getAreaNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(minionPiece.getColor().toString());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(minionPiece.getPlayerID()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the demon piece.
     * @param DemonPiece_HASH
     */
    public void saveDemonPiece(HashMap<Integer, DemonPiece> DemonPiece_HASH){
        String fileName = file_position+"DemonPiece.csv";
        String FILE_HEADER = "id,areaNumber" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(DemonPiece demonPiece : DemonPiece_HASH.values()){
                fileWriter.append(String.valueOf(demonPiece.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(demonPiece.getAreaNumber()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the troll piece.
     * @param TrollPiece_HASH
     */
    public void saveTrollPiece(HashMap<Integer, TrollPiece> TrollPiece_HASH){
        String fileName = file_position+"TrollPiece.csv";
        String FILE_HEADER = "id,areaNumber" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(TrollPiece trollPiece : TrollPiece_HASH.values()){
                fileWriter.append(String.valueOf(trollPiece.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(trollPiece.getAreaNumber()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the trouble marker.
     * @param TroubleMarker_HASH
     */
    public void saveTroubleMarker(HashMap<Integer, TroubleMarker>  TroubleMarker_HASH){
        String fileName = file_position+"TroubleMarker.csv";
        String FILE_HEADER = "id,areaNumber" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(TroubleMarker troubleMarker : TroubleMarker_HASH.values()){
                fileWriter.append(String.valueOf(troubleMarker.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(troubleMarker.getAreaNumber()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     *Function to save the coin.
     * @param Coin_HASH
     */
    public void saveCoin(HashMap<Integer, Coin> Coin_HASH){
        String fileName = file_position+"Coin.csv";
        String FILE_HEADER = "id,playerID,type" + NEW_LINE_SEPARATOR;

        FileWriter fileWriter = null;
        
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            
            for(Coin coin : Coin_HASH.values()){
                fileWriter.append(String.valueOf(coin.getId()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(coin.getPlayerID()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
        } catch (Exception e) {
            System.out.println("Error in CsvFIleWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
}
