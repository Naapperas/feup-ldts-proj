package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {

    @Test
    public void testCoords(){
        Element wall = new Wall(4, 5);


        Assertions.assertEquals(4, wall.getX());
        Assertions.assertEquals(5, wall.getY());
    }
}
