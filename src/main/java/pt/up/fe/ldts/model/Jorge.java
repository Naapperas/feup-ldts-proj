package pt.up.fe.ldts.model;

public class Jorge extends Entity{

    public static Jorge singleton = new Jorge(4, 5); // to be changed later

    private Jorge(int x, int y) {
        super(x, y);
    }

    @Override
    public void changeDirection() {
        // change direction based on keystrokes
    }


}
