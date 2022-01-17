package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.map.MapConfiguration;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public abstract class EmployeeAI {

    protected static final Point DEAD_TARGET = MapConfiguration.getGatePosition(); // respawn
    protected Point SCATTER_TARGET;

    public Point getScatterTarget() {
        return this.SCATTER_TARGET;
    }

    /**
     * Chooses the next direction for this Employee
     */
    public abstract Point chooseTargetPosition(Employee.EmployeeState state, Point position);

    public abstract TextColor getEmployeeColor();
}
