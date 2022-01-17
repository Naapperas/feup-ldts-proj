package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Point;

public class MapConfiguration {

    private static int mapHeight;
    private static int mapWidth;
    private static Point gatePosition;

    public MapConfiguration(int height, int width, Point gatePosition){
        mapHeight = height;
        mapWidth = width;
        MapConfiguration.gatePosition = gatePosition;
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

    public static Point getGatePosition() {
        return gatePosition;
    }

    public static void setGatePosition(Point gatePos){
        gatePosition = gatePos;
    }
}
