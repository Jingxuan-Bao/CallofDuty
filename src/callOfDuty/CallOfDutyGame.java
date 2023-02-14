package callOfDuty;

import java.util.Scanner;
/**
 * This is the “main” class, containing the main method, which starts by
 creating an instance of Base.
 */
public class CallOfDutyGame {
    /**
     * This is the main method to initiate the game
     * @param args
     */
    public static void main(String[] args) {
        while (true) { //game initialization
            Scanner sc = new Scanner (System.in); //import scanner
            Base base = new Base(); //initialize the game
            //initialize weapons
            RocketLauncher rL = new RocketLauncher();
            Missile m = new Missile();
            Weapon currentWeapon = rL; //default weapon is rL
            base.placeAllTargetRandomly(); //put all targets in
            //print game info
            System.out.println("Welcome to the game!");
            System.out.println("In the game, you serve as a soldier and your mission is destroying a 10x10 enemy \n"
                    + "base. You need to destroy all Targets in the base. ");
            System.out.println("You have 20 RPG and 3 Missile to fire");
            while(true) { // at each round of game
                //print game map and current game status
                base.print();
                System.out.println("RPG: "+rL.getShotleft()+" Missile: "+m.getShotleft());
                System.out.println("Your current weapon is: "+currentWeapon.getWeaponType());
                String input = getInputAndCheck(sc); //use helper method to check input and return modified input
                if (input.equals("q")){//when user want to change weapon
                    if (currentWeapon == rL) { //if rL is the current weapon
                        currentWeapon = m; //change to the other one
                    }else{
                        currentWeapon = rL;
                    }
                }else{// when user decide to shoot
                    int row = Character.getNumericValue(input.charAt(0)); //get input first index as row to shot
                    int column = Character.getNumericValue(input.charAt(1)); //get input second index as column to shot
                    currentWeapon.shootAt(row, column,base); //shot at the coordinate using shooAt function
                }//check if game is over after each shot
                if (base.isGameOver(rL, m)) {
                    break; //if is over then end this game
                }
            }
            if (base.win()) { //at the end of the game check if user wins
                //print winning message
                System.out.println("Congraduations you win!\n"
                        +"You took "+base.getShotsCount()+" shot to win!");
            }else{ //when user is the loser, print info
                System.out.println("Sorry you lose!");
            }
            System.out.println("Do you want to play again?"); //ask input
            if (!nextGame(sc)) { //use helper function to determine if user want to play again
                break; //if no then exit program
            }
        }
    }

    /**
     * helper to modify input, remove white space and comma
     * return modified input
     */
    public static String modifyInput(String input) {
        String inputWithoutWs = input.replaceAll("\\s",""); //remove white space
        String inputWithoutCommaWs = inputWithoutWs.replaceAll(",", ""); //remove comma
        return inputWithoutCommaWs;
    }
    /**
     * helper to check if input valid
     * return true if valid
     * return false if not
     * UNSOLVED: TWO letter input might has an exception 救命鲍哥
     */
    public static boolean inputCheck(String input) {
        if (input.length() == 1) { // if only one letter
            if (input.equals("q")){ //if input is q
                return true; //valid input
            }
        }
        if (input.length() == 2) { // if two numbers
            if (Integer.parseInt(input) <= 99 && Integer.parseInt(input)>=00) { //if coordinate is within the map
                return true;
            }
        }
        return false;
    }
    /**
     * ask for input, modified the input and check if it is valid
     * ask until input is valid
     * return the modified valid input
     */
    public static String getInputAndCheck(Scanner sc) {
        while (true) {//check input, break until valid answer
            System.out.println("Enter row, column, or q to switch a weapon "); //ask input
            String input = sc.nextLine(); //get the input
            String modInput = modifyInput(input); //modify input by helper
            if (inputCheck(modInput)) { //if input valid
                return modInput; //return input
            }
        }
    }

    /**
     * check if user want to play again
     * return true if another game, return false if user don't want to play
     * if input invalid, keep asking
     */
    public static boolean nextGame(Scanner sc) {
        while (true) {
            char answer = sc.next().charAt(0); //get the input and extract the first letter
            if (answer == 'y' || answer == 'Y' ) { //if input starts with y or Y
                return true;
            }if(answer == 'N' || answer == 'n') { //if input starts with N or n
                return false;
            }
            System.out.println("Do you want to play again?"); //ask input
        }

    }
}
