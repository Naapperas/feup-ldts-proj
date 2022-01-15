package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.map.MapConfiguration;

import java.util.Random;

public class ZeCastroAI extends EmployeeAI{ //inky

    private final Employee balta;

    public ZeCastroAI(Employee balta){
        this.SCATTER_TARGET = new Point(MapConfiguration.getMapWidth()-1, MapConfiguration.getMapHeight()-1);
        this.balta = balta;
    }

    Random random = new Random();

    @Override
    public Point chooseTargetPosition(Employee.EmployeeState state, Point position){
        return switch (state){
            case SCATTER -> this.getScatterTarget();
            case CHASING -> chasingTarget();
            case DEAD -> DEAD_TARGET;
            case FRIGHTENED -> new Point(random.nextInt(20), random.nextInt(20));
        };
    }

    @Override
    public TextColor getEmployeeColor() {
        return TextColor.Factory.fromString("#43eee4");
    }

    /**
     * ZeCastro's targeted position when chasing Jorge
     * @return targeted position when chasing Jorge
     */
    private Point chasingTarget(){
        Point target;
        if(Jorge.singleton.getDirection().equals(Vector.UP))
            target = Jorge.singleton.getPosition().addVector(Vector.UP.multiply(2)).addVector(Vector.LEFT.multiply(2));
        else
            target = Jorge.singleton.getPosition().addVector(Jorge.singleton.getDirection().multiply(2));

        return target.addVector(Vector.from(balta.getPosition(), target));
    }
}
