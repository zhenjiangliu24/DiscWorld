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
public class TakeMoney extends Action{
    private int amount;

    /**
     *Constructor, player take amount of money from the bank.
     * @param amount
     */
    public TakeMoney(int amount) {
        super("The gold circle has an amount of money shown in it. \n"
                + "You take"+ amount + " from the bank.");
        this.amount = amount;
    }
    
    /**
     *get money amount.
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     *set money amount.
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
