package pt.up.fe.ldts;

import java.io.IOException;

public interface AppState {
    public void start() throws IOException;
    public void render() throws IOException;
}
