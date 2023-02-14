package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class for the Weapon class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */
class WeaponTest {

    Base base;
    Missile mis;
    RocketLauncher rl;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();
        

        mis = new Missile();
        rl = new RocketLauncher();
    }

    @Test
    void testWeapon() {
        assertEquals(base.getTargetsArray()[0][0].getTargetName(), "ground");
        assertEquals(3, mis.getShotleft());

        // TODO: add more cases

        // test for rocketlauncher
        assertEquals(20, rl.getShotleft());

        // test for rocketlauncher type
        assertEquals(rl.getWeaponType(), "RocketLauncher");
    }

    @Test
    void testGetWeaponType() {
        assertEquals("missile", mis.getWeaponType().toLowerCase());

        // TODO: add more cases

        // test for rocketlauncher
        assertEquals(rl.getWeaponType(), "RocketLauncher");

        // test for lowercase rocketlauncher
        assertEquals(rl.getWeaponType().toLowerCase(), "rocketlauncher");
    }

    
    @Test
    void testGetShotLeft() {
        assertEquals(base.getTargetsArray()[0][0].getTargetName(), "ground");
        System.out.println((base.getTargetsArray()[0][0].getCoordinate()));
        assertEquals(base.getTargetsArray()[0][0].getCoordinate()[0], 0);
        mis.shootAt(0, 0, this.base);
        assertEquals(2, mis.getShotleft());

        // TODO: add more cases
        // make another shoot on 5,5
        mis.shootAt(5, 5, this.base);
        assertEquals(1, mis.getShotleft());

        // make a shoot by rocketlauncher
        rl.shootAt(3,3, this.base);
        assertEquals(19, rl.getShotleft());
        rl.shootAt(7,7, this.base);
        assertEquals(18, rl.getShotleft());

    }

    @Test
    void testDecrementShotleft() {
        mis.decrementShotLeft();
        assertEquals(2, mis.getShotleft());

        // TODO: add more cases
        // make another decrease on mis
        mis.decrementShotLeft();
        assertEquals(1, mis.getShotleft());

        // make a decrease on rl
        rl.decrementShotLeft();
        assertEquals(19, rl.getShotleft());
    }

    @Test
    void testShootAt() {
        mis.shootAt(0, 0, this.base);
        assertTrue(base.getTargetsArray()[0][0].isHitAt(0, 0));
        assertEquals(1, base.getShotsCount());
        // TODO: add more cases

        // make another shoot by mis
        mis.shootAt(5, 5, this.base);
        assertTrue(base.getTargetsArray()[6][6].isHitAt(6, 6));
        assertEquals(2, base.getShotsCount());
        assertEquals(1, mis.getShotleft());

        // make a shoot by rl
        rl.shootAt(3,3, this.base);
        assertTrue(base.getTargetsArray()[3][3].isHitAt(3, 3));
        assertEquals(3, base.getShotsCount());
        assertEquals(19, rl.getShotleft());
    }

}
