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
public class Action {
    private String description;

    /**
     *Constructor, card action.
     */
    public Action(){
        description="";
    }
    
    public Action(String description){
        this.description = description;
    }

    /**
     *return the action description.
     * @return
     */
    public String getDescription(){
        return description;
    }
}
