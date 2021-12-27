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

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 5);
    }

    @Test
    public void testMove() {

        Entity entity = new Entity(4, 5) {
            @Override
            public void move() {}
        };

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 5);

        entity.changePos(10, 9);

        Assertions.assertEquals(entity.getX(), 10);
        Assertions.assertEquals(entity.getY(), 9);
    }

}
