package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.model.menus.MenuDisplay;
import pt.up.fe.ldts.view.gui.GUI;

import java.io.IOException;

public abstract class AppState {

    protected Application app;
    protected GUI gui;

    protected MenuDisplay display;

    public AppState(Application app){
        this.app = app;
    }

    public abstract void start() throws Exception;
    public abstract void render() throws IOException;
}
