package pt.up.fe.ldts.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.model.game.Wall;

public class WallTest {

    @Test
    public void testEquality() {
        Wall w1 = new Wall(0, 0), w2 = new Wall(0, 1);

        Assertions.assertFalse(w1.equals(null));
        Assertions.assertTrue(w1.equals(w1));
    }
}