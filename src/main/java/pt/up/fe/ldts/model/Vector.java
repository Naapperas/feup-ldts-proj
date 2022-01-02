package pt.up.fe.ldts.model;

import java.util.Objects;

public record Vector(int x, int y) {

    public static Vector NULL = new Vector(0, 0);
    public static Vector LEFT = new Vector(-1, 0);
    public static Vector UP = new Vector(0, -1);
    public static Vector RIGHT = new Vector(1, 0);
    public static Vector DOWN = new Vector(0, 1);

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector multiply(int i) {
        return switch (i) {
            case 0 -> Vector.NULL;
            case -1 -> new Vector(-x, -y);
            case 1 -> this;
            default -> new Vector(i * this.x(), i * this.y());
        };
    }

    public static Vector from(Point a, Point b) {
        return new Vector(b.getX() - a.getX(), b.getY() - a.getY());
    }
}
