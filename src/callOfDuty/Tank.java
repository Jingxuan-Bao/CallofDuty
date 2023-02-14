package callOfDuty;

/**
 * The Tank class extends the Target class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */

public class Tank extends Target{
    // the type of the target
    private static String type = "tank";

    private static int length = 1;

    private static int width = 1;

    // the constructor of the tank
    public Tank(Base base) {
        super(length, width, base);
    }

    /**
     * implements the explode of the tank
     */
    @Override
    void explode() {
        //find the explosion range of the armory
        //four points: rowup, rowdown, colleft, and colright represents the range
        int[] coordinate = this.getCoordinate();
        int rowHead = coordinate[0];
        int colHead = coordinate[1];
        int rowup = 0;
        int rowdown = 0;
        int colleft = 0;
        int colright = 0;
        if(! isHorizontal()) {
            if(rowHead - 2 < 0) {
                rowup = 0;
            }
            else{
                rowup = rowHead - 2;
            }
            if(rowHead + 2 > 9) {
                rowdown = 9;
            }
            else {
                rowdown = rowHead + 2;
            }
            if(colHead - 2 < 0) {
                colleft = 0;
            }
            else {
                colleft = colHead - 2;
            }
            if(colHead + 2 > 9) {
                colright = 9;
            }
            else {
                colright = colHead + 2;
            }
        }
        else {
            if(rowHead - 2 < 0) {
                rowup = 0;
            }
            else{
                rowup = rowHead - 2;
            }
            if(rowHead + 2 > 9) {
                rowdown = 9;
            }
            else {
                rowdown = rowHead + 2;
            }
            if(colHead - 2 < 0) {
                colleft = 0;
            }
            else {
                colleft = colHead - 2;
            }
            if(colHead + 2 > 9) {
                colright = 9;
            }
            else {
                colright = colHead + 2;
            }
        }

        // get the base object of the armory
        Base base = this.getBase();

        // loop the explosion range
        for(int i = rowup; i <= rowdown; i ++) {
            for(int j = colleft; j <= colright; j ++) {
                // if the target is not ground
                if (! base.getTargetsArray()[i][j].getTargetName().equals("ground")) {
                    if(! base.getTargetsArray()[i][j].isDestroyed()) {
                        // hit the point
                        base.getTargetsArray()[i][j].getShot(i, j);
                        // check whether the explosion will lead more explosion
                        if (base.getTargetsArray()[i][j].getTargetName().equals("oilDrum")) {
                            if (base.getTargetsArray()[i][j].isDestroyed()) {
                                base.getTargetsArray()[i][j].explode();
                            }
                        }
                        if (base.getTargetsArray()[i][j].getTargetName().equals("tank")) {
                            if (base.getTargetsArray()[i][j].isDestroyed()) {
                                base.getTargetsArray()[i][j].explode();
                            }
                        }
                        if (base.getTargetsArray()[i][j].getTargetName().equals("armory")) {
                            if (base.getTargetsArray()[i][j].isDestroyed()) {
                                base.getTargetsArray()[i][j].explode();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @return return the type of the target
     */
    @Override
    public String getTargetName() {
        return this.type;
    }

    /**
     * check whether the tank is destroyed
     * @return tank is destroyed or not
     */
    @Override
    public boolean isDestroyed() {
        if(this.getHit()[0][0] >= 2) {
            return true;
        }
        return false;
    }

    /**
     * @return return the "X" shows the tank is destroyed, "T" means is not destroyed
     */
    @Override
    public String toString() {
        if(isDestroyed()) {
            return "X";
        }
        return "T";
    }

}
