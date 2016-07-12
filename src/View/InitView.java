
package View;

import Controller.BasicFunction;
import Controller.InterruptCard;
import Controller.PlayerGetCard;
import Controller.Utils;
import Model.ConstantField;
import Model.piece.DemonPiece;
import Model.piece.TrollPiece;
import Model.piece.TroubleMarker;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author ADMINISTRATOR
 */
public class InitView{
    BasicFunction basicFunction;
    int playerNumber;
    PlayerGetCard getCard;
    PlayerGetCard areaCard;
    JLabel cardName;
    InterruptCard interruptCard;
    /**
     *Constructor, set the basic function.
     * @param basicFunction
     */
    public InitView(BasicFunction basicFunction,JLabel cardName) {
        this.basicFunction = basicFunction;
        this.playerNumber=basicFunction.getNumPlayer();
        this.cardName = cardName;
        this.interruptCard = new InterruptCard(basicFunction);
    }
    
    
    // all 12 areas

    /**
     *Function to initial the 12 areas information.
     * @param jTextArea
     * @param areaNumber
     * @param panel
     */
    public void initArea(JTextArea jTextArea, int areaNumber,JPanel panel){
        int buildingNumber = basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getBuildingNum();
        
        jTextArea.setText("Area name: "+basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getNamePlate()+"\n"
                            +"Area number: "+basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getNumber()+"\n"
                            +"Area building cost: "+basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getBuildingCost()+"\n"
                            +"Adjacent Area: "+basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getAdjacentArea().toString()+"\n"
                            +"Area building number: "+(buildingNumber==ConstantField.DEFAULT_PIECE_AREA_NUMBER?"Empty":basicFunction.getDiscWorld().getBuildingPiece_HASH().get(buildingNumber).getColor())+"\n"
                            +"Area minion: "+Utils.minionListToColorString(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH())+"\n"
                            +"Area TroubleMarker: "+(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum()==ConstantField.DEFAULT_PIECE_AREA_NUMBER?"False":"True")+"\n"
                            +"Area Troll number: "+basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTrollList().size()+"\n"
                            +"Area Demon number: "+basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getDemonList().toString()+"\n");
        
    }

    /**
     *function to initial all the player's minion piece number.
     * @param areaNumber
     * @param panel
     * @param black
     * @param white
     * @param red
     * @param blue
     */
    public void initAreaMinionPiece(int areaNumber,JPanel panel,JLabel black,JLabel white,JLabel red,JLabel blue){
         black.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(),"YELLOW")+"");
         white.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(),"GREEN")+"");
         red.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(),"RED")+"");
         blue.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(),"BLUE")+"");
         panel.repaint();
    }

    /**
     *function to initial area trouble marker number.
     * @param areaNumber
     * @param panel
     * @param label
     */
    public void initAreaTroubleMarker(int areaNumber,JPanel panel,JLabel label){
        label.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum()==ConstantField.DEFAULT_PIECE_AREA_NUMBER?"False":"True"));
    }

    /**
     *initial building piece number.
     * @param areaNumber
     * @param panel
     * @param label
     */
    public void initAreaBuilding(int areaNumber,JPanel panel,JLabel label){
        int buildingNumber = basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getBuildingNum();
        label.setText(""+(buildingNumber==ConstantField.DEFAULT_PIECE_AREA_NUMBER?"Empty":basicFunction.getDiscWorld().getBuildingPiece_HASH().get(buildingNumber).getColor()));
    }

    /**
     *initial area troll number.
     * @param areaNumber
     * @param panel
     * @param label
     */
    public void initAreaTroll(int areaNumber,JPanel panel,JLabel label){
        label.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTrollList().size()+"");
    }   
   
    /**
     *Function to initial the player board information.
     * @param jTextPlayer
     * @param playerNumber
     */
    public void initPlayer(JTextArea jTextPlayer, int playerNumber){
        try{
            int playerHoldingMinionCount = 0;
            for(Integer integer : basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getMinionList()){
                if(basicFunction.getDiscWorld().getMinionPiece_HASH().get(integer).getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                    playerHoldingMinionCount += 1;
                }
            }
            int playerHoldingBuildingCount = 0;
            for(Integer integer : basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getBuildingList()){
                if(basicFunction.getDiscWorld().getBuildingPiece_HASH().get(integer).getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                    playerHoldingBuildingCount += 1;
                }
            }
            int personalityCardID  = basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getPersonalityCardID();
            String playerHoldingArea = "";
            for(Integer integer : basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getCityAreaCardList()){
                playerHoldingArea += basicFunction.getDiscWorld().getCityAreaCard_HASH().get(integer).getName()+" ";
            }
        
        
             jTextPlayer.setText("Player name:"+basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getName()+"\n"
                            +"Player ID: "+basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getID()+"\n"
                            +"Player Color: "+basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getColor()+"\n"
                            +"Player Money#: "+basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getTotalCoinAmount()+"\n"
                            +"Player Minion#: "+playerHoldingMinionCount+"\n"
                            +"Player Buildings#: "+playerHoldingBuildingCount+"\n"
                            +"Player Control Area: "+playerHoldingArea+"\n"
                            +"Personality Card: "+basicFunction.getDiscWorld().getPersonalityCard_HASH().get(personalityCardID).getName()+"\n"
                            +"Player Card: "+basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getPlayerCardList().toString()+"\n");
             if(interruptCard.hasInterruptCard(playerNumber)>0){
                 jTextPlayer.setBorder(new LineBorder(Color.RED,5));
             }else{
                 jTextPlayer.setBorder(new LineBorder(Color.LIGHT_GRAY,5));
             }
            }catch(NullPointerException e){
                jTextPlayer.setText("");
            }
   }
    //additional information

    /**
     *Function to initial all the pieces on the map.
     * @param demonNumber
     * @param trollNumber
     * @param troubleNumber
     * @param bankMoney
     * @param currentPlayer
     */
        public void initMapInfo(JLabel demonNumber,JLabel trollNumber,JLabel troubleNumber,JLabel bankMoney,JLabel currentPlayer){
        int demonCount = 0;
        for(DemonPiece demon : basicFunction.getDiscWorld().getDemonPiece_HASH().values()){
            if(demon.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                demonCount++;
            }
        }
        
        int trollCount = 0;
        for(TrollPiece troll : basicFunction.getDiscWorld().getTrollPiece_HASH().values()){
            if(troll.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                trollCount++;
            }
        }
        
        int troubleCount = 0;
        for(TroubleMarker troubleMarker : basicFunction.getDiscWorld().getTroubleMarker_HASH().values()){
            if(troubleMarker.getAreaNumber() == ConstantField.DEFAULT_PIECE_AREA_NUMBER){
                troubleCount++;
            }
        }
        
        demonNumber.setText(demonCount+"");
        trollNumber.setText(trollCount+"");
        troubleNumber.setText(troubleCount+"");
        bankMoney.setText(basicFunction.getDiscWorld().getTotalCoinAmount()+"");
        currentPlayer.setText(basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()).getName());
    }

    /**
     *initial card description.
     * @param cardDesciption
     * @param playerNumber
     * @param panel
     * @return
     */
    public ArrayList <JLabel> initCardDescription(JTextArea cardDesciption,int playerNumber,JPanel panel){
        getCard=null;
        getCard = new PlayerGetCard(this.basicFunction,this.basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber),this.basicFunction.getDiscWorld().getPlayer_HASH().size(),cardDesciption,cardName);
        cardDesciption.setText(basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getPlayerCardList().toString()+"\n");
        return getCard.showCard(panel, basicFunction.getDiscWorld().getPlayer_HASH().get(playerNumber).getPlayerCardList().size(),"playerCard");
    }

   
}
