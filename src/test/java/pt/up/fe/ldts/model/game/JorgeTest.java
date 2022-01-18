package pt.up.fe.ldts.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.game.Arena;
import pt.up.fe.ldts.model.game.Jorge;
import pt.up.fe.ldts.model.game.Tremoco;
import pt.up.fe.ldts.view.gui.GUI;

import java.util.Set;

public class JorgeTest {

    @BeforeEach
    public void setup(){
        Jorge.singleton.changePos(4, 5);
        Jorge.singleton.addPoints(-Jorge.singleton.getScore());
    }

    @Test
    public void testCoords() {

        Assertions.assertEquals(4, Jorge.singleton.getX());
        Assertions.assertEquals(5, Jorge.singleton.getY());
    }

    @Test
    public void testChooseDirection() {

        Arena a = Mockito.mock(Arena.class);
        Jorge.singleton.setDirection(Vector.UP);

        Mockito.doReturn(Set.of(Vector.LEFT)).when(a).getValidDirections(Mockito.any(), Mockito.any(), Mockito.eq(true));

        Jorge.singleton.chooseDirection(GUI.ACTION.LEFT, a);

        Assertions.assertEquals(Vector.LEFT, Jorge.singleton.getDirection());

        Jorge.singleton.setDirection(Vector.UP);

        Mockito.doReturn(Set.of()).when(a).getValidDirections(Mockito.any(), Mockito.any(), Mockito.eq(true));

        Jorge.singleton.chooseDirection(GUI.ACTION.LEFT, a);

        Assertions.assertEquals(Vector.UP, Jorge.singleton.getDirection());
    }

    @Test
    public void testChangeDirection() {

        Arena a = Mockito.mock(Arena.class);
        Jorge.singleton.setDirection(Vector.UP);

        Mockito.doReturn(Set.of(Vector.LEFT)).when(a).getValidDirections(Mockito.any(), Mockito.any(), Mockito.eq(true));

        Jorge.singleton.changeDirection(a);

        Assertions.assertEquals(Vector.NULL, Jorge.singleton.getDirection());

        Jorge.singleton.setDirection(Vector.UP);

        Mockito.doReturn(Set.of(Vector.UP)).when(a).getValidDirections(Mockito.any(), Mockito.any(), Mockito.eq(true));

        Jorge.singleton.changeDirection(a);

        Assertions.assertEquals(Vector.UP, Jorge.singleton.getDirection());
    }

    @Test
    public void testRestart() {

        Jorge.singleton.setDirection(Vector.LEFT);

        new Tremoco(0, 0).notifyCollected();

        Assertions.assertEquals(10, Jorge.singleton.getScore());

        Jorge.singleton.kill();
        Jorge.singleton.kill();
        Jorge.singleton.kill();

        Assertions.assertFalse(Jorge.singleton.isAlive());

        Jorge.singleton.restart();

        Assertions.assertEquals(Vector.NULL, Jorge.singleton.getDirection());
        Assertions.assertEquals(0, Jorge.singleton.getScore());
        Assertions.assertTrue(Jorge.singleton.isAlive());
    }
}
