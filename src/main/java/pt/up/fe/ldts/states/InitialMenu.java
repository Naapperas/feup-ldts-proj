package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.IOException;

public class InitialMenu extends AppState {

    private final GUI gui;

    private static final int WIDTH = 20, HEIGHT = 20;

    public InitialMenu(Application app) throws Exception {
        super(app);
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

    }

    @Override
    public void start() throws Exception {
        boolean running = true, next_map = false, next_leaderboard = false;
        while (running){

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
            this.render();
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
