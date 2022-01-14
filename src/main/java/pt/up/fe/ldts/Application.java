package pt.up.fe.ldts;

import pt.up.fe.ldts.model.Arena;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.map.DefaultMap;
import pt.up.fe.ldts.model.map.Map;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    private static final int TICK_TIME = 75;

    private final GUI gui;

    private static final int WIDTH = 27, HEIGHT = 30;

    private Arena arena;
    private Map map;


    public Application() throws Exception {
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

        this.arena = new Arena(WIDTH, HEIGHT);
        this.map = new DefaultMap();
        this.arena.setWalls(map.getMapWalls());
        this.arena.setEmployees(map.getMapEmployees());
        this.arena.setCollectibles(map.getMapCollecibles());
        this.arena.setGatePosition(map.getGatePosition());

        Renderer.addDrawable(arena);
    }

    public static void main(String[] args) {
        try {
            new Application().start();
        } catch (IOException | FontFormatException | URISyntaxException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {

        boolean running = true;

        int FPS = 6;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();
        while (running) {

            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT:
                    running = false;
                    break;
                case SELECT: // TODO: to be implemented if we add an "initial screen"
                    break;
                default:
                    break;
            }

            if (lastTime - startTime > TICK_TIME) {
                tick(currentAction);
                startTime = lastTime;
            }

            this.render();

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

    private void render() throws IOException {
        Renderer.render(gui);
    }

    private void tick(GUI.ACTION action) {

        Jorge.singleton.chooseDirection(action, this.arena);
        Jorge.singleton.changeDirection(this.arena);

        Jorge.singleton.move();

        this.arena.getEmployees().forEach(employee -> {
            employee.changeDirection(this.arena);
            employee.move();
        });
    }
}
