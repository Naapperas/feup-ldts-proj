package pt.up.fe.ldts.model.game;

public abstract class Collectible extends Element {

    // the points to give to the player when this item is collected
    protected abstract int pointsPerItem();

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Collectible w = (Collectible) obj;
        return w.getPosition().equals(this.getPosition());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
