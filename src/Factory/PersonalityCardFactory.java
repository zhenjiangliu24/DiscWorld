/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Factory;

import Model.card.PersonalityCard;
import java.util.Scanner;

/**
 *
 * @author yucunli
 */
public class PersonalityCardFactory {
    
    /**
     *factory method return a personality card.
     * @param line
     * @return
     */
    public PersonalityCard getPersonalityCard(String line){
        Scanner dataScanner = new Scanner(line);
        int index = 0;

        dataScanner.useDelimiter(",");
        PersonalityCard personalityCard = new PersonalityCard(-1, -1, "", "");

        while (dataScanner.hasNext()) {
            String data = dataScanner.next();
            if (index == 0) {
                personalityCard.setId(Integer.parseInt(data));
            } else if (index == 1) {
                personalityCard.setName(data);
            } else if (index == 2) {
                personalityCard.setVictoryCondition(data);
            } else if (index == 3) {
                personalityCard.setPlayerID(Integer.parseInt(data));
            } else {
                System.out.println("invalid data::" + data);
            }
            index++;
        }
        
        return personalityCard;
    }
}
