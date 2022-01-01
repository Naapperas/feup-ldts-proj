package pt.up.fe.ldts.model.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Vector;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public interface EmployeeAI {

    /**
     * Chooses the next location for this Employee
     */
    Vector chooseTargetDirection(Employee.EmployeeState state);
}
