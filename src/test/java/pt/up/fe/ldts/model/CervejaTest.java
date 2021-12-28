package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CervejaTest {

    @Test
    public void testCoords(){
        Element cerveja = new Cerveja(5, 4);

        Assertions.assertEquals(5, cerveja.getX());
        Assertions.assertEquals(4, cerveja.getY());
    }
}
