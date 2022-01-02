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

}
