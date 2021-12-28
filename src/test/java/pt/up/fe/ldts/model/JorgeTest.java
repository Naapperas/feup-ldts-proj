package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JorgeTest {

    private  Jorge entity;

    @BeforeEach
    public void setup(){
        entity = new Jorge(4,5);
    }

    @Test
    public void testCoords() {

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 5);
    }

    @Test
    public void testMove() {

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.LEFT);

        entity.move();

        Assertions.assertEquals(entity.getX(), 3);
        Assertions.assertEquals(entity.getY(), 5);
    }

    @Test
    public void testDirection() {

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.LEFT);

        // UP direction
        entity.setDirection(Jorge.Direction.UP);

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.UP);

        entity.move();

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 4);

        // DOWN direction
        entity.setDirection(Jorge.Direction.DOWN);

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.DOWN);

        entity.move();

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 5);

        // RIGHT
        entity.setDirection(Jorge.Direction.RIGHT);

        Assertions.assertEquals(entity.getDirection(), Jorge.Direction.RIGHT);

        entity.move();

        Assertions.assertEquals(entity.getX(), 5);
        Assertions.assertEquals(entity.getY(), 5);
    }
}
