package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VectorTest {

    @Test
    public void testCoords() {
        Vector v = new Vector(4, 5);

        Assertions.assertEquals(4, v.x());
        Assertions.assertEquals(5, v.y());
    }

    @Test
    public void testMagnitude() {

        Vector v = new Vector(4, 3);

        double mag = v.magnitude();

        Assertions.assertEquals(5.0, mag);

        v = Vector.LEFT;

        mag = v.magnitude();

        Assertions.assertEquals(1.0, mag);
    }

    @Test
    public void testVectorCreation() {

        Point a = new Point(4, 5), b = new Point(10, 3);

        Vector v = Vector.from(a, b);
        Vector expected = new Vector(6, -2);

        Assertions.assertEquals(expected, v);
        Assertions.assertEquals(6.32, v.magnitude(), 0.01);
    }

    @Test
    public void testMultiply() {

        Vector v = new Vector(4, 5);

        Vector expected1 = new Vector(-4, -5), expected2 = new Vector(8, 10);

        Assertions.assertEquals(expected1, v.multiply(-1));
        Assertions.assertEquals(expected2, v.multiply(2));
        Assertions.assertEquals(Vector.NULL, v.multiply(0));
        Assertions.assertEquals(v, v.multiply(1));
    }

    @Test
    public void testCompare() {

        Assertions.assertEquals(0, Vector.UP.compareTo(Vector.UP));
        Assertions.assertEquals(0, Vector.LEFT.compareTo(Vector.LEFT));
        Assertions.assertEquals(0, Vector.DOWN.compareTo(Vector.DOWN));
        Assertions.assertEquals(0, Vector.RIGHT.compareTo(Vector.RIGHT));

        Assertions.assertEquals(1, Vector.DOWN.compareTo(Vector.LEFT));
        Assertions.assertEquals(-1, Vector.DOWN.compareTo(Vector.RIGHT));
    }
}
