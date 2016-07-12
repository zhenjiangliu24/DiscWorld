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
public class RandomEvent extends Action{

    /**
     *Constructor, draw a random event card action on the player card.
     */
    public RandomEvent() {
        super("You must draw the top card from the Random Event deck.");
    }
}
