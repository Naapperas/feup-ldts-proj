package pt.up.fe.ldts.model;

/**
 * Class that represents a vector (used in calculations with points)
 */
public record Vector(int x, int y) implements Comparable<Vector> {

    public static Vector NULL = new Vector(0, 0);
    public static Vector LEFT = new Vector(-1, 0);
    public static Vector UP = new Vector(0, -1);
    public static Vector RIGHT = new Vector(1, 0);
    public static Vector DOWN = new Vector(0, 1);

    /**
     * Calculate the scalar size of this vector
     * @return the scalar size of this vector
     */
    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * Multiply vector by scalar
     * @param i scalar
     * @return multiplied vector
     */
    public Vector multiply(int i) {
        return switch (i) {
            case 0 -> Vector.NULL;
            case -1 -> new Vector(-x, -y);
            case 1 -> this;
            default -> new Vector(i * this.x(), i * this.y());
        };
    }

    /**
     * Represent a vector between two points
     * @param a first point (from)
     * @param b second point (to)
     * @return vector from a to b
     */
    public static Vector from(Point a, Point b) {
        return new Vector(b.getX() - a.getX(), b.getY() - a.getY());
    }

    @Override
    public int compareTo(Vector o) {  // UP > LEFT > DOWN > RIGHT
        if (this.equals(Vector.UP)) {
            return o.equals(Vector.UP) ? 0 : 1;
        }
        else if (this.equals(Vector.RIGHT)) {
            return o.equals(Vector.RIGHT) ? 0 : -1;
        }
        else {
            if (this.equals(Vector.LEFT)) {
                if (o.equals(Vector.UP))
                    return -1;
                else if (o.equals(Vector.LEFT))
                    return 0;
                else
                    return 1;
            } else {
                if (o.equals(Vector.RIGHT))
                    return 1;
                else if (o.equals(Vector.DOWN))
                    return 0;
                else
                    return -1;
            }
        }
    }
}
