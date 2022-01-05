package pt.up.fe.ldts.view;

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
        gui.clear();
        viewers.forEach(viewer -> {
            viewer.render(gui);
        });
        gui.refresh();
    }
}
