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
        if (Vector.UP.equals(direction)) {
            this.changePos(this.getX(), this.getY() - 1);
        } else if (Vector.DOWN.equals(direction)) {
            this.changePos(this.getX(), this.getY() + 1);
        } else if (Vector.RIGHT.equals(direction)) {
            this.changePos(this.getX() + 1, this.getY());
        } else if (Vector.LEFT.equals(direction)) {
            this.changePos(this.getX() - 1, this.getY());
        }
    }


    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public abstract void changeDirection(); // abstract so different entity have different ways of choosing which direction to take
}
