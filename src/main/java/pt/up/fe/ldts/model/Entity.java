package pt.up.fe.ldts.model;

/**
 *
 */
public abstract class Entity {

    static class Position {

        private int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        private void setX(int newX) {
            this.x = newX;
        }

        private void setY(int newY) {
            this.y = newY;
        }

        public String toString() {
            return "Pos[ x =" + this.x + ", y =" + this.y + "]";
        }
    }

    private Position pos;

    /**
     * Constructs a new Entity on the given position
     *
     * @param x the x coordinate of this entity
     * @param y the y coordinate of this entity
     */
    public Entity(int x, int y) {
        this.pos = new Position(x, y);
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

    public void changePos(int x, int y) {
        var pos = this.getPosition();
        pos.setX(x);
        pos.setY(y);
    }

    public abstract void move(); // mark this as abstract so we can override it with different behaviours

}
