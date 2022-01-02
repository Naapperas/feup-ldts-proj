package pt.up.fe.ldts.controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

import java.util.Random;

public class MariAI extends EmployeeAI{ //pinky

    static {
        SCATTER_TARGET = new Point(0,0);
    }

    public MariAI() {
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> SCATTER_TARGET;
            case CHASING -> chasingTarget();
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));

        };
    }

    /**
     * Mariana's targeted position when chasing Jorge
     * @return targeted position when chasing Jorge
     */
    private Point chasingTarget(){
        if(Jorge.singleton.getDirection().equals(Vector.UP))
            return Jorge.singleton.getPosition().addVector(Vector.UP.multiply(4)).addVector(Vector.LEFT.multiply(4));
        else
            return Jorge.singleton.getPosition().addVector(Jorge.singleton.getDirection().multiply(4));
    }
}
