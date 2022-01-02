package pt.up.fe.ldts.model;

import com.github.javaparser.utils.Pair;
import pt.up.fe.ldts.controller.employeeAI.EmployeeAI;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Entity implements CervejaListener {

    private static int SCORE_WHEN_EATEN = 20;

    public enum EmployeeState {
        SCATTER,
        FRIGHTENED,
        CHASING,
        DEAD
    }

    private EmployeeAI ai;
    private EmployeeState state = EmployeeState.SCATTER;

    public Employee(int x, int y, EmployeeAI ai) {
        super(x, y);
        this.ai = ai;
    }

    public EmployeeState getCurrentState() {
        return this.state;
    }

    public void setCurrentState(EmployeeState newState) {
        this.state = newState;
    }

    @Override
    public void cervejaPicked() {

        var currState = this.getCurrentState();
        this.setCurrentState(EmployeeState.FRIGHTENED);
        this.getDirection().multiply(-1);

        //TODO: implement timer

        this.setCurrentState(currState);
    }

    @Override
    public void changeDirection() {

        var targetPoint = this.ai.chooseTargetPosition(this.getCurrentState(), this.getPosition());

        this.setDirection(this.chooseNextDirection(targetPoint));
    }

    private Vector chooseNextDirection(Point targetPoint) {

        var possibleDirections = this.possibleDirections(this.getDirection());
        List<Pair<Vector, Double>> directionPairs = new ArrayList<>();

        for (var direction : possibleDirections)
            directionPairs.add(new Pair<>(direction, Vector.from(this.getPosition().addVector(direction), targetPoint).magnitude()));

        directionPairs.sort((pair1, pair2) -> {

            double mag1 = pair1.b, mag2 = pair2.b;

            return mag1 == mag2 ? pair1.a.compareTo(pair2.a) : Double.compare(mag1, mag2);
        });

        return directionPairs.get(0).a;
    }

    private List<Vector> possibleDirections(Vector direction){ // prototype, to be under the responsibility of the map
        List<Vector> directions = new ArrayList<>();

        if (Vector.UP.equals(direction)) {
            directions.add(Vector.UP);
            directions.add(Vector.LEFT);
            directions.add(Vector.RIGHT);
        } else if (Vector.LEFT.equals(direction)) {
            directions.add(Vector.LEFT);
            directions.add(Vector.DOWN);
            directions.add(Vector.UP);
        } else if (Vector.DOWN.equals(direction)) {
            directions.add(Vector.DOWN);
            directions.add(Vector.RIGHT);
            directions.add(Vector.LEFT);
        } else if (Vector.RIGHT.equals(direction)) {
            directions.add(Vector.RIGHT);
            directions.add(Vector.UP);
            directions.add(Vector.DOWN);
        }
        return directions;
    }
}