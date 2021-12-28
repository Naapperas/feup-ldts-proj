package pt.up.fe.ldts.model;

public class Jorge extends Entity{

    enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    private Direction direction;

    public Jorge(int x, int y) {
        super(x, y);
        direction = Direction.LEFT;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(){
        switch (direction) {
            case UP -> this.changePos(this.getX(), this.getY()-1);
            case DOWN -> this.changePos(this.getX(), this.getY()+1);
            case RIGHT -> this.changePos(this.getX()+1, this.getY());
            case LEFT -> this.changePos(this.getX()-1, this.getY());
        }
    }


}
