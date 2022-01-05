package pt.up.fe.ldts.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Class to represent walls (elements that serve as impassable positions for entities)
 */
public class Wall extends Element {

    /**
     * Constructs a new Wall on the given position
     * @param x the x coordinate of this Wall
     * @param y the y coordinate of this Wall
     */
    public Wall(int x, int y){
        super(x,y);
    }

    @Override
    public void render(TextGraphics tg) {

        var previousForegroundColor = tg.getForegroundColor();
        var previousBackgroundColor = tg.getBackgroundColor();

        tg.setBackgroundColor(TextColor.Factory.fromString(TextColor.ANSI.BLUE.name()));
        tg.setForegroundColor(TextColor.Factory.fromString(TextColor.ANSI.BLUE.name()));
        tg.putString(this.getX(), this.getY(), "W");

        tg.setForegroundColor(previousForegroundColor);
        tg.setBackgroundColor(previousBackgroundColor);
    }
}
