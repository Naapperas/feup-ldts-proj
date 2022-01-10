package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.model.Collectible;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Wall;

import java.util.List;

public class FileLoadedMap implements Map {

    private int width, height;

    private List<Wall> walls;
    private List<Employee> employees;
    private List<Collectible> collectibles;

    @Override
    public List<Wall> getMapWalls() {
        return this.walls;
    }

    @Override
    public List<Collectible> getMapCollecibles() {
        return this.collectibles;
    }

    @Override
    public List<Employee> getMapEmployees() {
        return this.employees;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
