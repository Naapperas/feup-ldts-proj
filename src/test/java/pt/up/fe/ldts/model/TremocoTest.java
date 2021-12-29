package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TremocoTest {

    @Test
    public void testCoords(){
        Element tremoco = new Tremoco(4,5);

        Assertions.assertEquals(4, tremoco.getX());
        Assertions.assertEquals(5, tremoco.getY());
    }
}
