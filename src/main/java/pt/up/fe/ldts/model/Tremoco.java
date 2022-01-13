package pt.up.fe.ldts.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tremoco extends Collectible {

    static {
        Tremoco.POINTS_PER_ITEM = 10;
    }

    /**
     * Constructs a new Tremoco on the given position
     * @param x the x coordinate of this Tremoço
     * @param y the x coordinate of this Tremoço
     */
    public Tremoco(int x, int y){
        super(x,y);
    }

    @Override
    public void notifyCollected() {

        //TODO: when that is implemented, add score to Jorge

    }

    @Override
    public void render(TextGraphics tg) {

        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(TextColor.Factory.fromString("#e4d5b7"));
        tg.putString(this.getX(), this.getY()+1, ".");

        tg.setForegroundColor(previousForegroundColor);

    }
}
