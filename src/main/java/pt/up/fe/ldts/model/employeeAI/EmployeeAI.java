package pt.up.fe.ldts.model.employeeAI;

import pt.up.fe.ldts.model.Entity;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public interface EmployeeAI {

    /**
     * Chooses the next location for this Employee
     */
    void chooseTargetLocation();
}
