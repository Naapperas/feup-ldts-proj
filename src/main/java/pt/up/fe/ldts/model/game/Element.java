package pt.up.fe.ldts.model.game;

import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.view.Drawable;

public abstract class Element implements Drawable {

    private final Point position;

    /**
     * Constructs a new Element on the given position
     *
     * @param x the x coordinate of this entity
     * @param y the y coordinate of this entity
     */
    public Element(int x, int y){
        this.position = new Point(x,y);
    }

    /**
     * Get the position of an Element as a Point
     * @return Point representing this element's position
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Get x coordinate of an Element
     * @return x coordinate of this element
     */
    public int getX() {
        return this.getPosition().getX();
    }

    /**
     * Get y coordinate of an Element
     * @return y coordinate of this element
     */
    public int getY() {
        return this.getPosition().getY();
    }
}
