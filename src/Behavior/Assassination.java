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
public class Assassination extends Action{
    /**
     *Constructor, assassination action on the player card.
     */
    public Assassination() {
        super("Remove one minion(but not your own)"
              + "or troll or demon of your choice from an area that contains a trouble marker.");
    }
}
