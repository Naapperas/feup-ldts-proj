package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public interface EmployeeAI {

    /**
     * Chooses the next direction for this Employee
     */
    Point chooseTargetPosition(Employee.EmployeeState state, Point position);

}
