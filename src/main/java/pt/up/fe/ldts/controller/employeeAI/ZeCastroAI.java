package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

import java.util.Random;

public class ZeCastroAI extends EmployeeAI{ //inky

    private final Employee balta;
    static {
        SCATTER_TARGET = new Point(20,20);
    }

    public ZeCastroAI(Employee balta){
        this.balta = balta;
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> SCATTER_TARGET;
            case CHASING -> chaseTarget();
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));
        };
    }

    private Point chaseTarget(){
        Point target;
        if(Jorge.singleton.getDirection().equals(Vector.UP))
            target = Jorge.singleton.getPosition().addVector(Vector.UP.multiply(2)).addVector(Vector.LEFT.multiply(2));
        else
            target = Jorge.singleton.getPosition().addVector(Jorge.singleton.getDirection().multiply(2));

        return target.addVector(Vector.from(balta.getPosition(), target));
    }
}
