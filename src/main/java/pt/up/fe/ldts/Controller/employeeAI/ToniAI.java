package pt.up.fe.ldts.Controller.employeeAI;

import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Entity;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ToniAI implements EmployeeAI{

    private Point target;
    static Point SCATTER_TARGET = new Point(0,20);// valores arbitrarios
    static Point DEAD_TARGET = new Point(10,10); // respawn


    public ToniAI(){}
    Random random = new Random();

    @Override
    public Vector chooseTargetDirection(Employee.EmployeeState state, Point position, Vector employeeDirection) {
        Point target = findTarget(state, position);
        List<Vector> directions = possibleDirections(employeeDirection);
        int i = 0;
        double[] distances = new double[3];
        Point newPos = new Point(0,0);

        for(Vector direction : directions){
            if (Vector.UP.equals(direction)) {
                //newPos.setX(position.getX());
                //newPos.setY(position.getY()-1);
            } else if (Vector.LEFT.equals(direction)) {
                //newPos.setX(position.getX()-1);
                //newPos.setY(position.getY());
            } else if (Vector.DOWN.equals(direction)) {
                //newPos.setX(position.getX());
                //newPos.setY(position.getY()+1);
            } else if (Vector.RIGHT.equals(direction)) {
                //newPos.setX(position.getX()+1);
                //newPos.setY(position.getY());
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

    private Point findTarget(Employee.EmployeeState state, Point position){
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
                return new Point(random.nextInt(20), random.nextInt(20));
            }
        }
        return null;
    }

    private double calculateDistance(Point targetPosition, Point startPosition){
        return sqrt(pow(targetPosition.getX() - startPosition.getX(),2) + pow(targetPosition.getY() - startPosition.getY(), 2));
    }

    private List<Vector> possibleDirections(Vector direction){ //prototipo
        List<Vector> directions = new ArrayList<>();

        if (Vector.UP.equals(direction)) {
            directions.add(Vector.UP);
            directions.add(Vector.LEFT);
            directions.add(Vector.RIGHT);
        } else if (Vector.LEFT.equals(direction)) {
            directions.add(Vector.LEFT);
            directions.add(Vector.DOWN);
            directions.add(Vector.UP);
        } else if (Vector.DOWN.equals(direction)) {
            directions.add(Vector.DOWN);
            directions.add(Vector.RIGHT);
            directions.add(Vector.LEFT);
        } else if (Vector.RIGHT.equals(direction)) {
            directions.add(Vector.RIGHT);
            directions.add(Vector.UP);
            directions.add(Vector.DOWN);
        }
        return directions;
    }

}
