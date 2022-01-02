package pt.up.fe.ldts.model;

/**
 *
 */
public abstract class Entity extends Element{

    protected Vector direction;

    public Entity(int x, int y) {
        super(x, y);
        direction = Vector.UP;
    }

    public void changePos(int x, int y) {
        var pos = this.getPosition();
        pos.setX(x);
        pos.setY(y);
    }

    public void move(){
        if (!(this.direction.equals(Vector.UP) || this.direction.equals(Vector.DOWN) || this.direction.equals(Vector.LEFT)|| this.direction.equals(Vector.RIGHT) || this.direction.equals(Vector.NULL)))
            return; // unknown direction
        var newPos = this.getPosition().addVector(this.direction);

        this.changePos(newPos.getX(), newPos.getY());
    }


    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public abstract void changeDirection(); // abstract so different entity have different ways of choosing which direction to take
}
