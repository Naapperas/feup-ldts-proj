package pt.up.fe.ldts.Controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ToniAI implements EmployeeAI{

    static Position SCATTER_TARGET = new Position(0,20);// valores arbitrarios
    static Position DEAD_TARGET = new Position(10,10); // respawn


    public ToniAI(){}
    Random random = new Random();

    @Override
    public Entity.Direction chooseTargetDirection(Employee.EmployeeState state, Position position, Entity.Direction employeeDirection) {
        Position target = findTarget(state, position);
        List<Entity.Direction> directions = possibleDirections(employeeDirection);
        int i = 0;
        double[] distances = new double[3];
        Position newPos = new Position(0,0);

        for(Entity.Direction direction : directions){
            switch (direction){
                case UP -> {
                    newPos.setX(position.getX());
                    newPos.setY(position.getY()-1);
                }
                case LEFT -> {
                    newPos.setX(position.getX()-1);
                    newPos.setY(position.getY());
                }
                case DOWN -> {
                    newPos.setX(position.getX());
                    newPos.setY(position.getY()+1);
                }
                case RIGHT -> {
                    newPos.setX(position.getX()+1);
                    newPos.setY(position.getY());
                }
            }
            distances[i] = calculateDistance(target, newPos);
            i++;
        }
         if(distances[0] < distances[1])
             if(distances[0] < distances[2])
                 return directions.get(0);
            else
                return directions.get(2);
         else
             if(distances[1] < distances[2])
                 return directions.get(1);
             else
                 return directions.get(2);

    }


    private Position findTarget(Employee.EmployeeState state, Position position){
        switch (state){
            case SCATTER -> {
                return SCATTER_TARGET;
            }
            case CHASING -> {
                double distance = calculateDistance(Jorge.singleton.getPosition(), position);
                if(distance > 8){
                    return Jorge.singleton.getPosition();
                }
                else
                    return SCATTER_TARGET;
            }
            case DEAD -> {
                return DEAD_TARGET;
            }
            case FRIGHTENED -> {
                return new Position(random.nextInt(20), random.nextInt(20));
            }
        }
        return null;
    }

    private double calculateDistance(Position targetPosition, Position startPosition){
        return sqrt(pow(targetPosition.getX() - startPosition.getX(),2) + pow(targetPosition.getY() - startPosition.getY(), 2));
    }

    private List<Entity.Direction> possibleDirections(Entity.Direction direction){ //prototipo
        List<Entity.Direction> directions = new ArrayList<>();

        switch (direction){
            case UP -> {
                directions.add(Entity.Direction.UP);
                directions.add(Entity.Direction.LEFT);
                directions.add(Entity.Direction.RIGHT);
            }
            case LEFT -> {
                directions.add(Entity.Direction.LEFT);
                directions.add(Entity.Direction.DOWN);
                directions.add(Entity.Direction.UP);
            }
            case DOWN -> {
                directions.add(Entity.Direction.DOWN);
                directions.add(Entity.Direction.RIGHT);
                directions.add(Entity.Direction.LEFT);
            }
            case RIGHT -> {
                directions.add(Entity.Direction.RIGHT);
                directions.add(Entity.Direction.UP);
                directions.add(Entity.Direction.DOWN);
            }
        }
        return directions;
    }

}
