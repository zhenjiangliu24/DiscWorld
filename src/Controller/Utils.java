package Controller;

import Model.piece.MinionPiece;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author yucunli
 */
public class Utils {

    /**
     *Convert a list of integers into a string.
     * @param list
     * @return String
     */
    public static String listToString(List<Integer> list){
        String result = "";
        for(Integer integer : list){
            result += integer + " ";
        }
        return result;
    }
    
    // input : area's minion List

    /**
     *Convert a minion list into a string.
     * @param list
     * @param minionMap
     * @return String
     */
        public static String minionListToColorString(List<Integer> list, HashMap<Integer, MinionPiece> minionMap){
        String result = "";
        for(Integer integer : list){
            result += minionMap.get(integer).getColor().toString() + " ";
        }
        
        return result;
    }

    /**
     *get a contain player's minion number in a area.
     * @param list
     * @param minionMap
     * @param string
     * @return
     */
    public static int getMinionNumberInArea(List<Integer> list, HashMap<Integer, MinionPiece> minionMap,String string){
            int blackMinionNumber=0;
            for(Integer integer : list){
                if(minionMap.get(integer).getColor().toString().equalsIgnoreCase(string))
                    ++blackMinionNumber;
            }
            return blackMinionNumber;
        }
}
