package pt.up.fe.ldts.model.game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Tremoco extends Collectible {

    @Override
    protected int pointsPerItem() {
        return 10;
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
        Jorge.singleton.addPoints(this.pointsPerItem());
    }

    @Override
    public void render(TextGraphics tg) {

        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(TextColor.Factory.fromString("#e4d5b7"));
        tg.putString(this.getX(), this.getY()+1, ".");

        tg.setForegroundColor(previousForegroundColor);

    }
}
