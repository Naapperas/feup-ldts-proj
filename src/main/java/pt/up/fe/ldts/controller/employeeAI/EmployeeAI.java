package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Point;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public abstract class EmployeeAI {

    protected static final Point DEAD_TARGET = new Point(10,10); // respawn
    protected static Point SCATTER_TARGET;

    /**
     * Chooses the next direction for this Employee
     */
    public abstract Point chooseTargetPosition(Employee.EmployeeState state, Point position);

}
