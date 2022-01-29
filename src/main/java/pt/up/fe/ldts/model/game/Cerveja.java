package pt.up.fe.ldts.model.game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Cerveja extends Collectible {

    public final List<CervejaListener> listeners;

    public void addListener(CervejaListener listener) {
        this.listeners.add(listener);
    }

    @Override
    protected int pointsPerItem() {
        return 20;
    }

    /**
     * Constructs a new Cerveja on the given position
     * @param x the x coordinate of this Cerveja
     * @param y the y coordinate of this Cerveja
     */
    public Cerveja(int x, int y){
        super(x,y);
        this.listeners = new ArrayList<>();
    }

    @Override
    public void notifyCollected() {

        Jorge.singleton.addPoints(this.pointsPerItem());

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
