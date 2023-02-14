package callOfDuty;

/**
 * The Barrack class extends the Target class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public class Barrack extends Target{
    // the type of Target
    private static String type = "barrack";

    private static int length = 3;

    private static int width = 1;

    // the contructor of barrack
    public Barrack(Base base) {
        super(length, width, base);
    }

    /**
     * do nothing because barrack will not explode
     */
    @Override
    void explode() {
    }

    /**
     * @return return the type of the target
     */
    @Override
    public String getTargetName() {
        return this.type;
    }

}
