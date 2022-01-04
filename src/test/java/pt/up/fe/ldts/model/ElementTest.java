package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElementTest {
    @Test
    public void testCoords() {
        Element entity = new Element(4, 5){};
        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }
}
