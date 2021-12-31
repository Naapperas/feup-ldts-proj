package pt.up.fe.ldts.Controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Position;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public interface EmployeeAI {

    /**
     * Chooses the next location for this Employee
     */
    Entity.Direction chooseTargetDirection(Employee.EmployeeState state, Position position, Entity.Direction direction);
}
