/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BasicFunction;
import Controller.Utils;
import Controller.PlayerGetCard;
import Controller.CardFunction;
import Controller.InterruptCard;
import Controller.MoneyFunction;
import Controller.PieceFunction;
import Controller.WinningConditionFunction;
import Model.Color;
import Model.ConstantField;
import Model.area.Area;
import Model.card.RandomEventCard;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import Model.card.PlayerCard;
import Model.card.PersonalityCard;
import Model.card.CityAreaCard;
import Model.player.Player;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Cheng Xin
 */
public class MainFrame extends javax.swing.JFrame {
    /**
     * global variable
     */
    int PictureView = 0;
    String test=null;
    BasicFunction basicFunction;
    String [] players;
    Color [] playerColor= {Color.BLACK,Color.BLUE,Color.RED,Color.WHITE};

    public  InitView init;
    ArrayList <JLabel> labelList;
    ArrayList <JLabel> areaListForPlayer1;
    
    PlayerGetCard getAreaCard;
    int playerNumber;
    CardFunction cardFunction;
    MoneyFunction moneyFunction;
    PieceFunction pieceFunction;
    List<Player> orderedList;
    WinningConditionFunction winCondition;
    InterruptCard interruptCard;
    //HashMap<Integer,RandomEventCard> randomEventCardHashMap;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
            basicFunction = new BasicFunction();
            //InitView init = new InitView(basicFunction); 
            initComponents();
            this.playerNumber=basicFunction.getNumPlayer();
           cardFunction =new CardFunction();
           moneyFunction = new MoneyFunction();
           pieceFunction = new PieceFunction();
           orderedList = new LinkedList<Player>();
           
    }
    public void setChooseButton(boolean view){
        chooseCardButton.setVisible(view);
    }
    public void setTransferMoneyButton(boolean view){
        PlayerGetMoney.setVisible(view);
        PlayerSendMoney.setVisible(view);
    }
    public void setTroubleMarkerIcon(boolean view){
        Area1TroubleMarker.setVisible(view);
        Area1TroubleMarkerLabel.setVisible(view);
    }
    public void setBuildingIcon(boolean view){
        AreaBuildingAdd.setVisible(view);
        Area1BuildingLabel.setVisible(view);
    }
    public void setMinionIcon(boolean view){
        Area1YellowM.setVisible(view);
        Area1YellowMinion.setVisible(view);
        Area1BlueM.setVisible(view);
        Area1BlueMinion.setVisible(view);
        Area1RM.setVisible(view);
        Area1RedMinion.setVisible(view);
        Area1GM.setVisible(view);
        Area1GreenMinion.setVisible(view);
    }
    /**
     *update player info on the right part of the game panel.
     */
    public void updatePlayer(){
        init.initPlayer(jTextArea_Player1, orderedList.get(0).getID());
        init.initPlayer(jTextArea_Player2, orderedList.get(1).getID());
        if(orderedList.size() >2)
            init.initPlayer(jTextArea_Player3, orderedList.get(2).getID());
        if(orderedList.size() > 3)
            init.initPlayer(jTextArea_Player4, orderedList.get(3).getID());
    }
    public void updateAreaSelectedPanel(){
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        int buildingNumber = basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getBuildingNum();
        Area1YellowMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.YELLOW.toString()) + "");
        Area1BlueMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.BLUE.toString()) + "");
        Area1RedMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.RED.toString()) + "");
        Area1GreenMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.GREEN.toString()) + "");
        Area1TroubleMarkerLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == ConstantField.DEFAULT_PIECE_AREA_NUMBER ? "False" : "True");
        Area1TrollLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTrollList().size() + "");
        Area1DemonLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getDemonList().size() + "");
        Area1BuildingLabel.setText((buildingNumber == ConstantField.DEFAULT_PIECE_AREA_NUMBER) ? "Empty" : basicFunction.getDiscWorld().getBuildingPiece_HASH().get(buildingNumber).getColor().toString());
    }
    /**
     *update all the area info.
     */
    public void updateArea(){
        init.initArea(jTextArea1, 1,Area1Panel);init.initArea(jTextArea2, 2,Area2Panel);
        init.initArea(jTextArea3, 3,Area3Panel);init.initArea(jTextArea4, 4,Area4Panel);
        init.initArea(jTextArea5, 5,Area5Panel);init.initArea(jTextArea6, 6,Area6Panel);
        init.initArea(jTextArea7, 7,Area7Panel);init.initArea(jTextArea8, 8,Area8Panel);
        init.initArea(jTextArea9, 9,Area9Panel);init.initArea(jTextArea10, 10,Area10Panel);
        init.initArea(jTextArea11, 11,Area11Panel);init.initArea(jTextArea12, 12,Area12Panel);
    }
    /**
     *Function to initial all the information on the playing board.
     */
    public void init(){
        this.interruptCard = new InterruptCard(basicFunction);
        init = new InitView(basicFunction,CardNameLabel); 
        updateArea();
        
        init.initAreaMinionPiece(1, Area1Panel, Area1YellowMinion, Area1GreenMinion, Area1RedMinion, Area1BlueMinion);
        init.initAreaTroubleMarker(1, Area1Panel, Area1TroubleMarkerLabel);
        init.initAreaBuilding(1, Area1Panel, Area1BuildingLabel);
        init.initAreaTroll(1, Area1Panel, Area1TrollLabel);
        
        //List<Player> orderedList = new LinkedList<Player>();
        for(Player player : basicFunction.getDiscWorld().getPlayer_HASH().values()){
            orderedList.add(player);
        }
        Collections.sort(orderedList, new Comparator<Player>(){

            @Override
            public int compare(Player lhs, Player rhs) {
                return lhs.getOrder() - rhs.getOrder();
            }
            
        });
        if(orderedList.size() ==2){
            Area1RM.setVisible(false);
            Area1RedMinion.setVisible(false);
            Area1GM.setVisible(false);
            Area1GreenMinion.setVisible(false);
        }
        if(orderedList.size() ==3){
            Area1GM.setVisible(false);
            Area1GreenMinion.setVisible(false);
        }    
            
        updatePlayer();
        basicFunction.getDiscWorld().setCur_Player(orderedList.get(0).getID());
        init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
        
        labelList=init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(),CardConsolePanel);
        CardConsolePanel.setLayout(new GridLayout(2,2));
        for(int i = 0;i<labelList.size();i++){
            CardConsolePanel.add(labelList.get(i));
        }
        pack();
        CardConsolePanel.repaint();
        setMinionIcon(false);
        setBuildingIcon(false);
        setTroubleMarkerIcon(false);
        setTransferMoneyButton(false);
    }
    
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MapPanel = new javax.swing.JPanel();
        Area1Panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator5 = new javax.swing.JSeparator();
        Area2Panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        Area3Panel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        Area4Panel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        Area5Panel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        Area6Panel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        Area7Panel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        Area8Panel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        Area9Panel = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea9 = new javax.swing.JTextArea();
        Area10Panel = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea10 = new javax.swing.JTextArea();
        Area11Panel = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextArea();
        Area12Panel = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea12 = new javax.swing.JTextArea();
        PlayerInfoPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextArea_Player1 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea_Player2 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea_Player3 = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea_Player4 = new javax.swing.JTextArea();
        CardInfoPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CardDescription = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextField_Load = new javax.swing.JTextField();
        jButton_Load = new javax.swing.JButton();
        jButton_Save = new javax.swing.JButton();
        jButton_Init = new javax.swing.JButton();
        jTextField_Save = new javax.swing.JTextField();
        jTextField_Init = new javax.swing.JTextField();
        playerInitialOrder = new javax.swing.JTextField();
        PlayerActionPanel = new javax.swing.JPanel();
        DiceButton = new javax.swing.JButton();
        DiceResult1 = new javax.swing.JLabel();
        PlayerGetMoney = new javax.swing.JButton();
        PlayerSendMoney = new javax.swing.JButton();
        MoneyAmountText = new javax.swing.JTextField();
        PlayerActionFromLabel = new javax.swing.JLabel();
        PlayerCombox = new javax.swing.JComboBox();
        to = new javax.swing.JLabel();
        RandomEventCardlabel = new javax.swing.JLabel();
        Area1YellowM = new javax.swing.JLabel();
        Area1YellowMinion = new javax.swing.JLabel();
        Area1BlueM = new javax.swing.JLabel();
        Area1BlueMinion = new javax.swing.JLabel();
        Area1RM = new javax.swing.JLabel();
        Area1RedMinion = new javax.swing.JLabel();
        Area1GM = new javax.swing.JLabel();
        Area1GreenMinion = new javax.swing.JLabel();
        selectAreaComboBox = new javax.swing.JComboBox();
        Area1TroubleMarker = new javax.swing.JLabel();
        Area1TroubleMarkerLabel = new javax.swing.JLabel();
        AreaBuildingAdd = new javax.swing.JLabel();
        Area1BuildingLabel = new javax.swing.JLabel();
        AreaTrollAdd = new javax.swing.JLabel();
        Area1TrollLabel = new javax.swing.JLabel();
        AreaDemonAdd = new javax.swing.JLabel();
        Area1DemonLabel = new javax.swing.JLabel();
        moveMinionButton = new javax.swing.JButton();
        moveMinionFromArea = new javax.swing.JComboBox();
        moveMinionToArea = new javax.swing.JComboBox();
        moveMinionFromLabel = new javax.swing.JLabel();
        moveMinionToLabel = new javax.swing.JLabel();
        moveMinionPlayerID = new javax.swing.JComboBox();
        chosenPlayerCard = new javax.swing.JLabel();
        chosenInterrupt = new javax.swing.JLabel();
        chosenCardPlayer = new javax.swing.JLabel();
        playerWinLabel = new javax.swing.JLabel();
        roleWinComboBox = new javax.swing.JComboBox();
        confirmRoleWinButton = new javax.swing.JButton();
        CardNameLabel = new javax.swing.JLabel();
        chooseCardButton = new javax.swing.JButton();
        CardConsolePanel = new javax.swing.JPanel();
        MapInfoPanel = new javax.swing.JPanel();
        MapDemonNum = new javax.swing.JLabel();
        MapTrollNum = new javax.swing.JLabel();
        MapTroubleNum = new javax.swing.JLabel();
        DemonNumber = new javax.swing.JLabel();
        trollNumber = new javax.swing.JLabel();
        troubleNumber = new javax.swing.JLabel();
        jButton_playerCard = new javax.swing.JButton();
        jButton_EventCard = new javax.swing.JButton();
        jButton_Personality = new javax.swing.JButton();
        jButton_CityCard = new javax.swing.JButton();
        bankMoney = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        currentPlayer = new javax.swing.JLabel();
        actionConfirmButton = new javax.swing.JButton();
        showPlayerAreaCard = new javax.swing.JButton();
        endTurnButton = new javax.swing.JButton();
        Menu = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DISCWORLD");
        setExtendedState(6);
        setMinimumSize(null);
        setName("MainWindow"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setType(java.awt.Window.Type.UTILITY);

        MapPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MapPanel.setName("Map"); // NOI18N
        MapPanel.setPreferredSize(new java.awt.Dimension(890, 557));
        MapPanel.setLayout(new java.awt.GridLayout(3, 4));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout Area1PanelLayout = new javax.swing.GroupLayout(Area1Panel);
        Area1Panel.setLayout(Area1PanelLayout);
        Area1PanelLayout.setHorizontalGroup(
            Area1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(Area1PanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        Area1PanelLayout.setVerticalGroup(
            Area1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area1PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        MapPanel.add(Area1Panel);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        javax.swing.GroupLayout Area2PanelLayout = new javax.swing.GroupLayout(Area2Panel);
        Area2Panel.setLayout(Area2PanelLayout);
        Area2PanelLayout.setHorizontalGroup(
            Area2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );
        Area2PanelLayout.setVerticalGroup(
            Area2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area2PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area2Panel);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        javax.swing.GroupLayout Area3PanelLayout = new javax.swing.GroupLayout(Area3Panel);
        Area3Panel.setLayout(Area3PanelLayout);
        Area3PanelLayout.setHorizontalGroup(
            Area3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        Area3PanelLayout.setVerticalGroup(
            Area3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area3PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area3Panel);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        javax.swing.GroupLayout Area4PanelLayout = new javax.swing.GroupLayout(Area4Panel);
        Area4Panel.setLayout(Area4PanelLayout);
        Area4PanelLayout.setHorizontalGroup(
            Area4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        Area4PanelLayout.setVerticalGroup(
            Area4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area4PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area4Panel);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane6.setViewportView(jTextArea5);

        javax.swing.GroupLayout Area5PanelLayout = new javax.swing.GroupLayout(Area5Panel);
        Area5Panel.setLayout(Area5PanelLayout);
        Area5PanelLayout.setHorizontalGroup(
            Area5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        Area5PanelLayout.setVerticalGroup(
            Area5PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area5PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area5Panel);

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane7.setViewportView(jTextArea6);

        javax.swing.GroupLayout Area6PanelLayout = new javax.swing.GroupLayout(Area6Panel);
        Area6Panel.setLayout(Area6PanelLayout);
        Area6PanelLayout.setHorizontalGroup(
            Area6PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );
        Area6PanelLayout.setVerticalGroup(
            Area6PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area6PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area6Panel);

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jScrollPane8.setViewportView(jTextArea7);

        javax.swing.GroupLayout Area7PanelLayout = new javax.swing.GroupLayout(Area7Panel);
        Area7Panel.setLayout(Area7PanelLayout);
        Area7PanelLayout.setHorizontalGroup(
            Area7PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
        );
        Area7PanelLayout.setVerticalGroup(
            Area7PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area7PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area7Panel);

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jScrollPane9.setViewportView(jTextArea8);

        javax.swing.GroupLayout Area8PanelLayout = new javax.swing.GroupLayout(Area8Panel);
        Area8Panel.setLayout(Area8PanelLayout);
        Area8PanelLayout.setHorizontalGroup(
            Area8PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
        );
        Area8PanelLayout.setVerticalGroup(
            Area8PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area8PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area8Panel);

        jTextArea9.setColumns(20);
        jTextArea9.setRows(5);
        jScrollPane10.setViewportView(jTextArea9);

        javax.swing.GroupLayout Area9PanelLayout = new javax.swing.GroupLayout(Area9Panel);
        Area9Panel.setLayout(Area9PanelLayout);
        Area9PanelLayout.setHorizontalGroup(
            Area9PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area9PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        Area9PanelLayout.setVerticalGroup(
            Area9PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area9PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area9Panel);

        jTextArea10.setColumns(20);
        jTextArea10.setRows(5);
        jScrollPane11.setViewportView(jTextArea10);

        javax.swing.GroupLayout Area10PanelLayout = new javax.swing.GroupLayout(Area10Panel);
        Area10Panel.setLayout(Area10PanelLayout);
        Area10PanelLayout.setHorizontalGroup(
            Area10PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11)
        );
        Area10PanelLayout.setVerticalGroup(
            Area10PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area10PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area10Panel);

        jTextArea11.setColumns(20);
        jTextArea11.setRows(5);
        jScrollPane12.setViewportView(jTextArea11);

        javax.swing.GroupLayout Area11PanelLayout = new javax.swing.GroupLayout(Area11Panel);
        Area11Panel.setLayout(Area11PanelLayout);
        Area11PanelLayout.setHorizontalGroup(
            Area11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12)
        );
        Area11PanelLayout.setVerticalGroup(
            Area11PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area11PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area11Panel);

        jTextArea12.setColumns(20);
        jTextArea12.setRows(5);
        jScrollPane13.setViewportView(jTextArea12);

        javax.swing.GroupLayout Area12PanelLayout = new javax.swing.GroupLayout(Area12Panel);
        Area12Panel.setLayout(Area12PanelLayout);
        Area12PanelLayout.setHorizontalGroup(
            Area12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area12PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        Area12PanelLayout.setVerticalGroup(
            Area12PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Area12PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        MapPanel.add(Area12Panel);

        PlayerInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PlayerInfoPanel.setName("RolePanel"); // NOI18N

        jSeparator2.setPreferredSize(new java.awt.Dimension(0, 0));

        jSeparator1.setToolTipText("");
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 0));

        jSeparator3.setPreferredSize(new java.awt.Dimension(0, 0));

        jTextArea_Player1.setColumns(20);
        jTextArea_Player1.setRows(5);
        jTextArea_Player1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextArea_Player1.setPreferredSize(new java.awt.Dimension(102, 102));
        jTextArea_Player1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea_Player1MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTextArea_Player1);

        jTextArea_Player2.setColumns(20);
        jTextArea_Player2.setRows(5);
        jTextArea_Player2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextArea_Player2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea_Player2MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(jTextArea_Player2);

        jTextArea_Player3.setColumns(20);
        jTextArea_Player3.setRows(5);
        jTextArea_Player3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextArea_Player3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea_Player3MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(jTextArea_Player3);

        jTextArea_Player4.setColumns(20);
        jTextArea_Player4.setRows(5);
        jTextArea_Player4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextArea_Player4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea_Player4MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(jTextArea_Player4);

        javax.swing.GroupLayout PlayerInfoPanelLayout = new javax.swing.GroupLayout(PlayerInfoPanel);
        PlayerInfoPanel.setLayout(PlayerInfoPanelLayout);
        PlayerInfoPanelLayout.setHorizontalGroup(
            PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                        .addGroup(PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                                .addGroup(PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18))
                            .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                                .addGroup(PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))))
        );
        PlayerInfoPanelLayout.setVerticalGroup(
            PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                .addGroup(PlayerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel18))
                    .addGroup(PlayerInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CardInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CardInfoPanel.setName("Card_Info"); // NOI18N

        CardDescription.setColumns(20);
        CardDescription.setRows(5);
        CardDescription.setText("Card Description");
        CardDescription.setAutoscrolls(false);
        CardDescription.setBorder(null);
        jScrollPane1.setViewportView(CardDescription);

        jTextField_Load.setText("file name");
        jTextField_Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LoadActionPerformed(evt);
            }
        });

        jButton_Load.setText("load");
        jButton_Load.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_LoadMouseClicked(evt);
            }
        });

        jButton_Save.setText("save");
        jButton_Save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_SaveMouseClicked(evt);
            }
        });
        jButton_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveActionPerformed(evt);
            }
        });

        jButton_Init.setText("init");
        jButton_Init.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_InitMouseClicked(evt);
            }
        });

        jTextField_Save.setText("file name");
        jTextField_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SaveActionPerformed(evt);
            }
        });

        jTextField_Init.setText("player1,player2,player3");
        jTextField_Init.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_InitActionPerformed(evt);
            }
        });

        playerInitialOrder.setText("1,2,3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_Init, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Save, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jTextField_Load))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Save)
                            .addComponent(jButton_Load)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(playerInitialOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Init)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Load, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Load))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Save)
                    .addComponent(jTextField_Save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Init, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerInitialOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Init))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DiceButton.setText("Dice");
        DiceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DiceButtonMouseClicked(evt);
            }
        });

        DiceResult1.setText("#");

        PlayerGetMoney.setText("Get");
        PlayerGetMoney.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerGetMoneyMouseClicked(evt);
            }
        });

        PlayerSendMoney.setText("Send");
        PlayerSendMoney.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerSendMoneyMouseClicked(evt);
            }
        });

        PlayerActionFromLabel.setText("From");

        PlayerCombox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "Bank" }));
        PlayerCombox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerComboxActionPerformed(evt);
            }
        });

        to.setText("To");

        RandomEventCardlabel.setText("random");
        RandomEventCardlabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RandomEventCardlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RandomEventCardlabelMouseEntered(evt);
            }
        });

        Area1YellowM.setText("M");
        Area1YellowM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        Area1YellowM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1YellowMMouseClicked(evt);
            }
        });

        Area1YellowMinion.setText("num");
        Area1YellowMinion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1YellowMinionMouseClicked(evt);
            }
        });

        Area1BlueM.setText("M");
        Area1BlueM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        Area1BlueM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1BlueMMouseClicked(evt);
            }
        });

        Area1BlueMinion.setText("num");
        Area1BlueMinion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1BlueMinionMouseClicked(evt);
            }
        });

        Area1RM.setText("M");
        Area1RM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        Area1RM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1RMMouseClicked(evt);
            }
        });

        Area1RedMinion.setText("num");
        Area1RedMinion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1RedMinionMouseClicked(evt);
            }
        });

        Area1GM.setText("M");
        Area1GM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 51)));
        Area1GM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1GMMouseClicked(evt);
            }
        });

        Area1GreenMinion.setText("num");
        Area1GreenMinion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1GreenMinionMouseClicked(evt);
            }
        });

        selectAreaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        selectAreaComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAreaComboBoxItemStateChanged(evt);
            }
        });

        Area1TroubleMarker.setText("TroubleMarker:");
        Area1TroubleMarker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1TroubleMarkerMouseClicked(evt);
            }
        });

        Area1TroubleMarkerLabel.setText("#");
        Area1TroubleMarkerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1TroubleMarkerLabelMouseClicked(evt);
            }
        });

        AreaBuildingAdd.setText("Building:");
        AreaBuildingAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AreaBuildingAddMouseClicked(evt);
            }
        });

        Area1BuildingLabel.setText("#");
        Area1BuildingLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1BuildingLabelMouseClicked(evt);
            }
        });

        AreaTrollAdd.setText("Troll:");
        AreaTrollAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AreaTrollAddMouseClicked(evt);
            }
        });

        Area1TrollLabel.setText("#");
        Area1TrollLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1TrollLabelMouseClicked(evt);
            }
        });

        AreaDemonAdd.setText("Demon:");
        AreaDemonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AreaDemonAddMouseClicked(evt);
            }
        });

        Area1DemonLabel.setText("#");
        Area1DemonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Area1DemonLabelMouseClicked(evt);
            }
        });

        moveMinionButton.setText("move");
        moveMinionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveMinionButtonMouseClicked(evt);
            }
        });

        moveMinionFromArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        moveMinionToArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        moveMinionFromLabel.setText("from");

        moveMinionToLabel.setText("to");

        moveMinionPlayerID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "demon", "troll" }));

        chosenPlayerCard.setText("Chosen");
        chosenPlayerCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chosenInterrupt.setText("Interrupt");
        chosenInterrupt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chosenInterrupt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chosenInterruptMouseClicked(evt);
            }
        });

        chosenCardPlayer.setText("name:");

        javax.swing.GroupLayout PlayerActionPanelLayout = new javax.swing.GroupLayout(PlayerActionPanel);
        PlayerActionPanel.setLayout(PlayerActionPanelLayout);
        PlayerActionPanelLayout.setHorizontalGroup(
            PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PlayerSendMoney))
                    .addComponent(DiceButton)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerActionPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(PlayerGetMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(RandomEventCardlabel)))
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                        .addComponent(MoneyAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(PlayerCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(PlayerActionFromLabel)
                                            .addComponent(to)))
                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(DiceResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(Area1TroubleMarker)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Area1TroubleMarkerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AreaBuildingAdd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Area1BuildingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerActionPanelLayout.createSequentialGroup()
                                                        .addComponent(Area1YellowM, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(Area1YellowMinion)
                                                        .addGap(4, 4, 4))
                                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                                        .addComponent(moveMinionButton)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                                                .addComponent(AreaTrollAdd)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(Area1TrollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(moveMinionPlayerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerActionPanelLayout.createSequentialGroup()
                                                        .addComponent(moveMinionFromLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(moveMinionFromArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(moveMinionToLabel)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(moveMinionToArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                                        .addComponent(Area1BlueM, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                                                .addComponent(Area1BlueMinion)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(Area1RM, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(Area1RedMinion)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(Area1GM, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(Area1GreenMinion))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerActionPanelLayout.createSequentialGroup()
                                                                .addComponent(AreaDemonAdd)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(Area1DemonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))))
                                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                                .addComponent(chosenInterrupt)
                                                .addGap(132, 132, 132)))
                                        .addComponent(selectAreaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(chosenCardPlayer)
                                .addGap(18, 18, 18)
                                .addComponent(chosenPlayerCard)))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        PlayerActionPanelLayout.setVerticalGroup(
            PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DiceButton)
                    .addComponent(DiceResult1))
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                .addComponent(PlayerGetMoney)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PlayerSendMoney))
                            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PlayerActionFromLabel)
                                    .addComponent(PlayerCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(to))
                                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(moveMinionButton)
                                            .addComponent(moveMinionFromLabel)
                                            .addComponent(moveMinionFromArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(moveMinionToLabel)
                                            .addComponent(moveMinionToArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(moveMinionPlayerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chosenInterrupt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(MoneyAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chosenPlayerCard, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RandomEventCardlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chosenCardPlayer))
                        .addGap(23, 23, 23))))
            .addGroup(PlayerActionPanelLayout.createSequentialGroup()
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Area1YellowM)
                    .addComponent(Area1YellowMinion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Area1BlueM)
                    .addComponent(Area1BlueMinion)
                    .addComponent(Area1RM)
                    .addComponent(Area1RedMinion)
                    .addComponent(Area1GM)
                    .addComponent(Area1GreenMinion)
                    .addComponent(selectAreaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Area1TroubleMarker)
                    .addComponent(Area1TroubleMarkerLabel)
                    .addComponent(AreaBuildingAdd)
                    .addComponent(Area1BuildingLabel))
                .addGap(18, 18, 18)
                .addGroup(PlayerActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AreaTrollAdd)
                    .addComponent(Area1TrollLabel)
                    .addComponent(AreaDemonAdd)
                    .addComponent(Area1DemonLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        playerWinLabel.setText("Role name: ");

        roleWinComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lord Vetinari", "Lord Selachii", "Lord Rust", "Lord de Worde", "Dragon King of Arms", "Chrysoprase", "Commander Vimes", "Calculate points" }));

        confirmRoleWinButton.setText("Win");
        confirmRoleWinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmRoleWinButtonMouseClicked(evt);
            }
        });

        CardNameLabel.setText("Card Name");

        chooseCardButton.setText("choose");
        chooseCardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseCardButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout CardInfoPanelLayout = new javax.swing.GroupLayout(CardInfoPanel);
        CardInfoPanel.setLayout(CardInfoPanelLayout);
        CardInfoPanelLayout.setHorizontalGroup(
            CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardInfoPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlayerActionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CardNameLabel)
                        .addGap(55, 55, 55)
                        .addGroup(CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerWinLabel)
                            .addComponent(chooseCardButton))
                        .addGap(24, 24, 24)
                        .addGroup(CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmRoleWinButton)
                            .addComponent(roleWinComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CardInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        CardInfoPanelLayout.setVerticalGroup(
            CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardInfoPanelLayout.createSequentialGroup()
                        .addGroup(CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerWinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roleWinComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CardInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CardNameLabel)
                            .addComponent(confirmRoleWinButton)
                            .addComponent(chooseCardButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addComponent(PlayerActionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        CardConsolePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CardConsolePanel.setName("Console"); // NOI18N

        javax.swing.GroupLayout CardConsolePanelLayout = new javax.swing.GroupLayout(CardConsolePanel);
        CardConsolePanel.setLayout(CardConsolePanelLayout);
        CardConsolePanelLayout.setHorizontalGroup(
            CardConsolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );
        CardConsolePanelLayout.setVerticalGroup(
            CardConsolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        MapInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MapInfoPanel.setName("Map_Info"); // NOI18N

        MapDemonNum.setText("Demon#");

        MapTrollNum.setText("Troll#");

        MapTroubleNum.setText("Trouble#");

        jButton_playerCard.setText("player card");
        jButton_playerCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_playerCardMouseClicked(evt);
            }
        });
        jButton_playerCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_playerCardActionPerformed(evt);
            }
        });

        jButton_EventCard.setText("random event card");
        jButton_EventCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_EventCardMouseClicked(evt);
            }
        });
        jButton_EventCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EventCardActionPerformed(evt);
            }
        });

        jButton_Personality.setText("personality card");
        jButton_Personality.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PersonalityMouseClicked(evt);
            }
        });

        jButton_CityCard.setText("city area card");
        jButton_CityCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CityCardMouseClicked(evt);
            }
        });

        jLabel24.setText("Bank :");

        jLabel17.setText("current player");

        javax.swing.GroupLayout MapInfoPanelLayout = new javax.swing.GroupLayout(MapInfoPanel);
        MapInfoPanel.setLayout(MapInfoPanelLayout);
        MapInfoPanelLayout.setHorizontalGroup(
            MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MapInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MapInfoPanelLayout.createSequentialGroup()
                        .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MapDemonNum)
                            .addComponent(MapTrollNum)
                            .addComponent(MapTroubleNum))
                        .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MapInfoPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DemonNumber)
                                    .addComponent(trollNumber))
                                .addGap(31, 31, 31))
                            .addGroup(MapInfoPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(troubleNumber)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(MapInfoPanelLayout.createSequentialGroup()
                        .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_playerCard)
                            .addComponent(jButton_EventCard)
                            .addComponent(jButton_Personality)
                            .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(MapInfoPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(18, 18, 18)
                                    .addComponent(bankMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton_CityCard)))
                        .addGap(0, 15, Short.MAX_VALUE))))
            .addGroup(MapInfoPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MapInfoPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(currentPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        MapInfoPanelLayout.setVerticalGroup(
            MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MapInfoPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MapDemonNum)
                    .addComponent(DemonNumber))
                .addGap(69, 69, 69)
                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MapTrollNum)
                    .addComponent(trollNumber))
                .addGap(74, 74, 74)
                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MapTroubleNum)
                    .addComponent(troubleNumber))
                .addGap(31, 31, 31)
                .addComponent(jButton_playerCard)
                .addGap(18, 18, 18)
                .addComponent(jButton_EventCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Personality)
                .addGap(18, 18, 18)
                .addComponent(jButton_CityCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(MapInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bankMoney, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(8, 8, 8)
                .addComponent(currentPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        actionConfirmButton.setText("Next Player");
        actionConfirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionConfirmButtonMouseClicked(evt);
            }
        });

        showPlayerAreaCard.setText("show area card");
        showPlayerAreaCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPlayerAreaCardMouseClicked(evt);
            }
        });

        endTurnButton.setText("End Turn");
        endTurnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endTurnButtonMouseClicked(evt);
            }
        });

        Menu.setName("Menu"); // NOI18N
        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MapInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CardInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CardConsolePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(endTurnButton)
                                .addGap(12, 12, 12)
                                .addComponent(showPlayerAreaCard))
                            .addComponent(actionConfirmButton)))
                    .addComponent(PlayerInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PlayerInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CardConsolePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showPlayerAreaCard)
                    .addComponent(endTurnButton))
                .addGap(7, 7, 7)
                .addComponent(actionConfirmButton)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MapInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(MapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CardInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jTextField_LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_LoadActionPerformed
        
    }//GEN-LAST:event_jTextField_LoadActionPerformed

    private void jButton_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveActionPerformed
        
    }//GEN-LAST:event_jButton_SaveActionPerformed

    private void jTextField_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SaveActionPerformed
        
    }//GEN-LAST:event_jTextField_SaveActionPerformed

    private void jTextField_InitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_InitActionPerformed
        
    }//GEN-LAST:event_jTextField_InitActionPerformed

    private void jButton_InitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_InitMouseClicked
        String playerInput = jTextField_Init.getText();
        players = playerInput.split(",");
        String[] order =playerInitialOrder.getText().split(",");
        // load initial data
        basicFunction.init(players, order,playerColor);
        
        init();
    }//GEN-LAST:event_jButton_InitMouseClicked

    private void jButton_SaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_SaveMouseClicked
        
        // save data to the file
        basicFunction.save(jTextField_Save.getText());
    }//GEN-LAST:event_jButton_SaveMouseClicked

    private void jButton_LoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_LoadMouseClicked
        
        // load from the filename
        basicFunction.load(jTextField_Load.getText());
        
        init();
    }//GEN-LAST:event_jButton_LoadMouseClicked

    private void jButton_playerCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_playerCardMouseClicked
        
        Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        cardFunction.drawPlayerCard(basicFunction.getDiscWorld(), currentPlayer);
        CardConsolePanel.removeAll();
        labelList=init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(),CardConsolePanel);
        CardConsolePanel.setLayout(new GridLayout(2,2));
        for(int i = 0;i<labelList.size();i++){
            CardConsolePanel.add(labelList.get(i));
        }
        pack();
        CardConsolePanel.repaint();
        //getAreaCard.showCard(CardConsolePanel, basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()).getPlayerCardList().size(),"playerCard");
    }//GEN-LAST:event_jButton_playerCardMouseClicked

    private void jButton_EventCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_EventCardMouseClicked
        Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        cardFunction.drawRandomEventCard(basicFunction.getDiscWorld(), currentPlayer);
        int random = currentPlayer.getRandomEventCardList().get(0);
        RandomEventCard randomCard = basicFunction.getDiscWorld().getRandomEventCard_HASH().get(random);
        RandomEventCardlabel.setText(randomCard.getName());
    }//GEN-LAST:event_jButton_EventCardMouseClicked

    private void jButton_PersonalityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PersonalityMouseClicked
        // TODO add your handling code here:
        String allPersonalityCard="";
        for(PersonalityCard personalityCard : basicFunction.getDiscWorld().getPersonalityCard_HASH().values()){
            allPersonalityCard += "id: "+personalityCard.getId() +" || Name: "+personalityCard.getName()+"  || Win-Condition: "+personalityCard.getVictoryCondition()+"\n";
        }
        CardDescription.setText(allPersonalityCard);
    }//GEN-LAST:event_jButton_PersonalityMouseClicked

    private void jButton_CityCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CityCardMouseClicked
        // TODO add your handling code here:
        String allCityAreaCard="";
        for(CityAreaCard cityAreaCard : basicFunction.getDiscWorld().getCityAreaCard_HASH().values()){
            allCityAreaCard += "id: "+cityAreaCard.getId()+"  Name: "+cityAreaCard.getName()+"  || ability: "+cityAreaCard.getAbility()+"\n";
        }
        CardDescription.setText(allCityAreaCard);
    }//GEN-LAST:event_jButton_CityCardMouseClicked

    private void actionConfirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionConfirmButtonMouseClicked
        // TODO add your handling code here:
        Player pre_player = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        pre_player.getRandomEventCardList().clear();
        RandomEventCardlabel.setText("random");
        
        int numberOfPlayer = basicFunction.getNumPlayer();
        Player cur_player = null;
        
        // refresh the order num for each player and set cur player
        
        for(Player player : basicFunction.getDiscWorld().getPlayer_HASH().values()){
            if(player.getOrder() != 1){
                int cur_order = player.getOrder();
                player.setOrder(cur_order-1);
                if(player.getOrder() == 1){
                    cur_player = player;
                }
            }
        }
        pre_player.setOrder(numberOfPlayer);
        
        basicFunction.getDiscWorld().setCur_Player(cur_player.getID());
        currentPlayer.setText(basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()).getName());
        CardConsolePanel.removeAll();
        labelList.clear();
        labelList=init.initCardDescription(CardDescription, cur_player.getID(),CardConsolePanel);
        CardConsolePanel.setLayout(new GridLayout(2,2));
        for(int i = 0;i<labelList.size();i++){
            CardConsolePanel.add(labelList.get(i));
        }
        pack();
        CardConsolePanel.repaint();
        DiceResult1.setText("#:");
        updatePlayer();
        setMinionIcon(false);
        setBuildingIcon(false);
        setTroubleMarkerIcon(false);
        setTransferMoneyButton(false);
        chosenInterrupt.setText("Interrupt");
        setChooseButton(true);
    }//GEN-LAST:event_actionConfirmButtonMouseClicked
/**
 * from player minion list to area minion list
 * @param evt 
 */
    private void Area1YellowMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1YellowMMouseClicked
        // TODO add your handling code here:
        //player current minion number
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()!=1){
            JOptionPane.showMessageDialog(null, "You can not add yellow minion to this area!");
        }else{
            if (pieceFunction.minionAdd(basicFunction.getDiscWorld(), 1, areaNumber)) {
                Area1YellowMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.YELLOW.toString()) + "");
                if (basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList().size() >= 2) {
                    pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
                    Area1TroubleMarkerLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == ConstantField.DEFAULT_PIECE_AREA_NUMBER ? "False" : "True");
                }
                init.initPlayer(jTextArea_Player1, 0);
                updateArea();
                init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "You can not add minion to this area!");
            }
        }
        
        
    }//GEN-LAST:event_Area1YellowMMouseClicked

    private void Area1BuildingLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1BuildingLabelMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        
        pieceFunction.buildingDelete(basicFunction.getDiscWorld(), areaNumber);
        Area1BuildingLabel.setText("Empty");
        updateArea();
        init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
    }//GEN-LAST:event_Area1BuildingLabelMouseClicked

    private void DiceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiceButtonMouseClicked
        // TODO add your handling code here:
        Random r = new Random();
        int random=r.nextInt(11)+1;
        DiceResult1.setText(DiceResult1.getText()+","+random);
    }//GEN-LAST:event_DiceButtonMouseClicked

    private void Area1YellowMinionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1YellowMinionMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        
        if(curPlayer.getID()==1&&!(RandomEventCardlabel.getText().indexOf("Dragon", 0)>=0||RandomEventCardlabel.getText().indexOf("Mysterious", 0)>=0)){
            JOptionPane.showMessageDialog(null, "You can not delete your own minion!");
        }else{
            pieceFunction.minionDelete(basicFunction.getDiscWorld(), 1, areaNumber);
            pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
            Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
            Area1YellowMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.YELLOW.toString()) + "");
            init.initPlayer(jTextArea_Player1, 0);
            updateArea();
            init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
        }
        
    }//GEN-LAST:event_Area1YellowMinionMouseClicked

    private void PlayerComboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerComboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PlayerComboxActionPerformed

    private void showPlayerAreaCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPlayerAreaCardMouseClicked
        // TODO add your handling code here:
        getAreaCard=null;
        JFrame f = new JFrame("player "+(basicFunction.getDiscWorld().getCur_Player()+1)+" area card");
        f.setLocation(500, 500);
        f.setSize(800, 800);
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new FlowLayout());
        JPanel p1 = new JPanel();
        getAreaCard=new PlayerGetCard(basicFunction,basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()),basicFunction.getDiscWorld().getPlayer_HASH().size(),CardDescription,CardNameLabel);
        p1.setLayout(new FlowLayout());
        System.out.println("current player city area card :"+basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()).getCityAreaCardList().size());
        try{
            areaListForPlayer1=getAreaCard.showCard(p1, basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()).getCityAreaCardList().size(),"areaCard");
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        
        for(int i = 0;i<areaListForPlayer1.size();i++){
            String cardName = areaListForPlayer1.get(i).getText();
            int areaCardID = Integer.parseInt(cardName.substring(14));
            //do not display the area which contain demon
            if(basicFunction.getDiscWorld().getArea_HASH().get(areaCardID).getDemonList().size()<=0){
                p1.add(areaListForPlayer1.get(i));
            }
            
        }
        pack();
        p1.repaint();
        contentPane.add(p1);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_showPlayerAreaCardMouseClicked

    private void jTextArea_Player1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea_Player1MouseClicked
        // TODO add your handling code here:
        final int currentPlayerNumber=basicFunction.getDiscWorld().getCur_Player();
        final Player player= basicFunction.getDiscWorld().getPlayer_HASH().get(orderedList.get(0).getID());
        final Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber);
        JFrame f = new JFrame("one player");
        f.setLocation(500, 500);
        f.setSize(800, 800);
        
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));
        final JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JButton b1 = new JButton();
        final JTextField t1 = new JTextField();
        t1.setPreferredSize(new Dimension(60,50));
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setText(" to ");
        label2.setText("player 1");
        b1.setText("Gives card ");
        final ArrayList <JLabel> labelListForPlayer1 = new ArrayList<JLabel>();
        b1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int moveCardNumber = Integer.parseInt(t1.getText());
                
                //determine choose card or not
                if(chosenPlayerCard.getText().indexOf("Chosen", 0)>0||chosenPlayerCard.getText().length()<7){
                    JOptionPane.showMessageDialog(null, "You have not choose a card to play!");
                }else{
                    int maohaoPosition = chosenPlayerCard.getText().indexOf(":", 0);
                    int chosenCardID = Integer.parseInt(chosenPlayerCard.getText().substring(4, maohaoPosition));
                    //player has that particular card
                    if (currentPlayer.getPlayerCardList().contains(moveCardNumber)) {
                        if (moveCardNumber == chosenCardID) {
                            JOptionPane.showMessageDialog(null, "You can not give the chosen card to other player");
                        }else{
                            cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), currentPlayer, player, moveCardNumber);
                            JLabel newLabel = new JLabel();
                            newLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                            newLabel.setName(t1.getText());
                            newLabel.setText("card" + moveCardNumber);
                            labelListForPlayer1.add(newLabel);
                            p1.add(newLabel);
                            p1.revalidate();
                            p1.repaint();
                            CardConsolePanel.removeAll();
                            labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                            CardConsolePanel.setLayout(new GridLayout(2, 2));
                            for (int i = 0; i < labelList.size(); i++) {
                                CardConsolePanel.add(labelList.get(i));
                            }
                            pack();
                            CardConsolePanel.repaint();
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong input! Please try again.");
                    }
                    
                }
                
            }
        });
        for(int i =0;i<player.getPlayerCardList().size();i++){
            final JLabel temp =  new JLabel();
            temp.setName(String.valueOf(player.getPlayerCardList().get(i)));
            temp.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
            temp.setText("card" + player.getPlayerCardList().get(i));
            temp.setVisible(true);
            labelListForPlayer1.add(temp);
            temp.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), player, basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber), Integer.parseInt(temp.getName()));
                
                labelListForPlayer1.remove(temp);
                p1.remove(temp);
                p1.revalidate();
                p1.repaint();
                CardConsolePanel.removeAll();
                labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);////
                CardConsolePanel.setLayout(new GridLayout(2, 2));
                for (int i = 0; i < labelList.size(); i++) {
                    CardConsolePanel.add(labelList.get(i));
                }
                pack();
                CardConsolePanel.repaint();
                
            }
            });
        }
        for(int i = 0;i<labelListForPlayer1.size();i++){
            p1.add(labelListForPlayer1.get(i));
        }
        
        
        p1.revalidate();
        p1.repaint();
        contentPane.add(p1);
        contentPane.add(p2);
        p2.add(b1);
        p2.add(t1);
        p2.add(label1);
        p2.add(label2);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jTextArea_Player1MouseClicked

    private void jButton_playerCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_playerCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_playerCardActionPerformed

    private void jTextArea_Player2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea_Player2MouseClicked
        // TODO add your handling code here:
        final int currentPlayerNumber=basicFunction.getDiscWorld().getCur_Player();
        final Player player= basicFunction.getDiscWorld().getPlayer_HASH().get(orderedList.get(1).getID());
        final Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber);
        JFrame f = new JFrame("player 2 card");
        f.setLocation(500, 500);
        f.setSize(800, 800);
        
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));
        final JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JButton b1 = new JButton();
        final JTextField t1 = new JTextField();
        t1.setPreferredSize(new Dimension(60,50));
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setText(" to ");
        label2.setText("player 2");
        b1.setText("Gives card ");
        final ArrayList <JLabel> labelListForPlayer1 = new ArrayList<JLabel>();
        b1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int moveCardNumber = Integer.parseInt(t1.getText());
                
                if(chosenPlayerCard.getText().indexOf("Chosen", 0)>0||chosenPlayerCard.getText().length()<7){
                    JOptionPane.showMessageDialog(null, "You have not choose a card to play!");
                }else{
                    int maohaoPosition = chosenPlayerCard.getText().indexOf(":", 0);
                    int chosenCardID = Integer.parseInt(chosenPlayerCard.getText().substring(4, maohaoPosition));
                    //player has that particular card
                    if (currentPlayer.getPlayerCardList().contains(moveCardNumber)) {
                        if (moveCardNumber == chosenCardID) {
                            JOptionPane.showMessageDialog(null, "You can not give the chosen card to other player");
                        }else{
                            cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), currentPlayer, player, moveCardNumber);
                            JLabel newLabel = new JLabel();
                            newLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                            newLabel.setName(t1.getText());
                            newLabel.setText("card" + moveCardNumber);
                            labelListForPlayer1.add(newLabel);
                            p1.add(newLabel);
                            p1.revalidate();
                            p1.repaint();
                            CardConsolePanel.removeAll();
                            labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                            CardConsolePanel.setLayout(new GridLayout(2, 2));
                            for (int i = 0; i < labelList.size(); i++) {
                                CardConsolePanel.add(labelList.get(i));
                            }
                            pack();
                            CardConsolePanel.repaint();
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong input! Please try again.");
                    }
                    
                }
            }
        });
        for(int i =0;i<player.getPlayerCardList().size();i++){
            final JLabel temp =  new JLabel();
            temp.setName(String.valueOf(player.getPlayerCardList().get(i)));
            temp.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
            temp.setText("card" + player.getPlayerCardList().get(i));
            temp.setVisible(true);
            labelListForPlayer1.add(temp);
            temp.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), player, basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber), Integer.parseInt(temp.getName()));
                
                labelListForPlayer1.remove(temp);
                p1.remove(temp);
                p1.revalidate();
                p1.repaint();
                CardConsolePanel.removeAll();
                labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                CardConsolePanel.setLayout(new GridLayout(2, 2));
                for (int i = 0; i < labelList.size(); i++) {
                    CardConsolePanel.add(labelList.get(i));
                }
                pack();
                CardConsolePanel.repaint();
                
            }
            });
        }
        for(int i = 0;i<labelListForPlayer1.size();i++){
            p1.add(labelListForPlayer1.get(i));
        }
        
        
        p1.revalidate();
        p1.repaint();
        contentPane.add(p1);
        contentPane.add(p2);
        p2.add(b1);
        p2.add(t1);
        p2.add(label1);
        p2.add(label2);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jTextArea_Player2MouseClicked

    private void jTextArea_Player3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea_Player3MouseClicked
        // TODO add your handling code here:
        final int currentPlayerNumber=basicFunction.getDiscWorld().getCur_Player();
        final Player player= basicFunction.getDiscWorld().getPlayer_HASH().get(orderedList.get(2).getID());
        final Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber);
        JFrame f = new JFrame("player 3 card");
        f.setLocation(500, 500);
        f.setSize(800, 800);
        
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));
        final JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JButton b1 = new JButton();
        final JTextField t1 = new JTextField();
        t1.setPreferredSize(new Dimension(60,50));
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setText(" to ");
        label2.setText("player 3");
        b1.setText("Gives card ");
        final ArrayList <JLabel> labelListForPlayer1 = new ArrayList<JLabel>();
        b1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int moveCardNumber = Integer.parseInt(t1.getText());
                
                if(chosenPlayerCard.getText().indexOf("Chosen", 0)>0||chosenPlayerCard.getText().length()<7){
                    JOptionPane.showMessageDialog(null, "You have not choose a card to play!");
                }else{
                    int maohaoPosition = chosenPlayerCard.getText().indexOf(":", 0);
                    int chosenCardID = Integer.parseInt(chosenPlayerCard.getText().substring(4, maohaoPosition));
                    //player has that particular card
                    if (currentPlayer.getPlayerCardList().contains(moveCardNumber)) {
                        if (moveCardNumber == chosenCardID) {
                            JOptionPane.showMessageDialog(null, "You can not give the chosen card to other player");
                        }else{
                            cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), currentPlayer, player, moveCardNumber);
                            JLabel newLabel = new JLabel();
                            newLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                            newLabel.setName(t1.getText());
                            newLabel.setText("card" + moveCardNumber);
                            labelListForPlayer1.add(newLabel);
                            p1.add(newLabel);
                            p1.revalidate();
                            p1.repaint();
                            CardConsolePanel.removeAll();
                            labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                            CardConsolePanel.setLayout(new GridLayout(2, 2));
                            for (int i = 0; i < labelList.size(); i++) {
                                CardConsolePanel.add(labelList.get(i));
                            }
                            pack();
                            CardConsolePanel.repaint();
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong input! Please try again.");
                    }
                    
                }
            }
        });
        for(int i =0;i<player.getPlayerCardList().size();i++){
            final JLabel temp =  new JLabel();
            temp.setName(String.valueOf(player.getPlayerCardList().get(i)));
            temp.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
            temp.setText("card" + player.getPlayerCardList().get(i));
            temp.setVisible(true);
            labelListForPlayer1.add(temp);
            temp.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), player, basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber), Integer.parseInt(temp.getName()));
                
                labelListForPlayer1.remove(temp);
                p1.remove(temp);
                p1.revalidate();
                p1.repaint();
                CardConsolePanel.removeAll();
                labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                CardConsolePanel.setLayout(new GridLayout(2, 2));
                for (int i = 0; i < labelList.size(); i++) {
                    CardConsolePanel.add(labelList.get(i));
                }
                pack();
                CardConsolePanel.repaint();
                
            }
            });
        }
        for(int i = 0;i<labelListForPlayer1.size();i++){
            p1.add(labelListForPlayer1.get(i));
        }
        
        
        p1.revalidate();
        p1.repaint();
        contentPane.add(p1);
        contentPane.add(p2);
        p2.add(b1);
        p2.add(t1);
        p2.add(label1);
        p2.add(label2);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jTextArea_Player3MouseClicked

    private void jTextArea_Player4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea_Player4MouseClicked
        // TODO add your handling code here:
        final int currentPlayerNumber=basicFunction.getDiscWorld().getCur_Player();
        final Player player= basicFunction.getDiscWorld().getPlayer_HASH().get(orderedList.get(3).getID());
        final Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber);
        JFrame f = new JFrame("player 4 card");
        f.setLocation(500, 500);
        f.setSize(800, 800);
        
        Container contentPane = f.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));
        final JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        JButton b1 = new JButton();
        final JTextField t1 = new JTextField();
        t1.setPreferredSize(new Dimension(60,50));
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setText(" to ");
        label2.setText("player 4");
        b1.setText("Gives card ");
        final ArrayList <JLabel> labelListForPlayer1 = new ArrayList<JLabel>();
        b1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int moveCardNumber = Integer.parseInt(t1.getText());

                if (chosenPlayerCard.getText().indexOf("Chosen", 0) > 0||chosenPlayerCard.getText().length()<7) {
                    JOptionPane.showMessageDialog(null, "You have not choose a card to play!");
                } else {
                    int maohaoPosition = chosenPlayerCard.getText().indexOf(":", 0);
                    int chosenCardID = Integer.parseInt(chosenPlayerCard.getText().substring(4, maohaoPosition));
                    //player has that particular card
                    if (currentPlayer.getPlayerCardList().contains(moveCardNumber)) {
                        if (moveCardNumber == chosenCardID) {
                            JOptionPane.showMessageDialog(null, "You can not give the chosen card to other player");
                        } else {
                            cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), currentPlayer, player, moveCardNumber);
                            JLabel newLabel = new JLabel();
                            newLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                            newLabel.setName(t1.getText());
                            newLabel.setText("card" + moveCardNumber);
                            labelListForPlayer1.add(newLabel);
                            p1.add(newLabel);
                            p1.revalidate();
                            p1.repaint();
                            CardConsolePanel.removeAll();
                            labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                            CardConsolePanel.setLayout(new GridLayout(2, 2));
                            for (int i = 0; i < labelList.size(); i++) {
                                CardConsolePanel.add(labelList.get(i));
                            }
                            pack();
                            CardConsolePanel.repaint();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong input! Please try again.");
                    }

                }
            }
        });
        for(int i =0;i<player.getPlayerCardList().size();i++){
            final JLabel temp =  new JLabel();
            temp.setName(String.valueOf(player.getPlayerCardList().get(i)));
            temp.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
            temp.setText("card" + player.getPlayerCardList().get(i));
            temp.setVisible(true);
            labelListForPlayer1.add(temp);
            temp.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                cardFunction.grapPlayerCard(basicFunction.getDiscWorld(), player, basicFunction.getDiscWorld().getPlayer_HASH().get(currentPlayerNumber), Integer.parseInt(temp.getName()));
                
                labelListForPlayer1.remove(temp);
                p1.remove(temp);
                p1.revalidate();
                p1.repaint();
                CardConsolePanel.removeAll();
                labelList = init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(), CardConsolePanel);
                CardConsolePanel.setLayout(new GridLayout(2, 2));
                for (int i = 0; i < labelList.size(); i++) {
                    CardConsolePanel.add(labelList.get(i));
                }
                pack();
                CardConsolePanel.repaint();
                
            }
            });
        }
        for(int i = 0;i<labelListForPlayer1.size();i++){
            p1.add(labelListForPlayer1.get(i));
        }
        
        
        p1.revalidate();
        p1.repaint();
        contentPane.add(p1);
        contentPane.add(p2);
        p2.add(b1);
        p2.add(t1);
        p2.add(label1);
        p2.add(label2);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jTextArea_Player4MouseClicked

    private void PlayerGetMoneyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerGetMoneyMouseClicked
        // TODO add your handling code here:
        Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        
        int moneyAmount = 0;
        try{
            moneyAmount=Integer.parseInt(MoneyAmountText.getText());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        String value =PlayerCombox.getSelectedItem().toString();
        if(value.equalsIgnoreCase("1")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(),  basicFunction.getDiscWorld().getPlayer_HASH().get(0), currentPlayer, moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }else if(value.equalsIgnoreCase("2")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(),  basicFunction.getDiscWorld().getPlayer_HASH().get(1), currentPlayer, moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }else if(value.equalsIgnoreCase("3")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(),  basicFunction.getDiscWorld().getPlayer_HASH().get(2), currentPlayer, moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }
       else if(value.equalsIgnoreCase("4")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(),  basicFunction.getDiscWorld().getPlayer_HASH().get(3), currentPlayer, moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }else if(value.equalsIgnoreCase("Bank")){
            moneyFunction.grabBankMoney(basicFunction.getDiscWorld(), currentPlayer, moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
           bankMoney.setText(basicFunction.getDiscWorld().getTotalCoinAmount()+"");
       }
    }//GEN-LAST:event_PlayerGetMoneyMouseClicked

    private void selectAreaComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAreaComboBoxItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED){
            updateAreaSelectedPanel();
            //buildingNumber==ConstantField.DEFAULT_PIECE_AREA_NUMBER?"Empty":basicFunction.getDiscWorld().getBuildingPiece_HASH().get(buildingNumber).getColor()
        }
            System.out.println("select area:"+selectAreaComboBox.getSelectedItem());
        
    }//GEN-LAST:event_selectAreaComboBoxItemStateChanged
/**
 * white player add minion
 * @param evt 
 */
    private void Area1GMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1GMMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()!=2){
            JOptionPane.showMessageDialog(null, "You can not add green minion to this area!");
        }else{
            if (pieceFunction.minionAdd(basicFunction.getDiscWorld(), 2, areaNumber)) {
                Area1GreenMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.GREEN.toString()) + "");
                if (basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList().size() >= 2) {
                    pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
                    Area1TroubleMarkerLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == ConstantField.DEFAULT_PIECE_AREA_NUMBER ? "False" : "True");
                }
                init.initPlayer(jTextArea_Player4, 3);
                updateArea();
                init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "You can not add minion to this area!");
            }
        }
        
    }//GEN-LAST:event_Area1GMMouseClicked
/**
 * blue player delete minion
 * @param evt 
 */
    private void Area1BlueMinionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1BlueMinionMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()==3&&!(RandomEventCardlabel.getText().indexOf("Dragon", 0)>=0||RandomEventCardlabel.getText().indexOf("Mysterious", 0)>=0)){
            JOptionPane.showMessageDialog(null, "You can not delete your own minion!");
        }else{
            pieceFunction.minionDelete(basicFunction.getDiscWorld(), 3, areaNumber);
            pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
            Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
            Area1BlueMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.BLUE.toString()) + "");
            init.initPlayer(jTextArea_Player2, 1);
            updateArea();
            init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
        }
        
    }//GEN-LAST:event_Area1BlueMinionMouseClicked
/**
 * blue player add minion
 * @param evt 
 */
    private void Area1BlueMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1BlueMMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()!=3){
            JOptionPane.showMessageDialog(null, "You can not add blue minion to this area!");
        }else{
            if (pieceFunction.minionAdd(basicFunction.getDiscWorld(), 3, areaNumber)) {
                Area1BlueMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.BLUE.toString()) + "");
                if (basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList().size() >= 2) {
                    pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
                    Area1TroubleMarkerLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == ConstantField.DEFAULT_PIECE_AREA_NUMBER ? "False" : "True");
                }
                init.initPlayer(jTextArea_Player2, 1);
                updateArea();
                init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "You can not add minion to this area!");
            }
        }
        
        
    }//GEN-LAST:event_Area1BlueMMouseClicked
/**
 * red player add minion
 * @param evt 
 */
    private void Area1RMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1RMMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()!=0){
            JOptionPane.showMessageDialog(null, "You can not add red minion to this area!");
        }else{
            if (pieceFunction.minionAdd(basicFunction.getDiscWorld(), 0, areaNumber)) {
                Area1RedMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.RED.toString()) + "");
                if (basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList().size() >= 2) {
                    pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
                    Area1TroubleMarkerLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == ConstantField.DEFAULT_PIECE_AREA_NUMBER ? "False" : "True");
                }
                init.initPlayer(jTextArea_Player3, 2);
                updateArea();
                init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
            } else {
                JOptionPane.showMessageDialog(null, "You can not add minion to this area!");
            }
        }
        
    }//GEN-LAST:event_Area1RMMouseClicked
/**
 * red player delete minion
 * @param evt 
 */
    private void Area1RedMinionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1RedMinionMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()==0&&!(RandomEventCardlabel.getText().indexOf("Dragon", 0)>=0||RandomEventCardlabel.getText().indexOf("Mysterious", 0)>=0)){
            JOptionPane.showMessageDialog(null, "You can not delete your own minion!");
        }else{
            pieceFunction.minionDelete(basicFunction.getDiscWorld(), 0, areaNumber);
            pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
            Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
            Area1RedMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.RED.toString()) + "");
            init.initPlayer(jTextArea_Player3, 2);
            updateArea();
            init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
        }
        
    }//GEN-LAST:event_Area1RedMinionMouseClicked
/**
 * white player delete minion
 * @param evt 
 */
    private void Area1GreenMinionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1GreenMinionMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(curPlayer.getID()==2&&!(RandomEventCardlabel.getText().indexOf("Dragon", 0)>=0||RandomEventCardlabel.getText().indexOf("Mysterious", 0)>=0)){
            JOptionPane.showMessageDialog(null, "You can not delete your own minion!");
        }else{
            pieceFunction.minionDelete(basicFunction.getDiscWorld(), 2, areaNumber);
            pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
            Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
            Area1GreenMinion.setText(Utils.getMinionNumberInArea(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getMinionList(), basicFunction.getDiscWorld().getMinionPiece_HASH(), Color.GREEN.toString()) + "");
            init.initPlayer(jTextArea_Player4, 3);
            updateArea();
            init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
        }
        
    }//GEN-LAST:event_Area1GreenMinionMouseClicked

    private void Area1TroubleMarkerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1TroubleMarkerMouseClicked
        // TODO add your handling code here:
         int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
         pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
         Area1TroubleMarkerLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum()==ConstantField.DEFAULT_PIECE_AREA_NUMBER?"False":"True");
         updateArea();
         init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
    }//GEN-LAST:event_Area1TroubleMarkerMouseClicked

    private void Area1TroubleMarkerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1TroubleMarkerLabelMouseClicked
        // TODO add your handling code here:
         int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
         pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
         Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum()==-1)?"False":"True");
         updateArea();
         init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
    }//GEN-LAST:event_Area1TroubleMarkerLabelMouseClicked

    private void AreaBuildingAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AreaBuildingAddMouseClicked
        // TODO add your handling code here:
         int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
         Area area = basicFunction.getDiscWorld().getArea_HASH().get(areaNumber);
         Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
         if(pieceFunction.canAddBuilding(basicFunction.getDiscWorld(), basicFunction.getDiscWorld().getCur_Player(), areaNumber)){
             pieceFunction.buildingAdd(basicFunction.getDiscWorld(), basicFunction.getDiscWorld().getCur_Player(), areaNumber);
             Area1BuildingLabel.setText(basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player()).getColor() + "");
             moneyFunction.giveBankMoney(basicFunction.getDiscWorld(), curPlayer, area.getBuildingCost());//////
             updateArea();
             updatePlayer();
             init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
             bankMoney.setText(basicFunction.getDiscWorld().getTotalCoinAmount()+"");
         }else{
             JOptionPane.showMessageDialog(null, "You can not add building piece to this area!\n"
                     + "Please check if you have minion in this area \n"
                     + "and the area is available and you have enough \n"
                     + " money and enough building piece and \n"
                     + "no troublemarker");
         }
         
    }//GEN-LAST:event_AreaBuildingAddMouseClicked

    private void AreaTrollAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AreaTrollAddMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        pieceFunction.trollAdd(basicFunction.getDiscWorld(), areaNumber);
        pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
        Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
        Area1TrollLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTrollList().size()+"");
        updateArea();
        init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
        
    }//GEN-LAST:event_AreaTrollAddMouseClicked

    private void Area1TrollLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1TrollLabelMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        pieceFunction.trollDelete(basicFunction.getDiscWorld(), areaNumber);
        pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
        Area1TrollLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTrollList().size()+"");
        Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
        updateArea();
        init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
    }//GEN-LAST:event_Area1TrollLabelMouseClicked

    private void AreaDemonAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AreaDemonAddMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        if(!pieceFunction.canAddDemon(basicFunction.getDiscWorld())){
            JOptionPane.showMessageDialog(null, "Running out of demon piece!");
        }else{
            pieceFunction.demonAdd(basicFunction.getDiscWorld(), areaNumber);
            pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), areaNumber);
            Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
            Area1DemonLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getDemonList().size() + "");
            updateArea();
            init.initMapInfo(DemonNumber, trollNumber, troubleNumber, bankMoney, currentPlayer);
            init.initPlayer(jTextArea_Player1, 0);
            updateAreaSelectedPanel();
        }
        
    }//GEN-LAST:event_AreaDemonAddMouseClicked

    private void Area1DemonLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Area1DemonLabelMouseClicked
        // TODO add your handling code here:
        int areaNumber = Integer.parseInt(selectAreaComboBox.getSelectedItem().toString());
        pieceFunction.demonDelete(basicFunction.getDiscWorld(), areaNumber);
        pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), areaNumber);
        Area1DemonLabel.setText(basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getDemonList().size()+"");
        Area1TroubleMarkerLabel.setText((basicFunction.getDiscWorld().getArea_HASH().get(areaNumber).getTroubleMarkerNum() == -1) ? "False" : "True");
        updateArea();
        init.initMapInfo(DemonNumber, trollNumber, troubleNumber,bankMoney,currentPlayer);
        
    }//GEN-LAST:event_Area1DemonLabelMouseClicked

    private void jButton_EventCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EventCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_EventCardActionPerformed

    private void RandomEventCardlabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RandomEventCardlabelMouseEntered
        // TODO add your handling code here:
        Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        if(currentPlayer.getRandomEventCardList().size()>0){
            int random = currentPlayer.getRandomEventCardList().get(0);
            RandomEventCard randomCard = basicFunction.getDiscWorld().getRandomEventCard_HASH().get(random);
            CardDescription.setText("random event card effect: \n"+randomCard.getEffect());
            CardNameLabel.setText(randomCard.getName());
        }
        
    }//GEN-LAST:event_RandomEventCardlabelMouseEntered

    private void PlayerSendMoneyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerSendMoneyMouseClicked
        // TODO add your handling code here:
        Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        
        int moneyAmount = Integer.parseInt(MoneyAmountText.getText());
        String value =PlayerCombox.getSelectedItem().toString();
        if(value.equalsIgnoreCase("1")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(), currentPlayer, basicFunction.getDiscWorld().getPlayer_HASH().get(0), moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }else if(value.equalsIgnoreCase("2")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(), currentPlayer,  basicFunction.getDiscWorld().getPlayer_HASH().get(1), moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }else if(value.equalsIgnoreCase("3")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(), currentPlayer,  basicFunction.getDiscWorld().getPlayer_HASH().get(2), moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }
       else if(value.equalsIgnoreCase("4")){
            moneyFunction.grabPlayerMoney(basicFunction.getDiscWorld(), currentPlayer,  basicFunction.getDiscWorld().getPlayer_HASH().get(3), moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
       }else if(value.equalsIgnoreCase("Bank")){
            moneyFunction.giveBankMoney(basicFunction.getDiscWorld(), currentPlayer, moneyAmount);
            init.initPlayer(jTextArea_Player1, 0);
            init.initPlayer(jTextArea_Player2, 1);
            init.initPlayer(jTextArea_Player3, 2);
            init.initPlayer(jTextArea_Player4, 3);
           bankMoney.setText(basicFunction.getDiscWorld().getTotalCoinAmount()+"");
       }
    }//GEN-LAST:event_PlayerSendMoneyMouseClicked

    private void confirmRoleWinButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmRoleWinButtonMouseClicked
        // TODO add your handling code here:
        winCondition = new WinningConditionFunction(basicFunction.getDiscWorld());
        int roleIndex = roleWinComboBox.getSelectedIndex()+1;
        String roleName = roleWinComboBox.getSelectedItem().toString();
        boolean winResult = false;
        int winnerID=-1;
        switch (roleIndex){
            case 1:
                winResult = winCondition.isWin_LordVetinari();
                break;
            case 2:
                winResult = winCondition.isWin_LordSelachii();
                break;
            case 3:
                winResult = winCondition.isWin_LordRust();
                break;
            case 4:
                winResult = winCondition.isWin_LordDeWorde();
                break;
            case 5:
                winResult = winCondition.isWin_DragonKingOfArms();
                break;
            case 6:
                winResult = winCondition.isWin_Chrysoprase();
                System.out.println(roleIndex+"hahahahaha");
                break;
            case 7:
                winResult = winCondition.isWin_CommanderVimes();
                break;
            case 8:
                winnerID = winCondition.whoWin();
            default:
                System.out.println("You are not one of the seven Lords!");
                break;
        }
        if(winResult){
            JOptionPane.showMessageDialog(null, roleName+" win the game!");
        }else if(winnerID>=0){
            Player winPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(winnerID);
            JOptionPane.showMessageDialog(null, winPlayer.getName()+" win the game!");
        }else{
            JOptionPane.showMessageDialog(null, roleName+", you do not win the game, game continues!");
        }
    }//GEN-LAST:event_confirmRoleWinButtonMouseClicked

    private void moveMinionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveMinionButtonMouseClicked
        // TODO add your handling code here:
        int fromAreaNumber = Integer.parseInt(moveMinionFromArea.getSelectedItem().toString());
        int toAreaNumber = Integer.parseInt(moveMinionToArea.getSelectedItem().toString());
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        //move demon
        if(moveMinionPlayerID.getSelectedItem().toString().equalsIgnoreCase("5")){
            pieceFunction.demonAdd(basicFunction.getDiscWorld(), toAreaNumber);
            pieceFunction.demonDelete(basicFunction.getDiscWorld(), fromAreaNumber);
            //move troll
        }else if(moveMinionPlayerID.getSelectedItem().toString().equalsIgnoreCase("6")){
            pieceFunction.trollAdd(basicFunction.getDiscWorld(), toAreaNumber);
            pieceFunction.trollDelete(basicFunction.getDiscWorld(), toAreaNumber);
        }else{
            int playerID = Integer.parseInt(moveMinionPlayerID.getSelectedItem().toString()) - 1;
            pieceFunction.minionMove(basicFunction.getDiscWorld(), playerID, fromAreaNumber, toAreaNumber);
            pieceFunction.troubleMarkerDelete(basicFunction.getDiscWorld(), fromAreaNumber);
            if (pieceFunction.canAddTroubleMarker(basicFunction.getDiscWorld(), toAreaNumber)) {
                pieceFunction.troubleMarkerAdd(basicFunction.getDiscWorld(), toAreaNumber);
            }
        }
 
        updateAreaSelectedPanel();
        updateArea();
        updateAreaSelectedPanel();
    }//GEN-LAST:event_moveMinionButtonMouseClicked

    private void endTurnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endTurnButtonMouseClicked
        // TODO add your handling code here:
        Player currentPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        cardFunction.drawToFiveCards(basicFunction.getDiscWorld(), currentPlayer);
        CardConsolePanel.removeAll();
        labelList=init.initCardDescription(CardDescription, basicFunction.getDiscWorld().getCur_Player(),CardConsolePanel);
        CardConsolePanel.setLayout(new GridLayout(2,2));
        for(int i = 0;i<labelList.size();i++){
            CardConsolePanel.add(labelList.get(i));
        }
        chosenPlayerCard.setText("chosen");
        chosenCardPlayer.setText("name:");
        pack();
        CardConsolePanel.repaint();
    }//GEN-LAST:event_endTurnButtonMouseClicked

    private void chooseCardButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseCardButtonMouseClicked
        // TODO add your handling code here:
        setMinionIcon(false);
        setBuildingIcon(false);
        setTroubleMarkerIcon(false);
        setTransferMoneyButton(false);
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        chosenPlayerCard.setText(CardNameLabel.getText());
        chosenCardPlayer.setText(curPlayer.getName());
        String[] temp=chosenPlayerCard.getText().split(":");
        int playerCardID = Integer.parseInt(temp[0].substring(4));
        PlayerCard tempCard = basicFunction.getDiscWorld().getPlayerCard_HASH().get(playerCardID);
        if(tempCard.getAction_text().indexOf("Scroll", 0)>=0){
            setMinionIcon(true);
            setBuildingIcon(true);  
            setTroubleMarkerIcon(true);
            setTransferMoneyButton(true);
        }else{
            if (tempCard.getAction_text().indexOf("PlaceMinion", 0) >= 0 || tempCard.getAction_text().indexOf("Assassination", 0) >= 0) {
                setMinionIcon(true);
            }
            if (tempCard.getAction_text().indexOf("PlaceBuilding", 0) >= 0) {
                setBuildingIcon(true);
            }
            if (tempCard.getAction_text().indexOf("RemoveTroubleMarker", 0) >= 0) {
                setTroubleMarkerIcon(true);
            }
            if (tempCard.getAction_text().indexOf("TakeMoney", 0) >= 0) {
                setTransferMoneyButton(true);
            }
            if (tempCard.getAction_text().indexOf("PlayAnotherCard", 0) < 0) {
                setChooseButton(false);
            }
        }
        
        
        //moveMinionButton.setEnabled(false);
        //RemoveTroubleMarker
    }//GEN-LAST:event_chooseCardButtonMouseClicked

    private void chosenInterruptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chosenInterruptMouseClicked
        // TODO add your handling code here:
        Player curPlayer = basicFunction.getDiscWorld().getPlayer_HASH().get(basicFunction.getDiscWorld().getCur_Player());
        int interruptCardID = interruptCard.hasInterruptCard(curPlayer.getID());
        if(interruptCardID<=0){
            JOptionPane.showMessageDialog(null, "You do not have interrupt card!");
        }else{
            PlayerCard interruptCard = basicFunction.getDiscWorld().getPlayerCard_HASH().get(interruptCardID);
            chosenInterrupt.setText(interruptCard.getName());
            setMinionIcon(true);
            setBuildingIcon(true);
            setTroubleMarkerIcon(true);
            setTransferMoneyButton(true);
        }
    }//GEN-LAST:event_chosenInterruptMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Area10Panel;
    private javax.swing.JPanel Area11Panel;
    private javax.swing.JPanel Area12Panel;
    private javax.swing.JLabel Area1BlueM;
    private javax.swing.JLabel Area1BlueMinion;
    private javax.swing.JLabel Area1BuildingLabel;
    private javax.swing.JLabel Area1DemonLabel;
    private javax.swing.JLabel Area1GM;
    private javax.swing.JLabel Area1GreenMinion;
    private javax.swing.JPanel Area1Panel;
    private javax.swing.JLabel Area1RM;
    private javax.swing.JLabel Area1RedMinion;
    private javax.swing.JLabel Area1TrollLabel;
    private javax.swing.JLabel Area1TroubleMarker;
    private javax.swing.JLabel Area1TroubleMarkerLabel;
    private javax.swing.JLabel Area1YellowM;
    private javax.swing.JLabel Area1YellowMinion;
    private javax.swing.JPanel Area2Panel;
    private javax.swing.JPanel Area3Panel;
    private javax.swing.JPanel Area4Panel;
    private javax.swing.JPanel Area5Panel;
    private javax.swing.JPanel Area6Panel;
    private javax.swing.JPanel Area7Panel;
    private javax.swing.JPanel Area8Panel;
    private javax.swing.JPanel Area9Panel;
    private javax.swing.JLabel AreaBuildingAdd;
    private javax.swing.JLabel AreaDemonAdd;
    private javax.swing.JLabel AreaTrollAdd;
    private javax.swing.JPanel CardConsolePanel;
    public javax.swing.JTextArea CardDescription;
    private javax.swing.JPanel CardInfoPanel;
    private javax.swing.JLabel CardNameLabel;
    private javax.swing.JLabel DemonNumber;
    private javax.swing.JButton DiceButton;
    private javax.swing.JLabel DiceResult1;
    private javax.swing.JLabel MapDemonNum;
    private javax.swing.JPanel MapInfoPanel;
    private javax.swing.JPanel MapPanel;
    private javax.swing.JLabel MapTrollNum;
    private javax.swing.JLabel MapTroubleNum;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JTextField MoneyAmountText;
    private javax.swing.JLabel PlayerActionFromLabel;
    private javax.swing.JPanel PlayerActionPanel;
    private javax.swing.JComboBox PlayerCombox;
    private javax.swing.JButton PlayerGetMoney;
    private javax.swing.JPanel PlayerInfoPanel;
    private javax.swing.JButton PlayerSendMoney;
    private javax.swing.JLabel RandomEventCardlabel;
    private javax.swing.JButton actionConfirmButton;
    private javax.swing.JLabel bankMoney;
    private javax.swing.JButton chooseCardButton;
    private javax.swing.JLabel chosenCardPlayer;
    private javax.swing.JLabel chosenInterrupt;
    private javax.swing.JLabel chosenPlayerCard;
    private javax.swing.JButton confirmRoleWinButton;
    private javax.swing.JLabel currentPlayer;
    private javax.swing.JButton endTurnButton;
    private javax.swing.JButton jButton_CityCard;
    private javax.swing.JButton jButton_EventCard;
    private javax.swing.JButton jButton_Init;
    private javax.swing.JButton jButton_Load;
    private javax.swing.JButton jButton_Personality;
    private javax.swing.JButton jButton_Save;
    private javax.swing.JButton jButton_playerCard;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea10;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTextArea jTextArea12;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private javax.swing.JTextArea jTextArea_Player1;
    private javax.swing.JTextArea jTextArea_Player2;
    private javax.swing.JTextArea jTextArea_Player3;
    private javax.swing.JTextArea jTextArea_Player4;
    private javax.swing.JTextField jTextField_Init;
    private javax.swing.JTextField jTextField_Load;
    private javax.swing.JTextField jTextField_Save;
    private javax.swing.JButton moveMinionButton;
    private javax.swing.JComboBox moveMinionFromArea;
    private javax.swing.JLabel moveMinionFromLabel;
    private javax.swing.JComboBox moveMinionPlayerID;
    private javax.swing.JComboBox moveMinionToArea;
    private javax.swing.JLabel moveMinionToLabel;
    private javax.swing.JTextField playerInitialOrder;
    private javax.swing.JLabel playerWinLabel;
    private javax.swing.JComboBox roleWinComboBox;
    private javax.swing.JComboBox selectAreaComboBox;
    private javax.swing.JButton showPlayerAreaCard;
    private javax.swing.JLabel to;
    private javax.swing.JLabel trollNumber;
    private javax.swing.JLabel troubleNumber;
    // End of variables declaration//GEN-END:variables
}
