package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JorgeTest {

    @BeforeEach
    public void setup(){
        Jorge.singleton.changePos(4, 5);
    }


}
