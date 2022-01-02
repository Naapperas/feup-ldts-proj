package pt.up.fe.ldts.model;

import java.util.Objects;

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

    public Point addVector(Vector v) {
        return new Point(this.getX() + v.x(), this.getY() + v.y());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "Pos[ x =" + this.x + ", y =" + this.y + "]";
    }

}
