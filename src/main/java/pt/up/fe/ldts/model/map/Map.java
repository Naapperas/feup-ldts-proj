package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Collectible;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Wall;

import java.util.List;

public interface Map {

    List<Wall> getMapWalls();
    List<Collectible> getMapCollecibles();
    List<Employee> getMapEmployees();

    Point getGatePosition();
    Point getBoxPosition();

    int getWidth();
    int getHeight();

    int getBoxWidth();
    int getBoxHeight();
}
