package callOfDuty;

public class HeadQuarter extends Target{
    // the type of the target
    private static String type = "headQuarter";

    private static int length = 6;

    private static int width = 1;

    // the constructor of the headquarter
    public HeadQuarter(Base base) {
        super(length, width, base);
    }

    /**
     * do nothing, because headquarter will not explode
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
