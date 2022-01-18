package pt.up.fe.ldts.model;

import java.util.Objects;

/**
 * Class that represents a point in a 2d space, functions as position
 */
public class Point {
    private int x, y;

    /**
     * Constructor
     * @param x the x coordinate of this Point
     * @param y the y coordinate of this Point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x coordinate of this Point
     * @return x coordinate of this Point
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get y coordinate of this Point
     * @return y coordinate of this Point
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set x coordinate for this Point
     * @param newX new x coordinate
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Set y coordinate for this Point
     * @param newY new y coordinate
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Shift a Point according to a Vector
     * @param v vector to be added
     * @return sum of this Point with v
     */
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

    @Override
    public String toString() {
        return "Pos[ x =" + this.x + ", y =" + this.y + "]";
    }

}
