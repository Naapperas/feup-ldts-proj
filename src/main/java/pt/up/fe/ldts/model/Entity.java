package pt.up.fe.ldts.model;

/**
 *
 */
public abstract class Entity extends Element {

    protected Vector direction;

    /**
     * Constructs a new Entity on the given position
     * @param x the x coordinate of this Entity
     * @param y the y coordinate of this Entity
     */
    public Entity(int x, int y) {
        super(x, y);
        direction = Vector.UP;  // default direction to start with
    }

    /**
     * Change this entity's position
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void changePos(int x, int y) {
        var pos = this.getPosition();
        pos.setX(x);
        pos.setY(y);
    }

    /**
     * Move this entity according to its current position and direction
     */
    public void move(){
        if (!(this.direction.equals(Vector.UP) || this.direction.equals(Vector.DOWN) || this.direction.equals(Vector.LEFT)|| this.direction.equals(Vector.RIGHT) || this.direction.equals(Vector.NULL)))
            return; // unknown direction
        var newPos = this.getPosition().addVector(this.direction);

        this.changePos(newPos.getX(), newPos.getY());
    }

    /**
     * Get an entity's direction
     * @return vector representing this entity's direction
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Set an entity's direction
     * @param direction new direction
     */
    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    /**
     * Change an entity's current direction
     * Abstract so different entities have different ways of choosing which direction to take
     */
    public abstract void changeDirection();
}
