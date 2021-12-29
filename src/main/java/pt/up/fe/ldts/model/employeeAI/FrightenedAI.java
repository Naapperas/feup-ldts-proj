package pt.up.fe.ldts.model.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;

public class FrightenedAI implements EmployeeAI {

    private FrightenedAI() {}

    public static FrightenedAI singleton = new FrightenedAI();

    @Override
    public Entity.Direction chooseTargetDirection(Employee.EmployeeState state) {
        return null;
    }
}
