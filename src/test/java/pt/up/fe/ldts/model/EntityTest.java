package pt.up.fe.ldts.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EntityTest {

    private Entity entity;

    @BeforeEach
    public void setup(){
        entity = new Entity(4, 5) {
            @Override
            public void changeDirection() {

            }
        };
    }
    
    @Test
    public void testCoords() {
        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }

    @Test
    public void testChangePos() {

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());

        entity.changePos(10, 9);

        Assertions.assertEquals(10, entity.getX());
        Assertions.assertEquals(9, entity.getY());
    }

    @Test
    public void testDirection() {

        Assertions.assertEquals(Entity.Direction.LEFT, entity.getDirection());

        // UP direction
        entity.setDirection(Entity.Direction.UP);

        Assertions.assertEquals(Entity.Direction.UP, entity.getDirection());

        entity.move();

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(4, entity.getY());

        // DOWN direction
        entity.setDirection(Entity.Direction.DOWN);

        Assertions.assertEquals(Entity.Direction.DOWN, entity.getDirection());

        entity.move();

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());

        // RIGHT
        entity.setDirection(Entity.Direction.RIGHT);

        Assertions.assertEquals(Entity.Direction.RIGHT, entity.getDirection());

        entity.move();

        Assertions.assertEquals(5, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }

    @Test
    public void testMove() {

        Assertions.assertEquals(Entity.Direction.LEFT, entity.getDirection());

        entity.move();

        Assertions.assertEquals(3, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }
}
