package pt.up.fe.ldts;

import pt.up.fe.ldts.model.Arena;
import pt.up.fe.ldts.model.Wall;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Application {

    private final GUI gui;
    private Object state;

    private static final int WIDTH = 20, HEIGTH = 20;

    private Arena arena;

    private List<Wall> getMapWalls() {
        return null;
    }

    public Application() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(WIDTH, HEIGTH);

        this.arena = new Arena(WIDTH, HEIGTH);
        this.arena.addWalls(this.getMapWalls());

        Renderer.addViewer(arena);
    }

    public static void main(String[] args) {
        try {
            new Application().start();
        } catch (IOException | FontFormatException | URISyntaxException ignored) {
        }
    }

    private void start() throws IOException {
        int FPS = 60;
        int frameTime = 1000 / FPS;

        int test = 0;

        long startTime = System.currentTimeMillis();
        while (test++ <= 500) {

            long lastTime = System.currentTimeMillis();

            if (lastTime - startTime > 500) {
                // update entities
            }

            Renderer.render(gui);
            startTime = lastTime;

            long elapsedTime = System.currentTimeMillis() - lastTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }

        gui.close();
    }
}
