package callOfDuty;

/**
 * The Missile class extends the Weapon class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public class Missile extends Weapon {
    // the type of the weapon
    private final static String type = "Missile";

    // the initial shotleft of missile
    private final static int shotCount = 3;

    // the constructor of the missile
    public Missile(){
        super(shotCount);
    }

    /**
     * @return return the type of the weapon
     */
    @Override
    public String getWeaponType() {
        return this.type;
    }

    /**
     * shot at the the row and column of the base
     * @param row the row of the base
     * @param column the column of the base
     * @param base the basemap of the game
     */
    @Override
    public void shootAt(int row, int column, Base base) {
        // if there is no shotleft
        if(getShotleft() <= 0) {
            System.out.println("no shot enough");
            return;
        }
        // get the hit range of the missile
        int rowup = 0;
        int rowdown = 0;
        int colleft = 0;
        int colright = 0;
        if(row -1 < 0) {
            rowup = 0;
        }
        else {
            rowup = row - 1;
        }
        if(row +1 > 9) {
            rowdown = 9;
        }
        else {
            rowdown = row + 1;
        }
        if(column -1 < 0) {
            colleft = 0;
        }
        else {
            colleft = column - 1;
        }
        if(column +1 > 9) {
            colright = 9;
        }
        else {
            colright = column + 1;
        }

        // loop the hit range of the missile
        for(int i = rowup; i <= rowdown; i ++) {
            for (int j = colleft; j <= colright; j++) {
                // if the target is not destroyed
                if (!base.getTargetsArray()[i][j].isDestroyed()) {
                    // hit the target
                    base.shootAt(i, j);
                    // check whether the target will explode
                    if (base.getTargetsArray()[i][j].getTargetName().equals("armory")) {
                        if (base.getTargetsArray()[i][j].isDestroyed()) {
                            base.getTargetsArray()[i][j].explode();
                        }
                    }
                    if (base.getTargetsArray()[i][j].getTargetName().equals("tank")) {
                        if (base.getTargetsArray()[i][j].isDestroyed()) {
                            base.getTargetsArray()[i][j].explode();
                        }
                    }
                    if (base.getTargetsArray()[i][j].getTargetName().equals("oilDrum")) {
                        if (base.getTargetsArray()[i][j].isDestroyed()) {
                            base.getTargetsArray()[i][j].explode();
                        }
                    }
                }
            }
        }
        // reduce the shotleft
        decrementShotLeft();
        // increase the shots count
        base.incrementShotsCount();
    }

}
