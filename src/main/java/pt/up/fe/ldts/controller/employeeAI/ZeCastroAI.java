package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Point;

public class ZeCastroAI implements EmployeeAI{ //inky
    private Point baltaPos;

    public ZeCastroAI(Point baltaPos){
        this.baltaPos = baltaPos;
    }

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position) {
        return null;
    }
}
