package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EntityTest {
    
    @Test
    public void testCoords() {
        Entity entity = new Entity(4, 5) {};

        Assertions.assertEquals(entity.getX(), 4);
        Assertions.assertEquals(entity.getY(), 5);
    }

}
