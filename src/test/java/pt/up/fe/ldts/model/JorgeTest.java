package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JorgeTest {

    @BeforeEach
    public void setup(){
        Jorge.singleton.changePos(4, 5);
    }

    @Test
    public void testCoords() {

        Assertions.assertEquals(4, Jorge.singleton.getX());
        Assertions.assertEquals(5, Jorge.singleton.getY());
    }
}
