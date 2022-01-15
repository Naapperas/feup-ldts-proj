package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;

import java.io.IOException;

public abstract class AppState {

    protected Application app;

    public AppState(Application app){
        this.app = app;
    }

    public abstract void start() throws Exception;
    public abstract void render() throws IOException;
}
