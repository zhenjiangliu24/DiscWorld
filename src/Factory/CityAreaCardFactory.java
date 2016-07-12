/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Factory;

import Model.card.CityAreaCard;
import Model.card.RandomEventCard;
import java.util.Scanner;

/**
 *
 * @author yucunli
 */
public class CityAreaCardFactory {
    
    /**
     *factory method return a city area card.
     * @param line
     * @return
     */
    public CityAreaCard getCityAreaCard(String line){
        Scanner dataScanner = new Scanner(line);
        int index = 0;    
                
        dataScanner.useDelimiter(",");
        CityAreaCard cityAreaCard = new CityAreaCard();

        while (dataScanner.hasNext()) {
            String data = dataScanner.next();
            if (index == 0) {
                cityAreaCard.setId(Integer.parseInt(data));
            } else if (index == 1) {
                cityAreaCard.setName(data);
            } else if (index == 2) {
                cityAreaCard.setAbility(data);
            } else if (index == 3) {
                cityAreaCard.setPlayerID(Integer.parseInt(data));
            } else {
                System.out.println("invalid data::" + data);
            }
            
            index++;
        }
        
        return cityAreaCard;
    }
}
