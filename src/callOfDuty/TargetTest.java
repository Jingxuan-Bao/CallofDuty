package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class for the Target class.
 * Author Jingxuan Bao and Yaoqi Deng in 25/11/2021
 */

class TargetTest {

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
    void testTarget() {

        // Armory
        assertEquals(2, armory.getHit().length);
        assertEquals(3, armory.getHit()[0].length);

        // Barrack
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);


        // TODO: add more cases

        // Test for oildrum
        assertEquals(1, od.getHit().length);
        assertEquals(1, od.getHit()[0].length);

        // Test for tank
        assertEquals(1, tank.getHit().length);
        assertEquals(1, tank.getHit()[0].length);
    }

    @Test
    void testToString() {
        assertEquals("O", st.toString());
        assertEquals("T", tank.toString());

        // TODO: add more cases

        // Test for Armory
        assertEquals("O", armory.toString());

        // Test for Ground
        assertEquals("-", base.getTargetsArray()[5][5].toString());

        //
        od.explode();
        System.out.println(armory.toString());
    }

    @Test
    void testGetTargetName() {
        assertEquals("tank", tank.getTargetName().toLowerCase());
        assertEquals("sentrytower", st.getTargetName().toLowerCase());
        assertEquals("oildrum", od.getTargetName().toLowerCase());

        // TODO: add more cases

        // Test for Armory
        assertEquals("armory", armory.getTargetName().toLowerCase());

        // Test for ground
        assertEquals("ground", base.getTargetsArray()[9][6].getTargetName().toLowerCase());
    }

    @Test
    void testExplode() {
        assertFalse(armory.isDestroyed());
        od.explode();
        assertTrue(armory.isDestroyed());


    }
    // TODO: add more cases
    // Test for tank explode
    @Test
    void testTankExplode() {
        // Test for sentrytower
        assertFalse(st.isDestroyed());
        tank.explode();
        assertTrue(st.isDestroyed());

        // Test for oildrum
        assertTrue(od.isDestroyed());

        // Test for Barrack
        assertFalse(barrack.isDestroyed());
    }

    // Test for armory explode
    @Test
    void testArmoryExplode() {
        armory.explode();

        // Test for oildrum
        assertTrue(od.isDestroyed());

        // Test for Barrack
        assertFalse(barrack.isDestroyed());
    }



    @Test
    void testGetShot() {
        Target am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, false);
        am.getShot(5, 6);
        assertEquals(1, am.getHit()[0][1]);


        // TODO: add more cases

        // Another shoot for the new armory
        am.getShot(5,5);
        assertEquals(1, am.getHit()[0][0]);
        assertEquals(0, am.getHit()[2][1]);

        // Shot at tank
        tank.getShot(1,3);
        assertEquals(1, tank.getHit()[0][0]);
        assertFalse(tank.isDestroyed());
    }

    @Test
    void testIsDestroyed() {
        assertFalse(armory.isDestroyed());
        assertFalse(od.isDestroyed());
        od.getShot(2, 1);
        assertTrue(od.isDestroyed());
        assertTrue(armory.isDestroyed());

        assertTrue(tank.isDestroyed());

        // TODO: add more cases

        // Test for a new tank
        Target tank2 = new Tank(this.base);
        assertTrue(this.base.okToPlaceTargetAt(tank2, 7, 7, false));
        this.base.placeTargetAt(tank2, 7, 7, false);
        tank2.getShot(7,7);
        assertFalse(tank2.isDestroyed());
        tank2.getShot(7,7);
        assertTrue(tank2.isDestroyed());

        // Test for two new oildrum near tank3
        Target tank3 = new Tank(this.base);
        assertTrue(this.base.okToPlaceTargetAt(tank3, 8, 8, false));
        this.base.placeTargetAt(tank3, 8, 8, false);
        Target oil1 = new OilDrum(this.base);
        assertTrue(this.base.okToPlaceTargetAt(oil1, 7, 8, false));
        this.base.placeTargetAt(oil1, 7, 8, false);
        Target oil2 = new OilDrum(this.base);
        assertTrue(this.base.okToPlaceTargetAt(oil2, 8, 7, false));
        this.base.placeTargetAt(oil2, 8, 7, false);
        oil1.explode();
        assertTrue(tank3.isDestroyed());
    }

    @Test
    void testIsHitAt() {
        Target am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, true);
        assertFalse(am.isHitAt(5, 5));
        am.getShot(5, 5);
        assertTrue(am.isHitAt(5, 5));

        // TODO: add more cases

        // Another shoot for the new armory
        assertFalse(am.isHitAt(5, 6));
        am.getShot(5, 6);
        assertTrue(am.isHitAt(5, 6));

        // Shoot the Barrack
        assertFalse(barrack.isHitAt(0, 5));
        barrack.getShot(0, 5);
        assertTrue(barrack.isHitAt(0, 5));
        assertFalse(barrack.isHitAt(0, 6));
    }

}