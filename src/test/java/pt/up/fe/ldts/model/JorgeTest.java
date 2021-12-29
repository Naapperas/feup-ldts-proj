package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JorgeTest {

    private  Jorge entity;

    @BeforeEach
    public void setup(){
        entity = new Jorge(4,5);
    }

    @Test
    public void testCoords() {

        Assertions.assertEquals(4, entity.getX());
        Assertions.assertEquals(5, entity.getY());
    }



}
