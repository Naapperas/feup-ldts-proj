package pt.up.fe.ldts.model;

public abstract class Element {

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

        protected void setX(int newX) {
            this.x = newX;
        }

        protected void setY(int newY) {
            this.y = newY;
        }

        public String toString() {
            return "Pos[ x =" + this.x + ", y =" + this.y + "]";
        }
    }

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
