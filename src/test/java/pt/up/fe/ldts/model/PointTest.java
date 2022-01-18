package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    public void testAddVector() {
        Vector v = new Vector(4, 5);
        Point p = new Point(6, 7);

        Point expected = new Point(10, 12);

        Assertions.assertEquals(expected, p.addVector(v));
    }

    @Test
    public void testEquality() {
        Point p = new Point(1, 2), p2 = new Point(2, 3);

        Assertions.assertTrue(p.equals(p));
        Assertions.assertFalse(p.equals(null));
        Assertions.assertFalse(p.equals(p2));
    }

}
