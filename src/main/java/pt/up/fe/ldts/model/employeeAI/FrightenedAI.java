package pt.up.fe.ldts.model.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Vector;

public class FrightenedAI implements EmployeeAI {

    private FrightenedAI() {}

    public static FrightenedAI singleton = new FrightenedAI();

    @Override
    public Vector chooseTargetDirection(Employee.EmployeeState state) {
        return null;
    }
}
