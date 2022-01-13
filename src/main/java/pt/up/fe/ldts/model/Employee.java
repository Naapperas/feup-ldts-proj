package pt.up.fe.ldts.model;

import com.github.javaparser.utils.Pair;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.controller.employeeAI.EmployeeAI;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Entity implements CervejaListener {

    public static final int SCORE_WHEN_EATEN = 20;

    @Override
    public void render(TextGraphics tg) {
        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(this.ai.getEmployeeColor());
        tg.putString(this.getX(), this.getY(), "d");

        tg.setForegroundColor(previousForegroundColor);
    }

    public enum EmployeeState {
        SCATTER,
        FRIGHTENED,
        CHASING,
        DEAD
    }

    private final EmployeeAI ai;
    private EmployeeState state = EmployeeState.SCATTER;

    /**
     * Constructs a new Employee on the given position, with a certain EmployeeAi
     * @param x the x coordinate of this Employee
     * @param y the y coordinate of this Employee
     * @param ai EmployeeAi used by this employee
     */
    public Employee(int x, int y, EmployeeAI ai) {
        super(x, y);
        this.ai = ai;
    }

    /**
     * Get the current state(EmployeeState) of an employee
     * @return the current state of this employee
     */
    public EmployeeState getCurrentState() {
        return this.state;
    }

    /**
     * Set the current state of an employee
     * @param newState new EmployeeState
     */
    public void setCurrentState(EmployeeState newState) {
        this.state = newState;
    }

    @Override
    public void cervejaPicked() {

        var currState = this.getCurrentState();
        this.setCurrentState(EmployeeState.FRIGHTENED);
        this.setDirection(this.getDirection().multiply(-1));

        //TODO: implement timer

        this.setCurrentState(currState);
    }

    @Override
    public void changeDirection(Arena arena) {

        Point targetPoint;

        if (this.getPosition().equals(new Point(13, 13)) || ((11 <= this.getPosition().getX() && this.getPosition().getX() <= 15) && (14 <= this.getPosition().getY() && this.getPosition().getY() <= 16))) {
            targetPoint = new Point(13, 12); // make them leave the box initially
        } else
            targetPoint = this.ai.chooseTargetPosition(this.getCurrentState(), this.getPosition());

        this.setDirection(this.chooseNextDirection(arena, targetPoint));
    }

    /**
     * Choose the direction of an employee according to the position it's targeting
     * @param targetPoint the position this employee is targeting
     * @return new direction as vector
     */
    private Vector chooseNextDirection(Arena arena, Point targetPoint) {

        var possibleDirections = arena.getValidDirections(this.getPosition(), this.getDirection(), false);
        List<Pair<Vector, Double>> directionPairs = new ArrayList<>();

        for (var direction : possibleDirections)
            directionPairs.add(new Pair<>(direction, Vector.from(this.getPosition().addVector(direction), targetPoint).magnitude()));

        directionPairs.sort((pair1, pair2) -> {

            double mag1 = pair1.b, mag2 = pair2.b;

            return mag1 == mag2 ? pair1.a.compareTo(pair2.a) : Double.compare(mag1, mag2);
        });

        return directionPairs.get(0).a;
    }
}