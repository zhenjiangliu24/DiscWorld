/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Behavior;

/**
 *
 * @author yucunli
 */
public class PlaceMinion extends Action{

    /**
     *Constructor, place minion piece action on the player card.
     */
    public PlaceMinion() {
        super("You take one of your minion pieces and you place it in an area on the board. "
                + "You must place it in either an area that you already have a minion in or in an adjacent area.");
        
    }
}
