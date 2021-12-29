package pt.up.fe.ldts.model;

/**
 *
 */
public abstract class Entity extends Element{

    enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    protected Direction direction;

    public Entity(int x, int y) {
        super(x, y);
        direction = Direction.LEFT;
    }

    public void changePos(int x, int y) {
        var pos = this.getPosition();
        pos.setX(x);
        pos.setY(y);
    }

    public void move(){
        switch (direction) {
            case UP -> this.changePos(this.getX(), this.getY()-1);
            case DOWN -> this.changePos(this.getX(), this.getY()+1);
            case RIGHT -> this.changePos(this.getX()+1, this.getY());
            case LEFT -> this.changePos(this.getX()-1, this.getY());
        }
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public abstract void changeDirection(); // abstract so different entity have different ways of choosing which direction to take
}
