package callOfDuty;

import java.util.Random;

/**
 * This contains a 10x10 array of Targets, representing a “base”, and
 *  some methods to manipulate it. Think of it as the map in this game.
 */
public class Base {
    Target Tank;
    Target Barrack;
    Target Ground;
    Target HeadQuarter;
    Target OilDrum;
    Target SentryTower;
    private Target[][] targets;
    private int shotsCount;
    private int destroyedTargetCount;
    Random random = new Random();

    /**
     *Creates an 10x10 ”empty” Base (and fills the Targets array with Ground
     *objects). You could create a private helper method to do this. Also initializes
     *any game variables, such as how many shots have been fired.
     */
    public Base() { //base constructor
        targets = new Target[10][10]; //make a 10*10 array
        for (int i = 0; i < 10; i++) { //loop all base
            for (int j = 0; j < 10; j++) { //loop base
                placeTargetAt(new Ground(this), i, j, true); //fill ground in base
            }
        }
        shotsCount = 0;
        destroyedTargetCount = 0;
    }

    /**
     *Initializing all targets
     *place all target randomly using placeTargetRandomly function for each target
     */
    public void placeAllTargetRandomly() {

        HeadQuarter hQ = new HeadQuarter(this);
        placeTargetRandomly(hQ);

        Armory armory1 = new Armory(this);
        Armory armory2 = new Armory(this);
        placeTargetRandomly(armory1);
        placeTargetRandomly(armory2);

        Barrack barrack1 = new Barrack(this);
        Barrack barrack2 = new Barrack(this);
        Barrack barrack3 = new Barrack(this);
        placeTargetRandomly(barrack1);
        placeTargetRandomly(barrack2);
        placeTargetRandomly(barrack3);

        SentryTower sT1 = new SentryTower (this);
        SentryTower sT2 = new SentryTower (this);
        SentryTower sT3 = new SentryTower (this);
        SentryTower sT4 = new SentryTower (this);
        placeTargetRandomly(sT1);
        placeTargetRandomly(sT2);
        placeTargetRandomly(sT3);
        placeTargetRandomly(sT4);

        Tank Tank1 = new Tank (this);
        Tank Tank2 = new Tank (this);
        Tank Tank3 = new Tank (this);
        Tank Tank4 = new Tank (this);
        placeTargetRandomly(Tank1);
        placeTargetRandomly(Tank2);
        placeTargetRandomly(Tank3);
        placeTargetRandomly(Tank4);

        OilDrum oD1 = new OilDrum (this);
        OilDrum oD2 = new OilDrum (this);
        OilDrum oD3 = new OilDrum (this);
        OilDrum oD4 = new OilDrum (this);
        placeTargetRandomly(oD1);
        placeTargetRandomly(oD2);
        placeTargetRandomly(oD3);
        placeTargetRandomly(oD4);
    }

    /**
     * helper method to place one target
     * @param target this is the target location
     */
    public void placeTargetRandomly(Target target) {
        while (true){
            int row = random.nextInt(10); //use random generator to get a random number for row
            int column = random.nextInt(10); //use random generator to get a random number for column
            boolean horizontal = random.nextBoolean(); //use random generator to get a random configuration
            if (okToPlaceTargetAt(target, row, column, horizontal) == true) { //check if ok to place at this random place
                placeTargetAt(target, row, column, horizontal); //if yes, place it
                break;
            }//if no, try a new place
        }
    }

    /**
     * check if the head can be placed in the given coordinate
     * @param target target to test input
     * @param row row of the target
     * @param column column of the target
     * @param horizontal if the target is horizontal
     * @return true if target can be placed there
     */
    public boolean okToPlaceTargetAt(Target target, int row, int column, boolean horizontal) {
        int rowEdge = 0;
        int columnEdge = 0;
        if (horizontal == true) { //when horizontal, record the edge of the target
            rowEdge = row + target.getWidth() - 1;
            columnEdge = column + target.getLength() - 1;
        }else{ //when vertical, record the edge of the target
            rowEdge = row + target.getLength() - 1;
            columnEdge = column + target.getWidth() - 1;
        }
        //when the edge is out of bound
        if (rowEdge > 9 || columnEdge > 9){
            return false;
        }
        //if the target is building
        if (target.getTargetName().equals("headQuarter") || target.getTargetName().equals("armory") ||target.getTargetName().equals("barrack") ||target.getTargetName().equals("sentryTower")) {
            //check if the target edge is at the edge of the base,
            //if yes then do not expand it, if no expand by 1 to check adjacency


            if (row != 0) {
                row--;
            }
            if (rowEdge != 9) {
                rowEdge++;
            }
            if (column != 0) {
                column--;
            }
            if(columnEdge != 9) {
                columnEdge++;
            }


            //loop through the expended coverage area and check if occupied
            for(int i = row; i <= rowEdge; i++) {
                for(int j = column; j <= columnEdge; j++) {
                    if (isOccupied(i,j) == true) {
                        return false; //if occupied return false
                    }
                }
            }
        }
        else{ //for Oil drum and tank
            if(isOccupied(row, column)) {
                return false;
            }
        }

        return true;
    }

    /**
     * place the target head at the given coordinate
     * @param target target location to put in
     * @param row row of the location
     * @param column column of the location
     * @param horizontal if the target is horizontal
     */
    public void placeTargetAt(Target target, int row, int column, boolean horizontal) {
        int [] coordinates = {row, column}; //initiate head coordinate
        if (horizontal == true) { //when horizontal
            int hit[][] = new int[target.getWidth()][target.getLength()]; //get hit array
            //set target hit
            target.setHit(hit);
            target.setCoordinate(coordinates);
            target.setHorizontal(true);
            int rowEdge = row + target.getWidth() - 1; //set row edge of the target
            int columnEdge = column + target.getLength() - 1; //set column edge of the target
            for(int i = row; i <= rowEdge; i++) {
                for(int j = column; j <= columnEdge; j++) {
                    targets[i][j] = target; //fill the area that the target covers and make the reference of the array to the target
                }
            }

        }else { //when vertical, same step as above but width and length coordinate switched
            int hit[][] = new int[target.getLength()][target.getWidth()];
            target.setHit(hit);
            target.setCoordinate(coordinates);
            target.setHorizontal(false);
            int rowEdge = row + target.getLength() - 1;
            int columnEdge = column + target.getWidth() - 1;
            for(int i = row; i <= rowEdge; i++) {
                for(int j = column; j <= columnEdge; j++) {
                    targets[i][j] = target;
                }
            }
        }


    }

    /**
     * check if the coordinate is occupied
     * @param row row of the coordinate
     * @param column column iof the coordinate
     * @return true when can be occupied
     */
    public boolean isOccupied(int row, int column) {
        if (getTargetsArray()[row][column].toString().equals("-")) { //if a ground
            return false;
        }else{
            return true;
        }

    }

    /**
     * shoot at the given coordinate using getshot function
     * @param row row of the coordinate
     * @param column column of the coordinate
     */
    public void shootAt(int row, int column) {
        targets[row][column].getShot(row, column);//call the target getShot function
    }

    /**
     * check if game is over
     * @param weapon1 weapon 1 used
     * @param weapon2 weapon 2 used
     * @return true if game is over
     */
    public boolean isGameOver(Weapon weapon1, Weapon weapon2) {
        if (weapon1.getShotleft() == 0 && weapon2.getShotleft()==0) {//when no shot left for all weapon
            return true; // game over
        }
        if (win()) { //if all targets are destroyed
            return true; //over
        }
        return false;
    }

    /**
     * check if all targets are destroyed
     * @return true if user wins
     */
    public boolean win() {
        if (destroyedTargetCount == 18) { //if destroyed target count is 18
            return true; //win
        }
        return false; //user lose
    }
    /**
     * Prints the Base. To aid the user, row numbers should be displayed
     * along the left edge of the array, and column numbers should be
     * displayed along the top. Numbers should be (0 to 9), not 1 to 10.
     * “O” (capital letter O): Used to indicate a location that you have fired
     * upon and hit a target
     * “-”: Use ‘-’ to indicate a location that you have fired upon and found
     * nothing there
     * “X” (capital letter X): Use ‘X’ to indicate a location containing a
     * destroyed Target.
     * ‘.’: Use ‘.’ (a period) to indicate a location that you have never fired upon
     * “T”: Used to indicate an undestroyed but hit Tank
     */
    public void print() {
        //make a new array to represent the base
        String[][] basemap = new String[11][11];
        //top left most map is empty
        basemap[0][0] = " ";
        //assign the targets with string representation using toString function
        for(int i = 1; i < 11; i ++) {
            basemap[0][i] = Integer.toString(i - 1);
            basemap[i][0] = Integer.toString(i - 1);
        }
        //when array is hit, change its toString representation
        for(int i = 1; i < 11; i ++) {
            for(int j = 1; j < 11; j ++) {

                if(getTargetsArray()[i-1][j-1].isHitAt(i-1, j-1)) {
                    basemap[i][j] = getTargetsArray()[i-1][j-1].toString();
                }
                else {
                    basemap[i][j] = "."; //no hit no change
                }
            }
        }
        //print out the map
        for(int i = 0; i < basemap.length; i++)
        {
            for(int j = 0; j < basemap[0].length; j++)
            {
                System.out.print(basemap[i][j] + "  ");
            }
            System.out.println(); //print an empty line
        }
    }

    /**
     * getter method to get shot count
     * @return shot count
     */
    public int getShotsCount() {
        return shotsCount;
    }

    /**
     * getter method to get target array outside of class
     * @return target array
     */
    public Target[][] getTargetsArray() {
        return targets;
    }

    /**
     * increase shot count
     */
    public void incrementShotsCount() {
        shotsCount ++;
    }

    /**
     * getter method to get destroyed target count
     * @return number of target being destroyed
     */
    public int getDestroyedTargetCount() {
        return destroyedTargetCount;
    }

    /**
     * setter to set destroyed target count
     * @param i
     */
    public void setDestroyedTargetCount(int i) {
        destroyedTargetCount = i;
    }

}
