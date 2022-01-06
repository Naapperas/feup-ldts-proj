package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;

import java.util.Random;

public class BaltaAI extends EmployeeAI{ //blinky

    public BaltaAI() {
        this.SCATTER_TARGET = new Point(37,-20); // somehow 21 fixes the problem
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> this.getScatterTarget();
            case CHASING -> Jorge.singleton.getPosition();
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));
        };
    }

    public TextColor getEmployeeColor() {
        return TextColor.Factory.fromString("#E70000");
    }
}
