package pt.up.fe.ldts;

import pt.up.fe.ldts.model.Arena;
import pt.up.fe.ldts.model.map.DefaultMap;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.IOException;

public class Menu implements AppState{

    private final GUI gui;

    private static final int WIDTH = 20, HEIGHT = 20;

    public Menu() throws Exception {
        this.gui = new LanternaGUI(WIDTH, HEIGHT +1);

    }

    @Override
    public void start() throws IOException {
        boolean running = true;
        while (running){

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT:
                    running = false;
                    break;
                default:
                    break;
            }
            this.render();
        }
        gui.close();
    }

    @Override
    public void render() throws IOException {
        Renderer.render(gui);
    }
}
