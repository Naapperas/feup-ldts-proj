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

public class InitialMenu extends AppState {

    private static final int TICK_TIME = 75;

    private final GUI gui;

    private static final int WIDTH = 40, HEIGHT = 40;

    private MenuDisplay display;

    public InitialMenu(Application app) throws Exception {
        super(app);
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

        List<Button> buttons = new ArrayList<>();

        buttons.add(new Button(10, 7, "ENTER TO PLAY"));
        buttons.add(new Button(10, 25, "L TO LEADERBOARD"));
        display = new MenuDisplay(buttons);

        Renderer.clearRenderer();
        Renderer.addDrawable(display);
    }

    @Override
    public void start() throws Exception {
        boolean running = true, next = false;
        int FPS = 60;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();

        this.display.selectTop();

        while (running){
            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT:
                    running = false;
                    break;
                case SELECT:
                    running=false;
                    next = true;
                    break;
                case UP:
                    display.selectUP();
                    break;
                case DOWN:
                    display.selectDown();
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
        if (next) {
            if (display.getSelected()==0)
                this.app.changeState(new MapMenu(this.app));
            if (display.getSelected()==1)
                this.app.changeState(new LeaderboardMenu(this.app));
        }
    }

    @Override
    public void render() throws IOException {
        Renderer.render(gui);
    }
}
