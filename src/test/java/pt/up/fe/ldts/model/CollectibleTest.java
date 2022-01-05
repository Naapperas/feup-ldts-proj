package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.view.gui.GUI;

public class CollectibleTest {

    @Test
    public void testCoords(){
        Collectible collectible = new Collectible(5, 4) {
            @Override
            public void render(GUI gui) {

            }

            @Override
            public void notifyCollected() {}
        };

        Assertions.assertEquals(5, collectible.getX());
        Assertions.assertEquals(4, collectible.getY());
    }
}
