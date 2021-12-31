package pt.up.fe.ldts.model;

public abstract class Element {

    private Position pos;

    /**
     * Constructs a new Element on the given position
     *
     * @param x the x coordinate of this entity
     * @param y the y coordinate of this entity
     */

    public Element(int x, int y){
        this.pos = new Position(x,y);
    }

    public Position getPosition() {
        return this.pos;
    }
    public int getX() {
        return this.getPosition().getX();
    }

    public int getY() {
        return this.getPosition().getY();
    }
}
