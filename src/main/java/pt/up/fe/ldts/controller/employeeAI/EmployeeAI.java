package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.game.Employee;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.map.MapConfiguration;

import java.util.Random;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public abstract class EmployeeAI {

    protected static final Point DEAD_TARGET = MapConfiguration.getGatePosition();
    Random random = new Random();

    public abstract Point getScatterTarget();

    /**
     * Chooses the next direction for this Employee
     */
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> this.getScatterTarget();
            case CHASING -> chasingTarget(position);
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));

        };
    }

    public abstract Point chasingTarget(Point position);

    public abstract TextColor getEmployeeColor();
}
