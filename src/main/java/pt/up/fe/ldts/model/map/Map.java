package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Collectible;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Wall;

import java.util.List;

public interface Map {

    List<Wall> getMapWalls();
    List<Collectible> getMapCollecibles();
    List<Employee> getMapEmployees();

    int getWidth();
    int getHeight();
}
