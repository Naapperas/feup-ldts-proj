package pt.up.fe.ldts.states;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.model.game.Element;
import pt.up.fe.ldts.model.menus.Button;
import pt.up.fe.ldts.model.menus.MenuDisplay;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitialMenu extends AppState {

    private static final int WIDTH = 40, HEIGHT = 40;

    public InitialMenu(Application app) throws Exception {
        super(app);
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

        List<Button> buttons = new ArrayList<>();

        Element title = new Element(13, 5) {
            @Override
            public void render(TextGraphics tg) {

                tg.putString(this.getX(), this.getY(), "WAGGA.WAGcA");

            }
        };

        buttons.add(new Button(8, 10, "PLAY"));
        buttons.add(new Button(8, 19, "LEADERBOARD"));
        buttons.add(new Button(8, 28, "QUIT"));
        display = new MenuDisplay(buttons);

        Renderer.clearRenderer();
        Renderer.addDrawable(title);
        Renderer.addDrawable(display);
    }

    @SuppressWarnings("CatchAndPrintStackTrace")
    @Override
    public void start() throws Exception {
        boolean running = true, next = false;
        int FPS = 60;
        int frameTime = 1000 / FPS;

        this.display.selectTop();

        while (running){
            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT -> running = false;
                case SELECT -> {
                    running = false;
                    next = true;
                }
                case UP -> display.selectUP();
                case DOWN -> display.selectDown();
                default -> {}
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
        if (next) {
            if (display.getSelected() == 0)
                this.app.changeState(new MapMenu(this.app));
            else if (display.getSelected() == 1)
                this.app.changeState(new LeaderboardMenu(this.app));
        }
    }

    @Override
    public void render() throws IOException {
        Renderer.render(gui);
    }
}
