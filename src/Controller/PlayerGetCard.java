/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Behavior.Action;
import Model.player.Player;
import Controller.BasicFunction;
import Model.area.Area;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
/**
 *
 * @author Zhenjiang Liu
 */
public class PlayerGetCard{
    private Player player;
    private Area area;

    /**
     *label list contain the player card list label.
     */
    public ArrayList <JLabel> labelList = new ArrayList<JLabel>();
    BasicFunction basicFunction;
    static int num=14;
    int numberOfPlayer;
    int numberOfArea;

    /**
     *area show the card description.
     */
    public JTextArea cardArea;
    public JLabel cardName;
     //List<Integer> carddemo = new LinkedList<Integer>();

    /**
     *Constructor, get player card.
     * @param basicFunction
     * @param player
     * @param numberOfPlayer
     * @param cardArea
     * @param cardName
     */
        public PlayerGetCard(BasicFunction basicFunction,Player player,int numberOfPlayer,JTextArea cardArea,JLabel cardName){
        
        this.player=player;
        this.basicFunction=basicFunction;
        this.numberOfPlayer=numberOfPlayer;
        this.cardArea=cardArea;
        this.cardName = cardName;
        //num=setStartCardNumber(numberOfPlayer);
    }

    /**
     *Constructor, get city area card.
     * @param basicFunction
     * @param area
     * @param numberOfArea
     * @param cardArea
     */
    public PlayerGetCard(BasicFunction basicFunction, Area area, int numberOfArea,JTextArea cardArea){
        this.area=area;
        this.basicFunction=basicFunction;
        this.numberOfArea=numberOfArea;
        this.cardArea=cardArea;
    }

    /**
     *
     * @param numberOfPlayer
     * @return
     */
    public int setStartCardNumber(int numberOfPlayer){
       int startCardNumber=0;
       if(numberOfPlayer==1){
           startCardNumber=4;
       }else if(numberOfPlayer==2){
           startCardNumber=9;
       }else if(numberOfPlayer==3){
           startCardNumber=14;
       }else if(numberOfPlayer==4){
           startCardNumber=19;
       }
       return startCardNumber;
   }

 
    /**
     * dynamincally show card to a panel
     * @param panel
     * @param cardNumber
     * @param type
     * @return 
     */
    public ArrayList <JLabel> showCard(final JPanel panel,int cardNumber,String type){//type="playerCard" or "areaCard"
        int i;

        for (i = 0; i < cardNumber; i++) {
            final JLabel temp = new JLabel("label" + cardNumber);
            if (type.equalsIgnoreCase("areaCard")) {
                temp.setName(String.valueOf(player.getCityAreaCardList().get(i)));
            } else {
                temp.setName(String.valueOf(player.getPlayerCardList().get(i)));
            }
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (type.equalsIgnoreCase("areaCard")) {
                temp.setText("city area card" + player.getCityAreaCardList().get(i));
            } else {
                temp.setText("card" + player.getPlayerCardList().get(i));
            }
            temp.setVisible(true);
            labelList.add(temp);
            //player.getPlayerCardList()
            temp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CardFunction cardFunction = new CardFunction();
                    labelList.remove(temp);//labelList.indexOf(temp)
 
                    cardFunction.playPlayerCard(basicFunction.getDiscWorld(), 
                            player, 
                            basicFunction.getDiscWorld().getPlayerCard_HASH().get(Integer.parseInt(temp.getName())));
                    //player.getPlayerCardList().remove(player.getPlayerCardList().indexOf(Integer.parseInt(temp.getName())));
                    panel.remove(temp);
                    panel.revalidate();
                    panel.repaint();
                    System.out.println("you close one card");

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    List<Action> list =basicFunction.getDiscWorld().getPlayerCard_HASH().get(Integer.parseInt(temp.getName())).getActions();
                    String allAction="";
                    for(Action action : list){
                        allAction += action.getDescription()+"\n";
                    }
                    
                    String card_action = "";
                    for(Action action : basicFunction.getDiscWorld().getPlayerCard_HASH().get(Integer.parseInt(temp.getName())).getActions()){
                        card_action+= action.getClass().getName()+"  :  "+action.getDescription()+"\n";
                    }
                    cardArea.setText("Card Name: \n"+basicFunction.getDiscWorld().getPlayerCard_HASH().get(Integer.parseInt(temp.getName())).getName()+"\n"
                    +"Card Action: \n"+card_action+"\n"
                            +/*"Card Text: \n"+ basicFunction.getDiscWorld().getPlayerCard_HASH().get(Integer.parseInt(temp.getName())).getText()+*/"\n"
                    +"Action Description: \n"+allAction);
                    cardName.setText(temp.getText()+":  "+basicFunction.getDiscWorld().getPlayerCard_HASH().get(Integer.parseInt(temp.getName())).getName());

                }
            });
        }
        return labelList;

    }
}
