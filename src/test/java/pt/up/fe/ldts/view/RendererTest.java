package pt.up.fe.ldts.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.view.gui.GUI;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class RendererTest {

    @Test
    public void testRender() throws IOException {
        Renderer.clearRenderer();

        AtomicInteger test = new AtomicInteger(0);

        for (int i = 0; i < 5; i++) {

            var drawable = Mockito.mock(Drawable.class);
            Mockito.doAnswer(invocation -> {
                test.incrementAndGet();
                return null;
            }).when(drawable).render(Mockito.any());
            Renderer.addDrawable(drawable);
        }

        GUI gui = Mockito.mock(GUI.class);

        Renderer.render(gui);

        Mockito.verify(gui, Mockito.atLeastOnce()).getTextGraphics();
        Assertions.assertEquals(5, test.get());
    }

}
