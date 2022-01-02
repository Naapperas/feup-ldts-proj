package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

import java.util.Random;

public class ZeCastroAI implements EmployeeAI{ //inky

    private Point baltaPos;
    static final Point SCATTER_TARGET = new Point(0,20);// valores arbitrarios
    static final Point DEAD_TARGET = new Point(10,10); // respawn

    public ZeCastroAI(Point baltaPos){
        this.baltaPos = baltaPos;
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> SCATTER_TARGET;
            case CHASING -> Vector.from(Jorge.singleton.getPosition(), position).magnitude() > 8 ? Jorge.singleton.getPosition() : SCATTER_TARGET;
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));
        };
    }
}
