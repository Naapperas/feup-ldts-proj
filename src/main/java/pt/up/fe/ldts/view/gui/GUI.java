package pt.up.fe.ldts.view.gui;

import pt.up.fe.ldts.model.Point;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawHero(Point position);

    void drawWall(Point position);

    void drawMonster(Point position);

    void drawText(Point position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}