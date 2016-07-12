
package Controller;

import java.io.File;

/**
 *
 * @author Zhenjiang Liu
 */
public class fileIsExist {
    //String filePath="./Data/statusData/";
    private String fileName="";
    public fileIsExist(String fileName){
        this.fileName=fileName;
    }
    public boolean isExist(){
        File file = new File(fileName);
        return file.exists();
    }
}
