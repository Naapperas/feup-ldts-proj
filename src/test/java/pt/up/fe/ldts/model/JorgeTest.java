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

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }

    @Test
    public void testMove() {

        Assertions.assertEquals(Jorge.Direction.LEFT, entity.getDirection());

        entity.move();

        Assertions.assertEquals(3, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }

    @Test
    public void testDirection() {

        Assertions.assertEquals(Jorge.Direction.LEFT, entity.getDirection());

        // UP direction
        entity.setDirection(Jorge.Direction.UP);

        Assertions.assertEquals(Jorge.Direction.UP, entity.getDirection());

        entity.move();

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(4, entity.getY());

        // DOWN direction
        entity.setDirection(Jorge.Direction.DOWN);

        Assertions.assertEquals(Jorge.Direction.DOWN, entity.getDirection());

        entity.move();

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());

        // RIGHT
        entity.setDirection(Jorge.Direction.RIGHT);

        Assertions.assertEquals(Jorge.Direction.RIGHT, entity.getDirection());

        entity.move();

        Assertions.assertEquals(5, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }
}
