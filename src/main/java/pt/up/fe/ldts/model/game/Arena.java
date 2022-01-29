package pt.up.fe.ldts.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.map.Map;
import pt.up.fe.ldts.model.map.MapConfiguration;
import pt.up.fe.ldts.view.Drawable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class to handle visual elements of the game (mainly the game arena, the surface where every element is placed)
 */
public class Arena implements Drawable {

    private int width;
    private int height;

    private Point gatePosition, boxCorner;
    private int boxWidth, boxHeight;

    private final List<Employee> employees = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private List<Collectible> collectibles = new ArrayList<>();

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Collectible> getCollectibles(){return this.collectibles;}

    public void setEmployees(List<Employee> employees) {
        this.employees.clear();
        this.employees.addAll(employees);
    }

    public void setWalls(List<Wall> walls) {
        this.walls.clear();
        this.walls.addAll(walls);
    }

    public void setCollectibles(List<Collectible> collectibles) {
        this.collectibles.clear();
        for (Collectible c : collectibles){
            if (c.getClass() == Cerveja.class){
                for (Employee e : this.employees)
                    ((Cerveja) c).addListener(e);
            }
            this.collectibles.add(c);
        }
    }

    public void setGatePosition(Point gatePosition) {
        this.gatePosition = gatePosition;
    }

    public Point getGatePosition() {
        return this.gatePosition;
    }

    public Arena(Map map) {
        this.setGatePosition(map.getGatePosition());

        this.boxCorner = map.getBoxPosition();
        this.boxWidth = map.getBoxWidth();
        this.boxHeight = map.getBoxHeight();

        this.setEmployees(map.getMapEmployees());
        this.setCollectibles(map.getMapCollectibles());
        this.setWalls(map.getMapWalls());

        this.width = map.getWidth();
        this.height = map.getHeight();

    }

    /**
     * Constructor
     * @param width horizontal size of the game arena
     * @param height vertical size of the game arena
     */
    public Arena(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    /**
     * Get width of the arena
     * @return width of the arena
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width of the arena
     * @param width new width value
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get height of the arena
     * @return height of the arena
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set height of the arena
     * @param height new height value
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Vector> getValidDirections(Point position, Vector currentDirection, boolean isJorge) {
        Set<Vector> validDirs = new HashSet<>(List.of(Vector.UP, Vector.LEFT, Vector.DOWN, Vector.RIGHT));

        return validDirs
                .stream()
                .filter(dir -> isJorge || !dir.equals(currentDirection.multiply(-1))) // ghosts can't go back
                .filter(dir -> { // can't go through walls
                    var newPosition = position.addVector(dir);

                    return !this.walls.contains(new Wall(newPosition.getX(), newPosition.getY()));
                })
                .filter(dir -> !position.equals(this.gatePosition.addVector(Vector.UP)) || !dir.equals(Vector.DOWN)) // can't go back to initial box
                .collect(Collectors.toUnmodifiableSet());
    }

    public boolean isInsideBox(Point position) {

        boolean insideWidth = this.boxCorner.getX() <= position.getX() && position.getX() < this.boxCorner.getX() + this.boxWidth;
        boolean insideHeight = this.boxCorner.getY() <= position.getY() && position.getY() < this.boxCorner.getY() + this.boxHeight;

        return insideWidth && insideHeight;
    }

    @Override
    public void render(TextGraphics tg) {

        tg.setBackgroundColor(TextColor.Factory.fromString(TextColor.ANSI.BLACK.name()));
        tg.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (var wall : this.walls)
            wall.render(tg);

        tg.putString(this.gatePosition.getX(),this.gatePosition.getY(), "_");

        for (var collectible : this.collectibles)
            collectible.render(tg);

        for (var employee : this.employees)
            employee.render(tg);

        Jorge.singleton.render(tg);
    }

    public void checkCollectibleCollision() {

        for (Collectible c : this.collectibles)
            if (Jorge.singleton.getPosition().equals(c.getPosition())) {
                c.notifyCollected();
                this.collectibles.remove(c);
                return;
            }
    }

    public void restartLevel(){
        this.collectibles = MapConfiguration.getCollectibles();
        resetEntities();
    }

    public boolean emptyCollectibles(){
        return collectibles.isEmpty();
    }

    public void checkEmployeeCollision(){
        for (Employee e : this.employees){
            if(Jorge.singleton.getPosition().equals(e.getPosition())){
                if(e.getCurrentState() == Employee.EmployeeState.FRIGHTENED){
                    e.setCurrentState(Employee.EmployeeState.DEAD);
                    Jorge.singleton.addPoints(Employee.SCORE_WHEN_EATEN);
                }
                else if (e.getCurrentState() == Employee.EmployeeState.CHASING || e.getCurrentState() == Employee.EmployeeState.SCATTER){
                    Jorge.singleton.kill();
                    resetEntities();
                }
            }
        }
    }

    private void resetEntities(){
        for (Employee e : this.employees){
            e.changePos(MapConfiguration.getGatePosition().getX(), MapConfiguration.getGatePosition().getY()+1);
            e.setCurrentState(Employee.EmployeeState.SCATTER);
            e.setDirection(Vector.UP);
        }
        Jorge.singleton.changePos(MapConfiguration.getJorgePosition().getX(), MapConfiguration.getJorgePosition().getY());
    }

}
