

package Model.piece;

/**
 *
 * @author yucunli
 */
public class Piece {

    /**
     *Piece ID.
     */
    protected int id;

    /**
     *Area number the piece belong to.
     */
    protected int areaNumber; // belong to which area, default value for not in area

    /**
     *Constructor, set the piece ID, area number it belongs to.
     * @param id
     * @param areaNumber
     */
    public Piece(int id, int areaNumber) {
        this.id = id;
        this.areaNumber = areaNumber;
    }

    /**
     *Function to get the piece ID.
     * @return piece ID.
     */
    public int getId() {
        return id;
    }

    /**
     *Function to set the piece ID.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Function to get the area number the piece belongs to.
     * @return area number.
     */
    public int getAreaNumber() {
        return areaNumber;
    }

    /**
     *Function to set the area number the piece belongs to.
     * @param areaNumber
     */
    public void setAreaNumber(int areaNumber) {
        this.areaNumber = areaNumber;
    }
    
    
}
