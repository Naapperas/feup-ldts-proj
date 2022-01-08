package pt.up.fe.ldts.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectibleTest {

    @Test
    public void testCoords(){
        Collectible collectible = new Collectible(5, 4) {
            @Override
            public void render(TextGraphics gui) {

            }

            @Override
            public void notifyCollected() {}
        };

        Assertions.assertEquals(5, collectible.getX());
        Assertions.assertEquals(4, collectible.getY());
    }
}
