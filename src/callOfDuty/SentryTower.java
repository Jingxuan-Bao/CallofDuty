package callOfDuty;

/**
 * The SentryTower class extends the Target class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public class SentryTower extends Target{
    // the type of Target
    private static String type = "sentryTower";

    private static int length = 1;

    private static int width = 1;
    // the sentryTower constructor
    public SentryTower(Base base) {
        super(length, width, base);
    }

    /**
     * do nothing because sentrytower will not explode
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
