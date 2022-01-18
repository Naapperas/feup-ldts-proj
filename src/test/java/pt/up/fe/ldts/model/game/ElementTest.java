package pt.up.fe.ldts.model.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.model.game.Element;

public class ElementTest {
    @Test
    public void testCoords() {
        Element entity = new Element(4, 5){
            @Override
            public void render(TextGraphics tg) {

            }
        };
        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }
}
