package pt.up.fe.ldts.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityTest {

    private Entity entity;

    @BeforeEach
    public void setup(){
        entity = new Entity(4, 5) {
            @Override
            public void render(TextGraphics gui) {

            }

            @Override
            public void changeDirection(Arena arena) {

            }
        };
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

        Assertions.assertEquals(Vector.UP, entity.getDirection());

        // UP direction
        entity.setDirection(Vector.LEFT);

        Assertions.assertEquals(Vector.LEFT, entity.getDirection());

        entity.move();

        Assertions.assertEquals(3, entity.getX());
        Assertions.assertEquals(5, entity.getY());

        // DOWN direction
        entity.setDirection(Vector.DOWN);

        Assertions.assertEquals(Vector.DOWN, entity.getDirection());

        entity.move();

        Assertions.assertEquals(3, entity.getX());
        Assertions.assertEquals(6, entity.getY());

        // RIGHT
        entity.setDirection(Vector.RIGHT);

        Assertions.assertEquals(Vector.RIGHT, entity.getDirection());

        entity.move();

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(6, entity.getY());
    }

    @Test
    public void testMove() {

        Assertions.assertEquals(Vector.UP, entity.getDirection());

        entity.move();

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(4, entity.getY());
    }
}
