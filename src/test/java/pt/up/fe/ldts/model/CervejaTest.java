package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicInteger;

public class CervejaTest {


    @Test
    public void testCollect() {
        Cerveja cerveja = new Cerveja(5, 4);

        Assertions.assertEquals(5, cerveja.getX());
        Assertions.assertEquals(4, cerveja.getY());

        AtomicInteger test = new AtomicInteger(0);

        for (int i = 0; i < 5; ++i) {
            var testListener = Mockito.mock(CervejaListener.class);
            Mockito.doAnswer((invocation) -> {
                test.getAndIncrement();
                return null;
            }).when(testListener).cervejaPicked();
            cerveja.addListener(testListener);
        }

        cerveja.notifyCollected();

        Assertions.assertEquals(5, test.get());
    }
}
