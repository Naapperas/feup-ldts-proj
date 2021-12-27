package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JorgeTest {
    @Test
    public void testCoords() {

        Jorge entity = new Jorge(4, 5);

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 5);
    }

    @Test
    public void testMove() {

        Jorge entity = new Jorge(4, 5);

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.LEFT);

        entity.move();

        Assertions.assertEquals(entity.getX(), 3);
        Assertions.assertEquals(entity.getY(), 5);
    }

    @Test
    public void testDirection() {

        Jorge entity = new Jorge(4, 5);

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.LEFT);

        entity.move();

        entity.setDirection(Jorge.Direction.UP);

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.UP);

        entity.move();

        Assertions.assertEquals(entity.getX(), 3);
        Assertions.assertEquals(entity.getY(), 4);
    }
}
