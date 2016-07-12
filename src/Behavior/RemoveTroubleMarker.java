/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Behavior;

import Model.piece.TroubleMarker;

/**
 *
 * @author yucunli
 */
public class RemoveTroubleMarker extends Action{

    /**
     *Constructor, remove one trouble marker piece from the area action on the player card.
     */
    public RemoveTroubleMarker() {
        super("You remove one trouble marker from an area of your choice.");
    }
        
}
