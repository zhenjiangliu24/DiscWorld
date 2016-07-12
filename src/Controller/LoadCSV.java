
package Controller;

import Factory.CityAreaCardFactory;
import Factory.PersonalityCardFactory;
import Factory.PlayerCardFactory;
import Factory.RandomEventCardFactory;
import Model.Color;
import Model.ConstantField;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yucunli
 */
public class LoadCSV {
    
    private String file_position;
    
    /**
     *Function to load the CSV file.
     * @param file_position
     */
    public LoadCSV(String file_position){
        this.file_position = file_position;
    }
    
    /**
     *Function to load the 12 area.
     * @param Area_HASH
     */
    public void loadArea(HashMap<Integer, Area> Area_HASH){
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"Area.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(Area_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                Area area = new Area();
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        area.setNumber(Integer.parseInt(data));
                    } else if (index == 1) {
                        area.setNamePlate(data);
                    } else if (index == 2) {
                        area.setBuildingCost(Integer.parseInt(data));
                    } else if (index == 3) {
                        String[] adj_area = data.split(ConstantField.CSV_ADJACENT_AREA_SEPERATOR);
                        for(String area_id : adj_area){
                            area.getAdjacentArea().add(Integer.parseInt(area_id));
                        }
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                Area_HASH.put(area.getNumber(), area);
                index = 0;
            }
            
            scanner.close();
            
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadArea");
        }
    }
    
    /**
     *Function ot load the city area card.
     * @param CityAreaCard_HASH
     */
    public void loadCityAreaCard(HashMap<Integer, CityAreaCard> CityAreaCard_HASH){
        CityAreaCardFactory cityAreaCardFactory = new CityAreaCardFactory();
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"CityAreaCard.csv"));
           
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                
                if(CityAreaCard_HASH.size() == 0){
                    line = scanner.nextLine();
                }
                
                CityAreaCard cityAreaCard = cityAreaCardFactory.getCityAreaCard(line);
                
                CityAreaCard_HASH.put(cityAreaCard.getId(), cityAreaCard);
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadCityAreaCard");
        }
    }
    
    /**
     *Function to load the personality card.
     * @param PersonalityCard_HASH
     */
    public void loadPersonalityCard(HashMap<Integer, PersonalityCard> PersonalityCard_HASH){
        PersonalityCardFactory personalityCardFactory = new PersonalityCardFactory();
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"PersonalityCard.csv"));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                if(PersonalityCard_HASH.size() == 0){
                    line = scanner.nextLine();
                }
                
                PersonalityCard personalityCard = personalityCardFactory.getPersonalityCard(line);
                
                PersonalityCard_HASH.put(personalityCard.getId(), personalityCard);
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadPersonalityCard");
        }
        
    }
    
    /**
     *Function to load the random event card.
     * @param RandomEventCard_HASH
     */
    public void loadRandomEventCard(HashMap<Integer, RandomEventCard> RandomEventCard_HASH){
        RandomEventCardFactory randomEventCardFactory = new RandomEventCardFactory();
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"RandomEventCard.csv"));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                if(RandomEventCard_HASH.size() == 0){
                    line = scanner.nextLine();
                }
                
                RandomEventCard randomEventCard = randomEventCardFactory.getRandomEventCard(line);
                
                RandomEventCard_HASH.put(randomEventCard.getId(), randomEventCard);
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadRandomEventCard");
        }
        
    }
    
    /**
     *Function to load the player card.
     * @param PlayerCard_HASH
     */
    public void loadPlayerCard(HashMap<Integer, PlayerCard> PlayerCard_HASH, String file_name){
        PlayerCardFactory playerCardFactory = new PlayerCardFactory();
        
        try {
            Scanner scanner = new Scanner(new File(file_position+file_name));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                if(PlayerCard_HASH.size() == 0){
                    line = scanner.nextLine();
                }
                
                PlayerCard playerCard = playerCardFactory.getPlayerCard(line);
                /*if(playerCard.getPlayerID() == 0)
                    playerCard.setPlayerID(ConstantField.DEFAULT_UNDEFINED_PLAYID);
                if(playerCard.getOrder() == 0)
                    playerCard.setOrder(ConstantField.DEFAULT_UNDEFINED_ORDER);
                */
                PlayerCard_HASH.put(playerCard.getId(), playerCard);
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadPlayerCard");
        }
    }
    
    /**
     *Function to load the coin.
     * @param Coin_HASH
     */
    public void loadCoin(HashMap<Integer, Coin> Coin_HASH){
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"Coin.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(Coin_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                Coin coin = new Coin();
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        coin.setId(Integer.parseInt(data));
                    } else if (index == 1) {
                        coin.setPlayerID(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                Coin_HASH.put(coin.getId(), coin);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadCoin");
        }
    }
    
    /**
     *Function to load the building piece.
     * @param BuildingPiece_HASH
     */
    public void loadBuildingPiece(HashMap<Integer, BuildingPiece> BuildingPiece_HASH){
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"BuildingPiece.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(BuildingPiece_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                BuildingPiece buildingPiece = new BuildingPiece(-1, -1, -1, Color.BLACK);
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        buildingPiece.setId(Integer.parseInt(data));
                    } else if (index == 1) {
                        buildingPiece.setAreaNumber(Integer.parseInt(data));
                    } else if (index == 2) {
                        buildingPiece.setColor(Color.valueOf(data));
                    } else if (index == 3) {
                        buildingPiece.setPlayerID(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                BuildingPiece_HASH.put(buildingPiece.getId(), buildingPiece);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadBuildingPiece");
        }
        
    }
    
    /**
     *Function to load the minion piece.
     * @param MinionPiece_HASH
     */
    public void loadMinionPiece(HashMap<Integer, MinionPiece> MinionPiece_HASH){
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"MinionPiece.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(MinionPiece_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                MinionPiece minionPiece = new MinionPiece(-1, -1, -1, Color.BLACK);
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        minionPiece.setId(Integer.parseInt(data));
                    } else if (index == 1) {
                        minionPiece.setAreaNumber(Integer.parseInt(data));
                    } else if (index == 2) {
                        minionPiece.setColor(Color.valueOf(data));
                    } else if (index == 3) {
                        minionPiece.setPlayerID(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                MinionPiece_HASH.put(minionPiece.getId(), minionPiece);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadMinionPiece");
        }
        
    }
    
    /**
     *Function to load the demon piece.
     * @param DemonPiece_HASH
     */
    public void loadDemonPiece(HashMap<Integer, DemonPiece> DemonPiece_HASH){
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"DemonPiece.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(DemonPiece_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                DemonPiece demonPiece = new DemonPiece(-1, -1);
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        demonPiece.setId(Integer.parseInt(data));
                    } else if (index == 1) {
                        demonPiece.setAreaNumber(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                DemonPiece_HASH.put(demonPiece.getId(), demonPiece);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadDemonPiece");
        }
    
    }
    
    /**
     *Function to load the troll piece.
     * @param TrollPiece_HASH
     */
    public void loadTrollPiece(HashMap<Integer, TrollPiece> TrollPiece_HASH){
    
        try {
            Scanner scanner = new Scanner(new File(file_position+"TrollPiece.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(TrollPiece_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                TrollPiece trollPiece = new TrollPiece(-1, -1);
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        trollPiece.setId(Integer.parseInt(data));
                    } else if (index == 1) {
                        trollPiece.setAreaNumber(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                TrollPiece_HASH.put(trollPiece.getId(), trollPiece);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadTrollPiece");
        }
        
    }
    
    /**
     *Function to load the trouble marker.
     * @param TroubleMarker_HASH
     */
    public void loadTroubleMarker(HashMap<Integer, TroubleMarker>  TroubleMarker_HASH){
        
        try {
            Scanner scanner = new Scanner(new File(file_position+"TroubleMarker.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(TroubleMarker_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                TroubleMarker troubleMarker = new TroubleMarker(-1, -1);
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        troubleMarker.setId(Integer.parseInt(data));
                    } else if (index == 1) {
                        troubleMarker.setAreaNumber(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                TroubleMarker_HASH.put(troubleMarker.getId(), troubleMarker);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadTroubleMarker");
        }
        
    }
    
    /**
     *Function to load the player hashmap.
     * @param Player_HASH
     */
    public void loadPlayer(HashMap<Integer, Player> Player_HASH){
    
        try {
            Scanner scanner = new Scanner(new File(file_position+"Player.csv"));
            Scanner dataScanner = null;
            int index = 0;
            
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                
                if(Player_HASH.size() == 0){
                    dataScanner = new Scanner(scanner.nextLine());
                }
                
                dataScanner.useDelimiter(",");
                Player player = new Player(-1, "", Color.BLACK, -1, -1);
                
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index == 0) {
                        player.setID(Integer.parseInt(data));
                    } else if (index == 1) {
                        player.setName(data);
                    } else if (index == 2) {
                        player.setColor(Color.valueOf(data));
                    } else if (index == 3) {
                        player.setOrder(Integer.parseInt(data));
                    } else {
                        System.out.println("invalid data::" + data);
                    }
                    index++;
                }
                
                Player_HASH.put(player.getID(), player);
                index = 0;
            }
            
            scanner.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: FileNotFound - loadPlayer");
        }
    }
}
