package pt.up.fe.ldts.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {

    @Test
    public void testEquality() {
        Wall w1 = new Wall(0, 0), w2 = new Wall(0, 1);

        Assertions.assertNotEquals(null, w1);
        Assertions.assertNotEquals(w1, w2);
        Assertions.assertEquals(w1, w1);
    }
}
