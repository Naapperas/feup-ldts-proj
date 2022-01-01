package pt.up.fe.ldts.model;

import java.util.Objects;

public record Vector(int x, int y) {

    public static Vector NULL = new Vector(0, 0);
    public static Vector LEFT = new Vector(-1, 0);
    public static Vector UP = new Vector(0, 1);
    public static Vector RIGHT = new Vector(1, 0);
    public static Vector DOWN = new Vector(0, -1);

    public double magnitude() {
        return 0.0;
    }

    public int getX() {
        return this.x();
    }

    public int getY() {
        return this.y();
    }

    public Vector multiply(int i) {
        return null;
    }

    public static Vector from(Point a, Point b) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
