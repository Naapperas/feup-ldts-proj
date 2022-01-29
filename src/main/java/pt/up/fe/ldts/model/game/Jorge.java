package pt.up.fe.ldts.model.game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.map.MapConfiguration;

import static pt.up.fe.ldts.view.gui.GUI.ACTION;

import java.util.concurrent.atomic.AtomicInteger;

public class Jorge extends Entity{

    public static Jorge singleton = new Jorge(13, 24);

    AtomicInteger score = new AtomicInteger(0), lives = new AtomicInteger(3);

    /**
     * Constructs a new Jorge on the given position
     * @param x the x coordinate of this Jorge
     * @param y the y coordinate of this Jorge
     */
    private Jorge(int x, int y) {
        super(x, y);
        this.setDirection(Vector.NULL);
    }

    public void chooseDirection(ACTION action, Arena arena) {
        
        Vector newDirection = switch (action) {
            case UP -> Vector.UP;
            case DOWN -> Vector.DOWN;
            case LEFT -> Vector.LEFT;
            case RIGHT -> Vector.RIGHT;
            default -> this.getDirection();
        };

        if (!arena.getValidDirections(this.getPosition(), this.getDirection(), true).contains(newDirection))
            return;

        this.setDirection(newDirection);
    }

    @Override
    public void changeDirection(Arena arena) {
        if (!arena.getValidDirections(this.getPosition(), this.getDirection(), true).contains(this.getDirection()))
            this.setDirection(Vector.NULL);
    }

    public void restart(){
        this.setDirection(Vector.NULL);
        resetPoints();
        fullHealth();
    }

    public void addPoints(int points) {
        score.addAndGet(points);
    }

    public void kill() {lives.decrementAndGet();}

    private void resetPoints(){score.set(0);}

    private void fullHealth(){lives.set(3);}

    public int getScore() {
        return score.get();
    }

    public boolean isAlive() {return lives.get()>0;}

    private boolean cycle = true;
    public void cycleAnimation(boolean cycle) {
        this.cycle = cycle;
    }

    private char previousRenderChar = 'h';

    @Override
    public void render(TextGraphics tg) {

        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));

        if (this.cycle) {
            if (previousRenderChar == 'h') {
                if (Vector.RIGHT.equals(Jorge.singleton.getDirection())) {
                    previousRenderChar = 'c';
                } else if (Vector.LEFT.equals(Jorge.singleton.getDirection())) {
                    previousRenderChar = 'g';
                } else if (Vector.UP.equals(Jorge.singleton.getDirection())) {
                    previousRenderChar = 'e';
                } else if (Vector.DOWN.equals(Jorge.singleton.getDirection())) {
                    previousRenderChar = 'f';
                }
            } else
                previousRenderChar = 'h';
        }
        tg.putString(this.getX(), this.getY()+1, "" + previousRenderChar);


        tg.putString(0, 0, "SCORE:" + this.getScore());

        for(AtomicInteger i = new AtomicInteger(0); i.get() < lives.get(); i.incrementAndGet()){
            tg.putString(MapConfiguration.getMapWidth() - 1 - i.get(), 0, "c");
        }

        tg.setForegroundColor(previousForegroundColor);
    }
}
