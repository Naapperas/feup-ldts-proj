package pt.up.fe.ldts.model;

/**
 *
 */
public abstract class Entity extends Element{


    public Entity(int x, int y) {
        super(x,y);
    }

    public void changePos(int x, int y) {
        var pos = this.getPosition();
        pos.setX(x);
        pos.setY(y);
    }

    public abstract void move(); // mark this as abstract so we can override it with different behaviours

}
