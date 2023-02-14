package callOfDuty;

/**
 * The Weapon class represents the weapon user can used in the game callofDuty.
 * The Weapon class provides the basic methods of weapon the user need in the game.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public abstract class Weapon {
    //the number of shots left. Initially, it’s 20 for RocketLauncher and 3 for Missile.
    private int shotleft;

    //the constructor of weapon
    public Weapon(int shotCount) {
        this.shotleft = shotCount;
    }

    /**
     * get the shotleft of the weapon
     * @return return the shotleft of the weapon
     */
    public int getShotleft() {
        return this.shotleft;
    }

    // the type of the weapon
    public abstract String getWeaponType();

    /**
     * shot at the the row and column of the base
     * @param row the row of the base
     * @param column the column of the base
     * @param base the basemap of the game
     */
    public abstract void shootAt(int row, int column, Base base);

    /**
     * reduce one of the shotleft
     */
    public void decrementShotLeft() {
        if(shotleft > 0) {
            this.shotleft --;
        }
        else {
            System.out.println("No shoot left! ");
        }
    }
}
