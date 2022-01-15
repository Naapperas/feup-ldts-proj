package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.model.menus.Button;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.IOException;

public class InitialMenu extends AppState {

    private static final int TICK_TIME = 75;

    private final GUI gui;

    private static final int WIDTH = 40, HEIGHT = 40;

    public InitialMenu(Application app) throws Exception {
        super(app);
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

        Button playButton = new Button(10, 7, "ENTER to Play");
        Button leaderboardButton = new Button(10, 25, "L to Leaderboard");

        Renderer.clearRenderer();
        Renderer.addDrawable(playButton);
        Renderer.addDrawable(leaderboardButton);
    }

    @Override
    public void start() throws Exception {
        boolean running = true, next_map = false, next_leaderboard = false;
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
                case SELECT:
                    running=false;
                    next_map = true;
                    break;
                case LEADERBOARD:
                    running=false;
                    next_leaderboard = true;
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
        if (next_map)
            this.app.changeState(new MapMenu(this.app));
        if (next_leaderboard)
            this.app.changeState(new LeaderboardMenu(this.app));
    }

    @Override
    public void render() throws IOException {
        Renderer.render(gui);
    }
}
