package pt.up.fe.ldts.Controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ToniAI implements EmployeeAI{

    private Point target;
    static Point scatterTarget = new Point(0,20);// valores arbitrarios
    static Point deadTarget = new Point(10,10); // respawn

    public ToniAI(){}

    @Override
    public Vector scatter(Vector direction, Point position) {
        return null;
    }

    @Override
    public Vector frightened(Vector direction, Point position) {
        return null;
    }

    @Override
    public Vector chase(Vector direction, Point position) {
        return null;
    }

    @Override
    public Vector dead(Vector direction, Point position) {
        return null;
    }

    private Point findTarget(Employee.EmployeeState state, Point position){
        switch (state){
            case SCATTER -> {
                return scatterTarget;
            }
            case CHASING -> {
                double distance = sqrt(pow(Jorge.singleton.getX() - position.getX(),2) + pow(Jorge.singleton.getY() - position.getY(), 2));
                if(distance > 8){
                    return Jorge.singleton.getPosition();
                }
                else
                    return scatterTarget;
            }
            case DEAD -> {
                return deadTarget;
            }
        }
        return null;
    }
}
