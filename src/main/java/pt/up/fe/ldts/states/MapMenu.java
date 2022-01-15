package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.model.map.DefaultMap;
import pt.up.fe.ldts.model.map.FileLoadedMap;
import pt.up.fe.ldts.model.menus.Button;
import pt.up.fe.ldts.states.AppState;
import pt.up.fe.ldts.states.Game;
import pt.up.fe.ldts.states.InitialMenu;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.IOException;

public class MapMenu extends AppState {

    private static final int TICK_TIME = 75;

    private final GUI gui;

    private static final int WIDTH = 40, HEIGHT = 40;

    public MapMenu(Application app) throws Exception {
        super(app);
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

        Button easyButton = new Button(10, 2, "EASY MODE (1)");
        Button defaultButton = new Button(10, 10, "DEFAULT MODE (2)");
        Button hardButton = new Button(10, 18, "HARD MODE (3)");
        Button goBackButton = new Button(10, 30, "Q TO GO BACK");

        Renderer.clearRenderer();
        Renderer.addDrawable(defaultButton);
        Renderer.addDrawable(easyButton);
        Renderer.addDrawable(hardButton);
        Renderer.addDrawable(goBackButton);
    }

    @Override
    public void start() throws Exception {
        boolean running = true, game = false;
        String mapName = "default";
        int FPS = 60;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();
        while (running){

            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT:
                    running = false;
                    break;
                case BABYMODE:
                    running=false;
                    game = true;
                    mapName = "baby";
                    break;
                case DEFAULTMODE:
                    running=false;
                    game = true;
                    mapName = "default";
                    break;
                case HARDMODE:
                    running=false;
                    game = true;
                    mapName = "hard";
                    break;
                default:
                    break;
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
            } catch (InterruptedException ignored) {
            }
        }
        gui.close();
        if (game)
            this.app.changeState(new Game(this.app, mapName));
        else
            this.app.changeState(new InitialMenu(this.app));
    }

    @Override
    public void render() throws IOException {
        Renderer.render(gui);
    }
}
