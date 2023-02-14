package callOfDuty;
/**
 * This is the test class for Bass class
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseTest {

    Base base;
    Armory armory;
    Barrack barrack;
    SentryTower st;
    Tank tank;
    OilDrum od;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();

        armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
    }

    @Test
    void testBase() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases DONE
        assertEquals(0, base.getShotsCount());
        assertEquals(0, base.getDestroyedTargetCount());
        assertEquals("oilDrum", base.getTargetsArray()[2][1].getTargetName());
        assertEquals("armory", base.getTargetsArray()[0][0].getTargetName());
        assertEquals("armory", base.getTargetsArray()[0][2].getTargetName());
        assertEquals("armory", base.getTargetsArray()[1][2].getTargetName());
        assertEquals("barrack", base.getTargetsArray()[0][6].getTargetName());


    }

    @Test
    void testPlaceAllTargetRandomly() {
        this.base = new Base();
        base.placeAllTargetRandomly();
        List<Target> list = new ArrayList<Target>();
        int headQuarterCount = 0;
        int armoryCount = 0;
        int barracksCount = 0;
        int sentryCount = 0;
        int tanksCount = 0;
        int odCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (base.getTargetsArray()[i][j].getTargetName() != "ground") {
                    if (!list.contains(base.getTargetsArray()[i][j])) {
                        list.add(base.getTargetsArray()[i][j]);
                        switch (base.getTargetsArray()[i][j].getTargetName().toLowerCase()) {
                            case "armory": {
                                armoryCount++;
                                break;
                            }
                            case "headquarter": {
                                headQuarterCount++;
                                break;
                            }
                            case "barrack": {
                                barracksCount++;
                                break;
                            }
                            case "sentrytower": {
                                sentryCount++;
                                break;
                            }
                            case "tank": {
                                tanksCount++;
                                break;
                            }
                            case "oildrum": {
                                odCount++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        assertEquals(list.size(), 18);

        assertEquals(1, headQuarterCount);
        assertEquals(2, armoryCount);
        assertEquals(3, barracksCount);
        assertEquals(4, sentryCount);
        assertEquals(4, tanksCount);
        assertEquals(4, odCount);
    }

    @Test
    void testOkToPlaceTargetAt() {
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 7, false));//diagonally
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, true)); //out
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, false));

        // TODO: add more cases DONE
        assertFalse(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 3, 5, false)); //diagonally adjacent
        assertFalse(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 0, 7, false)); // horizontally adjacent
        assertFalse(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 7, 8, false)); //part of it is out of bounce

        assertTrue(this.base.okToPlaceTargetAt(new OilDrum(this.base), 0, 3, true)); //horizontally adjacent
        assertFalse(this.base.okToPlaceTargetAt(new OilDrum(this.base), 10, 10, true)); //out of bound
        assertFalse(this.base.okToPlaceTargetAt(new OilDrum(this.base), 2, 1, true)); //Overlapped



    }



    @Test
    void testPlaceTargetAt() {
        Target armory = new Armory(base);
        this.base.placeTargetAt(armory, 5, 5, false);
        assertEquals(5, armory.getCoordinate()[0]);
        assertEquals(5, armory.getCoordinate()[1]);
        assertEquals(3, armory.getHit().length);
        assertEquals(2, armory.getHit()[0].length);

        // TODO: add more cases DONE
        Target od = new OilDrum(base);
        this.base.placeTargetAt(od, 7, 8, false);
        assertEquals(7, od.getCoordinate()[0]);
        assertEquals(8, od.getCoordinate()[1]);
        assertEquals(1, od.getHit().length);
        assertEquals(1, od.getHit()[0].length);

        Target barrack = new Barrack(base);
        this.base.placeTargetAt(barrack, 9, 0, true);
        assertEquals(9, barrack.getCoordinate()[0]);
        assertEquals(0, barrack.getCoordinate()[1]);
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);


    }


    @Test
    void testIsOccupied() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 0, 0, true);
        assertTrue(base.isOccupied(0, 0));

        // TODO: add more cases DONE
        Barrack barrack = new Barrack(base);
        this.base.placeTargetAt(barrack, 9, 0, true);
        assertTrue(base.isOccupied(9, 0));
        assertTrue(base.isOccupied(9, 1));
        assertFalse(base.isOccupied(9, 3));

        OilDrum od = new OilDrum(base);
        this.base.placeTargetAt(od, 8, 8, true);
        assertTrue(base.isOccupied(8, 8));

    }

    @Test
    void testShootAt() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 5, 5, true);

        base.shootAt(5, 5);
        assertTrue(arm.isHitAt(5, 5));

        // TODO: add more cases DONE
        Barrack barrack = new Barrack(base);
        this.base.placeTargetAt(barrack, 9, 0, true);

        base.shootAt(9, 1);
        assertTrue(barrack.isHitAt(9, 1));
        assertFalse(barrack.isHitAt(9, 0));


        OilDrum od = new OilDrum(base);
        this.base.placeTargetAt(od, 8, 8, true);

        base.shootAt(8, 8);
        assertTrue(od.isHitAt(8, 8));


    }

    @Test
    void testIsGameOver() {

        assertFalse(base.isGameOver(new RocketLauncher(), new Missile()));

        // TODO: add more cases DONE

        Base base1 = new Base();
        RocketLauncher rL = new RocketLauncher();
        Missile m = new Missile();
        for (int i=0; i<20; i++) {
            rL.decrementShotLeft();
        }
        for (int i=0; i<3; i++) {
            m.decrementShotLeft();

        }
        //Scenario when all ammo gone target not destroyed
        assertTrue(base1.isGameOver(rL, m));

        //scenario when one shot left and all target destroyed
        RocketLauncher rL1 = new RocketLauncher(); //input a new launcher
        assertFalse(base1.isGameOver(rL1, m)); //since there is a weapon with remaining ammo, not over
        base1.setDestroyedTargetCount(18); //all target destroyed, still has ammo left
        assertTrue(base1.isGameOver(rL1, m));


    }

    @Test
    void testWin() {
        assertFalse(this.base.win());

        // TODO: add more cases DONE
        Base base1 = new Base();
        RocketLauncher rL = new RocketLauncher();
        Missile m = new Missile();
        base1.setDestroyedTargetCount(18); //all target destroyed, still has ammo left
        //Scenario when all ammo gone target not destroyed
        assertTrue(base1.win());
        //Scenario when no ammo left but target remains
        base1.setDestroyedTargetCount(17);//one target remain
        assertFalse(base1.win());
    }

    @Test
    void testIncrementAndSetShotsCount() {

        assertEquals(0, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(1, base.getShotsCount());

        // TODO: add more cases
        base.incrementShotsCount();
        assertEquals(2, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(3, base.getShotsCount());
    }

    @Test
    void testSetAndGetDestroyedTargetCount() {
        base.setDestroyedTargetCount(10);
        assertEquals(10, base.getDestroyedTargetCount());

    }



    @Test
    void testGetTargetsArray() {
        assertEquals(10, base.getTargetsArray().length);

    }


    @Test

        //this a helper method test
    void placeTargetRandomly() {
        Base base2 = new Base();

        //randomly insert an armory
        Armory armory1 = new Armory(base2);
        base2.placeTargetRandomly(armory1);
        boolean hasArmory = false;
        boolean hasBarrack = false;
        boolean hasSt = false;
        for(int i = 0; i <= 9; i++) {
            for(int j = 0; j <= 9; j++) {
                if (base2.getTargetsArray()[i][j].getTargetName() == "armory") {
                    hasArmory = true ;
                }
            }
        }
        assertEquals(true, hasArmory);
        assertEquals(false, hasBarrack);
        assertEquals(false, hasSt);

        //randomly insert a barrack
        Barrack barrack1 = new Barrack(base2);
        base2.placeTargetRandomly(barrack1);
        for(int i = 0; i <= 9; i++) {
            for(int j = 0; j <= 9; j++) {
                if (base2.getTargetsArray()[i][j].getTargetName() == "barrack") {
                    hasBarrack = true ;
                }
            }
        }
        assertEquals(true, hasArmory);
        assertEquals(true, hasBarrack);
        assertEquals(false, hasSt);

        //randomly insert a SentryTower
        SentryTower st = new SentryTower(base2);
        base2.placeTargetRandomly(st);
        for(int i = 0; i <= 9; i++) {
            for(int j = 0; j <= 9; j++) {
                if (base2.getTargetsArray()[i][j].getTargetName() == "sentryTower") {
                    hasSt = true ;
                }
            }
        }
        assertEquals(true, hasArmory);
        assertEquals(true, hasBarrack);
        assertEquals(true, hasSt);
    }

}