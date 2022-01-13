package pt.up.fe.ldts.model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Cerveja extends Collectible {

    static {
        Cerveja.POINTS_PER_ITEM = 20;
    }

    public final List<CervejaListener> listeners;

    public void addListener(CervejaListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Constructs a new Cerveja on the given position
     * @param x the x coordinate of this Cerveja
     * @param y the y coordinate of this Cerveja
     */
    public Cerveja(int x, int y){
        super(x,y);
        this.listeners = new ArrayList<>(); // better for traversal
    }

    @Override
    public void notifyCollected() {

        //TODO: when that is implemented, add score to Jorge
        this.listeners.forEach(CervejaListener::cervejaPicked);
    }

    @Override
    public void render(TextGraphics tg) {

        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        tg.putString(this.getX(), this.getY()+1, "i");

        tg.setForegroundColor(previousForegroundColor);


    }
}
