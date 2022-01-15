package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Point;

public class MapConfiguration {

    private static int mapHeight;
    private static int mapWidth;
    private static Point boxPosition;

    public static MapConfiguration map = new MapConfiguration(0,0,new Point(0,0));

    public MapConfiguration(int height, int width, Point boxPosition){
        mapHeight = height;
        mapWidth = width;
        MapConfiguration.boxPosition = boxPosition;
    }


    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int height){
        mapHeight = height;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int width){
        mapWidth = width;
    }

    public Point getBoxPosition() {
        return boxPosition;
    }

    public void setBoxPosition(Point boxPos){
        boxPosition = boxPos;
    }
}
