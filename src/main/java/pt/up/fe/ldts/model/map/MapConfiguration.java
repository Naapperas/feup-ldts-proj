package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.game.Collectible;

import java.util.List;

public class MapConfiguration {

    private static int mapHeight;
    private static int mapWidth;
    private static Point gatePosition, jorgePosition;
    private static List<Collectible> collectibles;

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

    public static Point getJorgePosition() {
        return jorgePosition;
    }

    public static void setJorgePosition(Point jorgePos){
        jorgePosition = jorgePos;
    }

    public static List<Collectible> getCollectibles(){return collectibles;}

    public static void setCollectibles(List<Collectible> c){
        collectibles = c;
    }
}
