package pt.up.fe.ldts.view;

public class Arena {

    private int width;
    private int height;

    public Arena(int width, int height){
        setWidth(width);
        setHeight(height);
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
