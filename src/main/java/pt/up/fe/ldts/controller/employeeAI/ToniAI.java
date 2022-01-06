package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

import java.util.Random;

public class ToniAI extends EmployeeAI { //clyde

    {
        SCATTER_TARGET = new Point(0,20);
    }

    public ToniAI() {
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> this.getScatterTarget();
            case CHASING -> Vector.from(Jorge.singleton.getPosition(), position).magnitude() > 8 ? Jorge.singleton.getPosition() : SCATTER_TARGET;
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));
        };
    }

    @Override
    public TextColor getEmployeeColor() {
        return TextColor.Factory.fromString("#e5b362");
    }

}
