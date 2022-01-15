package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Point;

public class MapConfiguration {

    private int mapHeight;
    private int mapWidth;
    private Point boxPosition;

    public static MapConfiguration map = new MapConfiguration(0,0,new Point(0,0));

    public MapConfiguration(int height, int width, Point boxPosition){
        this.mapHeight = height;
        this.mapWidth = width;
        this.boxPosition = boxPosition;
    }


    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int height){
        this.mapHeight = height;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int width){
        this.mapWidth = width;
    }

    public Point getBoxPosition() {
        return boxPosition;
    }

    public void setBoxPosition(Point boxPos){
        this.boxPosition = boxPos;
    }
}
