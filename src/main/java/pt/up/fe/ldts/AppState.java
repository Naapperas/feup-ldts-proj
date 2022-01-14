package pt.up.fe.ldts;

import java.io.IOException;

public abstract class AppState {

    private Application app;

    public AppState(Application app){
        this.app = app;
    }

    public abstract void start() throws IOException;
    public abstract void render() throws IOException;
}
