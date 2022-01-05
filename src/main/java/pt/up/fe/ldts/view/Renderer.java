package pt.up.fe.ldts.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.view.gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private static final List<Drawable> viewers = new ArrayList<>();

    public static void addViewer(Drawable viewer) {
        viewers.add(viewer);
    }

    public static void render(GUI gui) throws IOException {

        TextGraphics tg = gui.getTextGraphics();

        gui.clear();
        viewers.forEach(viewer -> {
            viewer.render(tg);
        });
        gui.refresh();
    }
}
