package callOfDuty;

/**
 * The Ground class extends the Target class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public class Ground extends Target{

    static final int length = 1;

    static final int width = 1;

    // the type of Target
    private static String type = "ground";

    // the constructor of ground
    public Ground(Base base) {
        super(length, width, base);
    }

    /**
     * do nothing because ground will not explode
     */
    @Override
    public void explode(){

    }

    /**
     * @return return the type of the target
     */
    @Override
    public String getTargetName() {
        return this.type;
    }

    /**
     * override the method because ground will not be destroyed
     * @return return false because ground will not be destroyed
     */
    @Override
    public boolean isDestroyed() {
        return false;
    }

    /**
     * @return return the "-" to show the situation of the target
     */
    @Override
    public String toString() {
        return "-";
    }

}
