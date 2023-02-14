package callOfDuty;

/**
 * The RocketLauncher class extends the Weapon class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public class RocketLauncher extends Weapon{
    // the initial shotleft of missile
    private final static int shotCount = 20;

    // the type of the weapon
    final static String type = "RocketLauncher";

    // the constructor of the rocketlauncher
    public RocketLauncher() {
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
        // if the target is not destroyed
        if (!base.getTargetsArray()[row][column].isDestroyed()) {
            // hit the target
            base.shootAt(row, column);
            // check whether the target will explode
            if (base.getTargetsArray()[row][column].getTargetName().equals("armory")) {
                if (base.getTargetsArray()[row][column].isDestroyed()) {
                    base.getTargetsArray()[row][column].explode();
                }
            }
            if (base.getTargetsArray()[row][column].getTargetName().equals("tank")) {
                if (base.getTargetsArray()[row][column].isDestroyed()) {
                    base.getTargetsArray()[row][column].explode();
                }
            }
            if (base.getTargetsArray()[row][column].getTargetName().equals("OilDrum")) {
                if (base.getTargetsArray()[row][column].isDestroyed()) {
                    base.getTargetsArray()[row][column].explode();
                }
            }
        }
        // reduce the shotleft
        decrementShotLeft();
        // increase the shots count
        base.incrementShotsCount();
    }
}
