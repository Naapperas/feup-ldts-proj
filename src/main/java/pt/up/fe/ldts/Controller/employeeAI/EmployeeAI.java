package pt.up.fe.ldts.Controller.employeeAI;

import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Position;

/**
 * The AI to rule the movement of the employees throughout the game.
 */
public interface EmployeeAI {

    /**
     * Chooses the next location for this Employee
     */
    Entity.Direction scatter(Entity.Direction direction, Position position);

    Entity.Direction frightened(Entity.Direction direction, Position position);

    Entity.Direction chase(Entity.Direction direction, Position position);

    Entity.Direction dead(Entity.Direction direction, Position position);
}

