package pt.up.fe.ldts.model;

public abstract class Collectible extends Element {

    // the points to give to the player when this item is collected
    protected static int POINTS_PER_ITEM;

    /**
     * Constructs a new Collectible on the given position
     *
     * @param x the x coordinate of this collectible
     * @param y the y coordinate of this collectible
     */
    public Collectible(int x, int y) {
        super(x, y);
    }

    public abstract void notifyCollected();

}
