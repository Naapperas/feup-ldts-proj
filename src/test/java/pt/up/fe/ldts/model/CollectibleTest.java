package pt.up.fe.ldts.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectibleTest {

    @Test
    public void testPointsGiven() {

        Tremoco t = new Tremoco(0, 0);
        Cerveja c = new Cerveja(0, 0);

        Jorge.singleton.addPoints(-Jorge.singleton.getScore());

        Assertions.assertEquals(0, Jorge.singleton.getScore());

        t.notifyCollected();

        Assertions.assertEquals(10, Jorge.singleton.getScore());

        c.notifyCollected();

        Assertions.assertEquals(30, Jorge.singleton.getScore());
    }

}
