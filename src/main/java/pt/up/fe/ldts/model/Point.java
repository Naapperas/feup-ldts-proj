package pt.up.fe.ldts.model;

public class Point {
    private int x, y;

    public Point(int x, int y) {
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
