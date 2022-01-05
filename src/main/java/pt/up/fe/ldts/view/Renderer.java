package pt.up.fe.ldts.view;

import pt.up.fe.ldts.view.gui.GUI;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private static final List<Drawable> viewers = new ArrayList<>();

    public static void addViewer(Drawable viewer) {
        viewers.add(viewer);
    }

    public static void render(GUI gui) {
        viewers.forEach(viewer -> {
            viewer.render(gui);
        });
    }
}
