package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EntityTest {
    
    @Test
    public void testCoords() {

        Entity entity = new Entity(4, 5) {
            @Override
            public void move() {}
        };

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }

    @Test
    public void testMove() {

        Entity entity = new Entity(4, 5) {
            @Override
            public void move() {}
        };

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());

        entity.changePos(10, 9);

        Assertions.assertEquals(10, entity.getX());
        Assertions.assertEquals(9, entity.getY());
    }

}
