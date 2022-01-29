package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.model.menus.Button;
import pt.up.fe.ldts.model.menus.MenuDisplay;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapMenu extends AppState {

    private static final int TICK_TIME = 75;

    private static final int WIDTH = 40, HEIGHT = 40;

    public MapMenu(Application app) throws Exception {
        super(app);
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

        List<Button> buttons = new ArrayList<>();

        buttons.add(new Button(10, 2, "EASY MODE (1)"));
        buttons.add(new Button(10, 10, "NORMAL MODE (2)"));
        buttons.add(new Button(10, 18, "HARD MODE (3)"));
        buttons.add(new Button(10, 30, "GO BACK"));

        display = new MenuDisplay(buttons);

        Renderer.clearRenderer();
        Renderer.addDrawable(display);
    }

    @SuppressWarnings("CatchAndPrintStackTrace")
    @Override
    public void start() throws Exception {
        boolean running = true;
        String mapName;
        int FPS = 60;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();

        this.display.selectTop();

        while (running){

            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case SELECT -> running = false;
                case UP -> display.selectUP();
                case DOWN -> display.selectDown();
                default -> {
                }
            }

            if (lastTime - startTime > TICK_TIME) {
                startTime = lastTime;
            }

            this.render();

            long elapsedTime = System.currentTimeMillis() - lastTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gui.close();
        switch (display.getSelected()) {
            case 0 -> {
                mapName = "baby";
                this.app.changeState(new Game(this.app, mapName));
            }
            case 1 -> {
                mapName = "default";
                this.app.changeState(new Game(this.app, mapName));
            }
            case 2 -> {
                mapName = "hard";
                this.app.changeState(new Game(this.app, mapName));
            }
            case 3 -> this.app.changeState(new InitialMenu(this.app));
        }
    }

    @Override
    public void render() throws IOException {
        Renderer.render(gui);
    }
}
