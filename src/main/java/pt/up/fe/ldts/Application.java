package pt.up.fe.ldts;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    private AppState state;

    private Application() throws Exception {
        state = new Menu(this);
    }

    private void start() throws Exception {
        state.start();
    }

    public static void main(String[] args) {
        try {
            new Application().start();
        } catch (IOException | FontFormatException | URISyntaxException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeState(AppState state) throws Exception {
        this.state = state;
        this.start();
    }
}
