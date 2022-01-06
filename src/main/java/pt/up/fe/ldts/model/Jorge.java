package pt.up.fe.ldts.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.atomic.AtomicInteger;

public class Jorge extends Entity{

    public static Jorge singleton = new Jorge(13, 18); // to be changed later

    AtomicInteger score = new AtomicInteger(0);

    /**
     * Constructs a new Jorge on the given position
     * @param x the x coordinate of this Jorge
     * @param y the y coordinate of this Jorge
     */
    private Jorge(int x, int y) {
        super(x, y);
        this.setDirection(Vector.UP);
    }

    @Override
    public void changeDirection(Arena arena) {
        // change direction based on keystrokes
    }

    public void addPoints(int points) {
        score.addAndGet(points);
    }

    public int getScore() {
        return score.get();
    }

    @Override
    public void render(TextGraphics tg) {

        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        tg.putString(this.getX(), this.getY(), "O");

        tg.putString(0, 0, "Score:" + this.getScore());

        tg.setForegroundColor(previousForegroundColor);
    }
}
