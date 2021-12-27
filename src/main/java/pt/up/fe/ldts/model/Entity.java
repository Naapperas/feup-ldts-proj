package pt.up.fe.ldts.model;

/**
 *
 */
public abstract class Entity {

    private int x, y;

    public Entity() {} // no need to do anything explicitly here, both int variables will start at 0

    /**
     * Constructs a new Entity on the given position
     *
     * @param x the x coordinate of this entity
     * @param y the y coordinate of this entity
     */
    public Entity(int x, int y) {
        this.x= x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
