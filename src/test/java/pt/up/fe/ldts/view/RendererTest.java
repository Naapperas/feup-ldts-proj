package pt.up.fe.ldts.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.ldts.view.gui.GUI;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RendererTest {

    @Test
    public void testRender() {
        List<Drawable> drawableList = new ArrayList<>();

        AtomicInteger test = new AtomicInteger(0);

        for (int i = 0; i < 5; i++) {

            var drawable = Mockito.mock(Drawable.class);
            Mockito.doAnswer(invocation -> {
                test.incrementAndGet();
                return null;
            }).when(drawable).render(Mockito.any());
            Renderer.addViewer(drawable);
        }

        Renderer.render(null);
        Assertions.assertEquals(5, test.get());

    }

}
