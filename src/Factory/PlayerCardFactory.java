package Factory;

import Behavior.Action;
import Behavior.Assassination;
import Behavior.Interrupt;
import Behavior.PlaceBuilding;
import Behavior.PlaceMinion;
import Behavior.PlayAnotherCard;
import Behavior.RandomEvent;
import Behavior.RemoveTroubleMarker;
import Behavior.Scroll;
import Behavior.TakeMoney;
import Model.ConstantField;
import Model.card.BrownPlayerCard;
import Model.card.GreenPlayerCard;
import Model.card.PlayerCard;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author yucunli
 */
public class PlayerCardFactory {
    
    private String text;
    
    /**
     *factory method return the player card.
     * @param line
     * @return
     */
    public PlayerCard getPlayerCard(String line){
        Scanner dataScanner = new Scanner(line);
        dataScanner.useDelimiter(",");
            
        int index = 0;
        PlayerCard playerCard = null;
        
        while (dataScanner.hasNext()) {
            String data = dataScanner.next();
            if (index == 0) {
                int id = Integer.parseInt(data);
                if(id < 100)
                    playerCard = new GreenPlayerCard();
                else
                    playerCard = new BrownPlayerCard();
                playerCard.setId(id);
            } else if (index == 1) {
                playerCard.setName(data);
            } else if (index == 2) {
                playerCard.setText(data);
                text = data;
            } else if (index == 3) {
                playerCard.setAction_text(data);
                playerCard.setActions(createActionList(data));
            } else if (index == 4) {
                playerCard.setPlayerID(Integer.parseInt(data));
            } else if (index == 5) {
                playerCard.setType(data);
            } else if (index == 6) {
                playerCard.setOrder(Integer.parseInt(data));
            } else if (index == 7) {
                playerCard.setUsed(Integer.parseInt(data));
            } else {
                System.out.println("invalid data::" + data);
            }
            
            index++;
        }
        
        return playerCard;
    }
    
    /**
     *create player card action list.
     * @param actions
     * @return
     */
    public List<Action> createActionList(String actions){
        String[] action_array = actions.split(ConstantField.CSV_SEPERATOR);
        List<Action> action_list = new LinkedList<Action>();
        for(String action : action_array){
            Action temp_action = createAction(action);
            if(temp_action != null)
                action_list.add(temp_action);
        }
        return action_list;
    }
    
    /**
     *create player card action.
     * @param type
     * @return
     */
    public Action createAction(String type){
        Action action = null;
        if(type.contains("Assassination")){
            action = new Assassination();
        }else if(type.contains("Interrupt")){
            action = new Interrupt();
        }else if(type.contains("PlaceBuilding")){
            action = new PlaceBuilding();
        }else if(type.contains("PlaceMinion")){
            action = new PlaceMinion();
        }else if(type.contains("PlayAnotherCard")){
            action = new PlayAnotherCard();
        }else if(type.contains("RandomEvent")){
            action = new RandomEvent();
        }else if(type.contains("RemoveTroubleMarker")){
            action = new RemoveTroubleMarker();
        }else if(type.contains("Scroll")){
            action = new Scroll(text);
        }else if(type.contains("TakeMoney")){
            String[] temp = type.split(ConstantField.CSV_TAKEMONEY_BEHAVIOR_SEPERATOR);
            action = new TakeMoney(Integer.parseInt(temp[1]));
        }
        return action;
    }
}
