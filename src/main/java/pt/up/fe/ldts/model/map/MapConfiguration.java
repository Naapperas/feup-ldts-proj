package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Point;

public class MapConfiguration {

    private static int mapHeight;
    private static int mapWidth;
    private static Point boxPosition;

    public MapConfiguration(int height, int width, Point boxPosition){
        mapHeight = height;
        mapWidth = width;
        MapConfiguration.boxPosition = boxPosition;
    }


    public static int getMapHeight() {
        return mapHeight;
    }

    public static void setMapHeight(int height){
        mapHeight = height;
    }

    public static int getMapWidth() {
        return mapWidth;
    }

    public static void setMapWidth(int width){
        mapWidth = width;
    }

    public static Point getBoxPosition() {
        return boxPosition;
    }

    public static void setBoxPosition(Point boxPos){
        boxPosition = boxPos;
    }
}
