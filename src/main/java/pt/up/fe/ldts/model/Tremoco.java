package pt.up.fe.ldts.model;

public class Tremoco extends Collectible {

    static {
        Tremoco.POINTS_PER_ITEM = 10;
    }

    public Tremoco(int x, int y){
        super(x,y);
    }

    @Override
    public void notifyCollected() {

        //TODO: when that is implemented, add score to Jorge

    }
}
