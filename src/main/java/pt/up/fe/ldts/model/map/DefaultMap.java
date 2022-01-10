package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Collectible;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Wall;

import java.util.List;

public class DefaultMap implements Map{

    @Override
    public List<Wall> getMapWalls() {
        return null;
    }

    @Override
    public List<Collectible> getMapCollecibles() {
        return null;
    }

    @Override
    public List<Employee> getMapEmployees() {
        return null;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
