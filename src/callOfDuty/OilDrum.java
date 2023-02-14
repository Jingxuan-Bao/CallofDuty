package callOfDuty;

/**
 * The OliDrum class extends the Target class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */

public class OilDrum extends Target{
    // the type of the target
    private static String type = "oilDrum";

    private static int length = 1;

    private static int width = 1;

    // the constructor of the oildrum
    public OilDrum(Base base) {
        super(length, width, base);
    }

    /**
     * implements the explode of the oildrum
     */
    @Override
    void explode() {
        //find the explosion range of the armory
        //four points: rowup, rowdown, colleft, and colright represents the range
        int rowHead = getCoordinate()[0];
        int colHead = getCoordinate()[1];
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
                    // hit the point
                    if(! base.getTargetsArray()[i][j].isDestroyed()) {
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

}
