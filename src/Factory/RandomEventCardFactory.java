/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Factory;

import Model.card.RandomEventCard;
import java.util.Scanner;

/**
 *
 * @author yucunli
 */
public class RandomEventCardFactory {
    
    /**
     *factory method return a random event card.
     * @param line
     * @return
     */
    public RandomEventCard getRandomEventCard(String line){
        Scanner dataScanner = new Scanner(line);
        int index = 0;  
        
        dataScanner.useDelimiter(",");
        RandomEventCard randomEventCard = new RandomEventCard();
        
        while (dataScanner.hasNext()) {
            String data = dataScanner.next();
            if (index == 0) {
                randomEventCard.setId(Integer.parseInt(data));
            } else if (index == 1) {
                randomEventCard.setName(data);
            } else if (index == 2) {
                randomEventCard.setEffect(data);
            } else if (index == 3) {
                randomEventCard.setPlayerID(Integer.parseInt(data));
            } else if (index == 4) {
                randomEventCard.setOrder(Integer.parseInt(data));
            } else if (index == 5) {
                randomEventCard.setUsed(Integer.parseInt(data));
            } else {
                System.out.println("invalid data::" + data);
            }
            index++;
        }
        
        return randomEventCard;
    }
}
