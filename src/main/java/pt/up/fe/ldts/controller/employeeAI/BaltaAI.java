package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;

import java.util.Random;

public class BaltaAI extends EmployeeAI{ //blinky

    static {
        SCATTER_TARGET = new Point(20,0);
    }

    public BaltaAI() {
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> SCATTER_TARGET;
            case CHASING -> Jorge.singleton.getPosition();
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));
        };
    }
}
