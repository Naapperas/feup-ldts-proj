package pt.up.fe.ldts.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArenaTest {

    @Test
    public void measureTest(){
        Arena arena = new Arena(30, 50);

        Assertions.assertEquals(30, arena.getWidth());
        Assertions.assertEquals(50, arena.getHeight());
    }
}
