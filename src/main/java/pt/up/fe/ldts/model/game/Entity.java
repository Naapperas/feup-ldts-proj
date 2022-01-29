package pt.up.fe.ldts.model.game;

import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.map.MapConfiguration;

public abstract class Entity extends Element {

    protected Vector direction;

    /**
     * Constructs a new Entity on the given position
     * @param x the x coordinate of this Entity
     * @param y the y coordinate of this Entity
     */
    public Entity(int x, int y) {
        super(x, y);
        direction = Vector.UP;
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

        if (newPos.getY() == 0)
            newPos.setY(MapConfiguration.getMapHeight() -1);
        else if (newPos.getY() == MapConfiguration.getMapHeight()-1)
            newPos.setY(0);
        else if (newPos.getX() == 0)
            newPos.setX(MapConfiguration.getMapWidth()-1);
        else if (newPos.getX() == MapConfiguration.getMapWidth()-1)
            newPos.setX(0);

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
    public abstract void changeDirection(Arena arena);

}
