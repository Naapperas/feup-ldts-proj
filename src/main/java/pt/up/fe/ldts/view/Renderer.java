package pt.up.fe.ldts.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.view.gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private static final List<Drawable> viewers = new ArrayList<>();

    public static void addDrawable(Drawable drawable) {
        viewers.add(drawable);
    }
    public static void removeDrawable(Drawable drawable) {viewers.remove(drawable);}

    public static void clearRenderer() { Renderer.viewers.clear(); }

    public static void render(GUI gui) throws IOException {

        TextGraphics tg = gui.getTextGraphics();

        gui.clear();
        viewers.forEach(viewer -> viewer.render(tg));
        gui.refresh();
    }
}
