package pt.up.fe.ldts.view.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.model.Point;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    TextGraphics getTextGraphics();

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}