package callOfDuty;

/**
 * The Target class represents the attacked target in the game callofDuty.
 * The Target class provides the basic methods the user need in the game.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
public abstract class Target {

    //An array of length 2 that specifies the coordinate of the head of a
    //target. Head means the upper left part of a Target.
    private int[] coordinate;

    //The length of the Target.
    private int length;

    // The width of the Target
    private int width;

    // Indicates whether the Target is horizontal or not
    private boolean horizontal;

    // An array of the same size as the target, indicating the number of
    // times a part of the target has been hit.
    private int[][] hit;

    // An instance of Base that the target is placed in.
    private Base base;

    // default constructor, set length, width, and base
    public Target(int length, int width, Base base) {
        this.length = length;
        this.width = width;
        this.base = base;
    }


    /**
     * @return return the coordinate of the head of a target
     */
    public int[] getCoordinate() {
        return coordinate;
    }

    /**
     * @return return the length of the target
     */
    public int getLength() {
        return length;
    }

    /**
     * @return return the width of the target
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return return whether the target is horizontal or not
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * @return return the hit array, which indicates the number of times a part of the target has been hit.
     */
    public int[][] getHit() {
        return hit;
    }

    /**
     * @return return the base of the target
     */
    public Base getBase() {
        return base;
    }


    /**
     * set the coordinate of the target
     * @param coordinate An array of length 2 that specifies the coordinate of the head
     */
    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * set the horizontal or not of the target
     * @param horizontal Indicates whether the Target is horizontal or not
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * set the hit array of the target
     * @param hit  indicating the number of times a part of the target has been hit.
     */
    public void setHit(int[][] hit) {
        this.hit = hit;
    }

    /**
     * Defines the behavior when a target is destroyed. Some may explode.
     */
    abstract void explode();

    /**
     * @return return the target name.
     */
    public abstract String getTargetName();

    /**
     * Shot at target base on the row and the column of base
     * @param row the row in the base
     * @param column the column in the base
     */
    public void getShot (int row, int column){
        // find the coordinate of the hit[][]
        int rowHead = getCoordinate()[0];
        int colHead = getCoordinate()[1];
        int rowTar = 0;
        int colTar = 0;
        if(row >= rowHead) {
            rowTar = row - rowHead;
        }
        else{
            System.out.print("over the base range");
        }
        if(column >= colHead) {
            colTar = column - colHead;
        }
        else {
            System.out.print("over the base range");
        }

        // check whether the target need to explode
        if(! isDestroyed()) {
            this.hit[rowTar][colTar] += 1;
            if(getTargetName().equals("armory") || getTargetName().equals("tank") || getTargetName().equals("oilDrum")) {
                if(isDestroyed()) {
                    this.explode();
                }
            }
            if(isDestroyed()) {
                System.out.println("You have destroyed a " + getTargetName());
                base.setDestroyedTargetCount(base.getDestroyedTargetCount() + 1);
                System.out.println(base.getDestroyedTargetCount());
            }
        }
    }

    /**
     * Check whether the target is destroyed
     * @return whether the target is destroyed
     */
    public boolean isDestroyed() {
        // get the hit coordinate of the target
        int hor = 0;
        int ver = 0;
        if(! horizontal) {
            hor = this.width;
            ver = this.length;
        }
        else {
            hor = this.length;
            ver = this.width;
        }

        // loop the hit coordinate
        // whether every hit coordinate is higher than 1
        for(int i = 0; i < ver; i ++) {
            for(int j = 0; j < hor; j ++) {
                if(this.hit[i][j] < 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * check whether the coordiate is hit
     * @param row row of the base
     * @param column column of the base
     * @return check whether the coordiate is hit
     */
    public boolean isHitAt(int row, int column) {
        // get the hit coordinate of the target
        int rowHead = this.coordinate[0];
        int colHead = this.coordinate[1];
        int rowTar = 0;
        int colTar = 0;
        if(row >= rowHead) {
            rowTar = row - rowHead;
        }
        else{
            return false;
        }
        if(column >= colHead) {
            colTar = column - colHead;
        }
        else {
            return false;
        }

        // check whether the point of the target is hit
        if(this.hit[rowTar][colTar] >= 1) {
            return true;
        }
        return false;
    }

    /**
     * @return return the particular string to show the situation of the target
     */
    @Override
    public String toString() {
        if(isDestroyed()) {
            return "X";
        }
        return "O";
    }

}
