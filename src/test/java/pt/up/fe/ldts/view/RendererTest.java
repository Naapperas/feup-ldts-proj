package pt.up.fe.ldts.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
        Assertions.assertEquals(5, test.get());
    }

    @Test
    public void testClearRenderer() throws IOException {

        TextGraphics tg = Mockito.mock(TextGraphics.class);
        GUI gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getTextGraphics()).thenReturn(tg);

        var drawable = Mockito.mock(Drawable.class);
        Mockito.doAnswer(invocation -> null).when(drawable).render(Mockito.any());
        Renderer.addDrawable(drawable);

        Renderer.clearRenderer();

        Renderer.render(gui);

        Mockito.verify(drawable, Mockito.never()).render(tg);
    }

}
