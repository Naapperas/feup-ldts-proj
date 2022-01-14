package pt.up.fe.ldts;

import java.io.IOException;

public interface AppState {
    void start() throws IOException;
    void render() throws IOException;
}
