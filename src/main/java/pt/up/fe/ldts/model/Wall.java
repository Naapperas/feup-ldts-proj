package pt.up.fe.ldts.model;

import pt.up.fe.ldts.view.gui.GUI;

/**
 * Class to represent walls (elements that serve as impassable positions for entities)
 */
public class Wall extends Element {

    /**
     * Constructs a new Wall on the given position
     * @param x the x coordinate of this Wall
     * @param y the y coordinate of this Wall
     */
    public Wall(int x, int y){
        super(x,y);
    }


    @Override
    public void render(GUI gui) {

    }
}
