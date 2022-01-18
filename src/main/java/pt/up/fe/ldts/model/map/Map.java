package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.game.Collectible;
import pt.up.fe.ldts.model.game.Employee;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.game.Wall;

import java.util.List;

public interface Map {

    List<Wall> getMapWalls();
    List<Collectible> getMapCollectibles();
    List<Employee> getMapEmployees();

    Point getGatePosition();
    Point getBoxPosition();

    int getWidth();
    int getHeight();

    int getBoxWidth();
    int getBoxHeight();
}
