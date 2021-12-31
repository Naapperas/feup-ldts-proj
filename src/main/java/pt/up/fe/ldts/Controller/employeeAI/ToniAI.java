package pt.up.fe.ldts.Controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Position;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ToniAI implements EmployeeAI{

    private Position target;
    static Position scatterTarget = new Position(0,20);// valores arbitrarios
    static Position deadTarget = new Position(10,10); // respawn

    public ToniAI(){}

    @Override
    public Entity.Direction scatter(Entity.Direction direction, Position position) {
        return null;
    }

    @Override
    public Entity.Direction frightened(Entity.Direction direction, Position position) {
        return null;
    }

    @Override
    public Entity.Direction chase(Entity.Direction direction, Position position) {
        return null;
    }

    @Override
    public Entity.Direction dead(Entity.Direction direction, Position position) {
        return null;
    }


    private Position findTarget(Employee.EmployeeState state, Position position){
        switch (state){
            case SCATTER -> {
                return scatterTarget
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
